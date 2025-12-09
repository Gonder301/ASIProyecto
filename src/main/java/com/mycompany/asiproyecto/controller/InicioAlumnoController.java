package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.model.Contrato;
import com.mycompany.asiproyecto.dao.ContratoDAO;
import com.mycompany.asiproyecto.service.GoogleDriveService;
import com.mycompany.asiproyecto.view.Index;
import com.mycompany.asiproyecto.service.InicioAlumnoService;
import com.mycompany.asiproyecto.view.MisContratosJDialog;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.File;
import java.util.stream.Collectors;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InicioAlumnoController {
    
    public void filtrarOfertas(InicioAlumno vista) {
        String areaSeleccionada = (String) vista.areaOferaComboBox.getSelectedItem();
        String modalidadSeleccionada = (String) vista.modalidadOfertaComboBox.getSelectedItem();
        String distritoSeleccionado = (String) vista.distritoOfertaComboBox.getSelectedItem();

        List<Oferta> ofertasFiltradas = vista.todasLasOfertas.stream()
                .filter(o -> areaSeleccionada.equals("Todos")
                        || o.getArea().equalsIgnoreCase(areaSeleccionada))
                .filter(o -> modalidadSeleccionada.equals("Todos")
                        || o.getModalidad().equalsIgnoreCase(modalidadSeleccionada))
                .filter(o -> distritoSeleccionado.equals("Todos")
                        || o.getDistrito().equalsIgnoreCase(distritoSeleccionado))
                .collect(Collectors.toList());

        InicioAlumnoService.actualizarPanelOfertas(ofertasFiltradas, vista);
    }
    
    public void cerrarSesion(InicioAlumno vista) {
        vista.dispose();
        Index index = new Index();
        index.setLocationRelativeTo(null);
        index.setVisible(true);
    }
    
    public void cambiarCard(String cardName, InicioAlumno vista) {
        CardLayout cl = (CardLayout) vista.cardHolderPanel.getLayout();
        cl.show(vista.cardHolderPanel, cardName);
        switch(cardName) {
            case "miInformacionCard":
                vista.botonMiInfo.setBackground(Colores.BUTTON_YELLOW);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "ofertasDePracticasCard":
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misPostulacionesCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_YELLOW);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misContratosCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misInformesCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
    
    public void seleccionarFileContrato(MisContratosJDialog vista) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        int result = fileChooser.showOpenDialog(vista);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            if (f.getName().toLowerCase().endsWith(".pdf")) {
                vista.selectedFile[0] = f;
                vista.lblFileName.setText(f.getName());
                vista.btnEnviar.setEnabled(true);
            } else {
                javax.swing.JOptionPane.showMessageDialog(vista, "Por favor selecciona un archivo PDF válido.");
            }
        }
    }
    
    public void botonAnularPresionado(MisContratosJDialog vista) {
        if (vista.contrato != null) {//Se asegura que exista el contrado en la DB.
            int confirm = javax.swing.JOptionPane.showConfirmDialog(vista,
                    "Estas seguro de eliminar este contrato? Puedes volver a registrarlo",
                    "Confirmar eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                
                // 1. Crear un diálogo de "Cargando"
                JDialog loadingDialog = new JDialog(vista, "Procesando", true);
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel.add(new JLabel("Eliminando contrato, por favor espere..."), BorderLayout.CENTER);
                loadingDialog.add(panel);
                loadingDialog.pack();
                loadingDialog.setLocationRelativeTo(vista);
                loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);// Evitar que lo cierren manual
                
                // 2. Crear el SwingWorker para trabajar en segundo plano
                SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                    String errorMsg = "";
                    
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        try {
                            // --- TAREA PESADA (Drive y DB) ---
                            // Eliminar de Drive
                            String fileName = "Contrato_" + vista.idAlumno + "_" + vista.idOferta + ".pdf";
                            GoogleDriveService.deleteFileByName(fileName);
                            
                            // Eliminar de DB
                            ContratoDAO contratoDAO = new ContratoDAO();
                            return contratoDAO.eliminar(vista.idAlumno, vista.idOferta);
                        } catch (Exception ex) {
                            errorMsg = ex.getMessage();
                            ex.printStackTrace();
                            return false;
                        }
                    }
                    
                    @Override
                    protected void done() {
                        // 3. Esto se ejecuta cuando termina el background
                        loadingDialog.dispose(); // Cerrar el mensaje de "Cargando"
                        
                        try {
                            boolean exito = get();
                            
                            if (exito) {
                                javax.swing.JOptionPane.showMessageDialog(vista,
                                        "Contrato eliminado correctamente.");
                                InicioAlumnoService.actualizarDialogSegunExistenciaContrato(vista);
                            } 
                            else {
                                String msg = errorMsg.isEmpty() ? "Error al eliminar de la base de datos." : "Error: " + errorMsg;
                                javax.swing.JOptionPane.showMessageDialog(vista, msg);
                            }
                        } catch (Exception e) {
                            javax.swing.JOptionPane.showMessageDialog(vista, "Error interno: " + e.getMessage());
                        }
                    }
                };
                
                // 4. Ejecutar el worker y mostrar el diálogo
                worker.execute();
                loadingDialog.setVisible(true); // Esto bloquea la UI hasta que loadingDialog.dispose() se llame
            }
        }
    }
    
    public void botonEnviarPresionado(MisContratosJDialog vista) {
        if (vista.selectedFile[0] != null) {//Se asegura que el archivo esté cargado.
            int confirm = javax.swing.JOptionPane.showConfirmDialog(vista,
                    "Estas seguro de enviar este contrato?",
                    "Confirmar envío", javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                // 1. Crear un diálogo de "Cargando"
                JDialog loadingDialog = new JDialog(vista, "Procesando", true);
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel.add(new JLabel("Enviando contrato, por favor espere..."), BorderLayout.CENTER);
                loadingDialog.add(panel);
                loadingDialog.pack();
                loadingDialog.setLocationRelativeTo(vista);
                loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);// Evitar que lo cierren manual
                
                // 2. Crear el SwingWorker para trabajar en segundo plano
                SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                    String errorMsg = "";
                    
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        try {
                            Contrato c = new Contrato();
                            c.setIdAlumno(vista.contrato.getIdAlumno());
                            c.setIdOferta(vista.contrato.getIdOferta());
                            c.setFechaInicio(vista.datePickerInicio.getDate());
                            c.setFechaFin(vista.datePickerFin.getDate());
                            c.setEstadoContrato("Pendiente");
                            
                            // --- TAREA PESADA (Drive y DB) ---
                            // Subir de Drive
                            String fileName = "Contrato_" + c.getIdAlumno() + "_" + c.getIdOferta() + ".pdf";
                            String link = GoogleDriveService.uploadFile(vista.selectedFile[0], fileName, "application/pdf");
                            c.setDocumentoContrato(link);
                            
                            // Crear fila en DB
                            ContratoDAO contratoDAO = new ContratoDAO();
                            return contratoDAO.registrar(c);
                            
                        } catch (Exception ex) {
                            errorMsg = ex.getMessage();
                            ex.printStackTrace();
                            return false;
                        }
                    }
                    
                    @Override
                    protected void done() {
                        // 3. Esto se ejecuta cuando termina el background
                        loadingDialog.dispose(); // Cerrar el mensaje de "Cargando"
                        
                        try {
                            boolean exito = get();
                            
                            if (exito) {
                                javax.swing.JOptionPane.showMessageDialog(vista,
                                        "Contrato enviado correctamente.");
                                InicioAlumnoService.actualizarDialogSegunExistenciaContrato(vista);
                            }
                            else {
                                String msg = errorMsg.isEmpty() ? "Error al añadir contrado a la base de datos." : "Error: " + errorMsg;
                                javax.swing.JOptionPane.showMessageDialog(vista, msg);
                            }
                        } catch (Exception e) {
                            javax.swing.JOptionPane.showMessageDialog(vista, "Error interno: " + e.getMessage());
                        }
                    }
                };
                
                // 4. Ejecutar el worker y mostrar el diálogo
                worker.execute();
                loadingDialog.setVisible(true); // Esto bloquea la UI hasta que loadingDialog.dispose() se llame
            }
        }
    }
}
