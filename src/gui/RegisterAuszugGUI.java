package gui;

import bl.DataSetter;
import dataRead.ReadFromXML;
import dataWrite.WriteToXML;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import model.InfoTable;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class RegisterAuszugGUI extends javax.swing.JFrame {

    InfoTable it;
    int regNr;
    int zuNr;

    public RegisterAuszugGUI(int regNr, int zuNr) {
        try {
            initComponents();
            this.regNr = regNr;
            this.zuNr = zuNr;
            it = new InfoTable(regNr, zuNr);
            tb_info.setModel(it);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: ZuNr oder RegNr falsch.");
            System.exit(1);
        }
    }

    public RegisterAuszugGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp_info = new javax.swing.JScrollPane();
        tb_info = new javax.swing.JTable();
        btn_save = new javax.swing.JButton();
        btn_saveXml = new javax.swing.JButton();
        btn_loadXml = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        sp_info.setViewportView(tb_info);

        btn_save.setText("Speichern");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_saveXml.setText("save As XML");
        btn_saveXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveXmlActionPerformed(evt);
            }
        });

        btn_loadXml.setText("load As XML");
        btn_loadXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadXmlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_save, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sp_info, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
            .addComponent(btn_saveXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_loadXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sp_info, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn_loadXml)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_saveXml)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Registerauszug speichern");
            jfc.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
            if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = jfc.getSelectedFile();
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                DataSetter ds = new DataSetter();
                ds.writeToFile(bw, it);
            }
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error: IO");
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_saveXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveXmlActionPerformed
        WriteToXML wtx = new WriteToXML(tb_info);
        try {
            JFileChooser jfc = new JFileChooser();
            int ret = jfc.showSaveDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                wtx.writeDOM(wtx.createDOM(), new FileWriter(jfc.getSelectedFile()));
            }
            wtx.writeDOM(wtx.createDOM(), new FileWriter("src/infotable.xml"));
        } catch (SAXException | IOException | TransformerException | ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(null, "Fehler beim XML-Schreiben:" + ex.getMessage());
        }
    }//GEN-LAST:event_btn_saveXmlActionPerformed

    private void btn_loadXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadXmlActionPerformed
        ReadFromXML rtx = new ReadFromXML();
        try {
            JFileChooser jfc = new JFileChooser();
            int ret = jfc.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                InfoTable loaded = rtx.LoadXML(jfc.getSelectedFile());
                tb_info.setModel(loaded);
                this.setTitle("XML Document");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Fehler beim XML-Lesen:" + ex.getMessage());
        } catch (ParserConfigurationException | SAXException ex) {
            JOptionPane.showMessageDialog(null, "Fehler beim XML-Lesen:" + ex.getMessage());
        }
    }//GEN-LAST:event_btn_loadXmlActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterAuszugGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterAuszugGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterAuszugGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterAuszugGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new RegisterAuszugGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_loadXml;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_saveXml;
    private javax.swing.JScrollPane sp_info;
    private javax.swing.JTable tb_info;
    // End of variables declaration//GEN-END:variables
}
