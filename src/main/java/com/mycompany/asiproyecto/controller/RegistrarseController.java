package com.mycompany.asiproyecto.controller;

import java.util.Arrays;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.model.Alumno;
import com.mycompany.asiproyecto.dao.AlumnoDAO;
import com.mycompany.asiproyecto.view.RegistrarseJDialog;
import javax.swing.border.LineBorder;

public class RegistrarseController {
    private boolean contraCoinciden(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldA1.getPassword(), vista.contraPasswordFieldA2.getPassword());
    }
    
    private int camposVaciosFormAlumno(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.textFieldBorderErr, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.botonDefault, 1);
        
        if (vista.nombresTextFieldA.getText().isEmpty()) {
            vista.nombresTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.nombresTextFieldA.setBorder(lineBorderDef);
        }
        
        if (vista.apellidoTextFieldA.getText().isEmpty()) {
            vista.apellidoTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.apellidoTextFieldA.setBorder(lineBorderDef);
        }
        
        if (vista.contraPasswordFieldA1.getPassword().length == 0) {
            vista.contraPasswordFieldA1.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldA1.setBorder(lineBorderDef);
        }
        
        if (vista.contraPasswordFieldA2.getPassword().length == 0) {
            vista.contraPasswordFieldA2.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldA2.setBorder(lineBorderDef);
        }
        
        return camposVacios;
    }
    
    public void procesarRegistroAlumno(RegistrarseJDialog vista) {
        int camposVacios = camposVaciosFormAlumno(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseA.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoinciden(vista)) {
            vista.msgRegistrarseA.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldA1.getPassword();
        
        Alumno a = vista.obtenerAlumnoDeForm();
        
        //Poner dentro de servicio
        AlumnoDAO adao = new AlumnoDAO();
        if (adao.registrarAlumno(a, new String(contrasena))) {
            vista.msgRegistrarseA.setText("El alumno se registró con éxito.");
        }
    }
    
    
}
