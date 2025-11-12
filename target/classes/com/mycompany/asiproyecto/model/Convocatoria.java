package com.mycompany.asiproyecto.model;

import java.time.LocalDateTime;

public class Convocatoria {

    private int id;
    private String titulo;
    private String descripcion;
    private String imagenUrl;
    private LocalDateTime fechaPublicacion;
    private int adminId;

    public Convocatoria() {
    }

    public Convocatoria(String _titulo, String _descripcion, String _imagenUrl, int _adminId) {
        titulo = _titulo;
        descripcion = _descripcion;
        imagenUrl = _imagenUrl;
        adminId = _adminId;
    }

    // --- Getters y Setters ---
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}