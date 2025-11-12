package com.mycompany.asiproyecto.model;

public class Alumno {
    private String nombresAlumno;
    private String apellidosAlumno;
    private String dni;
    private String genero;
    private String fechaNacimiento;
    private String ciclo;
    private String carrera;
    private String correoElectronico;
    private String contrasena;
    
    public Alumno() {
    }
    
    public Alumno(String _nombresAlumno, String _apellidosAlumno, String _dni, 
            String _genero, String _fechaNacimiento, String _ciclo,
            String _carrera, String _correoElectronico, String _contrasena) {
        nombresAlumno = _nombresAlumno;
        apellidosAlumno = _apellidosAlumno;
        dni = _dni;
        genero = _genero;
        fechaNacimiento = _fechaNacimiento;
        ciclo = _ciclo;
        carrera = _carrera;
        correoElectronico = _correoElectronico;
        contrasena = _contrasena;
    }
    
    // --- Getters ---

    public String getNombresAlumno() {
        return nombresAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public String getDni() {
        return dni;
    }

    public String getGenero() {
        return genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCiclo() {
        return ciclo;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    // --- Setters ---

    public void setNombresAlumno(String nombresAlumno) {
        this.nombresAlumno = nombresAlumno;
    }

    public void setApellidosAlumno(String apellidosAlumno) {
        this.apellidosAlumno = apellidosAlumno;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
