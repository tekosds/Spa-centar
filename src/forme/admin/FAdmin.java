/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.admin;

import forme.t.FKompanija;
import forme.t.FPreparat;
import forme.t.FTretman;
import forme.t.FTretmanPrikaz;
import forme.t.FZaposleni;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.sesija.Sesija;

/**
 *
 * @author Megi
 */
public class FAdmin extends javax.swing.JFrame {

    /**
     * Creates new form Glavna
     */
    public FAdmin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiUnosT = new javax.swing.JMenuItem();
        jmiPrikazT = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiUnosZ = new javax.swing.JMenuItem();
        jmiUnosP = new javax.swing.JMenuItem();
        jmiUnosK = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiIzlaz = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Tretman");

        jmiUnosT.setText("Unos novog tretmana");
        jmiUnosT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUnosTActionPerformed(evt);
            }
        });
        jMenu1.add(jmiUnosT);

        jmiPrikazT.setText("Prikaz svih tretmana");
        jmiPrikazT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPrikazTActionPerformed(evt);
            }
        });
        jMenu1.add(jmiPrikazT);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Unos");

        jmiUnosZ.setText("zaposlenog");
        jmiUnosZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUnosZActionPerformed(evt);
            }
        });
        jMenu3.add(jmiUnosZ);

        jmiUnosP.setText("preparata");
        jmiUnosP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUnosPActionPerformed(evt);
            }
        });
        jMenu3.add(jmiUnosP);

        jmiUnosK.setText("kompanije");
        jmiUnosK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUnosKActionPerformed(evt);
            }
        });
        jMenu3.add(jmiUnosK);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Izlaz");

        jmiIzlaz.setText("Izađite iz programa");
        jmiIzlaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIzlazActionPerformed(evt);
            }
        });
        jMenu2.add(jmiIzlaz);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiUnosTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUnosTActionPerformed
        new FTretman().setVisible(true);
    }//GEN-LAST:event_jmiUnosTActionPerformed

    private void jmiPrikazTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrikazTActionPerformed

        try {
            new FTretmanPrikaz().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiPrikazTActionPerformed

    private void jmiIzlazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIzlazActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jmiIzlazActionPerformed

    private void jmiUnosZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUnosZActionPerformed
        new FZaposleni().setVisible(true);
    }//GEN-LAST:event_jmiUnosZActionPerformed

    private void jmiUnosPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUnosPActionPerformed
        try {
            new FPreparat().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiUnosPActionPerformed

    private void jmiUnosKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUnosKActionPerformed
        new FKompanija().setVisible(true);
    }//GEN-LAST:event_jmiUnosKActionPerformed

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
            java.util.logging.Logger.getLogger(FAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmiIzlaz;
    private javax.swing.JMenuItem jmiPrikazT;
    private javax.swing.JMenuItem jmiUnosK;
    private javax.swing.JMenuItem jmiUnosP;
    private javax.swing.JMenuItem jmiUnosT;
    private javax.swing.JMenuItem jmiUnosZ;
    // End of variables declaration//GEN-END:variables
}
