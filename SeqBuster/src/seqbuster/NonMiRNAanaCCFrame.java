

package seqbuster;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList.*;
import javax.swing.JDialog;




public class NonMiRNAanaCCFrame extends javax.swing.JDialog  {
        
        String namepro=SeqBusterView.loaprotext.getText();
        String pathout=SeqBusterView.pathout.getText();
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        int nofinite=0;

    public NonMiRNAanaCCFrame(java.awt.Frame parent) throws SQLException {
        super(parent);
        initComponents();
//        getRootPane().setDefaultButton(CloseButAdapFrame);
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        String readsamples="select `name` from `experiments` where  `type` like 'usRNA' ";
        ResultSet rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        int p=0;
        //ExpList.addItem("");
        while (rs.next()) {
            
             model.add(p,rs.getString(1).replace("clusmap",""));
             p++;
        }

        ExpList1.setModel(model);
        ExpList2.setModel(model);
        
        rs.close();
        statment.close();
        con.close();


        String info="If you want to select only\n a subset of your data,";
        info+="here, you can select according to\n where they are in the genome.";
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButAdapFrame = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        difexpBut = new javax.swing.JButton();
        statlist = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ExpList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        ExpList2 = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        stat2L = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        samseq = new javax.swing.JRadioButton();
        statrb = new javax.swing.JRadioButton();
        nouttext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        stat2rb = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(NonMiRNAanaCCFrame.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 270, -1));

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 650, -1));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 20, 20));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        difexpBut.setText(resourceMap.getString("difexpBut.text")); // NOI18N
        difexpBut.setName("difexpBut"); // NOI18N
        difexpBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difexpButActionPerformed(evt);
            }
        });
        getContentPane().add(difexpBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 280, -1));

        statlist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ztest", "Binomial", "Bayesian", "Fisher" }));
        statlist.setName("statlist"); // NOI18N
        getContentPane().add(statlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 100, -1));

        jLabel5.setBackground(resourceMap.getColor("jLabel5.background")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 210, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        ExpList1.setName("ExpList1"); // NOI18N
        jScrollPane1.setViewportView(ExpList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 100, 90));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        ExpList2.setName("ExpList2"); // NOI18N
        jScrollPane2.setViewportView(ExpList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 100, 90));

        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        stat2L.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anova", "Ttest" }));
        stat2L.setName("stat2L"); // NOI18N
        getContentPane().add(stat2L, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 100, -1));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 20, 20));

        samseq.setText(resourceMap.getString("samseq.text")); // NOI18N
        samseq.setName("samseq"); // NOI18N
        samseq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                samseqMouseClicked(evt);
            }
        });
        samseq.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                samseqStateChanged(evt);
            }
        });
        samseq.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                samseqPropertyChange(evt);
            }
        });
        getContentPane().add(samseq, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        statrb.setText(resourceMap.getString("statrb.text")); // NOI18N
        statrb.setName("statrb"); // NOI18N
        statrb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statrbMouseClicked(evt);
            }
        });
        statrb.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                statrbStateChanged(evt);
            }
        });
        statrb.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                statrbPropertyChange(evt);
            }
        });
        getContentPane().add(statrb, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 230, -1));

        nouttext.setText(resourceMap.getString("nouttext.text")); // NOI18N
        nouttext.setName("nouttext"); // NOI18N
        nouttext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nouttextActionPerformed(evt);
            }
        });
        getContentPane().add(nouttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 30, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 205, 180, 20));

        stat2rb.setText(resourceMap.getString("stat2rb.text")); // NOI18N
        stat2rb.setName("stat2rb"); // NOI18N
        stat2rb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stat2rbMouseClicked(evt);
            }
        });
        stat2rb.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                stat2rbStateChanged(evt);
            }
        });
        stat2rb.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                stat2rbPropertyChange(evt);
            }
        });
        getContentPane().add(stat2rb, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        Runtime.getRuntime().gc();
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void difexpButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difexpButActionPerformed
        // TODO add your handling code here:
        Date date = new Date();
        HelpDesk.helptext.setText("Process initiated at: "+date+"\n");
        if (new File("temp/df.done").exists()){
            new File("temp/df.done").delete();
        }
        String stat= (String) statlist.getSelectedItem();
        String stat2= (String) stat2L.getSelectedItem();
        int[] selectedIx = ExpList1.getSelectedIndices();
        String group1="";
        for (int i=0; i<selectedIx.length; i++) {
            Object sel = ExpList1.getModel().getElementAt(selectedIx[i]);
            group1=group1+sel.toString()+":";
        }
        int[] selectedIx2 = ExpList2.getSelectedIndices();
        String group2="";
        for (int i=0; i<selectedIx2.length; i++) {
            Object sel = ExpList2.getModel().getElementAt(selectedIx2[i]);
            group2=group2+sel.toString()+":";
        }
        //difexpseq("Ztest","BH","1000000",0,100,"test2","test2","testpro","","scale","localhost","lpantano","sqllorena")
        String opt=stat+"\nBH\n1000000\n0\n100\n"+group1+"\n"+group2+"\n"+namepro+"\nscale\n"+nouttext.getText()+"\n"+host+"\n"+user+"\n"+pssw;

        String cmd="Rscript  Rscripts/R/difexpclus.R >temp/difexpclus.Rout";
        if (stat2rb.isSelected()){
            opt=stat2+"\n"+group1+"\n"+group2+"\n"+namepro+"\n"+host+"\n"+user+"\n"+pssw;
            cmd="Rscript  Rscripts/R/difexpclus2.R temp/difexpclus2.Rout";
        }
        if (samseq.isSelected()){
            opt=SeqBusterView.pathout.getText()+"\n"+group1+"\n"+group2+"\n"+namepro+"\n"+host+"\n"+user+"\n"+pssw;
            cmd="Rscript  Rscripts/R/samseqclus.R temp/samseqclus.Rout";
        }
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("temp/args.txt")));
            out.write(opt);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAanaCCFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        
        //System.out.println(cmd);
        try {
            process = runtime.exec(cmd);
            try {
                process.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(NonMiRNAanaCCFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //browse data
            if (new File("temp/df.done").exists()){
                if (samseq.isSelected()){
                    tools.showinfo("You have all the files in the following folder: "+SeqBusterView.pathout.getText()+"/"+namepro);
                }else{
                    BrowDEnmFrame browse;
                        try {
                            browse = new BrowDEnmFrame(this);
                            browse.setLocationRelativeTo(this);
                            browse.setVisible(true);
                        }catch (SQLException ex) {
                            Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
                            tools.showerror(ex.getLocalizedMessage(),ex.toString());

                        }
                }
            }else{
                tools.showcerror("The analysis finished with an error,\nsee temp/difexp2.Rout\n ");
            }
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAanaCCFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());

        }

    }//GEN-LAST:event_difexpButActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void samseqPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_samseqPropertyChange

    }//GEN-LAST:event_samseqPropertyChange

    private void statrbPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_statrbPropertyChange

    }//GEN-LAST:event_statrbPropertyChange

    private void statrbStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_statrbStateChanged
        // TODO add your handling code here:
          
    }//GEN-LAST:event_statrbStateChanged

    private void samseqStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_samseqStateChanged
        // TODO add your handling code here:
         
    }//GEN-LAST:event_samseqStateChanged

    private void samseqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_samseqMouseClicked
        // TODO add your handling code here:
         statrb.setSelected(false);
    }//GEN-LAST:event_samseqMouseClicked

    private void statrbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statrbMouseClicked
        // TODO add your handling code here:
         samseq.setSelected(false);
    }//GEN-LAST:event_statrbMouseClicked

    private void nouttextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nouttextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nouttextActionPerformed

    private void stat2rbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stat2rbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_stat2rbMouseClicked

    private void stat2rbStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_stat2rbStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_stat2rbStateChanged

    private void stat2rbPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_stat2rbPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_stat2rbPropertyChange
    private static void copyfile(String srFile, String dtFile){
    try{
      File f1 = new File(srFile);
      File f2 = new File(dtFile);
      InputStream in = new FileInputStream(f1);

      //For Append the file.
//      OutputStream out = new FileOutputStream(f2,true);

      //For Overwrite the file.
      OutputStream out = new FileOutputStream(f2);

      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0){
        out.write(buf, 0, len);
      }
      in.close();
      out.close();
//      System.out.println("File copied.");
    }
    catch(FileNotFoundException ex){
//      System.out.println(ex.getMessage() + " in the specified directory.");
      tools.showerror(ex.toString());
      
    }
    catch(IOException ex){
        tools.showerror(ex.toString());
//      System.out.println(e.getMessage());
    }
  }
    private void showresult(String file){
           ///how to show results {overwiev, some lines???}
        try {
           ViewCluAnaFrame viewana= new ViewCluAnaFrame(file);
        } catch (IOException ex){
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        } 

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JList ExpList1;
    private javax.swing.JList ExpList2;
    private javax.swing.JButton difexpBut;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nouttext;
    private javax.swing.JRadioButton samseq;
    private javax.swing.JComboBox stat2L;
    private javax.swing.JRadioButton stat2rb;
    private javax.swing.JComboBox statlist;
    private javax.swing.JRadioButton statrb;
    // End of variables declaration//GEN-END:variables
    public static JDialog loadgenome_obj;
    public static JDialog loadtrack_obj;
    public static JDialog info_obj;

   

public void DoRuntime (String cmd) throws IOException  {

       if (cmd.matches("")) {
         System.err.println("Need command to run");
         System.exit(-1);
           }
       //System.out.printf("running %s ",cmd);
       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       

    }



}