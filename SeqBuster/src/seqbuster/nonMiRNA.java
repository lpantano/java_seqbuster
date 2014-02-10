
package seqbuster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class nonMiRNA {
    
    

    public  int startana (JCheckBox ssCheck,JCheckBox CusAlgCheck,
        JComboBox ExpList,JComboBox GenList,JComboBox SpeList,JCheckBox upannfile,JCheckBox clus2opt, JTextField freqtext,JProgressBar probar,JCheckBox addopt, JList tralist,int numexp,infoFrame info_obj) {

        Date date = new Date();
        HelpDesk.reload();
        HelpDesk.addinfo("Processed started at: "+date+"\n");
        HelpDesk.bar.setValue(0);
        HelpDesk.bar.setStringPainted(true);
        int ok=1,addok=0;

        tools.showinfo("This analysis takes normally 10 minutes.\nSee the bar progress situated below to check the state of the process.\nAt the end, a window will appear indicating the next step.");

        String file=null;
        String megablast=SeqBusterView.pathblast.getText()+"/blastn";
        //do fasta file
        String expname = (String)ExpList.getSelectedItem();
        String db = (String)GenList.getSelectedItem();
        String spe = (String)SpeList.getSelectedItem();
        String pathout=SeqBusterView.pathout.getText()+"/";
        String namepro=(String)SeqBusterView.nameproject.getSelectedItem();
        Boolean repeatfirst=false;
        int nf=0;
        boolean success = (new File(pathout+namepro+"/"+expname+"/results_usRNAs")).mkdir();
        probar.setValue(10);
        probar.repaint();

        Connection con = null;
        cluster clu= new cluster();
        int countseq=0;
        if (clus2opt.isSelected()){
        HelpDesk.addinfo("selecting sequences with count>= "+freqtext.getText()+"\n");


        try {
            con =  tools.docon(namepro);
            Statement statment =  con.createStatement();
            ResultSet rs=statment.executeQuery("select `name` from `experiments` where `name` like '"+expname+"clusraw' limit 0,1");
            Boolean doit=true;
            if (rs.next()  &  upannfile.isSelected()){nf=1;}
            rs.last();
            int numr=rs.getRow();
            if (numr>0  &  !upannfile.isSelected()){
                Object[] options = {"Yes","No"};
                repeatfirst=true;
                int n = JOptionPane.showOptionDialog(null,"This table exists, would you like to repeat the analysis?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (n==1){
                   doit=false;
                }else{
                    statment.executeUpdate("drop table if exists `"+expname+"clusraw`");
                    statment.executeUpdate("drop table if exists `"+expname+"clusmap`");
                    statment.executeUpdate("delete from `experiments` where `name` like  '%"+expname+"%' and `description` not like 'desc'");
                    statment.executeUpdate("delete from `experiments` where `name` like  '"+expname+"clusraw'");
                    tools.removefiles("cluster",pathout + namepro + "/" + expname);
                    tools.removefiles("class",pathout + namepro + "/" + expname);
                }
            }
           if (doit==true &  !upannfile.isSelected()){


            String readsamples="select `seq`,`freq` from `"+expname+"` where `DB` like 'na'  and `freq`>="+freqtext.getText();
            rs=statment.executeQuery(readsamples);
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/seqs.fa")));


                 while (rs.next()) {
                     countseq++;
                     out.write(">seq_"+rs.getString(2)+"\n"+rs.getString(1)+"\n");
                 }
                 out.close();
            } catch (IOException ex) {
                Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                tools.writelog(logger, ex);

                ok=0;
            }



        HelpDesk.addinfo("number of sequences selected: "+countseq+"\n");


        info_obj.changemsg("clustering 1, detecting pre-usRNAs\n(100000 seqs=> 6 min)\n");
            try {
                file=pathout + namepro + "/" + expname + "/seqs.fa";
                try {

                    nf=clu.docluster2(file, expname);
                    if (nf==0){
                        tools.showinfo("No clusters have been detected.");
                    }
                    Runtime.getRuntime().gc();
                    HelpDesk.addinfo(nf*300+" clusters detected\n");
                    HelpDesk.addinfo(nf+" files for alignment with 300 seqs each of them\n");
                } catch (FileNotFoundException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                    ok=0;
                } catch (SQLException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                    ok=0;
                }
            } catch (IOException ex) {
                Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                tools.writelog(logger, ex);

                ok=0;
            }

        }
        HelpDesk.bar.setValue(25);
        rs.close();
        statment.close();
        con.close();
        } catch (SQLException ex) {
            Logger logger =Logger.getLogger(nonMiRNA.class.getName());
            tools.writelog(logger, ex);

            ok=0;
        }
            probar.setValue(25);
            probar.repaint();
        }else{
            try {
                con = tools.docon(namepro);
                Statement statment =  con.createStatement();
                ResultSet rs=statment.executeQuery("select `name` from `experiments` where `name` like '"+expname+"clusraw' limit 0,1");
                if (rs.next()){nf=1;}
                rs.close();
                statment.close();
                con.close();
            } catch (SQLException ex) {
                ok=0;
                Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                tools.writelog(logger, ex);

            }
            
        }

        if (nf>0 & clus2opt.isSelected() & ok==1){

            try {
                con = tools.docon(namepro);
                Statement statment =  con.createStatement();
                ResultSet rs=statment.executeQuery("select `name` from `experiments` where `name` like '"+expname+"clusmap' limit 0,1");

                Boolean doit=true;
                if (rs.next() &  !upannfile.isSelected()){
                Object[] options = {"Yes","No"};
                int n = JOptionPane.showOptionDialog(null,"This table exists, would you like to repeat the analysis?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                    if (n == 1) {
                        doit = false;
                    } else {
                        statment.executeUpdate("drop  table if exists `" + expname + "clusmap`");
                        //statment.executeUpdate("drop table `" + expname + "clusmap`");
                        statment.executeUpdate("delete from `experiments` where `name` like  '" + expname + "clusmap'");
                        //delete cluster*,*class files
                    }
                }

                boolean donecusalg=false;

                if (doit==true){
                    if (!CusAlgCheck.isSelected() & !upannfile.isSelected()){
                    
                    info_obj.changemsg("clustering 2, detecting usRNAs... "+"\n");
                    info_obj.changemsg("mapping\n");
                    File dir = new File(pathout + namepro + "/" + expname);
                    Runtime runtime = Runtime.getRuntime();
                    Process process = null;
                    String[] children = dir.list();
                    HelpDesk.addinfo("Mapping sequences against: " + db + "\nnumber of files to map: " + nf+" (300 sequences/20 minutes/2 cores in human)\n");
                    String nucor = JOptionPane.showInputDialog("Number of cores to use in the alignment process: " );
                    if (nucor==null){
                        nucor="1";
                    }
                    for (int i = 0; i < children.length; i++) {


                        if (children[i].matches("cluster[0-9]+.fa")) {
                            //System.out.println(children[i]);
                            info_obj.changemsg("file:"+children[i]+"\n");
                            String cmdmegablast = megablast + " -task megablast -db DB/genome/" + db + "_" + spe + ".db -query " + pathout + namepro + "/" + expname + "/" + children[i] + " -word_size 10 -num_threads "+Integer.parseInt(nucor)+" -evalue 1 -perc_identity 85 -ungapped -max_target_seqs 21 " + " -outfmt 6" + " -out " + pathout + namepro + "/" + expname + "/" + children[i] + ".megablast";
                            try {
                                //DoRuntime(cmdmegablast);

                                process = runtime.exec(cmdmegablast);
                                try {
                                    process.waitFor();
                                } catch (InterruptedException ex) {
                                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                                    tools.writelog(logger, ex);

                                    ok=0;
                                }
                            } catch (IOException ex) {
                                Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                                tools.writelog(logger, ex);

                                ok=0;
                            }


                        }


                    }



                
                info_obj.changemsg("taking best score alignment\n");

                try {
                    int num=clu.parse(file, expname,db,spe);
                    HelpDesk.addinfo("Number of pre-usRNAs mapped: "+num+"\n");

                } catch (FileNotFoundException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                    ok=0;
                } catch (IOException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                    ok=0;
                } catch (SQLException ex) {
                   Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                   ok=0;
                }
                

//////////                    do cluster
               }else{
                    
                    donecusalg = false;
                       //System.out.println("have choose no done cus alg");
                    
                    addopt.setSelected(false);
                    if (upannfile.isSelected()) {
                         //donecusalg = true;
                          addopt.setSelected(true);
                        //System.out.println("have choose yes done cus alg");
                        con =  tools.docon(namepro);
                        rs=statment.executeQuery("select `name` from `experiments` where `name` like '"+expname+"clusraw' limit 0,1");
                        
                        if (rs.next()){
                        
                             try {
                                 //do parse file//go to the corresponding windows
                                   callcusalg();
                             } catch (MalformedURLException ex) {
                                   Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                                    tools.writelog(logger, ex);

                                   ok=0;
                              } catch (IOException ex) {
                                   Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                                   tools.writelog(logger, ex);

                                   ok=0;
                              }
                                donecusalg = true;
                        }else{
                            tools.showcerror("You need to run the analysis with the first option checked\nand select the custom alignment step.");

                        }

                    }else{
                        tools.showinfo("Use this file as the input file for your software alignment:\n"+pathout + namepro + "/" + expname + "/cluster.fa"+"\nWhen you have your output, come back here, skipping the 1st step and \nselect 'Upload the output of the mapping tool'.");
                    }


               }//if CusAlgCheck==false
            try {
                
                rs=statment.executeQuery("select `name` from `experiments` where `name` like '"+expname+"clusmap' limit 0,1");
                if (!rs.next()){donecusalg=false;}
                rs.close();
                
            } catch (SQLException ex) {
                Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                tools.writelog(logger, ex);

                ok=0;
            }
            if((CusAlgCheck.isSelected()==false | donecusalg==true ) & ok==1){

            info_obj.changemsg("clustering pre-uRNAs\n");
            try {
                   int num= clu.clusterpos(expname);
                   HelpDesk.addinfo("Number of usRNAs detected: "+num+"\n");
                } catch (SQLException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                    ok=0;
                }

             info_obj.changemsg("consistency clusters pre-uRNAs\n");
                try {
                    int num=clu.consistency(expname);
                    
                    HelpDesk.addinfo("Number of usRNAs detected with consistency: "+num+"\n");
                    num=clu.consistency2(expname);
                    
                    HelpDesk.addinfo("Number of usRNAs detected with consistency: "+num+"\n");
                    clu.nocons(expname);
                    ssCheck.setSelected(true);
                    addok=1;
                } catch (SQLException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                    ok=0;
                    addok=0;
                }

                    statment.executeUpdate("ALTER TABLE  `"+namepro+"`.`"+expname+"clusmap` ADD  `idu` int NOT NULL DEFAULT  '0' ");
                    try {
                        try {

                            cluster.dobedfile(expname, pathout);
                            HelpDesk.addinfo("Bed file created at"+pathout+namepro+"/"+expname+"/cluster.bed\n");
                        } catch (IOException ex) {
                            Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                            tools.writelog(logger, ex);

                            ok=0;
                        }
                     } catch (SQLException ex) {
                        Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                        tools.writelog(logger, ex);

                        ok=0;
                    }
            }
        }

        rs.close();
        statment.close();
        con.close();

        } catch (SQLException ex) {
          Logger logger =Logger.getLogger(nonMiRNA.class.getName());
          tools.writelog(logger, ex);

          ok=0;
        }

            probar.setValue(75);
            probar.repaint();
            //here do bed file
           //if (addok==1 & repeatfirst==false){numexp++;}
        }else if (nf==0){
            tools.showcerror("No pre-usRNAs selected, the first step should be done and with positive detection.");

        }

        HelpDesk.bar.setValue(50);
        //System.out.println(nf+" "+ok+" "+addopt.isSelected());
        if (addopt.isSelected() & nf>0 & ok==1){

        int[] selectedIx = tralist.getSelectedIndices();
        for (int i=0; i<selectedIx.length; i++) {
                try {
                    Object sel = tralist.getModel().getElementAt(selectedIx[i]);
                    info_obj.changemsg("mapping aginst: " + sel + "\n");
                    info_obj.changemsg("WAIT...\n");
                    //check if this mapping exists
                    con = tools.docon(namepro);
                    Statement statment = con.createStatement();
                    ResultSet rs = statment.executeQuery("select `name` from `experiments` where `name` like '" + expname + sel.toString() + "_" + spe + "' limit 0,1");
                    Boolean doit = true;
                    if (rs.next()) {
                        Object[] options = {"Yes", "No"};
                        int n = JOptionPane.showOptionDialog(null, "This table exists, would you like to repeat the analysis?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (n == 1) {
                            doit = false;
                        } else {
                            statment.executeUpdate("ALTER TABLE `" + namepro + "`.`" + expname + "clusmap` DROP COLUMN `" + sel.toString() + "_" + spe + "`");
                            statment.executeUpdate("ALTER TABLE   `" + namepro + "`.`" + expname + "clusmap` ADD  `" + sel.toString() + "_" + spe + "` varchar( 250 ) NOT NULL DEFAULT  '0' ");
                            statment.executeUpdate("update ignore `" + namepro + "`.`" + expname + "clusmap` set `" + sel.toString() + "_" + spe + "`='0'");
                        }
                    } else {
                        statment.executeUpdate("insert into `experiments` values('" + expname + sel.toString() + "_" + spe + "','" + namepro + "','noshow','user')");
                        statment.executeUpdate("ALTER TABLE  `" + namepro + "`.`" + expname + "clusmap` ADD  `" + sel.toString() + "_" + spe + "` varchar(250) NOT NULL DEFAULT  '0' ");
                    }
                    if (doit == true) {
                        try {
                            clu.mapping(expname, namepro, sel.toString(), spe);
                            try {
                                int num = clu.classify(sel.toString(), expname, namepro, spe);
                                HelpDesk.addinfo("Number of usRNAs classified as " + sel.toString() + " : " + num + "\n");
                            } catch (SQLException ex) {
                                Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                                tools.writelog(logger, ex);

                                ok = 0;
                            }
                        } catch (IOException ex) {
                            Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                            tools.writelog(logger, ex);
                            ok = 0;
                        }
                    }
                    //        infoFrame.infText.setText("calculate the unique id us-RNAs");
                } catch (SQLException ex) {
                    Logger logger =Logger.getLogger(nonMiRNA.class.getName());
                    tools.writelog(logger, ex);

                }
                  
                  //        infoFrame.infText.setText("calculate the unique id us-RNAs");

        }

        probar.setValue(75);
        probar.repaint();

        HelpDesk.bar.setValue(75);

            
        }
        
        //calculate secondary structure and FDR rate
        if (ssCheck.isSelected() & nf>0 & ok==1 ){
            try {
                HelpDesk.addinfo("Calculating secondary structure\n");
                info_obj.changemsg("Doing RNAfold\n");
                if (db==null){
                 tools.showcerror("You need to load the corresponding genome to run RNAfold,"
                 + " thus secondary structure prediction will be ignored.");
                }

                nonMirnaAna.gettable(namepro, expname, db, spe);
                
            
            } catch (SQLException ex) {
                Logger.getLogger(nonMiRNA.class.getName()).log(Level.SEVERE, null, ex);
                Logger logger=Logger.getLogger(nonMiRNA.class.getName());
                tools.writelog(logger, ex);
            }
            try{
                info_obj.changemsg("Doing p-value detection by R");
                nonMirnaAna.pvalue(expname);
            } catch (IOException ex) {
                Logger.getLogger(nonMiRNA.class.getName()).log(Level.SEVERE, null, ex);
                Logger logger=Logger.getLogger(nonMiRNA.class.getName());
                tools.writelog(logger, ex);
            }


        }
        
        try {
            
            con = tools.docon(namepro);
            Statement statment = con.createStatement();
            ResultSet rs=statment.executeQuery("select `name` from `experiments` where `type` like 'usRNA' ");
            numexp=0;
            while (rs.next()) {
                numexp++;
            }
            //System.out.println(numexp+" "+addok+" "+repeatfirst);
                if (addok==1){
                    if (numexp == 1 ) {

                        clu.idunique(expname, namepro);
                        //System.out.println("iduunique");
                    } else {
                        clu.iduniqueadd(expname, namepro);
                    }
                    String msg="Now you can go to usRNA analysis to continue with the characterization,\n";
                    msg=msg+" or go to Manage data to browse yourself usRNAs by selecting the last option in that panel.\n\n";
                    msg=msg+"You can add a new annotation coming back here, selecting only the last step showed on the "
                            + "right side of this panel\n and choosing the new database you want to add to the annotation.";

                    tools.showinfo(msg);
                }
                  } catch (SQLException ex) {
                      Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                      tools.showerror(ex.getLocalizedMessage(),ex.getMessage());
                      ok=0;
            }


        info_obj.dispose();
        HelpDesk.bar.setValue(100);
        probar.setValue(100);
        probar.repaint();
        if (ok==0){
            tools.showcerror("An unexpected error ocurred");
        }else{
            tools.showinfo("Everything is ready.");
        }
        HelpDesk.addinfo("Process finished at: "+new Date()+"\n");

        return numexp;

    }


    private void callcusalg() throws MalformedURLException, IOException{
        CusAlgFrame CusAlg_obj=new CusAlgFrame();
        CusAlg_obj.setVisible(true);
        CusAlg_obj=null;
    }
}
