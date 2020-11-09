/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GestorBD;
import Modelo.Actividad;
import Modelo.Publicacion;
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
 * @author Caro
 */
@WebServlet(name = "PortalPublicaciones", urlPatterns = {"/PortalPublicaciones"})
public class PortalPublicaciones extends HttpServlet {

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
       
        request.setAttribute("listaActividades", gestor.obtenerActividades());
        request.setAttribute("listaPublicaciones", gestor.obtenerPublicacionesActivas());
        
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/portalPublicaciones.jsp");
        rd.forward(request, response);  
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
        String idActividadString = request.getParameter("cmbActividad");
        
        if (idActividadString != null && !idActividadString.isEmpty()) {
            int idActividad = Integer.parseInt(request.getParameter("cmbActividad"));
            request.setAttribute("idActividad", idActividad);
            request.setAttribute("listaPublicaciones", gestor.obtenerPublicacionesActivasPorActividadId(idActividad));
        } else {
            request.setAttribute("listaPublicaciones", gestor.obtenerPublicacionesActivas());
        }
        
        request.setAttribute("listaActividades", gestor.obtenerActividades());
          
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/portalPublicaciones.jsp");
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
