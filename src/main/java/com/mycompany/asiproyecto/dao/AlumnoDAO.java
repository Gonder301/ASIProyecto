package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    public Alumno obtenerAlumno(String correoElectronico, String contrasena) {
        Alumno a = null;
        
        String sql = "SELECT * FROM Alumno WHERE correoElectronico = ? AND contrasena = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoElectronico);
            pstmt.setString(2, contrasena);
            
            // 4. Ejecuta la consulta (ya no se le pasa 'sql' aquí)
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    a = new Alumno(); 
                    a.setIdAlumno(rs.getInt("idAlumno"));
                    a.setNombresAlumno(rs.getString("nombresAlumno"));
                    a.setApellidosAlumno(rs.getString("apellidosAlumno"));
                    a.setDni(rs.getString("dni"));
                    a.setGenero(rs.getString("genero"));
                    
                    java.sql.Date fechaSql = rs.getDate("fechaNacimiento");
                    if (fechaSql != null) {
                        a.setFechaNacimiento(fechaSql.toLocalDate());
                    }
                    
                    a.setCodigo(rs.getString("codigo"));
                    a.setCarrera(rs.getString("carrera"));
                    a.setCurso(rs.getString("curso"));
                    a.setDocenteACargo(rs.getString("docenteACargo"));
                    a.setCorreoElectronico(rs.getString("correoElectronico"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión / obtener alumno: " + e.getMessage());
            e.printStackTrace();
        }
        
        return a;
    }
}
