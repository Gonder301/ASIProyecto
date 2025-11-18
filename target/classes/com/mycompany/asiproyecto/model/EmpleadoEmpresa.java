package com.mycompany.asiproyecto.model;

public class EmpleadoEmpresa {
    private int idEmpleado;
    private String nombreCompleto;
    private String nombreEmpresa;
    private String correoCorporativo;
    private String telefono;
    private String ruc;

    public EmpleadoEmpresa() {
    }
    
    public EmpleadoEmpresa(int idEmpleado, String nombreCompleto, String nombreEmpresa, 
                           String correoCorporativo, String telefono, String ruc) {
        this.idEmpleado = idEmpleado;
        this.nombreCompleto = nombreCompleto;
        this.nombreEmpresa = nombreEmpresa;
        this.correoCorporativo = correoCorporativo;
        this.telefono = telefono;
        this.ruc = ruc;
    }
    
    // --- Getters y Setters ---

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCorreoCorporativo() {
        return correoCorporativo;
    }

    public void setCorreoCorporativo(String correoCorporativo) {
        this.correoCorporativo = correoCorporativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
