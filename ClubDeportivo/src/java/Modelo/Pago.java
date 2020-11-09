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
public class Pago {

    private int id;
    private double monto;
    private int idSocio;
    private Date fecha;
    private String fechaString;
    private String referencia;

    public Pago() {
    }

    public Pago(int id, double monto, int idSocio, Date fecha, String referencia) {
        this.id = id;
        this.monto = monto;
        this.idSocio = idSocio;
        this.fecha = fecha;
        this.referencia = referencia;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        this.fechaString = dateFormat.format(this.fecha); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaString() {
        return fechaString;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
