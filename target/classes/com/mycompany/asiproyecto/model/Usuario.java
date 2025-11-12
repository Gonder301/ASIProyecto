
package com.mycompany.asiproyecto.model;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;
    
    public Usuario() {
    }
    
    public Usuario(int _id, String _nombre, String _email, String _contrasena, String _rol) {
        id = _id;
        nombre = _nombre;
        email = _email;
        contrasena = _contrasena;
        rol = _rol;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int _id) {
        id = _id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String _nombre) {
        nombre = _nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String _email) {
        email = _email;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String _contrasena) {
        contrasena = _contrasena;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String _rol) {
        rol = _rol;
    }
}
