/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author lpantano
 */
public class updates {

    public static void reinstall (JProgressBar probar){
        
        probar.setValue(0);
        probar.setStringPainted(true);


      
        tools check=new tools();
        check.readparameters("seqbuster.op");

        try {
            String host=check.host;
            String user=check.user;
            String pssw=check.passw;
            String port=check.port;
            if (!check.port.matches("")){host=host+":"+port;}
            Connection con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/mysql",user,pssw);
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
                boolean success_testpro = (new File("test")).mkdir();
                //boolean success_temp = (new File("temp")).mkdir();
                if (!success_testpro ) {
                    tools.showcerror("Error creating test folder, maybe it already exists...");
                }
                probar.setValue(40);
                probar.repaint();
            //download test sample
                    try {
                        url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/test.seq");
                        URLConnection urlConnection = url.openConnection();
                        urlConnection.connect();
                        InputStream input = url.openStream();

                    try {
                        FileWriter fw = new FileWriter("test/test.seq");
                        Reader reader = new InputStreamReader(input);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String l = null;

                        while ((l = bufferedReader.readLine()) != null) {
                            //statment.executeUpdate(l);
                            fw.write(l + "\n");
                        }
                        fw.close();

                    } catch (IOException ex) {
                        Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                        tools.showerror(ex.getLocalizedMessage(),ex.toString());
                    }



                } catch (Exception ex) {
                    Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
                    //////////////////////////////////////////////////////////////////////


        //get scriptsfile
        probar.setValue(50);
        probar.repaint();
        boolean success = (new File("Rscripts")).mkdir();
        if (!success) {
            tools.showcerror("Error creating Rscripts or already exists");
        }
        boolean success1 = (new File("Rscripts/R")).mkdir();
        if (!success1) {
            tools.showcerror("Error creating Rscripts/R or already exists");
        }
        boolean success2 = (new File("Rscripts/GUIR")).mkdir();
        if (!success2) {
            tools.showcerror("Error creating Rscripts/GUIR or already exists");
        }

        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/scriptGUI.sql");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("Rscripts/script.sql");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){
                    statment.executeUpdate(l);
                    fw.write(l);
                }
                fw.close();

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
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/SeqBuster.R");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("Rscripts/SeqBuster.R");
                FileWriter fw2 = new FileWriter("Rscripts/R/db.R");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                fw.write("localhost<-\""+host+"\"\n");
                fw.write("user<-\""+user+"\"\n");
                fw.write("db<-\"seqbuster\"\n");
                fw.write("psswd<-\""+pssw+"\"\n");
                fw2.write("hostname<-\""+host+"\"\n");
                fw2.write("username<-\""+user+"\"\n");
                fw2.write("dbname<-\"seqbuster\"\n");
                fw2.write("pssw<-\""+pssw+"\"\n");
                if (port.matches("")){
                    fw2.write("port<-0\n");
                }else{
                    fw2.write("port<-"+port+"\n");
                }
                while ((l=bufferedReader.readLine())!=null){
                    //statment.executeUpdate(l);
                    fw.write(l+"\n");
                }
                fw.close();
                fw2.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }



        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
        //functions scripts
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/R/list");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {

                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){

                    FileWriter fw = new FileWriter("Rscripts/R/"+l);
                    URL url2 = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/R/"+l);
                    URLConnection urlConnection2 = url2.openConnection();
                    urlConnection2.connect();
                    InputStream input2 = url2.openStream();
                    Reader reader2 = new InputStreamReader(input2);
                    BufferedReader bufferedReader2 = new BufferedReader(reader2);
                    String l2=null;
                    while ((l2=bufferedReader2.readLine())!=null){
                        fw.write(l2+"\n");

                    }
                    fw.close();
                }


            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }




        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }

        //guir scripts
        //functions scripts
        probar.setValue(90);
        probar.repaint();
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/GUIR/list");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {

                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){

                    FileWriter fw = new FileWriter("Rscripts/GUIR/"+l);
                    URL url2 = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/GUIR/"+l);
                    URLConnection urlConnection2 = url2.openConnection();
                    urlConnection2.connect();
                    InputStream input2 = url2.openStream();
                    Reader reader2 = new InputStreamReader(input2);
                    BufferedReader bufferedReader2 = new BufferedReader(reader2);
                    String l2=null;
                    while ((l2=bufferedReader2.readLine())!=null){
                        fw.write(l2+"\n");

                    }
                    fw.close();
                }


            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }

            //guir scripts


        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
        //date update
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/date");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("Rscripts/date");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;

                while ((l=bufferedReader.readLine())!=null){
                    //statment.executeUpdate(l);
                    fw.write(l+"\n");
                }
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }



        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }

            tools.showinfo("SeqBuster database created succefully.");
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



    }

}
