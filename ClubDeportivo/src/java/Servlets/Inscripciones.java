/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GestorBD;
import Modelo.Actividad;
import Modelo.Inscripcion;
import Modelo.Socio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "Inscripciones", urlPatterns = {"/Inscripciones"})
public class Inscripciones extends HttpServlet {

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

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscripciones/seleccionSociosInscripciones.jsp");
            rd.forward(request, response);
        } else if (modo.equals("listadoInscripciones")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));

            ArrayList<Inscripcion> inscripcionesDelSocio = gestor.obtenerInscripcionesDeSocioId(idSocio);
            request.setAttribute("listaInscripciones", inscripcionesDelSocio);
            
            ArrayList<Actividad> actividades = gestor.obtenerActividades();  
            request.setAttribute("listaActividades", actividades);
            
            Socio socio = gestor.obtenerSocioPorId(idSocio);
            request.setAttribute("modeloSocio", socio);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscripciones/listadoInscripciones.jsp");
            rd.forward(request, response);
        } else if (modo.equals("eliminacion")) {
            int idInscripcion = Integer.parseInt(request.getParameter("id"));

            Inscripcion inscripcion = gestor.obtenerInscripcionPorId(idInscripcion);
            request.setAttribute("modeloInscripcion", inscripcion);
            request.setAttribute("modeloSocio", inscripcion.getSocio());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscripciones/eliminarInscripcion.jsp");
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
        String idInscripcion = request.getParameter("idInscripcion");
        int idSocio = Integer.parseInt(request.getParameter("idSocio"));
        String eliminar = request.getParameter("eliminar");
        GestorBD gestor = new GestorBD();
        
        if (eliminar != null &&  idInscripcion != null && !idInscripcion.isEmpty()) {
            gestor.eliminarRegistro(Integer.parseInt(idInscripcion), "Inscripciones", false);
            request.setAttribute("mensaje", "Se ha realizado la desinscripcion correctamente");
        } else {
            
            int idActividad = Integer.parseInt(request.getParameter("idActividad"));
            
            ArrayList<Inscripcion> inscripcionesDelSocio = gestor.obtenerInscripcionesDeSocioId(idSocio);
            
            boolean actividadInscripta = false;
            for (Inscripcion inscripcion : inscripcionesDelSocio) {
                if (inscripcion.getActividad().getId() == idActividad) {
                    actividadInscripta = true;
                }
            }
            
            if (actividadInscripta == false) {
                
                Actividad actividad = new Actividad();
                actividad.setId(idActividad);
                Socio socio = new Socio();
                socio.setId(idSocio);

                Inscripcion inscripcion = new Inscripcion(0, socio, actividad, new Date());
                
                gestor.agregarInscripcion(inscripcion);
                request.setAttribute("mensaje", "Se inscripto ha el socio a la actividad correctamente");
            } else {
                request.setAttribute("mensaje", "El socio ya se encuentra inscripto a esta Actividad");
            }
            
        }

        ArrayList<Inscripcion> inscripcionesDelSocio = gestor.obtenerInscripcionesDeSocioId(idSocio);
        request.setAttribute("listaInscripciones", inscripcionesDelSocio);

        ArrayList<Actividad> actividades = gestor.obtenerActividades();  
        request.setAttribute("listaActividades", actividades);

        Socio socio = gestor.obtenerSocioPorId(idSocio);
        request.setAttribute("modeloSocio", socio);
        
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscripciones/listadoInscripciones.jsp");
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
