/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GestorBD;
import Modelo.Actividad;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Actividades", urlPatterns = {"/Actividades"})
public class Actividades extends HttpServlet {

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
            request.setAttribute("actividades", gestor.obtenerActividades());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/actividades/listadoActividades.jsp");
            rd.forward(request, response);
        } else if (modo.equals("creacion")) {

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/actividades/actividad.jsp");
            rd.forward(request, response);
        } else if (modo.equals("edicion")) {
            int idActividad = Integer.parseInt(request.getParameter("id"));

            Actividad actividad = gestor.obtenerActividadPorId(idActividad);
            request.setAttribute("modeloActividad", actividad);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/actividades/editarActividad.jsp");
            rd.forward(request, response);
        } else if (modo.equals("eliminacion")) {
            int idActividad = Integer.parseInt(request.getParameter("id"));

            Actividad actividad = gestor.obtenerActividadPorId(idActividad);
            request.setAttribute("modeloActividad", actividad);
            
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/actividades/eliminarActividad.jsp");
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
        String idActividad = request.getParameter("idActividad");
        String eliminar = request.getParameter("eliminar");
        GestorBD gestor = new GestorBD();
        
        if (eliminar != null &&  idActividad != null && !idActividad.isEmpty()) {
            gestor.eliminarRegistro(Integer.parseInt(idActividad), "Actividades", true);
            request.setAttribute("mensaje", "Se ha eliminado la actividad correctamente");
        } else {
            String nombre = request.getParameter("txtNombre");
            String descripcion = request.getParameter("txtDescripcion");
            String costoMensual = request.getParameter("txtCostoMensual");
            
            Actividad actividad = new Actividad(0, nombre, descripcion, Double.parseDouble(costoMensual));
            
            if (idActividad == null || idActividad.isEmpty()) {
                gestor.agregarActividad(actividad);
                request.setAttribute("mensaje", "Se ha creado una nueva actividad correctamente");
            } else {
                actividad.setId(Integer.parseInt(idActividad));
                gestor.actualizarActividad(actividad);
                request.setAttribute("mensaje", "Se ha modificado los datos de la actividad correctamente");
            }
        }

        request.setAttribute("actividades", gestor.obtenerActividades());
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/actividades/listadoActividades.jsp");
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
