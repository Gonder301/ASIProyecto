package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.model.Oferta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO {

    public List<Oferta> obtenerTodasLasOfertas() {
        
        List<Oferta> lista = new ArrayList<>();
        String sql = "SELECT idoferta, nombreempresa, descripcionperfil, puestopractica, " +
                    "requisitos, fechainicio, fechafin, modalidad, habilidadescompetencias, " +
                    "area, distrito, beneficios, fechalimiterecepcion, consultas " +
                    "FROM Oferta";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Oferta o = new Oferta();
                    
                    o.setIdOferta(rs.getInt("idoferta"));
                    o.setNombreEmpresa(rs.getString("nombreempresa"));
                    o.setDescriptionPerfil(rs.getString("descripcionperfil"));
                    o.setPuestoPractica(rs.getString("puestopractica"));
                    o.setRequisitos(rs.getString("requisitos"));
                    o.setFechaInicio(rs.getObject("fechainicio", LocalDate.class));
                    o.setFechaFin(rs.getObject("fechafin", LocalDate.class));
                    o.setModalidad(rs.getString("modalidad"));
                    o.setHabilidadesCompetencias(rs.getString("habilidadescompetencias"));
                    o.setArea(rs.getString("area"));
                    o.setDistrito(rs.getString("distrito"));
                    o.setBeneficios(rs.getString("beneficios"));
                    o.setFechaLimiteRecepcion(rs.getObject("fechalimiterecepcion", LocalDate.class));
                    o.setConsultas(rs.getString("consultas"));
                    
                    lista.add(o);
                }
            } 
            catch (SQLException e) {
                System.err.println("Error al obtener ofertas: " + e.getMessage());
                e.printStackTrace();
            }
        return lista;
    }

    public boolean crearOferta(Oferta c) {
        System.out.println("xd");
        return false;
    }
}