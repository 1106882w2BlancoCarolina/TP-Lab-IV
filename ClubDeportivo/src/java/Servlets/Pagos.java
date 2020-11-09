/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GestorBD;
import Modelo.Actividad;
import Modelo.Pago;
import Modelo.Socio;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carolina
 */
@WebServlet(name = "Pagos", urlPatterns = {"/Pagos"})
public class Pagos extends HttpServlet {

    private String obtenerReferencia() {
        String[] meses = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Calendar cal = Calendar.getInstance();
        String mes = meses[cal.get(Calendar.MONTH)];
        
        int anio = cal.get(Calendar.YEAR);
        return "Cuota correspondiente a " + mes + " del " + anio;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GestorBD gestor = new GestorBD();
        String modo = request.getParameter("modo");

        if (modo == null || modo.isEmpty()) {
            request.setAttribute("socios", gestor.obtenerSocios());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/pagos/seleccionSociosPagos.jsp");
            rd.forward(request, response);
        } else if (modo.equals("creacion")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));

            ArrayList<Actividad> actividadesDelSocio = gestor.obtenerActividadesDeSocioId(idSocio);
            request.setAttribute("actividadesDelSocio", actividadesDelSocio);

            double montoAPagar = 0;
            for (Actividad actividad : actividadesDelSocio) {
                montoAPagar += actividad.getCostoMensual();
            }

            String referencia = obtenerReferencia();
            Pago pago = new Pago(0, montoAPagar, idSocio, new Date(), referencia);
            request.setAttribute("modeloPago", pago);

            Socio socio = gestor.obtenerSocioPorId(idSocio);
            request.setAttribute("modeloSocio", socio);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/pagos/pago.jsp");
            rd.forward(request, response);
        } else if (modo.equals("listaPagos")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));

            Socio socio = gestor.obtenerSocioPorId(idSocio);
            request.setAttribute("modeloSocio", socio);

            ArrayList<Pago> listadoPagos = gestor.obtenerPagosPorSocioId(idSocio);
            request.setAttribute("listadoPagos", listadoPagos);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/pagos/listadoPagos.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GestorBD gestor = new GestorBD();
        
        int idSocio = Integer.parseInt(request.getParameter("idSocio"));
        String txtMonto = request.getParameter("txtMonto");
        
        double monto = Double.parseDouble(txtMonto);
        String referencia = request.getParameter("txtReferencia");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = new Date();
        try {
            fecha = dateFormat.parse(request.getParameter("txtFecha"));
        } catch (ParseException ex) {
            Logger.getLogger(Publicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Pago pago = new Pago(0, monto, idSocio, fecha, referencia);
        ArrayList<Actividad> actividadesDelSocio = gestor.obtenerActividadesDeSocioId(idSocio);
     
        gestor.agregarPago(pago, actividadesDelSocio);
        request.setAttribute("mensaje", "Se ha registrado un nuevo pago correctamente");

 
        Socio socio = gestor.obtenerSocioPorId(idSocio);
        request.setAttribute("modeloSocio", socio);

        ArrayList<Pago> listadoPagos = gestor.obtenerPagosPorSocioId(idSocio);
        request.setAttribute("listadoPagos", listadoPagos);
            
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/pagos/listadoPagos.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
