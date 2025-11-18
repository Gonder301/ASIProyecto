package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.Placeholder;
import java.util.Arrays;

public class LoginService {
    public int validarCampos(String correo, char[] contrasena) {
        /*Codigos de validación:
        0 <- todo bien
        1 <- Correo vacío o con el placeholder
        2 <- Contraseña vacía o con el placeholder
        3 <- Correo y Contraseña ...
        */
       
        boolean correoInvalido = (correo == null || correo.trim().isEmpty() || correo.equals(Placeholder.correo));
        // IMPORTANTE: Placeholder.contra es String, se convertirá a toCharArray() para poder comparar
        boolean contraInvalida = (contrasena == null || contrasena.length == 0 || 
                Arrays.equals(contrasena, Placeholder.contra.toCharArray()));
        
        if (correoInvalido && contraInvalida) {
            return 3;
        } else if (correoInvalido) {
            return 1;
        } else if (contraInvalida) {
            return 2;
        }
        return 0;
    }
}
