/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Carolina
 */
public class Publicacion {
    private int id;
    private String titulo;
    private String contenido;
    private String urlImagen;
    private Actividad actividad;
    private Date fecha;
    private String fechaString;
    private boolean activa;

    public Publicacion() {
    }
    
    public Publicacion(int id, String titulo, String contenido, String urlImagen, Actividad actividad, Date fecha, boolean activa) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.urlImagen = urlImagen;
        this.actividad = actividad;
        this.fecha = fecha;
        this.activa = activa;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        this.fechaString = dateFormat.format(this.fecha); 
        
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
