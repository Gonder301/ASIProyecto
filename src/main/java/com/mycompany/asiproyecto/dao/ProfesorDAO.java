package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

    public Profesor obtenerProfesor(String correoInstitucional, String contrasena) {
        Profesor prof = null;

        // Se asume que la tabla se llama Profesor y tiene columna contrasena
        String sql = "SELECT * FROM Profesor WHERE correoInstitucional = ? AND contrasena = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoInstitucional);
            pstmt.setString(2, contrasena);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prof = new Profesor();
                    // Mapeo exacto según los atributos de tu clase Profesor.java
                    prof.setIdProfesor(rs.getInt("idProfesor"));
                    prof.setNombresProfesor(rs.getString("nombresProfesor"));
                    prof.setApellidosProfesor(rs.getString("apellidosProfesor"));
                    prof.setDni(rs.getString("dni"));
                    prof.setCodigoCurso(rs.getString("codigoCurso"));
                    prof.setNombreCurso(rs.getString("nombreCurso"));
                    prof.setCarrera(rs.getString("carrera"));
                    prof.setCorreoInstitucional(rs.getString("correoInstitucional"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Profesor: " + e.getMessage());
            e.printStackTrace();
        }

        return prof;
    }
}