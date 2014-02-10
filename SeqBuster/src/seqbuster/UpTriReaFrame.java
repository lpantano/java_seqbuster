 /* License: GNU General Public License
SeqBuster: uder-friendly tool for small RNA analysis. Copyright (C) 2009 Lorena Pantano
 This program is free software: you can redistribute it and/or modify it under the terms
 of the GNU General Public License as published by the Free Software Foundation, either
 version 3 of the License, or (at your option) any later version. This program is distributed
 in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 more details. You should have received a copy of the GNU General Public License along with this
 program. If not, see http://www.gnu.org/licenses/.
 */

/*
 * seqbusterAboutBox.java
 */

package seqbuster;

import javax.swing.JFileChooser;
//import org.jdesktop.application.Action;

import javax.swing.JOptionPane;

public class UpTriReaFrame extends javax.swing.JDialog {

    public UpTriReaFrame(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        doad = new javax.swing.JButton();
        CloseButAdapFrame = new javax.swing.JButton();
        nameexp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MinCouUTRText = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(UpTriReaFrame.class);
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 310, -1));

        input.setText(resourceMap.getString("input.text")); // NOI18N
        input.setName("input"); // NOI18N
        input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputMouseClicked(evt);
            }
        });
        getContentPane().add(input, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 270, -1));

        doad.setText(resourceMap.getString("doad.text")); // NOI18N
        doad.setName("doad"); // NOI18N
        doad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doadActionPerformed(evt);
            }
        });
        getContentPane().add(doad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 150, -1));

        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, -1));

        nameexp.setText(resourceMap.getString("nameexp.text")); // NOI18N
        nameexp.setName("nameexp"); // NOI18N
        getContentPane().add(nameexp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        MinCouUTRText.setText(resourceMap.getString("MinCouUTRText.text")); // NOI18N
        MinCouUTRText.setName("MinCouUTRText"); // NOI18N
        getContentPane().add(MinCouUTRText, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 30, -1));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 20, 20));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 20, 20));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 20, 20));

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 370, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Thread thread =null;
    private void doadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doadActionPerformed
        // TODO add your handling code here:
        //try {

        UpTriRea adapter= new UpTriRea();
        thread = new Thread(adapter);
        thread.start();
        
        
    }//GEN-LAST:event_doadActionPerformed

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        if (thread!=null){

            if (thread.isAlive()){
              Object[] options = {"Yes","No"};

              int n = JOptionPane.showOptionDialog(null,"Do you want to cancel the process?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
              if (n==1){


               }else{
                    thread.stop();
                    Runtime.getRuntime().gc();
                    dispose();
               }
              }else{
                Runtime.getRuntime().gc();
                dispose();
              }

        }else{
            Runtime.getRuntime().gc();
             dispose();
        }
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void inputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(input.getText());
        String filename;

        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            input.setText(filename);
        }
}//GEN-LAST:event_inputMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Name of the sample. Only put alpha-numeric characters not superior to 20 in length.");
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
       tools.showinfo("File containing the sequences without the adapter.\nThe file should have two columns (separated by tab) in the following format:\nseq counts\nAlso fasta format is allowed.");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Minimun number of counts that sequences should have to be stored.");
    }//GEN-LAST:event_jButton8ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    public static javax.swing.JTextField MinCouUTRText;
    private javax.swing.JButton doad;
    public static javax.swing.JTextField input;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JTextField nameexp;
    // End of variables declaration//GEN-END:variables
    
}
