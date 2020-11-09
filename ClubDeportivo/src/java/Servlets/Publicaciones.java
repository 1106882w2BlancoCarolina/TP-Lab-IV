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
@WebServlet(name = "Publicaciones", urlPatterns = {"/Publicaciones"})
public class Publicaciones extends HttpServlet {

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
            request.setAttribute("publicaciones", gestor.obtenerPublicaciones());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/publicaciones/listadoPublicaciones.jsp");
            rd.forward(request, response);
        } else if (modo.equals("creacion")) {
            ArrayList<Actividad> actividades = gestor.obtenerActividades();
            request.setAttribute("listaActividades", actividades);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/publicaciones/publicacion.jsp");
            rd.forward(request, response);
        } else if (modo.equals("edicion")) {
            int idPublicacion = Integer.parseInt(request.getParameter("id"));

            Publicacion publicacion = gestor.obtenerPublicacionPorId(idPublicacion);
            request.setAttribute("modeloPublicacion", publicacion);

            ArrayList<Actividad> actividades = gestor.obtenerActividades();
            request.setAttribute("listaActividades", actividades);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/publicaciones/editarPublicacion.jsp");
            rd.forward(request, response);
        } else if (modo.equals("eliminacion")) {
            int idPublicacion = Integer.parseInt(request.getParameter("id"));

            Publicacion publicacion = gestor.obtenerPublicacionPorId(idPublicacion);
            request.setAttribute("modeloPublicacion", publicacion);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/publicaciones/eliminarPublicacion.jsp");
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
        String idPublicacion = request.getParameter("idPublicacion");
        String eliminar = request.getParameter("eliminar");
        String modo = request.getParameter("modo");
        GestorBD gestor = new GestorBD();

        if (eliminar != null && idPublicacion != null && !idPublicacion.isEmpty()) {
            gestor.eliminarRegistro(Integer.parseInt(idPublicacion), "Publicaciones", true);
            request.setAttribute("mensaje", "Se ha eliminado la publicacion correctamente");
        } else {
            String titulo = request.getParameter("txtTitulo");
            String contenido = request.getParameter("txtContenido");
            String urlImagen = request.getParameter("txtUrlImagen");
            int idActividad = Integer.parseInt(request.getParameter("cmbActividad"));
            boolean activa = request.getParameter("chkActiva") != null;
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = new Date();
            try {
                fecha = dateFormat.parse(request.getParameter("txtFecha"));
            } catch (ParseException ex) {
                Logger.getLogger(Publicaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Actividad actividad = new Actividad(idActividad, "", "", 0);
            Publicacion publicacion = new Publicacion(0, titulo, contenido, urlImagen, actividad, fecha, activa);
            
            if (idPublicacion == null || idPublicacion.isEmpty()) {
                gestor.agregarPublicacion(publicacion);
                request.setAttribute("mensaje", "Se ha agregado la publicacion correctamente");
            } else {
                publicacion.setId(Integer.parseInt(idPublicacion));
                gestor.actualizarPublicacion(publicacion);
                request.setAttribute("mensaje", "Se ha modificado la publicacion correctamente");
            }
        }

        request.setAttribute("publicaciones", gestor.obtenerPublicaciones());
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/publicaciones/listadoPublicaciones.jsp");
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
