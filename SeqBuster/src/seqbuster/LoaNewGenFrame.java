/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoaNewGenFrame.java
 *
 * Created on Apr 27, 2010, 6:30:17 PM
 */

package seqbuster;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import seqbuster.tools.*;
/**
 *
 * @author lpantano
 */
public class LoaNewGenFrame extends javax.swing.JDialog {
    Thread nThread=null;
    /** Creates new form LoaNewGenFrame */
    public LoaNewGenFrame(javax.swing.JDialog parent) {
        super(parent);
        initComponents();
        bargen.setValue(0);
        bargen.setStringPainted(true);
        //editpanel.setText("http://hgdownload.cse.ucsc.edu/goldenPath/hg19/chromosomes/");
    }

   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadbut = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        gentext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labtext = new javax.swing.JTextField();
        spstext = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        bargen = new javax.swing.JProgressBar();
        multicheck = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(LoaNewGenFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadbut.setText(resourceMap.getString("loadbut.text")); // NOI18N
        loadbut.setName("loadbut"); // NOI18N
        loadbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadbutActionPerformed(evt);
            }
        });
        getContentPane().add(loadbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 100, -1));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 100, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        gentext.setText(resourceMap.getString("gentext.text")); // NOI18N
        gentext.setName("gentext"); // NOI18N
        gentext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gentextMouseClicked(evt);
            }
        });
        getContentPane().add(gentext, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 270, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        labtext.setText(resourceMap.getString("labtext.text")); // NOI18N
        labtext.setName("labtext"); // NOI18N
        getContentPane().add(labtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 90, -1));

        spstext.setText(resourceMap.getString("spstext.text")); // NOI18N
        spstext.setName("spstext"); // NOI18N
        getContentPane().add(spstext, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 60, -1));

        jButton4.setBackground(resourceMap.getColor("jButton4.background")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 20, 20));

        jButton5.setBackground(resourceMap.getColor("jButton5.background")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 20, 20));

        jButton6.setBackground(resourceMap.getColor("jButton6.background")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setFocusable(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 20, 20));

        bargen.setName("bargen"); // NOI18N
        getContentPane().add(bargen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        multicheck.setText(resourceMap.getString("multicheck.text")); // NOI18N
        multicheck.setName("multicheck"); // NOI18N
        getContentPane().add(multicheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jButton7.setBackground(resourceMap.getColor("jButton7.background")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setFocusable(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 20, 20));

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, -1));

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 160, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadbutActionPerformed
        // TODO add your handling code here:

        Runnable view = new Runnable() {

        public void run() {
            bargen.setValue(50);

        
        LoadData loaddata_obj=new LoadData();
        try {
            try {
                int ret=0;
                if (multicheck.isSelected()){
                    ret=loaddata_obj.loadgenomedir(gentext.getText(), labtext.getText(), spstext.getText());
                }else{
                    ret=loaddata_obj.loadgenomefile(gentext.getText(), labtext.getText(), spstext.getText());
                }
                
                bargen.setValue(100);
                if (ret==1){
                    tools.showinfo("Your  genome has been loaded.");
                    NonMiRNAFrame.GenList.addItem(labtext.getText());
                }
                Runtime.getRuntime().gc();
//                setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(LoaNewGenFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(LoaNewGenFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }

        }
        };
        nThread=new Thread(view);
        nThread.start();

    }//GEN-LAST:event_loadbutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if (nThread!=null){

            if (nThread.isAlive()){
              Object[] options = {"Yes","No"};

              int n = JOptionPane.showOptionDialog(null,"Do you want to cancel the process?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
              if (n==1){


               }else{
                    nThread.stop();
                    dispose();
               }
              }else{
                dispose();
              }

        }else{
             dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Give a name for the genome.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Checked if your genome is in more than 1 file.");
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Write the specie ID.");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Select the file that contains the genome or the directory where all the files are.\n If you select a directory only files ending in '.fa' will be considered.");

    }//GEN-LAST:event_jButton7ActionPerformed

    private void gentextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gentextMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(gentext.getText());
        String filename;
        if (multicheck.isSelected()){
            c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            gentext.setText(filename);
        }
}//GEN-LAST:event_gentextMouseClicked

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bargen;
    private javax.swing.JTextField gentext;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField labtext;
    private javax.swing.JButton loadbut;
    private javax.swing.JCheckBox multicheck;
    private javax.swing.JTextField spstext;
    // End of variables declaration//GEN-END:variables

}
