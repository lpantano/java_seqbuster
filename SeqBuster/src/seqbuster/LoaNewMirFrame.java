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
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import javax.swing.JFileChooser;
//import javax.swing.JDialog;
import javax.swing.JOptionPane;
//import seqbuster.LoadData;
/**
 *
 * @author lpantano
 */
public class LoaNewMirFrame extends javax.swing.JDialog {

    /** Creates new form LoaNewGenFrame */
    public LoaNewMirFrame(javax.swing.JDialog parent) {
        super(parent);
        initComponents();
        bargen.setValue(0);
        bargen.setStringPainted(true);
        editpanel.setText("http://hgdownload.cse.ucsc.edu/goldenPath/hg19/chromosomes/");
    }

   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadbut = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mitext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        labtext = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        bargen = new javax.swing.JProgressBar();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        editpanel = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();
        hairtext = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(LoaNewMirFrame.class);
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
        getContentPane().add(loadbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 100, -1));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 100, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        mitext.setText(resourceMap.getString("mitext.text")); // NOI18N
        mitext.setName("mitext"); // NOI18N
        mitext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mitextMouseClicked(evt);
            }
        });
        getContentPane().add(mitext, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 270, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        labtext.setText(resourceMap.getString("labtext.text")); // NOI18N
        labtext.setName("labtext"); // NOI18N
        getContentPane().add(labtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 90, -1));

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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 20, 20));

        bargen.setName("bargen"); // NOI18N
        getContentPane().add(bargen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 20, 20));

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 460, -1));

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 160, 60));

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 430, 20));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        editpanel.setName("editpanel"); // NOI18N
        jScrollPane1.setViewportView(editpanel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 480, 40));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        hairtext.setText(resourceMap.getString("hairtext.text")); // NOI18N
        hairtext.setName("hairtext"); // NOI18N
        hairtext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hairtextMouseClicked(evt);
            }
        });
        getContentPane().add(hairtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 270, -1));

        jButton8.setBackground(resourceMap.getColor("jButton8.background")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setFocusable(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setOpaque(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 20, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadbutActionPerformed
        // TODO add your handling code here:

        Runnable view = new Runnable() {

        public void run() {
            bargen.setValue(50);

        
            LoadData loaddata_obj=new LoadData();
       
        }
        };
        Thread nThread = new Thread(view);
        nThread.start();

    }//GEN-LAST:event_loadbutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give a name for the genome.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the file that contains the hairpin-like database.\n ");

    }//GEN-LAST:event_jButton7ActionPerformed

    private void mitextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mitextMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(mitext.getText());
        String filename;
        
        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            mitext.setText(filename);
        }
}//GEN-LAST:event_mitextMouseClicked

    private void hairtextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hairtextMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(mitext.getText());
        String filename;

        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            hairtext.setText(filename);
        }
    }//GEN-LAST:event_hairtextMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         JOptionPane.showMessageDialog(null,"Select the file that contains the miRNA-like database.\n ");
    }//GEN-LAST:event_jButton8ActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bargen;
    private javax.swing.JEditorPane editpanel;
    private javax.swing.JTextField hairtext;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField labtext;
    private javax.swing.JButton loadbut;
    private javax.swing.JTextField mitext;
    // End of variables declaration//GEN-END:variables

}
