/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectTrackFrame.java
 *
 * Created on Sep 6, 2011, 6:20:18 PM
 */
package seqbuster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SelectTrackFrame extends javax.swing.JDialog {
    String expname="";
    public SelectTrackFrame(javax.swing.JDialog parent,String exp) throws SQLException {
        super(parent, true);
        initComponents();
        expname=exp;
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
        Statement statment = (Statement) con.createStatement();
        
        
        ResultSet rs=statment.executeQuery("select `specie` from `seqbuster`.`track` group by `specie`");
        ArrayList<String> spea = new ArrayList<String>();

        while (rs.next()) {
             if (!spea.contains(rs.getString(1))){
                spea.add(rs.getString(1));
             }

        }
        for(int i=0; i<spea.size(); i++) {
            spelist.addItem(spea.get(i));
        }
         //int numtrack=Integer.parseInt(rs.getString(1));
         rs=statment.executeQuery("select * from `seqbuster`.`track`");
        //int p=tralist.getModel().getSize();
        int p=0;
        while (rs.next()) {

            tralist.addItem(rs.getString(1));
            p++;

        }

       
        tralist.repaint();
         rs=statment.executeQuery("select * from `seqbuster`.`genome`");
        //int p=tralist.getModel().getSize();
        while (rs.next()) {

            genlist.addItem(rs.getString(1));
            p++;

        }

        rs.close();
        statment.close();
        con.close();

    }

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genlist = new javax.swing.JComboBox();
        tralist = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        closebut = new javax.swing.JButton();
        okbut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        orgtext = new javax.swing.JTextField();
        vertext = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spelist = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        genlist.setName("genlist"); // NOI18N
        getContentPane().add(genlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 159, -1));

        tralist.setName("tralist"); // NOI18N
        getContentPane().add(tralist, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 160, -1));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(SelectTrackFrame.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 120, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 130, 20));

        closebut.setText(resourceMap.getString("closebut.text")); // NOI18N
        closebut.setName("closebut"); // NOI18N
        closebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebutActionPerformed(evt);
            }
        });
        getContentPane().add(closebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 120, -1));

        okbut.setText(resourceMap.getString("okbut.text")); // NOI18N
        okbut.setName("okbut"); // NOI18N
        okbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okbutActionPerformed(evt);
            }
        });
        getContentPane().add(okbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 120, -1));

        jScrollPane1.setEnabled(false);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText(resourceMap.getString("jTextArea1.text")); // NOI18N
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 10, 390, -1));

        orgtext.setText(resourceMap.getString("orgtext.text")); // NOI18N
        orgtext.setName("orgtext"); // NOI18N
        getContentPane().add(orgtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 70, -1));

        vertext.setText(resourceMap.getString("vertext.text")); // NOI18N
        vertext.setName("vertext"); // NOI18N
        vertext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertextActionPerformed(evt);
            }
        });
        getContentPane().add(vertext, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 70, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        spelist.setName("spelist"); // NOI18N
        getContentPane().add(spelist, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 159, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void closebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebutActionPerformed
       dispose();
   }//GEN-LAST:event_closebutActionPerformed

   private void okbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okbutActionPerformed
       //String genefile=genetext.getText();
       final String track=(String)tralist.getSelectedItem();
       final String spe=(String)spelist.getSelectedItem();
       final String db=(String)genlist.getSelectedItem();
       final String org= orgtext.getText();
       final String ver= vertext.getText();
       Thread nThread = null;
       final infoFrame info_obj;
       info_obj = new infoFrame("Starting analysis\n");
       Runnable view = new Runnable() {
       public void run() {

        try {
           info_obj.changemsg("Creating html and calculating random secondary structures.");
           info_obj.changemsg("This could take long time...");
           if (db==null){
            tools.showcerror("You need to load the corresponding genome to run RNAfold,"
                 + " thus secondary structure prediction will be ignored.");
           }
           nonMirnaAna.sstrack( track, spe,expname,org,ver,db);

        } catch (SQLException ex) {
            tools.showerror(ex.toString()+"\nMake sure this track exists and contain gene information.");
            Logger log = Logger.getLogger(SelectTrackFrame.class.getName());
            tools.writelog(log,ex);
            Logger.getLogger(SelectTrackFrame.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       }
       };
       nThread= new Thread(view);
       nThread.start();
   }//GEN-LAST:event_okbutActionPerformed

   private void vertextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertextActionPerformed
       // TODO add your handling code here:
   }//GEN-LAST:event_vertextActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closebut;
    private javax.swing.JComboBox genlist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton okbut;
    private javax.swing.JTextField orgtext;
    private javax.swing.JComboBox spelist;
    private javax.swing.JComboBox tralist;
    private javax.swing.JTextField vertext;
    // End of variables declaration//GEN-END:variables
}
