
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


package seqbuster;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AdapRecFrame extends javax.swing.JDialog {

    public AdapRecFrame(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        fastaad = new javax.swing.JCheckBox();
        headerad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        skipad = new javax.swing.JCheckBox();
        adapterseq = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        adapter5seq = new javax.swing.JTextField();
        lenad = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        mmad = new javax.swing.JTextField();
        comad = new javax.swing.JTextField();
        doad = new javax.swing.JButton();
        CloseButAdapFrame = new javax.swing.JButton();
        nameexp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MinCouARText = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(AdapRecFrame.class);
        setTitle(resourceMap.getString("adaprec.title")); // NOI18N
        setName("adaprec"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        input.setText(resourceMap.getString("input.text")); // NOI18N
        input.setName("input"); // NOI18N
        input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputMouseClicked(evt);
            }
        });
        getContentPane().add(input, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 310, -1));

        fastaad.setText(resourceMap.getString("fastaad.text")); // NOI18N
        fastaad.setName("fastaad"); // NOI18N
        fastaad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fastaadMouseClicked(evt);
            }
        });
        getContentPane().add(fastaad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        headerad.setText(resourceMap.getString("headerad.text")); // NOI18N
        headerad.setName("headerad"); // NOI18N
        getContentPane().add(headerad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 120, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        skipad.setText(resourceMap.getString("skipad.text")); // NOI18N
        skipad.setName("skipad"); // NOI18N
        getContentPane().add(skipad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, -1));

        adapterseq.setText(resourceMap.getString("adapterseq.text")); // NOI18N
        adapterseq.setName("adapterseq"); // NOI18N
        getContentPane().add(adapterseq, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 160, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel46.setText(resourceMap.getString("jLabel46.text")); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N
        getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        adapter5seq.setName("adapter5seq"); // NOI18N
        getContentPane().add(adapter5seq, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 160, -1));

        lenad.setText(resourceMap.getString("lenad.text")); // NOI18N
        lenad.setName("lenad"); // NOI18N
        getContentPane().add(lenad, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 50, -1));

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, -1, -1));

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel42.setText(resourceMap.getString("jLabel42.text")); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        mmad.setText(resourceMap.getString("mmad.text")); // NOI18N
        mmad.setName("mmad"); // NOI18N
        getContentPane().add(mmad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 376, 30, 30));

        comad.setText(resourceMap.getString("comad.text")); // NOI18N
        comad.setName("comad"); // NOI18N
        getContentPane().add(comad, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 376, 40, 30));

        doad.setText(resourceMap.getString("doad.text")); // NOI18N
        doad.setName("doad"); // NOI18N
        doad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doadActionPerformed(evt);
            }
        });
        getContentPane().add(doad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, 180, -1));

        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 170, -1));

        nameexp.setText(resourceMap.getString("nameexp.text")); // NOI18N
        nameexp.setName("nameexp"); // NOI18N
        getContentPane().add(nameexp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 110, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        MinCouARText.setText(resourceMap.getString("MinCouARText.text")); // NOI18N
        MinCouARText.setName("MinCouARText"); // NOI18N
        getContentPane().add(MinCouARText, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 30, 30));

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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 20, 20));

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
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 20, 20));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 145, 20, 20));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 20, 20));

        jButton9.setBackground(resourceMap.getColor("jButton9.background")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setOpaque(true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 20, 20));

        jButton10.setBackground(resourceMap.getColor("jButton10.background")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.setFocusable(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 20, 20));

        jButton11.setBackground(resourceMap.getColor("jButton11.background")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.setFocusable(false);
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setOpaque(true);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 20, 20));

        jButton12.setBackground(resourceMap.getColor("jButton12.background")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setBorderPainted(false);
        jButton12.setFocusable(false);
        jButton12.setName("jButton12"); // NOI18N
        jButton12.setOpaque(true);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 20, 20));

        jButton13.setBackground(resourceMap.getColor("jButton13.background")); // NOI18N
        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setBorderPainted(false);
        jButton13.setFocusable(false);
        jButton13.setName("jButton13"); // NOI18N
        jButton13.setOpaque(true);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 20, 20));

        jButton14.setBackground(resourceMap.getColor("jButton14.background")); // NOI18N
        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setBorderPainted(false);
        jButton14.setFocusable(false);
        jButton14.setName("jButton14"); // NOI18N
        jButton14.setOpaque(true);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 20, 20));

        jButton16.setBackground(resourceMap.getColor("jButton16.background")); // NOI18N
        jButton16.setText(resourceMap.getString("jButton16.text")); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setFocusable(false);
        jButton16.setName("jButton16"); // NOI18N
        jButton16.setOpaque(true);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 20, 20));

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 4, 450, 60));

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 250, -1));

        jLabel8.setBackground(resourceMap.getColor("jLabel8.background")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 430, 60));

        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 430, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-574)/2, (screenSize.height-645)/2, 574, 645);
    }// </editor-fold>//GEN-END:initComponents
    Thread thread=null;
    private void doadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doadActionPerformed
        // TODO add your handling code here:
        //try {

        AdaptorRecognition adapter= new AdaptorRecognition();
        thread = new Thread(adapter);
        thread.start();
        Runtime.getRuntime().gc();
        //dispose();
//        setVisible(false);
}//GEN-LAST:event_doadActionPerformed

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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("If your file have a header line, select this option.");
}//GEN-LAST:event_jButton8ActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Choose the name for the sample to add.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("If the file is in tab format (colum separated by tab) \n please put the name fo each column separated by '-'.\n The column containing the sequence should be name 'seq'");
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("If your file is a fasta file, select this option.");
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Put the sequence adapter in 3'.");
}//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("The minimun number of nucleotides of the adapter \nthat should be recognized in sequences to remove the adapter.  ");
}//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Choose the file with the raw data.");
}//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Type the sequence adapter in 5' (leave in blanck if the sequences don't have it.)");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Maximum number of mismatches allowed in the adapter alignment.");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Ratio to measure a hight level of repetition of some nucleotide.\n You remove low complexity sequences like: 'AAAAAAAAAAAA'. ");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Minimum number of counts sequences have to be repeated in the file to be added.");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void fastaadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fastaadMouseClicked
        // TODO add your handling code here:
        if (fastaad.isSelected()){
            headerad.setEnabled(false);
        }else{
            headerad.setEnabled(true);
        }
    }//GEN-LAST:event_fastaadMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    public static javax.swing.JTextField MinCouARText;
    public static javax.swing.JTextField adapter5seq;
    public static javax.swing.JTextField adapterseq;
    public static javax.swing.JTextField comad;
    private javax.swing.JButton doad;
    public static javax.swing.JCheckBox fastaad;
    public static javax.swing.JTextField headerad;
    public static javax.swing.JTextField input;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JTextField lenad;
    public static javax.swing.JTextField mmad;
    public static javax.swing.JTextField nameexp;
    public static javax.swing.JCheckBox skipad;
    // End of variables declaration//GEN-END:variables
    
}
