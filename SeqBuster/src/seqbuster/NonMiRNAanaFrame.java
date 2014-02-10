

package seqbuster;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList.*;
import javax.swing.JDialog;
import javax.swing.JFrame;




public class NonMiRNAanaFrame extends javax.swing.JDialog  {
        
        String namepro=SeqBusterView.loaprotext.getText();
        String pathout=SeqBusterView.pathout.getText();
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();

        int nofinite=0;

    public NonMiRNAanaFrame(java.awt.Frame parent) throws SQLException {
        super(parent);
        initComponents();
//        getRootPane().setDefaultButton(CloseButAdapFrame);
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        String readsamples="select `name` from `experiments` where  `description` like 'usRNA' ";
        ResultSet rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        int p=0;
        //ExpList.addItem("");
        while (rs.next()) {
            ExpList.addItem(rs.getString(1).replace("clusmap",""));
             model.add(p,rs.getString(1).replace("clusmap",""));
             p++;
        }




       
        rs.close();
        statment.close();
        con.close();


        String info="You can select only a subset of your data,";
        info+="depending on their genome annotation.";
        
        helpana1.setText(info);

//        JDialog browse = new BrowDEnmFrame(this);
//        browse.setLocationRelativeTo(this);
//
//        browse.setVisible(true);


    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        overviewdo = new javax.swing.JButton();
        CloseButAdapFrame = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ExpList = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        helpNonMiAnaFrame = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        DBlist = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        typelist = new javax.swing.JList();
        lendo = new javax.swing.JButton();
        fastado = new javax.swing.JButton();
        freqdo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        helpana1 = new javax.swing.JEditorPane();
        locationdo = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        helpNonMiAnaFrame1 = new javax.swing.JButton();
        helpNonMiAnaFrame2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        html_but = new javax.swing.JToggleButton();
        ntbut = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        conscheck = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        htmlss_but = new javax.swing.JToggleButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(NonMiRNAanaFrame.class);
        overviewdo.setText(resourceMap.getString("overviewdo.text")); // NOI18N
        overviewdo.setName("overviewdo"); // NOI18N
        overviewdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewdoActionPerformed(evt);
            }
        });
        getContentPane().add(overviewdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 130, -1));

        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 500, -1));

        jLabel2.setBackground(resourceMap.getColor("jLabel2.background")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, -1));

        ExpList.setName("ExpList"); // NOI18N
        ExpList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExpListMouseClicked(evt);
            }
        });
        ExpList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ExpListItemStateChanged(evt);
            }
        });
        getContentPane().add(ExpList, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 130, -1));

        jButton4.setBackground(resourceMap.getColor("jButton4.background")); // NOI18N
        jButton4.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 20, 20));

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 320, -1));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 20, 20));

        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        helpNonMiAnaFrame.setText(resourceMap.getString("helpNonMiAnaFrame.text")); // NOI18N
        helpNonMiAnaFrame.setName("helpNonMiAnaFrame"); // NOI18N
        helpNonMiAnaFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpNonMiAnaFrameActionPerformed(evt);
            }
        });
        getContentPane().add(helpNonMiAnaFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 130, -1));

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        DBlist.setName("DBlist"); // NOI18N
        DBlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DBlistMouseClicked(evt);
            }
        });
        DBlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                DBlistValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(DBlist);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 130, 100));

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        typelist.setName("typelist"); // NOI18N
        jScrollPane4.setViewportView(typelist);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 120, 100));

        lendo.setText(resourceMap.getString("lendo.text")); // NOI18N
        lendo.setName("lendo"); // NOI18N
        lendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lendoActionPerformed(evt);
            }
        });
        getContentPane().add(lendo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 130, -1));

        fastado.setText(resourceMap.getString("fastado.text")); // NOI18N
        fastado.setName("fastado"); // NOI18N
        fastado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fastadoActionPerformed(evt);
            }
        });
        getContentPane().add(fastado, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 130, -1));

        freqdo.setText(resourceMap.getString("freqdo.text")); // NOI18N
        freqdo.setName("freqdo"); // NOI18N
        freqdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freqdoActionPerformed(evt);
            }
        });
        getContentPane().add(freqdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 130, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 90, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 100, -1));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setName("jScrollPane5"); // NOI18N

        helpana1.setName("helpana1"); // NOI18N
        jScrollPane5.setViewportView(helpana1);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 270, 70));

        locationdo.setText(resourceMap.getString("locationdo.text")); // NOI18N
        locationdo.setEnabled(false);
        locationdo.setName("locationdo"); // NOI18N
        locationdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationdoActionPerformed(evt);
            }
        });
        getContentPane().add(locationdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 130, -1));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 20, 20));

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
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 20, 20));

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
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 20, 20));

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
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 20, 20));

        jLabel3.setBackground(resourceMap.getColor("jLabel3.background")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 320, 50));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 130, 30));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 130, -1));

        helpNonMiAnaFrame1.setText(resourceMap.getString("helpNonMiAnaFrame1.text")); // NOI18N
        helpNonMiAnaFrame1.setName("helpNonMiAnaFrame1"); // NOI18N
        helpNonMiAnaFrame1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpNonMiAnaFrame1ActionPerformed(evt);
            }
        });
        getContentPane().add(helpNonMiAnaFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 130, -1));

        helpNonMiAnaFrame2.setText(resourceMap.getString("helpNonMiAnaFrame2.text")); // NOI18N
        helpNonMiAnaFrame2.setName("helpNonMiAnaFrame2"); // NOI18N
        helpNonMiAnaFrame2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpNonMiAnaFrame2ActionPerformed(evt);
            }
        });
        getContentPane().add(helpNonMiAnaFrame2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, 130, -1));

        jLabel4.setBackground(resourceMap.getColor("jLabel4.background")); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 310, -1));

        html_but.setText(resourceMap.getString("html_but.text")); // NOI18N
        html_but.setName("html_but"); // NOI18N
        html_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                html_butActionPerformed(evt);
            }
        });
        getContentPane().add(html_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 160, -1));

        ntbut.setText(resourceMap.getString("ntbut.text")); // NOI18N
        ntbut.setName("ntbut"); // NOI18N
        ntbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ntbutActionPerformed(evt);
            }
        });
        getContentPane().add(ntbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 130, -1));

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
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 20, 20));

        conscheck.setText(resourceMap.getString("conscheck.text")); // NOI18N
        conscheck.setName("conscheck"); // NOI18N
        getContentPane().add(conscheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 260, -1));

        jLabel5.setBackground(resourceMap.getColor("jLabel5.background")); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 310, 50));

        htmlss_but.setText(resourceMap.getString("htmlss_but.text")); // NOI18N
        htmlss_but.setEnabled(false);
        htmlss_but.setName("htmlss_but"); // NOI18N
        htmlss_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                htmlss_butActionPerformed(evt);
            }
        });
        getContentPane().add(htmlss_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 160, -1));

        jLabel8.setBackground(resourceMap.getColor("jLabel8.background")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, 650, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void overviewdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overviewdoActionPerformed
        // TODO add your handling code here:
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        HelpDesk.helptext.setText("Process initiated at: "+date+"\n");
        HelpDesk.bar.setValue(0);
        HelpDesk.bar.setStringPainted(true);
        Runnable view = new Runnable() {

        public void run() {


        String expname= (String) ExpList.getSelectedItem();
        ArrayList <String> listdb = new ArrayList <String> ();
        String pathout=SeqBusterView.pathout.getText()+"/";


        //run R script

        //get fasta file [13 cloum standard]
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con;
        File file=new File(pathout + namepro + "/" + expname+"/"+"results_usRNAs");
        boolean exists = file.exists();
        if (exists==false){
            boolean success = (new File(pathout + namepro + "/" + expname+"/"+"results_usRNAs")).mkdir();
            if (success==false){
              tools.showcerror("Error creating directory, check persmissions: "+pathout + namepro + "/" + expname+"/"+"results_usRNAs/");
            }
        }
        

            try {
                con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
                Statement statment =  con.createStatement();
                Statement statment2 =  con.createStatement();
                Statement statment3 =  con.createStatement();
                String types="describe `" + namepro + "`.`" + expname + "clusmap`";
                ResultSet rs = statment.executeQuery(types);
                int c=0;
                while (rs.next()) {
                    c++;
                    if (c>15){
                        listdb.add(rs.getString(1));

                    }

                }

                        String dislentotal    ="select `len`,count(*) from (select `len` from `" + namepro + "`.`" + expname + "clusmap` where `id`>0  group by `id`) as t group by `len`";
                        ResultSet dislentotalrs = statment.executeQuery(dislentotal);
                        try {
                                //open file for each subtipe
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/"+"results_usRNAs/" + "All_DistLen.txt")));
                            while(dislentotalrs.next()){
                            // System.out.println(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getInt(2));
                              out.write(dislentotalrs.getInt(1)+"\t"+dislentotalrs.getInt(2)+"\n");
                            }
                            out.close();
                        } catch (IOException ex) {
                            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                            tools.writelog(log,ex);
                        }
                        dislentotalrs.close();



                try {
                    //foreach db mapped
                    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/" +"results_usRNAs/" + "DistType.txt")));

                for (int i=0;i<listdb.size();i++){
                    HelpDesk.helptext.append("Analysis database: "+listdb.get(i)+"\n");
                    HelpDesk.bar.setValue(50);
                    copyfile(pathout + namepro + "/" + expname + "/"+ listdb.get(i)+".class",pathout + namepro + "/" + expname + "/"+"results_usRNAs/" + listdb.get(i)+"_location.txt");
                    String readsamples = "select * from `" + namepro + "`.`" + expname + "clusmap` where `"+listdb.get(i)+"` not like '0' and `"+listdb.get(i)+"`  not like 'na'  and `"+listdb.get(i)+"` regexp '^\\"+"\\"+"$' and `id`>0 group by `id` ";
                    rs = statment.executeQuery(readsamples);
               // while (rs.next()) {
                     //subtipe distribution of this db
                    //System.out.println(rs.getString(expname));
                     String countsubtypes="select `"+listdb.get(i)+"`,count(*) from (select `"+listdb.get(i)+"` from `" + namepro + "`.`" + expname + "clusmap` where `"+listdb.get(i)+"` not like '0' and `"+listdb.get(i)+"`  not like 'na' and `"+listdb.get(i)+"` regexp '^\\"+"\\"+"$' and `id`>0 group by `id`) as t group by `"+listdb.get(i)+"`";
                     ResultSet rscountsubtypes = statment2.executeQuery(countsubtypes);
                     rscountsubtypes.last();
                     int rowCount = rscountsubtypes.getRow();
                     if (rowCount<=10){
                         rscountsubtypes.first();
                             while(rscountsubtypes.next()){
                                //System.out.println(rscountsubtypes.getString(1)+"\t"+rscountsubtypes.getInt(2));
                                out2.write(rscountsubtypes.getString(1)+"\t"+rscountsubtypes.getInt(2)+"\n");
                                //get seq.subtype,len dist
                                String countsubtypes2    ="select `len`,count(*) from (select `len` from `" + namepro + "`.`" + expname + "clusmap` where `"+listdb.get(i)+"`  like '"+rscountsubtypes.getString(1)+"' and `id`>0  group by `id`) as t group by `len`";
                                ResultSet rscountsubtypes2 = statment3.executeQuery(countsubtypes2);
                                try {
                                        //open file for each subtipe
                                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/"+"results_usRNAs/" + listdb.get(i) + "_" + rscountsubtypes.getString(1) + "_DistLen.txt")));
                                    while(rscountsubtypes2.next()){
                                    // System.out.println(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getInt(2));
                                      out.write(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getInt(2)+"\n");
                                    }
                                    out.close();
                                } catch (IOException ex) {
                                    //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                                    tools.writelog(log,ex);
                                }
                                rscountsubtypes2.close();

                                countsubtypes2    ="select `id`,`seq` from `" + namepro + "`.`" + expname + "clusmap` where `"+listdb.get(i)+"`  like '"+rscountsubtypes.getString(1)+"' and `id`>0  group by `id`";
                                rscountsubtypes2 = statment3.executeQuery(countsubtypes2);
                                //each subtipe sequences
                                try {
                                        //open file for each subtipe
                                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/"+"results_usRNAs/" + listdb.get(i) + "_" + rscountsubtypes.getString(1) + "_seq.fa")));
                                    while(rscountsubtypes2.next()){
                                    // System.out.println(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getInt(2));
                                      out.write(">"+rscountsubtypes2.getInt(1)+"\n"+rscountsubtypes2.getString(2)+"\n");
                                    }
                                    out.close();
                                } catch (IOException ex) {
                                    //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                                    tools.writelog(log,ex);
                                }
                                rscountsubtypes2.close();


                             }
                     }
                     rscountsubtypes.close();
                     //total of this database
    //                 System.out.println(rowCount+"\t"+listdb.get(i));

                    //print rs.get seq,len dist by db
                        String countsubtypes2    ="select `len`,count(*) from (select `len` from `" + namepro + "`.`" + expname + "clusmap` where `"+listdb.get(i)+"`  not like '0' and `"+listdb.get(i)+"`  not like 'na'  and `"+listdb.get(i)+"` regexp '^\\"+"\\"+"$' and `id`>0 group by `id`) as t group by `len`";
                        ResultSet rscountsubtypes2 = statment2.executeQuery(countsubtypes2);
                        try {
                                //open file for each subtipe
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/"+"results_usRNAs/" + listdb.get(i) + "_DistLen.txt")));
                            while(rscountsubtypes2.next()){
                            // System.out.println(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getInt(2));
                              out.write(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getString(2)+"\n");
                            }
                            out.close();
                        } catch (IOException ex) {
                            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                             Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                            tools.writelog(log,ex);
                        }
                        rscountsubtypes2.close();

                        countsubtypes2    ="select `id`,`seq` from `" + namepro + "`.`" + expname + "clusmap` where `"+listdb.get(i)+"`  not like '0' and  `"+listdb.get(i)+"`  not like 'na'  and `"+listdb.get(i)+"` regexp '^\\"+"\\"+"$' and `id`>0 group by `id`";
                        rscountsubtypes2 = statment2.executeQuery(countsubtypes2);

                        try {
                                //open file for each subtipe
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/"+"results_usRNAs/" + listdb.get(i) + "_seq.fa")));
                            while(rscountsubtypes2.next()){
                            // System.out.println(rscountsubtypes2.getInt(1)+"\t"+rscountsubtypes2.getInt(2));
                              out.write(">"+rscountsubtypes2.getInt(1)+"\n"+rscountsubtypes2.getString(2)+"\n");
                            }
                            out.close();
                        } catch (IOException ex) {
                            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                             Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                            tools.writelog(log,ex);
                        }
                        rscountsubtypes2.close();


                
                }
                out2.close();
                    HelpDesk.helptext.append("Process finished at: "+new Date()+"\n");
                    HelpDesk.bar.setValue(100);
                } catch (IOException ex) {
                    //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                     Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);
                }

                //add nm interdabases and non classify
            } catch (SQLException ex) {
                //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                 Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);
            }

                tools.showinfo("Your process has finished. You will find all the files in:\n"+pathout + namepro + "/" + expname+"/"+"results_usRNAs");

        }
        };

        Thread nThread = new Thread(view);
        nThread.start();
    }//GEN-LAST:event_overviewdoActionPerformed


    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        Runtime.getRuntime().gc();
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Select the sample that will be used for the analysis.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        tools.showinfo("You will get the nucleotide distribution for the sequences.");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void helpNonMiAnaFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpNonMiAnaFrameActionPerformed
        try {
            NonMirAnaHelp help =new NonMirAnaHelp(this,true,"html/NoMirAnaFrame.html");
            help.setVisible(true);
        } catch (IOException ex) {
            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
           Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);

        }
    }//GEN-LAST:event_helpNonMiAnaFrameActionPerformed

    private void lendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lendoActionPerformed
        //
        Connection con;
        //String expname= (String) ExpList.getSelectedItem();
        try {
            con = tools.docon(namepro);
            Statement statment = con.createStatement();
            String query=getquery();
            query="select `len`,count(*) as nc from ("+query+" group by `id`) as t group by `len` ";
            ResultSet rs=statment.executeQuery(query);
            //query group by id, and the group by len

           try {

                FileWriter fw = new FileWriter("temp/usrna");
                while (rs.next()){
                    fw.write(rs.getInt(1)+"\t"+rs.getString(2)+"\n");
                }
                fw.close();
                tools.showinfo("Your process has finished");
                showresult("temp/usrna");
            } catch (IOException ex) {
                //Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);

            }
            rs.close();
            statment.close();
            con.close();

        } catch (SQLException ex) {
            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);

        }

        
    }//GEN-LAST:event_lendoActionPerformed

    private void fastadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fastadoActionPerformed
        // TODO add your handling code here:
        
        Connection con;
        String expname= (String) ExpList.getSelectedItem();
        try {
            con =  tools.docon(namepro);
            Statement statment = con.createStatement();
            Statement statmentraw=con.createStatement();
            String query=getquery();
            query="select `id` from ("+query+" group by `id`) as t group by `id` ";
            //System.out.println(query);
            ResultSet rs=statment.executeQuery(query);
            ResultSet rsraw= rs;
            //query group by id, and the group by len

          

                FileWriter fw;
            try {
                fw = new FileWriter("temp/usrna");
                while (rs.next()){
                    rsraw=statmentraw.executeQuery("select `seq` from `"+expname+"clusraw` where `id`="+rs.getInt(1)+" order by freq desc limit 0,1");
                    rsraw.next();
                    fw.write(">"+rs.getInt(1)+"\n"+rsraw.getString(1)+"\n");
                    
                }
                
                fw.close();
                tools.showinfo("Your process has finished");
                showresult("temp/usrna");
            } catch (IOException ex) {
                //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
                Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);
            }
                
            
            rsraw.close();
            rs.close();
            statment.close();
            con.close();

        } catch (SQLException ex) {
            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);

        }
    }//GEN-LAST:event_fastadoActionPerformed

    private void freqdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freqdoActionPerformed
        // TODO add your handling code here:
        
        Connection con;
        //String expname= (String) ExpList.getSelectedItem();
        try {
            con = tools.docon(namepro);
            Statement statment = con.createStatement();
            String query=getquery();
            query="select `freq` from ("+query+" group by `id`) as t  ";
            ResultSet rs=statment.executeQuery(query);
            //query group by id, and the group by len

           try {

                FileWriter fw = new FileWriter("temp/usrna");
                while (rs.next()){
                    fw.write(rs.getInt(1)+"\n");
                }
                fw.close();
                tools.showinfo("Your process has finished");
                showresult("temp/usrna");
            } catch (IOException ex) {
                //Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                 Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
                 tools.writelog(log,ex);

            }
            rs.close();
            statment.close();
            con.close();

        } catch (SQLException ex) {
            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);

        }

    }//GEN-LAST:event_freqdoActionPerformed

    private void locationdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationdoActionPerformed

            final  WaitFrame frame = new WaitFrame(this);
            frame.setVisible(true);

            Runnable view = new Runnable() {

            public void run() {
              String expname= (String) ExpList.getSelectedItem();

                int [] typeind= DBlist.getSelectedIndices();
                if (typeind.length>1){
                 tools.showcerror("only one database can be selected.");
                }else{
                
                 Object seldb = DBlist.getModel().getElementAt(typeind[0]);
                 copyfile(pathout +"/"+ namepro + "/" + expname + "/"+ seldb.toString()+".class",pathout+"/" + namepro + "/" + expname + "/"+"results_usRNAs/" + seldb.toString()+"_location.txt");
                 frame.setVisible(true);
                 tools.showinfo("Process finished. Results in :"
                        + namepro + "/" + expname + "/"+"results_usRNAs/" + seldb.toString()+"_location.txt");
                }
                
                
        }
        };
             Thread nThread= new Thread(view);
             nThread.start();
    }//GEN-LAST:event_locationdoActionPerformed

    private void ExpListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExpListMouseClicked

    }//GEN-LAST:event_ExpListMouseClicked

    private void ExpListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ExpListItemStateChanged
        // TODO add your handling code here:
         String expname= (String) ExpList.getSelectedItem();
        try {
            GetlistDB(expname);
        } catch (SQLException ex) {
            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);
        }
    }//GEN-LAST:event_ExpListItemStateChanged

    private void DBlistValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_DBlistValueChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_DBlistValueChanged

    private void DBlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DBlistMouseClicked
        // TODO add your handling code here:
          String expname= (String) ExpList.getSelectedItem();
          //System.out.print("clikinnnnnnnn");
        try {
            GetlistValue(expname);
        } catch (SQLException ex) {
            //Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);
        }
    }//GEN-LAST:event_DBlistMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("You will get the length distribution for the sequences.");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("You will get the sequences in a fasta format.");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("You will get the expression distribution for the sequences.");

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("You will get the exact location of the sequences inside the genome content.");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            JDialog anaRNAFrame_obj = new NonMiRNAanaCCFrame(mainFrame);
            anaRNAFrame_obj.setLocationRelativeTo(mainFrame);
            anaRNAFrame_obj.setVisible(true);
        } catch (SQLException ex) {
            Logger log =Logger.getLogger(SeqBusterView.class.getName());
            tools.writelog(log,ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            JDialog anaRNAFrame_obj = new NonMiRNAanaTSFrame(mainFrame);
            anaRNAFrame_obj.setLocationRelativeTo(mainFrame);
            anaRNAFrame_obj.setVisible(true);
        } catch (SQLException ex) {
            Logger log =Logger.getLogger(SeqBusterView.class.getName());
            tools.writelog(log,ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void helpNonMiAnaFrame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpNonMiAnaFrame1ActionPerformed
        // TODO add your handling code here:
        try {
            NonMirAnaHelp help =new NonMirAnaHelp(this,true,"html/NoMirAnaDEFrame.html");
            help.setVisible(true);
        } catch (IOException ex) {
           Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
           tools.writelog(log,ex);

        }
    }//GEN-LAST:event_helpNonMiAnaFrame1ActionPerformed

    private void helpNonMiAnaFrame2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpNonMiAnaFrame2ActionPerformed
        // TODO add your handling code here:
        try {
            NonMirAnaHelp help =new NonMirAnaHelp(this,true,"html/NoMirAnaTSFrame.html");
            help.setVisible(true);
        } catch (IOException ex) {
           Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
           tools.writelog(log,ex);

        }
    }//GEN-LAST:event_helpNonMiAnaFrame2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void ntbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ntbutActionPerformed
         
        Connection con;
        String expname= (String) ExpList.getSelectedItem();
        try {
            con =  tools.docon(namepro);
            Statement statment = con.createStatement();
            Statement statmentraw=con.createStatement();
            String query=getquery();
            query="select `id` from ("+query+" group by `id`) as t group by `id` ";
            //System.out.println(query);
            ResultSet rs=statment.executeQuery(query);
            ResultSet rsraw= rs;
            //query group by id, and the group by len

           try {

                FileWriter fw = new FileWriter("temp/usrna");
                while (rs.next()){
                    //System.out.println(rs.getInt(1));
                    rsraw=statmentraw.executeQuery("select `seq`,`freq` from `"+expname+"clusraw` where `freq` >0 and `id`="
                            +rs.getInt(1)+" order by freq desc ");
                    rsraw.next();
                    String [] nt=rsraw.getString(1).split("");
                    int len=nt.length-1;
                    for (int i=1;i<nt.length;i++){
                       fw.write(i+"\t"+nt[i]+"\t"+rsraw.getInt(2)+"\t" +len
                               + "\t" +"\n");
                       //System.out.println(i+"\t"+nt[i]+"\t"+rsraw.getInt(2)+"\t" +nt.length
                              // + "\t" +"\n"+rsraw.getString(1)+"\n\n");
                    }
                    
                    
                }
                
                fw.close();
                tools check=new tools();
                check.readparameters("seqbuster.op");
                String r=check.r;
                if (!r.matches("")){
                 r=r+"/";
                }
                String cmd=r+"R CMD BATCH --vanilla Rscripts/R/ntcomposition.R"+ " temp/ntcomposition.Rout";
                tools.appwait(cmd); 
             
                
                cmd="cp temp/ntcomposition.bmp "+pathout+"/"+namepro+"/"+expname+"/results_usRNAs/";
                tools.app(cmd);
                tools.showinfo("Your process has finished. The results are in: "+pathout+namepro+"/"+
                     expname+"/results_usRNAs/ntcomposition.bmp");
                
            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());

            }
            rsraw.close();
            rs.close();
            statment.close();
            con.close();

        } catch (SQLException ex) {
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);

        }
    }//GEN-LAST:event_ntbutActionPerformed

    private void html_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_html_butActionPerformed
        try {
            String expname= (String) ExpList.getSelectedItem();
            SelectTrackFrame dialog=new SelectTrackFrame(this,expname);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);
        }
        
    }//GEN-LAST:event_html_butActionPerformed

    private void htmlss_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_htmlss_butActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_htmlss_butActionPerformed
    private String getquery() throws SQLException{
        String query=null;
        //need sample,projec, db and list
        String expname= (String) ExpList.getSelectedItem();
        //go to database
        query="select * from `"+expname+"clusmap` where `id`>0 and ";
        Object seldb=null;
        String cons="";
        if (conscheck.isSelected()){
            cons="$";
        }
        int [] typeind= DBlist.getSelectedIndices();
        if (typeind.length>0){
            seldb = DBlist.getModel().getElementAt(typeind[0]);
        }
        int [] typeselind= typelist.getSelectedIndices();
        
        if (typeind.length==1 & nofinite==0){
           if (typeselind.length>0){
            
            for (int i=0;i<typeselind.length;i++){
                 Object sel = typelist.getModel().getElementAt(typeselind[i]);
                 query=query+"`"+seldb.toString()+"` regexp '^\\"+"\\"+cons+sel.toString()+"' ";
                 if (i<typeselind.length-1){
                    query=query+" or ";
                }
            }
            
           }else{
                query=query+"`"+seldb.toString()+"` regexp '^\\"+"\\"+cons+"' ";

           }

        }else if (typeind.length>1 | (typeind.length==1 & nofinite==1)){

            for (int i=0;i<typeind.length;i++){
                Object sel = DBlist.getModel().getElementAt(typeind[i]);
                query=query+"`"+sel.toString()+"` regexp '^\\"+"\\"+cons+"' ";
                if (i<typeind.length-1){
                    query=query+" and ";
                }
            }

        }
        if (typeind.length==0 & typeselind.length==0){
            query="select * from `"+expname+"clusmap` where `id`>0 ";
        }
        //System.out.print(query+"\n");
        return query;
    }
    private void GetlistValue (String exp) throws SQLException{
        
        Connection con = tools.docon(namepro);
        Statement statment =  con.createStatement();
        int [] typeind= DBlist.getSelectedIndices();
        Object sel = DBlist.getModel().getElementAt(typeind[0]);
        
        String readsamples="select `"+sel.toString()+"` from `"+exp+"clusmap` where `"+sel.toString()+"` regexp '^\\"+"\\"+"$' group by `"+sel.toString()+"` ";
        //System.out.print("clikinnnnnnnn "+readsamples);
        ResultSet rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        int count=0;
        int p=0;
        //System.out.print("clikinnnnnnnn "+sel.toString());
        while (rs.next()){
            //System.out.print("rs "+rs.getString(1));
            String type=rs.getString(1).replace("$", "");
            model.add(p,type);
            p++;

            count ++;
            if (count>10){
                break;
            }
        }
        if (count<=10){
            typelist.setModel(model);
            nofinite=0;
        }else{
            tools.showinfo("This class of genome content is not divided in a finite number of type");
            model.clear();
            typelist.setModel(model);
            nofinite=1;
        }
        rs.close();
        statment.close();
        con.close();
        //getquery();
    }
    private void GetlistDB (String exp) throws SQLException{
       
        Connection con =  tools.docon(namepro);
        Statement statment = con.createStatement();
        String readsamples="describe `"+exp+"clusmap`";
        ResultSet rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        int count=0;
        int p=0;
        while (rs.next()){

            if (count>=15){
                model.add(p,rs.getString(1));
                p++;
            }
            
            count ++;
        }

        DBlist.setModel(model);
        typelist.setModel(new DefaultListModel());
        rs.close();
        statment.close();
        con.close();
       // getquery();
    }
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
      Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
      tools.writelog(log,ex);
      
    }
    catch(IOException ex){
        Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
        tools.writelog(log,ex);
//      System.out.println(e.getMessage());
    }
  }
    private void showresult(String file){
           ///how to show results {overwiev, some lines???}
        try {
           ViewCluAnaFrame viewana= new ViewCluAnaFrame(file);
        } catch (IOException ex){
            Logger log =Logger.getLogger(NonMiRNAanaFrame.class.getName());
            tools.writelog(log,ex);
        } 

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JList DBlist;
    private javax.swing.JComboBox ExpList;
    private javax.swing.JCheckBox conscheck;
    private javax.swing.JButton fastado;
    private javax.swing.JButton freqdo;
    private javax.swing.JButton helpNonMiAnaFrame;
    private javax.swing.JButton helpNonMiAnaFrame1;
    private javax.swing.JButton helpNonMiAnaFrame2;
    private javax.swing.JEditorPane helpana1;
    private javax.swing.JToggleButton html_but;
    private javax.swing.JToggleButton htmlss_but;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton lendo;
    private javax.swing.JButton locationdo;
    private javax.swing.JButton ntbut;
    private javax.swing.JButton overviewdo;
    private javax.swing.JList typelist;
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