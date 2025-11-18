package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.EmpleadoEmpresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoEmpresaDAO {

    public EmpleadoEmpresa obtenerEmpleadoEmpresa(String correoCorporativo, String contrasena) {
        EmpleadoEmpresa emp = null;
        
        // Se asume que la tabla se llama EmpleadoEmpresa y tiene columna contrasena
        String sql = "SELECT * FROM EmpleadoEmpresa WHERE correoCorporativo = ? AND contrasena = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoCorporativo);
            pstmt.setString(2, contrasena);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    emp = new EmpleadoEmpresa();
                    // Mapeo exacto según los atributos de tu clase EmpleadoEmpresa.java
                    emp.setIdEmpleado(rs.getInt("idEmpleado"));
                    emp.setNombreCompleto(rs.getString("nombreCompleto"));
                    emp.setNombreEmpresa(rs.getString("nombreEmpresa"));
                    emp.setCorreoCorporativo(rs.getString("correoCorporativo"));
                    emp.setTelefono(rs.getString("telefono"));
                    emp.setRuc(rs.getString("ruc"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener EmpleadoEmpresa: " + e.getMessage());
            e.printStackTrace();
        }
        
        return emp;
    }
}