package com.mycompany.asiproyecto.model;

import java.time.LocalDate;

public class Oferta {
    private int idOferta;
    private String nombreEmpresa;
    private String descripcionPerfil;
    private String puestoPractica;
    private String requisitos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String modalidad;
    private String habilidadesCompetencias;
    private String area;
    private String distrito;
    private String beneficios;
    private String consultas;
    private int empleadoID;

    // Constructor vacío
    public Oferta() {}

    // Getters y Setters
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public String getPuestoPractica() {
        return puestoPractica;
    }

    public void setPuestoPractica(String puestoPractica) {
        this.puestoPractica = puestoPractica;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getHabilidadesCompetencias() {
        return habilidadesCompetencias;
    }

    public void setHabilidadesCompetencias(String habilidadesCompetencias) {
        this.habilidadesCompetencias = habilidadesCompetencias;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public String getConsultas() {
        return consultas;
    }

    public void setConsultas(String consultas) {
        this.consultas = consultas;
    }
    
    public int getEmpleadoID() {
        return empleadoID;
    }
    
    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }
}