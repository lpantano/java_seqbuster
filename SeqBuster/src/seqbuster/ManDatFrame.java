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



import java.io.IOException;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ManDatFrame extends javax.swing.JDialog {

    public ManDatFrame(java.awt.Frame parent) throws SQLException {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con =  tools.docon(namepro);
        Statement statment = con.createStatement();
        String readsamples="select `name` from `experiments` where `description` like 'desc'";
        ResultSet rs=statment.executeQuery(readsamples);
        remlist.addItem("");
        reslist.addItem("");
        manlist.addItem("");
        usrnalist.addItem("");
        while (rs.next()) {
            remlist.addItem(rs.getString(1));
            reslist.addItem(rs.getString(1));
            manlist.addItem(rs.getString(1));
        }
        
        rs=statment.executeQuery("select `name` from `experiments` where `description` like 'usRNA'");
        while (rs.next()) {
            String name=rs.getString(1).replace("clusmap","");
            usrnalist.addItem(name);
        }

        rs=statment.executeQuery("select `name` from `seqbuster`.`project`");
        rempro.addItem("");
        while (rs.next()) {
            
            rempro.addItem(rs.getString(1));
        }
        rs.close();
        statment.close();
        con.close();

    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        doad = new javax.swing.JButton();
        CloseButAdapFrame = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        remlist = new javax.swing.JComboBox();
        reslist = new javax.swing.JComboBox();
        usrnalist = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        manlist = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rempro = new javax.swing.JComboBox();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(ManDatFrame.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setEnabled(false);
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        doad.setText(resourceMap.getString("doad.text")); // NOI18N
        doad.setName("doad"); // NOI18N
        doad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doadActionPerformed(evt);
            }
        });
        getContentPane().add(doad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 120, -1));

        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 120, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 20, 20));

        jButton7.setBackground(resourceMap.getColor("jButton7.background")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 20, 20));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 20, 20));

        remlist.setName("remlist"); // NOI18N
        getContentPane().add(remlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 120, 30));

        reslist.setEnabled(false);
        reslist.setName("reslist"); // NOI18N
        getContentPane().add(reslist, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 120, 30));

        usrnalist.setName("usrnalist"); // NOI18N
        getContentPane().add(usrnalist, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 120, 30));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        manlist.setName("manlist"); // NOI18N
        getContentPane().add(manlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 120, 30));

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
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 20, 20));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, -1));

        rempro.setName("rempro"); // NOI18N
        getContentPane().add(rempro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 120, 30));

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
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 20, 20));

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 70, 350, 85));

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 155, 350, 85));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doadActionPerformed
        // TODO add your handling code here:
       

        String usrnalistname = (String)usrnalist.getSelectedItem();
        String manlistname = (String)manlist.getSelectedItem();
        String remlistname = (String)remlist.getSelectedItem();
        String remproname = (String)rempro.getSelectedItem();
        
        String namepro=SeqBusterView.loaprotext.getText();

        if (!manlistname.matches("")){
            BrowFrame browse;
            try {
                try {
                    browse = new BrowFrame(this, (String) manlist.getSelectedItem());
                    browse.setLocationRelativeTo(this);
                    
                } catch (IOException ex) {
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                    Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                //browse.showdata((String) manlist.getSelectedItem(),"");
                
               
            } catch (SQLException ex) {
                Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
            }
        }

        if (!usrnalistname.matches("")){
            BrowusRNAFrame browse;
            try {
                browse = new BrowusRNAFrame(this,(String) usrnalist.getSelectedItem());
                browse.setLocationRelativeTo(this);
                browse.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
            }
        }

        if (!remlistname.matches("")){

                Object[] options = {"Yes","No"};
                int n = JOptionPane.showOptionDialog(null,"Do you want to remove table "+remlistname+"?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (n==0){
                    Connection con = null;
                    try {
                        con =  tools.docon(namepro);
                        Statement statment =  con.createStatement();
                        statment.executeUpdate("drop table if exists  `"+remlistname+"clusmap`");
                        statment.executeUpdate("drop table if exists  `"+remlistname+"`");
                        statment.executeUpdate("drop table if exists `"+remlistname+"clusraw`");
                        statment.executeUpdate("drop table if exists `"+remlistname+"clus`");
                        statment.executeUpdate("delete from `experiments` where `name` like '%"+remlistname+"%'");
                        remlist.removeItem(remlist.getSelectedItem());
                        remlist.repaint();
                        tools.showinfo("Sample removed.");
                    } catch (SQLException ex) {
                        Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
                    }


                }

        }

        if (!remproname.matches("")){
            Object[] options = {"Yes","No"};
                int n = JOptionPane.showOptionDialog(null,"Do you want to remove project: "+remproname+"?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (n==0){
                    Connection con = null;
                    try {
                        con =  tools.docon("seqbuster");
                        Statement statment =  con.createStatement();
                        statment.executeUpdate("drop database if exists  `"+remproname+"`");
                        statment.executeUpdate("delete from `project` where `name` like '"+remproname+"'");
                        rempro.removeItem(reslist.getSelectedItem());
                        rempro.repaint();
                        tools.showinfo("Project removed.");
                    } catch (SQLException ex) {
                        Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
                        tools.showerror(ex.getLocalizedMessage(),ex.getMessage());
                    }


                }
        }
    }//GEN-LAST:event_doadActionPerformed

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("This sample will be remove it.");
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("This sample will be reset (removing annotation information)");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("You will directly navigate through the data processed for each sample");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("You will directly navigate through the tables generated with usRNAs information");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("This sample will be remove it.");
    }//GEN-LAST:event_jButton10ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JButton doad;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox manlist;
    private javax.swing.JComboBox remlist;
    private javax.swing.JComboBox rempro;
    private javax.swing.JComboBox reslist;
    private javax.swing.JComboBox usrnalist;
    // End of variables declaration//GEN-END:variables
    
}
