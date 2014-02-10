/*
 * firstrun.java
 */

package seqbuster;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class FirstrunFrame extends javax.swing.JDialog {

    tools check=new tools ();
    public FirstrunFrame( ) throws IOException {
        super(SeqBusterApp.getApplication().getMainFrame());
        setModal(true);
        initComponents();
        if (!new File ("installed").exists() & new File ("seqbuster.op").exists()){
            
            check.readparameters("seqbuster.op");
            if (!check.user.matches("")){
                UseMysText.setText(check.user);
            }

            PswMysText.setText(check.passw);
            if(!check.host.matches("")){
                HosMysText.setText(check.host);
            }
            PorMysText.setText(check.port);

            tools.showcerror("The installation is incomplete, complete now!");
        }
        
        getRootPane().setDefaultButton(GoBut);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreSQBut = new javax.swing.JButton();
        GoBut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CheMysBut = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        UseMysText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PswMysText = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        HosMysText = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        PorMysText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        probar = new javax.swing.JProgressBar();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        closebut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(FirstrunFrame.class);
        CreSQBut.setText(resourceMap.getString("CreSQBut.text")); // NOI18N
        CreSQBut.setName("CreSQBut"); // NOI18N
        CreSQBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreSQButActionPerformed(evt);
            }
        });
        getContentPane().add(CreSQBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 160, -1));

        GoBut.setBackground(resourceMap.getColor("GoBut.background")); // NOI18N
        GoBut.setText(resourceMap.getString("GoBut.text")); // NOI18N
        GoBut.setName("GoBut"); // NOI18N
        GoBut.setOpaque(true);
        GoBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoButActionPerformed(evt);
            }
        });
        getContentPane().add(GoBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 190, -1));

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 420, -1));

        jLabel2.setBackground(resourceMap.getColor("jLabel2.background")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 430, -1));

        CheMysBut.setText(resourceMap.getString("CheMysBut.text")); // NOI18N
        CheMysBut.setName("CheMysBut"); // NOI18N
        CheMysBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheMysButActionPerformed(evt);
            }
        });
        getContentPane().add(CheMysBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 170, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        UseMysText.setText(resourceMap.getString("UseMysText.text")); // NOI18N
        UseMysText.setName("UseMysText"); // NOI18N
        UseMysText.setPreferredSize(new java.awt.Dimension(80, 26));
        getContentPane().add(UseMysText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 90, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        PswMysText.setText(resourceMap.getString("PswMysText.text")); // NOI18N
        PswMysText.setName("PswMysText"); // NOI18N
        getContentPane().add(PswMysText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 90, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        HosMysText.setText(resourceMap.getString("HosMysText.text")); // NOI18N
        HosMysText.setName("HosMysText"); // NOI18N
        getContentPane().add(HosMysText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 90, -1));

        jButton2.setBackground(resourceMap.getColor("jButton2.background")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 20, 20));

        jLabel8.setBackground(resourceMap.getColor("jLabel8.background")); // NOI18N
        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 70, 50));

        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jSeparator2.setName("jSeparator2"); // NOI18N
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 293, 90, 40));

        PorMysText.setText(resourceMap.getString("PorMysText.text")); // NOI18N
        PorMysText.setName("PorMysText"); // NOI18N
        getContentPane().add(PorMysText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 90, -1));

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        probar.setBackground(resourceMap.getColor("probar.background")); // NOI18N
        probar.setForeground(resourceMap.getColor("probar.foreground")); // NOI18N
        probar.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("probar.border.highlightColor"), null)); // NOI18N
        probar.setName("probar"); // NOI18N
        probar.setStringPainted(true);
        getContentPane().add(probar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 440, -1));

        jButton19.setBackground(resourceMap.getColor("jButton19.background")); // NOI18N
        jButton19.setText(resourceMap.getString("jButton19.text")); // NOI18N
        jButton19.setBorderPainted(false);
        jButton19.setFocusable(false);
        jButton19.setName("jButton19"); // NOI18N
        jButton19.setOpaque(true);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 20, 20));

        jButton18.setBackground(resourceMap.getColor("jButton18.background")); // NOI18N
        jButton18.setText(resourceMap.getString("jButton18.text")); // NOI18N
        jButton18.setBorderPainted(false);
        jButton18.setFocusable(false);
        jButton18.setName("jButton18"); // NOI18N
        jButton18.setOpaque(true);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 20, 20));

        jButton17.setBackground(resourceMap.getColor("jButton17.background")); // NOI18N
        jButton17.setText(resourceMap.getString("jButton17.text")); // NOI18N
        jButton17.setBorderPainted(false);
        jButton17.setFocusable(false);
        jButton17.setName("jButton17"); // NOI18N
        jButton17.setOpaque(true);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 20, 20));

        jButton20.setBackground(resourceMap.getColor("jButton20.background")); // NOI18N
        jButton20.setText(resourceMap.getString("jButton20.text")); // NOI18N
        jButton20.setBorderPainted(false);
        jButton20.setFocusable(false);
        jButton20.setName("jButton20"); // NOI18N
        jButton20.setOpaque(true);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 20, 20));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 110, -1));

        jSeparator3.setName("jSeparator3"); // NOI18N
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 450, 10));

        jSeparator4.setName("jSeparator4"); // NOI18N
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 460, 10));

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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 20, 20));

        jSeparator5.setName("jSeparator5"); // NOI18N
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jSeparator6.setName("jSeparator6"); // NOI18N
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 460, 20));

        closebut.setText(resourceMap.getString("closebut.text")); // NOI18N
        closebut.setName("closebut"); // NOI18N
        closebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebutActionPerformed(evt);
            }
        });
        getContentPane().add(closebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreSQButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreSQButActionPerformed

        probar.setValue(0);
        probar.setStringPainted(true);
        

            try
        {
              Class.forName("com.mysql.jdbc.Driver");
         } catch (Exception e)
        {
            //e.printStackTrace();
            tools.showerror(e.getLocalizedMessage(),e.toString());
        }



        try {
            String host=HosMysText.getText();
            String user=UseMysText.getText();
            String pssw=PswMysText.getText();
            String port=SeqBusterView.PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}
            Connection con = DriverManager.getConnection ("jdbc:mysql://"+host+"/mysql",user,pssw);
            Statement statment = con.createStatement();
            ResultSet rst =statment.executeQuery("show databases like 'seqbuster'");
            Boolean doit=true;
            if (rst.next()){
                Object[] options = {"Yes","No"};
                tools.showinfo("If you have projects, remeber to put the same output folder as the first installation\nwhen configuring parameters, otherwise you will get error whe loading your old projects.");
                int n = JOptionPane.showOptionDialog(null,"SeqBuster exists, would you like to reinstall the database?\nAll your data will be kept except genomes and tracks databases","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (n==1){
                   doit=false;
                }
            }
            if (doit){

            probar.setValue(25);
            probar.repaint();
            statment.executeUpdate("create database IF NOT EXISTS `seqbuster`");
            statment.executeUpdate("drop database IF EXISTS `genome`");
            statment.executeUpdate("create database IF NOT EXISTS `genome`");
            statment.executeUpdate("drop database IF EXISTS `tracks`");
            statment.executeUpdate("create database IF NOT EXISTS `tracks`");
            statment.executeUpdate("create table IF NOT EXISTS  `seqbuster`.`scriptsGUI` (`date` date NOT NULL,`name` varchar(40) collate utf8_unicode_ci NOT NULL,`type` varchar(40) collate utf8_unicode_ci NOT NULL default 'General', description text collate utf8_unicode_ci NOT NULL, samples tinyint(3) unsigned NOT NULL, groups tinyint(3) unsigned NOT NULL, script varchar(40) collate utf8_unicode_ci NOT NULL, link enum('1','0') collate utf8_unicode_ci NOT NULL, `user` varchar(50) collate utf8_unicode_ci NOT NULL, `order` tinyint(4) NOT NULL, UNIQUE KEY `name` (`name`), KEY `date` (`date`,`user`), KEY `type` (`type`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci");
            statment.executeUpdate("truncate table `seqbuster`.`scriptsGUI`");
            statment.executeUpdate("create table IF NOT EXISTS  `seqbuster`.`project` (`name` varchar (20),`date` date not null,  PRIMARY KEY (`name`))");
            //statment.executeUpdate("truncate table `seqbuster`.`project`");
            statment.executeUpdate("create table IF NOT EXISTS `seqbuster`.`genome` (`name` varchar (20),`specie` varchar(10) not null,  index (`name`))");
            statment.executeUpdate("truncate table `seqbuster`.`genome`");
            statment.executeUpdate("create table IF NOT EXISTS  `seqbuster`.`track` (`name` varchar (20),`specie`  varchar(10) not null,`type` enum('1','0') , index (`name`))");
            statment.executeUpdate("truncate table `seqbuster`.`track`");
            statment.executeUpdate("use `seqbuster`");
            
            //create test project
                    String namevar="testpro";
                    SeqBusterView.nameproject.addItem("testpro");
                    statment.executeUpdate ("CREATE DATABASE IF NOT EXISTS "+namevar);
                    statment.executeUpdate ("use "+namevar);
                    //create ann and read table
                    String anntable = "create table IF NOT EXISTS `ann` (`id` BIGINT UNSIGNED NOT NULL ,`code`VARCHAR( 50 ) NOT NULL PRIMARY KEY ,`chr` VARCHAR( 20 ) NOT NULL ,`start` INT UNSIGNED NOT NULL ,`end` INT UNSIGNED NOT NULL ,`strand` CHAR( 1 ) NOT NULL ,`sp` VARCHAR( 10 ) NOT NULL ,`mut` VARCHAR( 10 ) NOT NULL ,`addition3` VARCHAR( 10 ) NOT NULL,`DB` VARCHAR( 20 ) NOT NULL) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
                    statment.executeUpdate(anntable);
                    String readtable="create table IF NOT EXISTS `reads` (`id` BIGINT UNSIGNED AUTO_INCREMENT  ,`seq` VARCHAR( 50 ) NOT NULL UNIQUE,PRIMARY KEY (id)) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
                    statment.executeUpdate(readtable);
                    //create experiment and results table
                    String exptable="CREATE TABLE IF NOT EXISTS `experiments` (`name` varchar(20) collate utf8_unicode_ci NOT NULL, `project` varchar(20) collate utf8_unicode_ci NOT NULL,`description` varchar(50) collate utf8_unicode_ci NOT NULL,  `type` varchar(29) collate utf8_unicode_ci NOT NULL,  UNIQUE KEY `name` (`name`),  KEY `project` (`project`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
                    statment.executeUpdate(exptable);
                    String restable="CREATE TABLE IF NOT EXISTS `results` (  `name` varchar(20) collate utf8_unicode_ci NOT NULL,  `project` varchar(20) collate utf8_unicode_ci NOT NULL,  `type` enum('exp','diff') collate utf8_unicode_ci NOT NULL,  `date` datetime default NULL,  UNIQUE KEY `name` (`name`),  KEY `project` (`project`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
                    statment.executeUpdate(restable);
                    String insertpro="insert ignore into `seqbuster`.`project` values ('"+namevar+"',NOW())";
                    statment.executeUpdate(insertpro);
                    SeqBusterView.nameproject.addItem(namevar);
                    SeqBusterView.nameproject.repaint();
            //////////////////////////////////////////
                    statment.executeUpdate("use `seqbuster`");
                URL url = null;
                
                probar.setValue(40);
                probar.repaint();
            

        
        //get scriptsfile
        probar.setValue(50);
        probar.repaint();
       
        try {
            BufferedReader in= new BufferedReader(new FileReader("Rscripts/script.sql"));
            
            try {
                
                String l="";
                while ((l=in.readLine())!=null){
                    statment.executeUpdate(l);
                    
                }
                
                
            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }
        


        } catch (Exception ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
                
           
        }
        //seqbuster.R
        probar.setValue(75);
        probar.repaint();
        try {
            //functions scripts
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Rscripts/R/db.R")));
            out.write("hostname<-\""+host+"\"\n");
            out.write("pssw<-\""+pssw+"\"\n");
            out.write("username<-\""+user+"\"\n");
            out.write("dbname<-\"seqbuster\"\n");
            String portid="0";
            if (!port.matches("")){portid=port;}
            out.write("port<-"+portid+"\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //guir scripts
        //functions scripts
        probar.setValue(90);
        probar.repaint();
        
        //guir scripts


       
        //date update
        

            tools.showinfo("SeqBuster database created successfully.");
        }
        statment.close();
        con.close();
        probar.setValue(100);
        probar.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
               
        //get Rscript list and copy Rscripts and save in in R folder
        
        
    }//GEN-LAST:event_CreSQButActionPerformed

    private boolean checkmysql(){
        boolean test=false;
        try
    {
          Class.forName("com.mysql.jdbc.Driver");
     } catch (Exception e)
    {
        e.printStackTrace();
    }
        try {
            String host=HosMysText.getText();
            String user=UseMysText.getText();
            String pssw=PswMysText.getText();
            String port=PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}
            Connection con = DriverManager.getConnection ("jdbc:mysql://"+host+"/mysql",user,pssw);
            
            test=true;
            
            con.close();
//            JOptionPane.showMessageDialog(null,"MySQL is working");
        } catch (SQLException ex) {
            tools.showcerror("To install mysql press HELP button.\n");
            //tools.showerror(ex.getLocalizedMessage(),ex.toString());
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    private boolean checkdb(){
        boolean test=false;
             try
            {
                  Class.forName("com.mysql.jdbc.Driver");
             } catch (Exception e)
            {
                //e.printStackTrace();
                tools.showerror(e.getLocalizedMessage(),e.toString());
            }
        try {
            String host=HosMysText.getText();
            String user=UseMysText.getText();
            String pssw=PswMysText.getText();
            String port=PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}
            Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
            //show tables
            Statement statment = (Statement) con.createStatement();
            ResultSet rs = statment.executeQuery("show tables");
            int track=0;
            int genome=0;
            while (rs.next()){
                //check track
                //check genome
                if (rs.getString(1).matches("track")){
                    track=1;
                }
                if (rs.getString(1).matches("genome")){
                    genome=1;
                }
            }
            if (track==1 & genome==1){
                test=true;
            }
            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            tools.showcerror("The database 'seqbuster' has not been created yet, complete the installation please (Step 2).");
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

        return test;

    }
    private boolean checkR(){
        boolean test=false;
            try
            {
                  Class.forName("com.mysql.jdbc.Driver");
             } catch (Exception e)
            {
                //e.printStackTrace();
                tools.showerror(e.getLocalizedMessage(),e.toString());
            }
        try {
            String host=HosMysText.getText();
            String user=UseMysText.getText();
            String pssw=PswMysText.getText();
            String port=PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}
            Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
            //show tables
            Statement statment = (Statement) con.createStatement();
            ResultSet rs = statment.executeQuery("show tables");
            int scripts=0;

            while (rs.next()){
                //check track
                //check genome
                if (rs.getString(1).matches("scriptsGUI")){
                    scripts=1;
                }

            }
            if (scripts==1 ){
                test=true;
            }
            rs.close();
            statment.close();
            con.close();
            boolean success = (new File("Rscripts")).exists();
            if (!success){
                test=false;
                tools.showcerror("The data for 'miRNA analysis' has not been created yet, complete the installation please (Step 2).");
            }
        } catch (SQLException ex) {
            tools.showcerror("The database 'scripts' has not been created yet, complete the installation please (Step 2).");
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return test;
    }
    private boolean checkmirna(){
         boolean test=false;
            try
            {
                  Class.forName("com.mysql.jdbc.Driver");
             } catch (Exception e)
            {
                e.printStackTrace();
                tools.showerror(e.getLocalizedMessage(),e.toString());

            }
        
            
            boolean smb = (new File("DB/mbhairpinRNA.db")).exists();
            if (!smb ){
                test=false;
                tools.showcerror("The data for 'miRNA detection' has not been created yet, complete the installation please (Step 3).");
            }
        

        return true;
    }

    private void GoButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoButActionPerformed
        // TODO add your handling code here:
        //checking installation
        boolean mysql=false;
        boolean db=false;
        boolean R=false;
        boolean mirna=false;
        mysql=checkmysql();
        if (mysql){
            db=checkdb();
            R=checkR();
            mirna=checkmirna();
            if (mysql==true & db==true & R==true & mirna==true){
                SeqBusterView.pannel.setSelectedIndex(2);
                tools.showinfo("To finish with the configuration, please go to the mysql conf. tab  \nand the preference tab in the top panel");
                try {
                    new File("installed").createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
                this.dispose();
            }else{
                //remove seqbuster.op
                tools.showcerror("Not all elements have been installed\nSeqBuster cannot start\n mysql: "+mysql+"\nSeqBuster database: "+db+"\nRscripts: "+R+"\n miRNA data: "+mirna );

                //seqbusterApp.getApplication().exit();
            }
        }
        
    }//GEN-LAST:event_GoButActionPerformed

    private void CheMysButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheMysButActionPerformed
        // TODO add your handling code here:
        
             try
    {
          Class.forName("com.mysql.jdbc.Driver");
     } catch (Exception e)
    {
        e.printStackTrace();
        tools.showerror(e.getLocalizedMessage(),e.toString());
    }
        try {
            String host=HosMysText.getText();
            String user=UseMysText.getText();
            String pssw=PswMysText.getText();
            String port=PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}

            Connection con =null;
            con=  DriverManager.getConnection("jdbc:mysql://"+host+"/mysql",user,pssw);
            

            con.close();
            check.host=host;
            check.port=port;
            check.passw=pssw;
            check.user=user;
            check.saveparameters("seqbuster.op");
            SeqBusterView.UseMysText.setText(check.user);
            SeqBusterView.HosMysText.setText(check.host);
            SeqBusterView.PswMysText.setText(check.passw);
            SeqBusterView.PorMysText.setText(check.port);
            tools.showinfo("MySQL working perfectly.");
        } catch (SQLException ex) {
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CheMysButActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tools.showcerror("Fill with mysql configuration to connect to your database and install SeqBuster.\n If you have not create any new user when installing mysql put 'root' in the user field \nand leave in blank the password field.");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        tools.showcerror("Select the host where mysql is installed. \nIf you have no idea what it is, put: localhost.");
}//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        tools.showcerror("Put the passw to connect mysql. \nIf you have not created any,leave empty.");
}//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        tools.showcerror("Put the user to connect mysql. \nIf you have not created any, write: root");
}//GEN-LAST:event_jButton17ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        tools.showcerror("Select the port where mysql is installed. \nIf you have no idea what it is, leave empty.");
}//GEN-LAST:event_jButton20ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tools.showcerror("Create the database in mysql to perform any analysis.");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JDialog ins_obj;
        try {
            ins_obj = new InstallFirstRun(this, true);
            ins_obj.setLocation(550, 0);
            ins_obj.setVisible(true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void closebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebutActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closebutActionPerformed
    public void DoRuntime (String cmd) throws IOException {

       if (cmd.matches("")) {
  
         System.exit(-1);
           }

       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       process=null;
       runtime=null;


    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheMysBut;
    private javax.swing.JButton CreSQBut;
    private javax.swing.JButton GoBut;
    private javax.swing.JTextField HosMysText;
    private javax.swing.JTextField PorMysText;
    private javax.swing.JPasswordField PswMysText;
    private javax.swing.JTextField UseMysText;
    private javax.swing.JButton closebut;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JProgressBar probar;
    // End of variables declaration//GEN-END:variables
    
}
