

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




public class NonMiRNAanaTSFrame extends javax.swing.JDialog  {
        
        String namepro=SeqBusterView.loaprotext.getText();
        String pathout=SeqBusterView.pathout.getText();
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();

        int nofinite=0;

    public NonMiRNAanaTSFrame(java.awt.Frame parent) throws SQLException {
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
        difexpBut = new javax.swing.JButton();
        selsamp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ExpList1 = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        predval = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        samplest = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numvalues = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(NonMiRNAanaTSFrame.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 270, -1));

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 650, -1));

        difexpBut.setText(resourceMap.getString("difexpBut.text")); // NOI18N
        difexpBut.setName("difexpBut"); // NOI18N
        difexpBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difexpButActionPerformed(evt);
            }
        });
        getContentPane().add(difexpBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 280, -1));

        selsamp.setBackground(resourceMap.getColor("selsamp.background")); // NOI18N
        selsamp.setText(resourceMap.getString("selsamp.text")); // NOI18N
        selsamp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selsamp.setName("selsamp"); // NOI18N
        selsamp.setOpaque(true);
        getContentPane().add(selsamp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 400, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        ExpList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ExpList1.setName("ExpList1"); // NOI18N
        ExpList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExpList1MouseClicked(evt);
            }
        });
        ExpList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ExpList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ExpList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 100, 90));

        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        predval.setText(resourceMap.getString("predval.text")); // NOI18N
        predval.setName("predval"); // NOI18N
        predval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                predvalMousePressed(evt);
            }
        });
        getContentPane().add(predval, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 260, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 150, -1));

        samplest.setText(resourceMap.getString("samplest.text")); // NOI18N
        samplest.setName("samplest"); // NOI18N
        getContentPane().add(samplest, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 260, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 150, -1));

        numvalues.setBackground(resourceMap.getColor("numvalues.background")); // NOI18N
        numvalues.setText(resourceMap.getString("numvalues.text")); // NOI18N
        numvalues.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        numvalues.setName("numvalues"); // NOI18N
        numvalues.setOpaque(true);
        getContentPane().add(numvalues, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 260, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        Runtime.getRuntime().gc();
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void difexpButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difexpButActionPerformed
        // TODO add your handling code here:
        sendscript();

    }//GEN-LAST:event_difexpButActionPerformed

    private void  sendscript (){
        Date date = new Date();
        HelpDesk.helptext.setText("Process initiated at: "+date+"\n");


        int[] selectedIx = ExpList1.getSelectedIndices();
        String group1=samplest.getText();
//        for (int i=0; i<selectedIx.length; i++) {
//            Object sel = ExpList1.getModel().getElementAt(selectedIx[i]);
//            group1=group1+sel.toString()+":";
//        }
        if (new File("temp/ts.done").exists()){
            new File("temp/ts.done").delete();
        }
        //difexpseq("Ztest","BH","1000000",0,100,"test2","test2","testpro","","scale","localhost","lpantano","sqllorena")
        String opt=group1+"\n"+namepro+"\n"+host+"\n"+user+"\n"+pssw+"\n"+predval.getText();
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("temp/args.txt")));
            out.write(opt);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAanaTSFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        //tools.r.matches(user);
        tools check=new tools();
        check.readparameters("seqbuster.op");
        String r=check.r;
        if (!r.matches("")){
            r=r+"/";
        }
        String cmd=r+"R CMD BATCH Rscripts/R/timeserieclus.R"+ " temp/timeserieclus.Rout";
//
        //String cmd="Rscript  Rscripts/R/timeserieclus.R ";
        //System.out.println(cmd);
        try {
            process = runtime.exec(cmd);
            try {

                process.waitFor();

            } catch (InterruptedException ex) {

                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }
            //browse data
            if (new File("temp/ts.done").exists()){
                BrowTSnmFrame browse;
                try {
                    browse = new BrowTSnmFrame(this);
                    browse.setLocationRelativeTo(this);
                    browse.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NonMiRNAanaTSFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());

                }
            }else{
                tools.showcerror("The analysis finished with an error,\nsee temp/timeserieclus.Rout\n ");
            }
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAanaTSFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());

        }

    }
    private void ExpList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ExpList1ValueChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ExpList1ValueChanged

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        predval.setText("");
    }//GEN-LAST:event_formMouseClicked

    private void predvalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_predvalMousePressed
        // TODO add your handling code here:
        predval.setText("");
    }//GEN-LAST:event_predvalMousePressed

    private void ExpList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExpList1MouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        int[] selectedIx = ExpList1.getSelectedIndices();
        
        String out=samplest.getText();
        for (int i=0; i<selectedIx.length; i++) {
            Object sel = ExpList1.getModel().getElementAt(selectedIx[i]);
            out=samplest.getText();
            samplest.setText(out+sel.toString()+":");
        }
        String  [] num =samplest.getText().split(":");
        numvalues.setText("Number of values needed: "+num.length);

    }//GEN-LAST:event_ExpList1MouseClicked
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
    private static javax.swing.JList ExpList1;
    public static javax.swing.JButton difexpBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel numvalues;
    private static javax.swing.JTextField predval;
    private javax.swing.JTextField samplest;
    private javax.swing.JLabel selsamp;
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