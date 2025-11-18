package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.view.LoginView;
import com.mycompany.asiproyecto.Colores;

public class LoginController {
    public void procesarValidacion(int codigoValidacion, LoginView vista) {
        switch (codigoValidacion) {
            case 0:
                //Logica de login
                break;
            case 1:
                vista.cambiarCorreoBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarMsgError("Por favor ingrese un correo válido.");
                break;
            case 2:
                vista.cambiarContraBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarMsgError("Por favor ingrese su contraseña.");
                break;
            case 3:
                vista.cambiarCorreoBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarContraBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarMsgError("Debe ingresar correo y contraseña.");
                break;
        }
    }
}
