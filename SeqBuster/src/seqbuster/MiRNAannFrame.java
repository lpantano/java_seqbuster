/*
 * seqbusterAboutBox.java
 */

package seqbuster;

//import javax.swing.JFileChooser;
//import org.jdesktop.application.Action;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class MiRNAannFrame extends javax.swing.JDialog {
    String tdb;
    public MiRNAannFrame(String typeDB) throws SQLException, FileNotFoundException, IOException {
//        super(parent);
        initComponents();
        tdb=typeDB;
        getRootPane().setDefaultButton(doad);
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
        ArrayList<String> spslist=new ArrayList<String> ();
        Statement statment =  con.createStatement();
        String readsamples="select `name` from `experiments` where `type`  like 'miRNA'";
        ResultSet rs=statment.executeQuery(readsamples);
        while (rs.next()) {
            nameexp.addItem(rs.getString(1));

        }
//        readsamples="select `sp` from `seqbuster`.`"+typeDB+"miRNA` group by `sp`";
//        rs=statment.executeQuery(readsamples);
//        while (rs.next()) {
//            speMirAnnList.addItem(rs.getString(1));
//
//        }
         BufferedReader inmi= new BufferedReader(new FileReader("DB/mbhairpinRNA.db"));
         String l=null;
         while ((l=inmi.readLine())!=null){
            if (l.contains(">")){
                String [] sps=l.split("-");
                sps[0]=sps[0].replace(">", "");
                if (!spslist.contains(sps[0])){
                    
                    spslist.add(sps[0]);
                    //System.out.println(sps[0]);

                }
            }
         }

         for (int i=0;i<spslist.size();i++){

            speMirAnnList.addItem(spslist.get(i));
         }

//        rs=statment.executeQuery("select `sp` from `seqbuster`.`DBhairpinRNA` group by `sp` ");
        rs.close();
        statment.close();
        con.close();
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doad = new javax.swing.JButton();
        CloseButAdapFrame = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        MisMirAnnText = new javax.swing.JTextField();
        AddMirAnnText = new javax.swing.JTextField();
        TriMirAnnText = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        nameexp = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        speMirAnnList = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(MiRNAannFrame.class);
        doad.setText(resourceMap.getString("doad.text")); // NOI18N
        doad.setName("doad"); // NOI18N
        doad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doadActionPerformed(evt);
            }
        });
        getContentPane().add(doad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 100, -1));

        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 90, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        MisMirAnnText.setText(resourceMap.getString("MisMirAnnText.text")); // NOI18N
        MisMirAnnText.setName("MisMirAnnText"); // NOI18N
        getContentPane().add(MisMirAnnText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 30, -1));

        AddMirAnnText.setText(resourceMap.getString("AddMirAnnText.text")); // NOI18N
        AddMirAnnText.setName("AddMirAnnText"); // NOI18N
        getContentPane().add(AddMirAnnText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 30, -1));

        TriMirAnnText.setText(resourceMap.getString("TriMirAnnText.text")); // NOI18N
        TriMirAnnText.setName("TriMirAnnText"); // NOI18N
        TriMirAnnText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TriMirAnnTextActionPerformed(evt);
            }
        });
        getContentPane().add(TriMirAnnText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 30, -1));

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
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 20, 20));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 65, 20, 20));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 20, 20));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 20, 20));

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
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 20, 20));

        nameexp.setName("nameexp"); // NOI18N
        getContentPane().add(nameexp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 130, 30));

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setForeground(resourceMap.getColor("jLabel6.foreground")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 50, 20));

        speMirAnnList.setName("speMirAnnList"); // NOI18N
        getContentPane().add(speMirAnnList, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, 30));

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 100, 50));

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 50, 20));

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 50, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doadActionPerformed
        //try {

        miRNAann mirann= new miRNAann();
        try {
            mirann.miRNAann(tdb);
        } catch (SQLException ex) {
            Logger.getLogger(MiRNAannFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.getSQLState());
        } catch (IOException ex) {
            Logger.getLogger(MiRNAannFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
        

    }//GEN-LAST:event_doadActionPerformed

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        Runtime.getRuntime().gc();
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void TriMirAnnTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TriMirAnnTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TriMirAnnTextActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"choose the sample you want to analyze.");
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"choose the specie of your data");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Number of mismatch allowed in the alignment.");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Number of nucleotides allowed as additions respect with the reference miRNA.");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Number of nucleotides allowed as variations at the ends of the reference miRNA.");
    }//GEN-LAST:event_jButton9ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField AddMirAnnText;
    private javax.swing.JButton CloseButAdapFrame;
    public static javax.swing.JTextField MisMirAnnText;
    public static javax.swing.JTextField TriMirAnnText;
    private javax.swing.JButton doad;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JComboBox nameexp;
    public static javax.swing.JComboBox speMirAnnList;
    // End of variables declaration//GEN-END:variables
    
}
