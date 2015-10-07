package nprot.vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class GUI extends javax.swing.JFrame {

    public GUI() {
        this.setTitle("Cysteine Counting Algorithm V1.3");
        initComponents();
        this.cysteine_resultsMI.addActionListener(new EventHandler());
        this.evoDistMi.addActionListener(new EventHandler());
        this.edGraphMI.addActionListener(new EventHandler());
        this.cyBarGraph.addActionListener(new EventHandler());
        this.cyVsEdBarGraph.addActionListener(new EventHandler());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        desktopP = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        load_informationMI = new javax.swing.JMenuItem();
        load_folderMI = new javax.swing.JMenuItem();
        npletsMI = new javax.swing.JMenuItem();
        evoDistMi = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        cysteine_resultsMI = new javax.swing.JMenuItem();
        cysteine_graph_resultsMI = new javax.swing.JMenuItem();
        edGraphMI = new javax.swing.JMenuItem();
        cyBarGraph = new javax.swing.JMenuItem();
        cyVsEdBarGraph = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPLayout = new javax.swing.GroupLayout(desktopP);
        desktopP.setLayout(desktopPLayout);
        desktopPLayout.setHorizontalGroup(
            desktopPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1238, Short.MAX_VALUE)
        );
        desktopPLayout.setVerticalGroup(
            desktopPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );

        jMenuBar1.setName("Cysteine Counting Algorythm V1.3"); // NOI18N

        jMenu1.setText("File");

        load_informationMI.setText("Load Organism");
        load_informationMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_informationMIActionPerformed(evt);
            }
        });
        jMenu1.add(load_informationMI);

        load_folderMI.setText("Load Folder");
        jMenu1.add(load_folderMI);

        npletsMI.setText("Nplets txt");
        npletsMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                npletsMIActionPerformed(evt);
            }
        });
        jMenu1.add(npletsMI);

        evoDistMi.setText("Load Evolutionary distances");
        jMenu1.add(evoDistMi);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Cysteine Information");

        cysteine_resultsMI.setText("View Results");
        jMenu3.add(cysteine_resultsMI);

        cysteine_graph_resultsMI.setText("View Cisteine Histogram");
        cysteine_graph_resultsMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cysteine_graph_resultsMIActionPerformed(evt);
            }
        });
        jMenu3.add(cysteine_graph_resultsMI);

        edGraphMI.setText("ED");
        edGraphMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edGraphMIActionPerformed(evt);
            }
        });
        jMenu3.add(edGraphMI);

        cyBarGraph.setText("Cysteines");
        jMenu3.add(cyBarGraph);

        cyVsEdBarGraph.setText("Cysteine vs ED ");
        jMenu3.add(cyVsEdBarGraph);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desktopP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void load_informationMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_informationMIActionPerformed
        Load_files a=new Load_files();
        a.setVisible(true);
        desktopP.add(a);
        
    }//GEN-LAST:event_load_informationMIActionPerformed

    private void npletsMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_npletsMIActionPerformed
       Generate_Nplets a= new Generate_Nplets();
        a.setVisible(true);
        desktopP.add(a);
        
    }//GEN-LAST:event_npletsMIActionPerformed

    private void cysteine_graph_resultsMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cysteine_graph_resultsMIActionPerformed
        totalConglomerate nueva = new totalConglomerate();
        nueva.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        nueva.setLocationRelativeTo(null);
        nueva.setVisible(true) ;
        
        
    }//GEN-LAST:event_cysteine_graph_resultsMIActionPerformed

    private void edGraphMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edGraphMIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edGraphMIActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI nueva = new GUI();
                /*
                    nueva.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                    nueva.setLocationRelativeTo(null);
                */
                nueva.setVisible(true);
            }
        });
    }

    public class EventHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            if(e.getSource() == cyVsEdBarGraph)
            {
                OrganismBars nuevo = new OrganismBars("BO");
                nuevo.setVisible(true);
                desktopP.add(nuevo);
                
            
            }
            
            if(e.getSource() == cyBarGraph)
            {
                OrganismBars nuevo = new OrganismBars("CI");
                nuevo.setVisible(true);
                desktopP.add(nuevo);
                
            
            }
            
            
            if(e.getSource() == edGraphMI) 
            {
                OrganismBars nuevo = new OrganismBars("ED");
                nuevo.setVisible(true);
                desktopP.add(nuevo);
                
            }
            
            
            if(e.getSource() == cysteine_resultsMI)
            {
                Cysteine_topN nuevo=new Cysteine_topN();
                nuevo.setVisible(true);
                desktopP.add(nuevo);
            }
            if( e.getSource () == evoDistMi)
            {
                loadED nuevo = new loadED();
                nuevo.setVisible(true);
                desktopP.add(nuevo);
                
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cyBarGraph;
    private javax.swing.JMenuItem cyVsEdBarGraph;
    private javax.swing.JMenuItem cysteine_graph_resultsMI;
    private javax.swing.JMenuItem cysteine_resultsMI;
    private javax.swing.JDesktopPane desktopP;
    private javax.swing.JMenuItem edGraphMI;
    private javax.swing.JMenuItem evoDistMi;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem load_folderMI;
    private javax.swing.JMenuItem load_informationMI;
    private javax.swing.JMenuItem npletsMI;
    // End of variables declaration//GEN-END:variables
}
