/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GestorBD;
import Modelo.DtoActividadGanancia;
import Modelo.Socio;
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
@WebServlet(name = "Reportes", urlPatterns = {"/Reportes"})
public class Reportes extends HttpServlet {

   
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
       
        request.setAttribute("idActividad", 0);
        request.setAttribute("socios", new ArrayList<Socio>());
        request.setAttribute("listaActividades", gestor.obtenerActividades());
        request.setAttribute("totalGanancias", gestor.obtenerTotalGanancias());
        
        ArrayList<DtoActividadGanancia> actividadGanancias = gestor.obtenerActividadesMayorGanancias(); 
        request.setAttribute("actividadesGanancias", actividadGanancias);
        request.setAttribute("mayorGanancia", actividadGanancias.get(0).getGanancia());
        
        

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/reportes/reportes.jsp");
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
        int idActividad = Integer.parseInt(request.getParameter("cmbActividad"));
        
        request.setAttribute("idActividad", idActividad);
        request.setAttribute("socios", gestor.obtenerSociosPorActividadId(idActividad));
        request.setAttribute("listaActividades", gestor.obtenerActividades());
        request.setAttribute("totalGanancias", gestor.obtenerTotalGanancias());
        
        ArrayList<DtoActividadGanancia> actividadGanancias = gestor.obtenerActividadesMayorGanancias(); 
        request.setAttribute("actividadesGanancias", actividadGanancias);
        request.setAttribute("mayorGanancia", actividadGanancias.get(0).getGanancia());
        
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/reportes/reportes.jsp");
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
