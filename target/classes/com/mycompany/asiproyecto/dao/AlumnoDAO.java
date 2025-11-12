
package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    public Alumno obtenerAlumno(String email) {
        Alumno a = null;
        
        String sql = "SELECT * " +
                     "FROM Alumno WHERE correoElectronico = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            // 4. Ejecuta la consulta (ya no se le pasa 'sql' aquí)
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    a = new Alumno(); 
                    
                    a.setNombresAlumno(rs.getString("nombresAlumno"));
                    a.setApellidosAlumno(rs.getString("apellidosAlumno"));
                    a.setDni(rs.getString("dni"));
                    a.setGenero(rs.getString("genero"));
                    a.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    a.setCiclo(rs.getString("ciclo"));
                    a.setCarrera(rs.getString("carrera"));
                    a.setCorreoElectronico(rs.getString("correoElectronico"));
                    a.setContrasena(rs.getString("contrasena"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Alumno por email: " + e.getMessage());
            e.printStackTrace();
        }
        
        return a;
    }
}
