package gui;

import bl.DataGetter;
import bl.DataSetter;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainGUI extends javax.swing.JFrame {

    public MainGUI(){
        try {
            initComponents();
            DataGetter dg = new DataGetter();
            ArrayList al = dg.getRegisNumbers();
            cb_regis.setModel(new DefaultComboBoxModel(al.toArray()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: IO");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPnl = new javax.swing.JPanel();
        lbl_regis = new javax.swing.JLabel();
        cb_regis = new javax.swing.JComboBox<>();
        lbl_zus = new javax.swing.JLabel();
        tf_zusatz = new javax.swing.JTextField();
        btn_info = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        lbl_pic = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RA-Reader");
        setResizable(false);

        mainPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_regis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_regis.setText("Registernummer:");

        cb_regis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_zus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_zus.setText("Zusatznummer:");
        lbl_zus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_info.setText("Informationen");
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });

        btn_save.setText("Speichern");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        lbl_pic.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pic.setText("RA-READER");

        javax.swing.GroupLayout mainPnlLayout = new javax.swing.GroupLayout(mainPnl);
        mainPnl.setLayout(mainPnlLayout);
        mainPnlLayout.setHorizontalGroup(
            mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPnlLayout.createSequentialGroup()
                .addGroup(mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbl_regis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_zus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_info, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_regis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_zusatz)
                    .addGroup(mainPnlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(lbl_pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPnlLayout.setVerticalGroup(
            mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPnlLayout.createSequentialGroup()
                .addGroup(mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_regis)
                    .addComponent(cb_regis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_zus)
                    .addComponent(tf_zusatz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_info)
                    .addComponent(btn_save))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_infoActionPerformed
        int regNr = (int) cb_regis.getSelectedItem();
        int zuNr;
        if("".equals(tf_zusatz.getText())) tf_zusatz.setText("0");
        try {
            zuNr = Integer.parseInt(tf_zusatz.getText());
        } catch (Exception e) {
            zuNr = 0;
        }
        RegisterAuszugGUI ra = new RegisterAuszugGUI(regNr, zuNr);
        ra.setTitle("Artikel " + regNr + ":" + zuNr);
        ra.setVisible(true);
    }//GEN-LAST:event_btn_infoActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Registerauszug speichern");
            jfc.setFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt", "text"));
            if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = jfc.getSelectedFile();
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                DataSetter ds = new DataSetter();
                int regNr = (int) cb_regis.getSelectedItem();
                int zuNr;
                if("".equals(tf_zusatz.getText())) tf_zusatz.setText("0");
                    try {
                        zuNr = Integer.parseInt(tf_zusatz.getText());
                    } catch (Exception e) {
                        zuNr = 0;
                    }
                ds.writeToFile(bw, regNr, zuNr);
            }
        } catch(HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error: IO");
        }
    }//GEN-LAST:event_btn_saveActionPerformed

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_info;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> cb_regis;
    private javax.swing.JLabel lbl_pic;
    private javax.swing.JLabel lbl_regis;
    private javax.swing.JLabel lbl_zus;
    private javax.swing.JPanel mainPnl;
    private javax.swing.JTextField tf_zusatz;
    // End of variables declaration//GEN-END:variables
}
