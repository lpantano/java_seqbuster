

package seqbuster;
import java.awt.Point;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RemovetrackFrame extends javax.swing.JDialog {

    /** Creates new form LoaNewGenFrame */
    public RemovetrackFrame() throws MalformedURLException, IOException, SQLException {
        //super(parent);
        initComponents();
        Point  p= SeqBusterApp.getApplication().getMainFrame().getLocation();
        this.setLocation(p.x+500, p.y+100);
        showinfo();
        
        //editpanel.setText("http://hgdownload.cse.ucsc.edu/goldenPath/hg19/chromosomes/");
    }

   private void showinfo() throws SQLException{
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
        Statement statment = (Statement) con.createStatement();
        String readsamples="select `name` from `experiments` where `type` like 'miRNA' ";
        ResultSet rs=statment.executeQuery(readsamples);

        rs=statment.executeQuery("select `specie` from `seqbuster`.`track` group by `specie`");
        SpeList.removeAllItems();
        while (rs.next()) {
            SpeList.addItem(rs.getString(1));

        }

        statment.close();
        con.close();
   }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadbut = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        SpeList = new javax.swing.JComboBox();
        GenList = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(RemovetrackFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadbut.setText(resourceMap.getString("loadbut.text")); // NOI18N
        loadbut.setName("loadbut"); // NOI18N
        loadbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadbutActionPerformed(evt);
            }
        });
        getContentPane().add(loadbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 100, -1));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 100, -1));

        SpeList.setName("SpeList"); // NOI18N
        SpeList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SpeListMouseReleased(evt);
            }
        });
        SpeList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpeListItemStateChanged(evt);
            }
        });
        getContentPane().add(SpeList, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 130, 30));

        GenList.setName("GenList"); // NOI18N
        getContentPane().add(GenList, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 130, 30));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadbutActionPerformed
        // TODO add your handling code here:
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        String db = (String)GenList.getSelectedItem();
        String spe = (String)SpeList.getSelectedItem();
        if (!port.matches("")){host=host+":"+port;}
        Connection con;
        try {
            con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
            Statement statment = (Statement) con.createStatement();
            statment.executeUpdate("delete from `seqbuster`.`track` where `name` like '"+db+"'  and `specie` like '"+spe+"'");
            statment.executeUpdate("drop table `tracks`.`"+db+"_"+spe+"`");
            statment.executeUpdate("drop table if exists `tracks`.`"+db+"_"+spe+"_data`");
            tools.removefiles("track", "DB/"+db+"_"+spe);
            tools.removedir("DB/"+db+"_"+spe);
            tools.showinfo("Track was removed!");
            showinfo();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemovetrackFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_loadbutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SpeListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpeListMouseReleased
        // TODO add your handling code here:
}//GEN-LAST:event_SpeListMouseReleased

    private void SpeListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpeListItemStateChanged
        // TODO add your handling code here:
        String pathout=SeqBusterView.pathout.getText()+"/";
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}

        String spe = (String)SpeList.getSelectedItem();
        Connection con = null;
        try {
            con =  DriverManager.getConnection("jdbc:mysql://"+host+"/"+namepro,user,pssw);
            Statement statment =con.createStatement();
            //statment.executeUpdate("drop table if exists `"+expname+"clusraw` ");
            String readsamples="select `name` from `seqbuster`.`track` where `specie` like '"+spe+"' ";
            ResultSet rs=statment.executeQuery(readsamples);

            GenList.removeAllItems();
            while (rs.next()) {

                GenList.addItem(rs.getString(1));

            }
            
            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }

    }//GEN-LAST:event_SpeListItemStateChanged
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox GenList;
    public static javax.swing.JComboBox SpeList;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton loadbut;
    // End of variables declaration//GEN-END:variables

}
