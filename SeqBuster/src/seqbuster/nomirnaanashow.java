/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * infoFrame.java
 *
 * Created on May 5, 2010, 12:37:17 PM
 */

package seqbuster;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class nomirnaanashow extends javax.swing.JDialog {

    /** Creates new form infoFrame */
    public nomirnaanashow(String msg) {
        super(SeqBusterApp.getApplication().getMainFrame());
        initComponents();
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Point  p= SeqBusterApp.getApplication().getMainFrame().getLocation();
        this.setLocation(p.x+500, p.y+100);
        changemsg();
    }


    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        infText = new javax.swing.JTextArea();
        savebut = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusCycleRoot(false);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        infText.setColumns(20);
        infText.setRows(5);
        infText.setName("infText"); // NOI18N
        jScrollPane1.setViewportView(infText);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, 320));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(nomirnaanashow.class);
        savebut.setText(resourceMap.getString("savebut.text")); // NOI18N
        savebut.setName("savebut"); // NOI18N
        savebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebutActionPerformed(evt);
            }
        });
        getContentPane().add(savebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 90, -1));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void savebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebutActionPerformed
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(SeqBusterView.pathout.getText());
        String filename;
        c.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
        
        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            try {
            filename=c.getSelectedFile().toString();
            FileWriter fw = new FileWriter(filename);
            FileInputStream fstream = new FileInputStream("temp/usrna");
            DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int num=0;
            while ((strLine = br.readLine()) != null & num<10)   {

              fw.write(strLine+"\n");
            }
            fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error!!!!.Copy this message and send to lorena.pantano@crg.es : " + ex);
            }
        }
        
    }//GEN-LAST:event_savebutActionPerformed

   public void changemsg (){
       //open temp/usrna
       infText.setText("");
       try {
            FileInputStream fstream = new FileInputStream("temp/usrna");
            DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int num=0;
            while ((strLine = br.readLine()) != null & num<10)   {
                num++;
              infText.append(strLine+"\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error!!!!.Copy this message and send to lorena.pantano@crg.es : " + ex);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea infText;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton savebut;
    // End of variables declaration//GEN-END:variables

}
