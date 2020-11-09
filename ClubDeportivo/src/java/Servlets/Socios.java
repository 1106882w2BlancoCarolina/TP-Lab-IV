/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GestorBD;
import Modelo.Socio;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(name = "Socios", urlPatterns = {"/Socios"})
public class Socios extends HttpServlet {
    
    private ArrayList<String> obtenerGeneros() {
        ArrayList<String> lista = new ArrayList<String>();

        lista.add("Masculino");
        lista.add("Femenino");
        lista.add("Otro");

        return lista;
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

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/socios/listadoSocios.jsp");
            rd.forward(request, response);
        } else if (modo.equals("creacion")) {
            request.setAttribute("listaGenero", obtenerGeneros());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/socios/socio.jsp");
            rd.forward(request, response);
        } else if (modo.equals("edicion")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));

            Socio socio = gestor.obtenerSocioPorId(idSocio);
            request.setAttribute("modeloSocio", socio);

            request.setAttribute("listaGenero", obtenerGeneros());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/socios/editarSocio.jsp");
            rd.forward(request, response);
        } else if (modo.equals("eliminacion")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));

            Socio socio = gestor.obtenerSocioPorId(idSocio);
            request.setAttribute("modeloSocio", socio);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/socios/eliminarSocio.jsp");
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
        String idSocio = request.getParameter("idSocio");
        String eliminar = request.getParameter("eliminar");
        GestorBD gestor = new GestorBD();

        if (eliminar != null && idSocio != null && !idSocio.isEmpty()) {
            gestor.eliminarRegistro(Integer.parseInt(idSocio), "Socios", true);
            request.setAttribute("mensaje", "Se ha eliminado al socio correctamente");
        } else {
            String nombre = request.getParameter("txtNombre");
            String apellido = request.getParameter("txtApellido");
            int dni = Integer.parseInt(request.getParameter("txtDni"));
            String genero = request.getParameter("cmbGenero");
            String telefono = request.getParameter("txtTelefono");
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = new Date();
            try {
                fechaNacimiento = dateFormat.parse(request.getParameter("txtFechaNacimiento"));
            } catch (ParseException ex) {
                Logger.getLogger(Publicaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Socio socio = new Socio(0, nombre, apellido, dni, fechaNacimiento, genero, telefono);
           
            if (idSocio == null) {
                gestor.agregarSocio(socio);
                request.setAttribute("mensaje", "Se ha registrado un nuevo socio correctamente");
            } else {
                socio.setId(Integer.parseInt(idSocio));
                gestor.actualizarSocio(socio);
                request.setAttribute("mensaje", "Se ha modificado los datos del socio correctamente");
            }
        }

        request.setAttribute("socios", gestor.obtenerSocios());
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/socios/listadoSocios.jsp");
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
