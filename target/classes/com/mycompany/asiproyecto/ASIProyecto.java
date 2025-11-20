package com.mycompany.asiproyecto;

import com.mycompany.asiproyecto.view.Index;

public class ASIProyecto {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Index indexFrame = new Index();
            indexFrame.setVisible(true);
        });
    }
}
