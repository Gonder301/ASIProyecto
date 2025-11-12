package com.mycompany.asiproyecto;

import com.mycompany.asiproyecto.dao.AlumnoDAO;
import com.mycompany.asiproyecto.model.Alumno;
import com.mycompany.asiproyecto.view.Index;


public class ASIProyecto {

    public static void main(String[] args) {
        
        AlumnoDAO alumnodao = new AlumnoDAO();
        Alumno alumno = alumnodao.obtenerAlumno("pepe.villa@email.com");
        
        if (alumno != null) {
            System.out.println(alumno.getNombresAlumno());
            System.out.println(alumno.getApellidosAlumno());
            System.out.println(alumno.getDni());
            System.out.println(alumno.getGenero());
            System.out.println(alumno.getFechaNacimiento());
            System.out.println(alumno.getCiclo());
            System.out.println(alumno.getCarrera());
            System.out.println(alumno.getCorreoElectronico());
            System.out.println(alumno.getContrasena());
        } 
        else {
            System.out.println("Alumno no encontrado");
        }
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            Index indexFrame = new Index();
            indexFrame.setVisible(true);
        });
    }
}
