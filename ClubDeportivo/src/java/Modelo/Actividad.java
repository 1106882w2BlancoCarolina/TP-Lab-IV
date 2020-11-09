/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Carolina
 */
public class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private double costoMensual;

    public Actividad() {
    }

    public Actividad(int id, String nombre, String descripcion, double costoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoMensual = costoMensual;
    }

    public Actividad(String nombre, String descripcion, double costoMensual) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoMensual = costoMensual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoMensual() {
        return costoMensual;
    }

    public void setCostoMensual(double costoMensual) {
        this.costoMensual = costoMensual;
    }
    
}
