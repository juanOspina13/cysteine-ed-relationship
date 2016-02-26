
package nprot.vista;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import nprot.modelo.Reader;

public class Generate_Nplets extends JInternalFrame {
private File entrada;
 
    public Generate_Nplets() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        windowLbl = new javax.swing.JLabel();
        windowCmb = new javax.swing.JComboBox();
        textRuta = new javax.swing.JTextField();
        butExaminar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Aminoacid sequence counter");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        windowLbl.setText("Window Size");

        windowCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));

        butExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExaminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(windowLbl)
                .addGap(8, 8, 8)
                .addComponent(windowCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butExaminar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(windowLbl)
                        .addComponent(windowCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(butExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExaminarActionPerformed
        entrada = escogerArchivos();
        System.out.println(entrada);
        String txt="";

        try {
            Reader lector=new Reader();
            
            String[] informationStored = lector.readSingleTxt(
                    Integer.parseInt( (String) windowCmb.getSelectedItem() ),
                     //50000 ,
                    entrada,
                    "nplets_count"
            );
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_butExaminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butExaminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField textRuta;
    private javax.swing.JComboBox windowCmb;
    private javax.swing.JLabel windowLbl;
    // End of variables declaration//GEN-END:variables
  private File escogerArchivos() {
        JFileChooser selectorArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Text files", 
                "fasta"
        );
        
        selectorArchivo.setFileFilter(filtro);
        File f;
        int resultado = selectorArchivo.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            f = selectorArchivo.getSelectedFile();
            if (f == null || f.getName().equals("")) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Nombre de archivo incorrecto", 
                        "Nombre incorrecto", 
                        JOptionPane.ERROR_MESSAGE
                );
                
                return null;
            }
            return f;
        } else {
            return null;
        }
    }
}
