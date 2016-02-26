/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nprot.vista;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import nprot.modelo.Reader;
import nprot.modelo.loEvoDist;

/**
 *
 * @author juan
 * clase que carga el archivo plano mediante un JFIleChooser
 */
public class loadED extends JInternalFrame {
 private File entrada;
    public loadED() {
        initComponents();
     //   lector = new LectorMatriz();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEntrada = new javax.swing.JPanel();
        textRuta = new javax.swing.JTextField();
        butExaminar = new javax.swing.JButton();
        panelSalida1 = new javax.swing.JPanel();
        scrollSalida1 = new javax.swing.JScrollPane();
        areaSalida1 = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 51))); // NOI18N
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Load evolutionary distances");

        panelEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder("CSV File"));

        butExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExaminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEntradaLayout = new javax.swing.GroupLayout(panelEntrada);
        panelEntrada.setLayout(panelEntradaLayout);
        panelEntradaLayout.setHorizontalGroup(
            panelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textRuta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelEntradaLayout.setVerticalGroup(
            panelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSalida1.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));

        areaSalida1.setEditable(false);
        areaSalida1.setColumns(20);
        areaSalida1.setRows(5);
        areaSalida1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        areaSalida1.setEnabled(false);
        areaSalida1.setOpaque(false);
        scrollSalida1.setViewportView(areaSalida1);

        javax.swing.GroupLayout panelSalida1Layout = new javax.swing.GroupLayout(panelSalida1);
        panelSalida1.setLayout(panelSalida1Layout);
        panelSalida1Layout.setHorizontalGroup(
            panelSalida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalida1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollSalida1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
        );
        panelSalida1Layout.setVerticalGroup(
            panelSalida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalida1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollSalida1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSalida1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(panelEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSalida1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Load evolutionary distances");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExaminarActionPerformed
        entrada = escogerArchivos();
        System.out.println( entrada );
        String txt = "Exito";
        loEvoDist inLoEvoDi = new loEvoDist( entrada.getPath() );
        txt = inLoEvoDi.getSbResuVal();
        areaSalida1.setFont(new Font("sansserif", Font.BOLD, 20));
        areaSalida1.setForeground(Color.BLACK);
        areaSalida1.setText(txt);
    }//GEN-LAST:event_butExaminarActionPerformed

   

    
    private File escogerArchivos() {
      //  JFileChooser selectorArchivo = new JFileChooser();
        JFileChooser selectorArchivo = new JFileChooser("C:\\Users\\juan\\Documents\\github\\prot\\codigos\\1.4\\NProt\\NProt1.4");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Text files", "txt");
        selectorArchivo.setFileFilter(filtro);
        File f;
        int resultado = selectorArchivo.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            f = selectorArchivo.getSelectedFile();
            if (f == null || f.getName().equals("")) {
                JOptionPane.showMessageDialog(null, "Nombre de archivo incorrecto", "Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return f;
        } else {
            return null;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaSalida1;
    private javax.swing.JButton butExaminar;
    private javax.swing.JPanel panelEntrada;
    private javax.swing.JPanel panelSalida1;
    private javax.swing.JScrollPane scrollSalida1;
    private javax.swing.JTextField textRuta;
    // End of variables declaration//GEN-END:variables
}
