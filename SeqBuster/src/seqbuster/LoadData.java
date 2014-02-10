/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;
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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.*;
import seqbuster.tools.*;
import seqbuster.CusAnnFrame.*;
import java.lang.Math.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lpantano
 */
public class LoadData {

    public void loadmirlikedb (String hairfile,String mirfile,String name){
        //read hairpin, crete coipy file and makedb for megablast and index files (load database and save bin file)

        //read mirfile, create a copy, do megablast, parse, create mirna like table
        //add new tables and files to seqbuster

    }

      public void createindexdb(String namein,String labeldb) throws IOException, SQLException{
      
        String name="";
     
        String [] col;
        long pos=0;
        long len=0;
        int numlist=0,flag=0,lenseq=0;
        String l;
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con = DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment = con.createStatement();

        statment.executeUpdate("create table IF NOT EXISTS `"+labeldb+"DBhairpinRNA` (`id` varchar(30),`pos` int unsigned,`len` int(3) unsigned, key (`id`))");
        statment.executeUpdate("truncate table `"+labeldb+"DBhairpinRNA`");

        statment.executeUpdate("insert into `cusDB` values ('"+labeldb+"','"+new Date()+"')");
        
        HashMap<String,Integer> namelist = new HashMap<String,Integer>();
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("DB/custom/"+labeldb+".index")));
        PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/custom/"+labeldb+".db")));

       BufferedReader in= new BufferedReader(new FileReader(namein));
//        log.append("Reading files: "+filename+"...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        if (flag==1){
            outdb.write("\n");
            len++;
        }
        flag=0;

        while ((l=in.readLine())!=null)
		{

            Pattern header = Pattern.compile("^>.*");
            Matcher fit = header.matcher(l);
            if ((fit.matches())){
                //l.substring(5,l.length());
                //System.out.println("1 reading name "+name+",pos "+pos+" len "+len);
                if (flag==1){
                    //System.out.println("writing in file,flag "+name+" "+pos);
                    out.write(name+"\t"+pos+"\t"+lenseq+"\n");
                    statment.executeUpdate("insert into `"+labeldb+"DBhairpinRNA` values("+name+","+pos+","+lenseq+")");
                    //upload to table mysql 
                    outdb.write("\n");
                    len++;
                }
                flag=1;
                l=l.replace(">", "");
                //System.out.println("flag");
                if (l.contains(" ")){
                    col = l.split(" ");
                    //check only [a-z0-9] no more thant 20 letters
                    name=col[0];
                }else{
                    name=l;
                }

                if (name.length()>20){

                    name=name.substring(1,20);

                }
                if (namelist.containsKey(name)){
                    numlist=namelist.get(name);
                    name=name+"_"+numlist;
                    namelist.put(name,numlist+1);
                }else{
                    namelist.put(name,1);
                }
                outdb.write(">"+name+"\n");
                len+=name.length()+2;
                pos=len;
                lenseq=0;
                //System.out.println("reading name "+name+",pos "+pos+" len "+len);
                //check name exist


            }else{
                //System.out.println("reading seq name "+name+",pos "+pos);
                l=l.replace("U","T");
                l=l.replace("u","t");
                //System.out.println(l.toUpperCase());
                outdb.write(l.toUpperCase());
                len+=l.length();
                lenseq+=l.length();
            }
             //System.out.println("len "+len);

        }
        //System.out.println("writing in file,flag "+name+" "+pos);
        out.write(name+"\t"+pos+"\t"+lenseq+"\n");
        statment.executeUpdate("insert into `"+labeldb+"DBhairpinRNA` values("+name+","+pos+","+lenseq+")");
        in.close();

        out.close();
        outdb.close();

//        log.append("Converting DB in megablast format...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        statment.close();
        con.close();
        
        DoRuntime(SeqBusterView.pathblast.getText()+"/makeblastdb -in DB/custom/"+labeldb+"hairpinRNA.db -dbtype nucl ");
        JOptionPane.showMessageDialog(null,"Custom "+labeldb+" haripin  sequence database created successfully.");
//        log.append("DB loaded.DONE.\n");
//        log.setCaretPosition(log.getText().length() - 1);
    }


    public void createmirtable (String hairpin, String filemir) throws SQLException, IOException{
         String name="";

        String [] col;
        long pos=0;
        long len=0;
        int numlist=0,flag=0,lenseq=0;
        String l;
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment = con.createStatement();

        statment.executeUpdate("create table IF NOT EXISTS `"+hairpin+"miRNA` (`miRNA` varchar(30),`hRNA` varchar(30),`start` int(3) unsigned,`end` int(3) unsigned, index (`miRNA`))");
        statment.executeUpdate("truncate table `"+hairpin+"miRNA`");

        HashMap<String,Integer> namelist = new HashMap<String,Integer>();

       PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/custom/"+hairpin+"mir.db")));

       BufferedReader in= new BufferedReader(new FileReader(filemir));
//        log.append("Reading files: "+filename+"...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        if (flag==1){
            outdb.write("\n");
            len++;
        }
        flag=0;
        TreeMap<String,Integer> seqmapped =new TreeMap<String,Integer>();
        while ((l=in.readLine())!=null){

            Pattern header = Pattern.compile("^>.*");
            Matcher fit = header.matcher(l);
            if ((fit.matches())){
                //l.substring(5,l.length());
                //System.out.println("1 reading name "+name+",pos "+pos+" len "+len);
                if (flag==1){
                    //System.out.println("writing in file,flag "+name+" "+pos);
                    //statment.executeUpdate("insert into `"+labeldb+"DBhairpinRNA` values("+name+","+pos+","+lenseq+")");
                    //upload to table mysql
                    outdb.write("\n");
                    len++;
                }
                flag=1;
                l=l.replace(">", "");
                //System.out.println("flag");
                if (l.contains(" ")){
                    col = l.split(" ");
                    //check only [a-z0-9] no more thant 20 letters
                    name=col[0];
                }else{
                    name=l;
                }

                if (name.length()>20){

                    name=name.substring(1,20);

                }
                if (namelist.containsKey(name)){
                    numlist=namelist.get(name);
                    name=name+"_"+numlist;
                    namelist.put(name,numlist+1);
                }else{
                    namelist.put(name,1);
                }
                outdb.write(">"+name+"\n");
                len+=name.length()+2;
                pos=len;
                lenseq=0;
                //System.out.println("reading name "+name+",pos "+pos+" len "+len);
                //check name exist


            }else{
                //System.out.println("reading seq name "+name+",pos "+pos);
                l=l.replace("U","T");
                l=l.replace("u","t");
                //System.out.println(l.toUpperCase());
                outdb.write(l.toUpperCase());
                len+=l.length();
                lenseq+=l.length();
                seqmapped.put(name, l.length());
            }
             //System.out.println("len "+len);

        }
        outdb.close();
        in.close();
        //megablast
        String megablast=SeqBusterView.pathblast.getText()+"/blastn";
        String cmdmegablast = megablast + " -task megablast -db DB/custom/" + hairpin + ".db -query DB/custom/" +hairpin +"mir.db -word_size 7 -num_threads 2 -evalue 1 -perc_identity 100 " + " -outfmt 6" + " -out DB/custom/" +hairpin + ".megablast";
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        try {
            //DoRuntime(cmdmegablast);

            process = runtime.exec(cmdmegablast);
            try {
                process.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es: " + ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es: " + ex);
        }
        //parse
        int start,end;
        
        int mism,gaps;
         in= new BufferedReader(new FileReader("DB/custom/"+hairpin+".megablast"));
         while ((l=in.readLine())!=null){
            col = l.split("\t");
            start=Integer.parseInt(col[8]);
            end=Integer.parseInt(col[9]);
            mism=Integer.parseInt(col[4]);
            gaps=Integer.parseInt(col[5]);
            len=seqmapped.get(col[0]);
            if (start<end & end-start+1==len & mism==0 & gaps==0){
                //add to table
                statment.executeUpdate("insert into `"+hairpin+"miRNA` values ('"+col[0]+"','"+col[1]+"',"+start+","+end+")");

            }

         }
        in.close();
        //rs.close();
        statment.close();
        con.close();
        JOptionPane.showMessageDialog(null,"Custom "+hairpin+" miRNA  sequence database created successfully.");

    }
    public int loadgenomefile(String namein,String labeldb,String sp) throws IOException, SQLException{
        int ret=0;
        JDialog info_obj;
        info_obj = new infoFrame("Processing data");
//        info_obj.setLocationRelativeTo(this);
        info_obj.setVisible(true);

        String l,dirpath="";
        String name="";
        String namefilter="";
        String formatdb=SeqBusterView.pathblast.getText()+"/makeblastdb";
        String [] col,path;
        long pos=0;
        long len=0;
        int numlist=0,i,flag=0,lenseq=0;

        path=namein.split("\\/");
        namefilter=path[path.length-1];

        for (i=0;i<path.length-1;i++){
            dirpath+=path[i]+"/";
        }
//        System.out.println("dir: "+dirpath);
        infoFrame.infText.append("Creating index database:"+dirpath+labeldb+"_"+sp+".index...\n");
        infoFrame.infText.append("Creating fasta database files:"+dirpath+labeldb+"_"+sp+".db...\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        HashMap<String,Integer> namelist = new HashMap<String,Integer>();
        //PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+labeldb+".index")));
        



        try {
                Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

        String host=SeqBusterView.HosMysText.getText();
        //String db=seqbusterView.loaprotext.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        Connection con = DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment = con.createStatement();
        //check if exists database
        boolean exists = new File("DB/genome/"+labeldb+"_"+sp+".db").exists();
        boolean repeatfirst=true;
        if (exists==true){

          Object[] options = {"Yes","No"};
          repeatfirst=true;
          int n = JOptionPane.showOptionDialog(null,"This table exists, would you like to repeat the analysis?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
          if (n==1){
                   repeatfirst=false;
           }else{
                    statment.executeUpdate("drop table `genome`.`"+labeldb+"_"+sp+"`");
                    statment.executeUpdate("delete from `seqbuster`.`genome` where `name` like  '"+labeldb+"' and `specie` like '"+sp+"'");
           }
        }

       if (repeatfirst==true){
        try{
         PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/genome/"+labeldb+"_"+sp+".db")));
         //statment.executeUpdate("create database `genome`");
         statment.executeUpdate("insert into `seqbuster`.`genome` values('"+labeldb+"','"+sp+"')");


         statment.executeUpdate("use `genome`");
         statment.executeUpdate("create table `"+labeldb+"_"+sp+"` (`name` varchar (20),`pos` int unsigned,`len` int unsigned, primary key(name))");


        BufferedReader in= new BufferedReader(new FileReader(namein));
        infoFrame.infText.append("Reading files: "+namein+"...\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        if (flag==1){
            outdb.write("\n");
            len++;
        }
        flag=0;

        while ((l=in.readLine())!=null)
		{

            Pattern header = Pattern.compile("^>.*");
            Matcher fit = header.matcher(l);
            if ((fit.matches())){
                //l.substring(5,l.length());
                //System.out.println("1 reading name "+name+",pos "+pos+" len "+len);
                if (flag==1){
                    //System.out.println("writing in file,flag "+name+" "+pos);
                    //out.write(name+"\t"+pos+"\t"+lenseq+"\n");
                    statment.executeUpdate("insert into `"+labeldb+"_"+sp+"` values ('"+name+"',"+pos+","+lenseq+")");
                    outdb.write("\n");
                    len++;
                }
                flag=1;
//                System.out.println("flag");
                col = l.split(" ");
                //check only [a-z0-9] no more thant 20 letters
                name=col[0].replace(">", "");
//                System.out.println(name);
                if (col[0].length()>20){

                    name=name.substring(1,20);

                }
//                System.out.println(name);
                if (namelist.containsKey(name)){
                    numlist=namelist.get(name);
                    name=name+"_"+numlist;
                    namelist.put(name,numlist+1);
                }else{
                    namelist.put(name,1);
                }
                outdb.write(">"+name+"\n");
                len+=name.length()+2;
                pos=len;
                lenseq=0;
                //System.out.println("reading name "+name+",pos "+pos+" len "+len);
                //check name exist


            }else{
                //System.out.println("reading seq name "+name+",pos "+pos);
                l=l.replace("U","T");
                l=l.replace("u","t");
                //System.out.println(l.toUpperCase());
                outdb.write(l.toUpperCase());
                len+=l.length();
                lenseq+=l.length();
            }
             //System.out.println("len "+len);

        }
        //System.out.println("writing in file,flag "+name+" "+pos);
        //out.write(name+"\t"+pos+"\t"+lenseq+"\n");
        statment.executeUpdate("insert into `"+labeldb+"_"+sp+"` values ('"+name+"',"+pos+","+lenseq+")");
        //
        in.close();

//        }

        //out.close();
        outdb.close();

        infoFrame.infText.append("Converting DB in megablast format...\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        String cmdmegablast=formatdb+" -in "+"DB/genome/"+labeldb+"_"+sp+".db -dbtype nucl ";
        DoRuntime(cmdmegablast);
        //addign to table genome
        infoFrame.infText.append("DB loaded.DONE.\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        statment.close();
        con.close();
        Integer val=Integer.parseInt(NonMiRNAFrame.NumGenText.getText())+1;
        NonMiRNAFrame.NumGenText.setText(val.toString());
        showinfo();
        ret=1;
        }catch (SQLException ex) {
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
    }
    info_obj.dispose();
    return(ret);
    }

    public int loadgenomedir(String namein,String labeldb,String sp) throws IOException, SQLException{
        int ret=0;
        JDialog info_obj;
        info_obj = new infoFrame("Processing data");
//        info_obj.setLocationRelativeTo(this);
        info_obj.setVisible(true);

        String l,dirpath="";
        String name="";
        String namefilter="";
        String formatdb=SeqBusterView.pathblast.getText()+"/makeblastdb ";
        String [] col,path;
        long pos=0;
        long len=0;
        int numlist=0,i,flag=0,lenseq=0;
        //if (!(new File(formatdb).exists())){
        //tools.showcerror("The program needed for the mapping is not installed or bad configured\n:"+formatdb);

       // }else{
            dirpath=namein+"/";

    //        System.out.println("dir: "+dirpath);
            infoFrame.infText.append("Creating index database:"+dirpath+labeldb+"_"+sp+".index...\n");
            infoFrame.infText.append("Creating fasta database files:"+dirpath+labeldb+"_"+sp+".db...\n");
            infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
            HashMap<String,Integer> namelist = new HashMap<String,Integer>();
            //PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+labeldb+".index")));



            File dir = new File(dirpath);

            String[] children = dir.list();
            try {
                    Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            }

            String host=SeqBusterView.HosMysText.getText();
            //String db=seqbusterView.loaprotext.getText();
            String user=SeqBusterView.UseMysText.getText();
            String pssw=SeqBusterView.PswMysText.getText();
            Connection con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
            Statement statment =  con.createStatement();
            //check if exists database
            boolean exists = new File("DB/genome/"+labeldb+"_"+sp+".db").exists();
            boolean repeatfirst=true;
            if (exists){

              Object[] options = {"Yes","No"};
              repeatfirst=true;
              int n = JOptionPane.showOptionDialog(null,"This table exists "+labeldb+"_"+sp+", would you like to repeat the analysis?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
              if (n==1){
                       repeatfirst=false;
               }else{
                        statment.executeUpdate("drop table if exists `genome`.`"+labeldb+"_"+sp+"`");
                        statment.executeUpdate("delete from `seqbuster`.`genome` where `name` like  '"+labeldb+"' and `specie` like '"+sp+"'");
    //                    System.out.println("removing "+labeldb+"' and `specie` like '"+sp);
               }
            }

           if (repeatfirst==true){
             PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/genome/"+labeldb+"_"+sp+".db")));
            try{
             //statment.executeUpdate("create database `genome`");
             statment.executeUpdate("insert into `seqbuster`.`genome` values('"+labeldb+"','"+sp+"')");


             statment.executeUpdate("use `genome`");
             statment.executeUpdate("create table `"+labeldb+"_"+sp+"` (`name` varchar (21),`pos` int unsigned,`len` int unsigned, primary key(name))");

            //String[] children = dir.list();
    //        System.out.println(namefilter);
            for (i=0; i<children.length; i++) {
                // Get filename of file or directory
                String filename = children[i];

            if (filename.contains(".fa")){

                BufferedReader in= new BufferedReader(new FileReader(dirpath+filename));
                infoFrame.infText.append("Reading files: "+filename+"...\n");
                infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
                if (flag==1){
                    outdb.write("\n");
                    len++;
                }
                flag=0;

                while ((l=in.readLine())!=null)
                        {

                    Pattern header = Pattern.compile("^>.*");
                    Matcher fit = header.matcher(l);
                    if ((fit.matches())){
                        //l.substring(5,l.length());
                        //System.out.println("1 reading name "+name+",pos "+pos+" len "+len);
                        if (flag==1){
                            //System.out.println("writing in file,flag "+name+" "+pos);
                            //out.write(name+"\t"+pos+"\t"+lenseq+"\n");
                            statment.executeUpdate("insert into `"+labeldb+"_"+sp+"` values ('"+name+"',"+pos+","+lenseq+")");
                            outdb.write("\n");
                            len++;
                        }
                        flag=1;
        //                System.out.println("flag");
                        col = l.split(" ");
                        //check only [a-z0-9] no more thant 20 letters
                        name=col[0].replace(">", "");
        //                System.out.println(name);
                        if (col[0].length()>20){

                            name=name.substring(1,20);

                        }
        //                System.out.println(name);
                        if (namelist.containsKey(name)){
                            numlist=namelist.get(name);
                            name=name+"_"+numlist;
                            namelist.put(name,numlist+1);
                        }else{
                            namelist.put(name,1);
                        }
                        outdb.write(">"+name+"\n");
                        len+=name.length()+2;
                        pos=len;
                        lenseq=0;
                        //System.out.println("reading name "+name+",pos "+pos+" len "+len);
                        //check name exist


                    }else{
                        //System.out.println("reading seq name "+name+",pos "+pos);
                        l=l.replace("U","T");
                        l=l.replace("u","t");
                        //System.out.println(l.toUpperCase());
                        outdb.write(l.toUpperCase());
                        len+=l.length();
                        lenseq+=l.length();
                    }
                     //System.out.println("len "+len);

                }
                //System.out.println("writing in file,flag "+name+" "+pos);
                //out.write(name+"\t"+pos+"\t"+lenseq+"\n");
                statment.executeUpdate("insert into `"+labeldb+"_"+sp+"` values ('"+name+"',"+pos+","+lenseq+")");
                //
                in.close();
               }
    //        }
            }
            //out.close();
            outdb.close();
            try{
                infoFrame.infText.append("Converting DB in megablast format...\n");
                infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
                String cmdmegablast=formatdb+" -in "+"DB/genome/"+labeldb+"_"+sp+".db -dbtype nucl ";
                DoRuntime(cmdmegablast);
                //addign to table genome
                infoFrame.infText.append("DB loaded.DONE.\n");
                infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
                statment.close();
                con.close();
                Integer val=Integer.parseInt(NonMiRNAFrame.NumGenText.getText())+1;
                NonMiRNAFrame.NumGenText.setText(val.toString());
                showinfo();
                ret=1;
            }catch (IOException ex){
             tools.showerror(ex.getLocalizedMessage(),ex.toString());
             tools.showinfo("You can only use this genome to get secondary structure of small RNA precursors");
            }
            }catch (SQLException ex) {
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }
       //}
       
    }
       
       info_obj.dispose();
       return(ret);
    }
    public void loadtrackgene (String name,String dbfile,String sp) throws SQLException, FileNotFoundException, IOException{
            //variable definition
        JDialog info_obj;
        info_obj = new infoFrame("Processing data");
        info_obj.setVisible(true);

        HelpDesk.reload();
        HelpDesk.helptext.setText("Processed started at: "+new Date()+"\n");
        HelpDesk.bar.setValue(0);
        HelpDesk.bar.setStringPainted(true);
         try {
                    Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

        String host=SeqBusterView.HosMysText.getText();
        String db=SeqBusterView.loaprotext.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        Connection con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment =  con.createStatement();
        Statement statment2 = con.createStatement();

        String [] col =new String [20];
        String l;
        //end variable definition
        //Ini
        boolean exists = new File("DB/"+name+"gene_"+sp).exists();
        boolean repeatfirst=true;
        if (exists){

          Object[] options = {"Yes","No"};
          repeatfirst=true;
          int n = JOptionPane.showOptionDialog(null,"This table exists "+name+"_"+sp+", would you like to repeat the analysis?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
          if (n==1){
                   repeatfirst=false;
                   //System.out.println("saying no");
           }else{
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"tss_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"gene_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"exon_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"intron_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"ss_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"promoter_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"_"+sp+"_data`");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"gene' and `specie` like '"+sp+"'");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"promoter' and `specie` like '"+sp+"'");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"tss' and `specie` like '"+sp+"'");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"exon' and `specie` like '"+sp+"'");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"intron' and `specie` like '"+sp+"'");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"ss' and `specie` like '"+sp+"'");
                    //System.out.println("removing "+name+"' and `specie` like '"+sp);
           }
        }else{
            //create direcotry
            boolean success = new File("DB/"+name+"gene_"+sp).mkdir();
            success = new File("DB/"+name+"promoter_"+sp).mkdir();
            success = new File("DB/"+name+"tss_"+sp).mkdir();
            success = new File("DB/"+name+"intron_"+sp).mkdir();
            success = new File("DB/"+name+"exon_"+sp).mkdir();
            success = new File("DB/"+name+"ss_"+sp).mkdir();
        }

       if (repeatfirst==true){
         

        try{
            statment.executeUpdate("use `tracks`");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"tss','"+sp+"','1')");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"gene','"+sp+"','1')");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"exon','"+sp+"','1')");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"intron','"+sp+"','1')");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"ss','"+sp+"','1')");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"promoter','"+sp+"','1')");
            statment.executeUpdate("create table `"+name+"_"+sp+"_data` (`chr` varchar (25),`start` int unsigned,`end` int unsigned,`strand` varchar(1),`name` varchar (20),`type` varchar (7), index(`start`,`chr`,`type`))");
            statment.executeUpdate("create table `"+name+"tss_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");
            statment.executeUpdate("create table `"+name+"gene_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");
            statment.executeUpdate("create table `"+name+"exon_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");
            statment.executeUpdate("create table `"+name+"intron_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");
            statment.executeUpdate("create table `"+name+"ss_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");
            statment.executeUpdate("create table `"+name+"promoter_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");
               

         //create table temp data chr, start,end, name , type
         //call function to split in different part:gene, tss, exon, intron, ss
        HelpDesk.helptext.append("Preparing file\n");
        HelpDesk.bar.setValue(15);
        infoFrame.infText.append("Prepating file to be separated in promoter, tss, exon, intron and splice site\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        load2genedata( name,  sp,  140,50,5000,  50,dbfile);
        infoFrame.infText.append("Loading temporal data in database\n");
        HelpDesk.helptext.append("Loading to database\n");
        HelpDesk.bar.setValue(30);
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        statment.executeUpdate("load data local infile 'DB/temp' into table `"+name+"_"+sp+"_data`");

        String [] types = {"gene","promoter","tss","exon","intron","ss"};
        //String [] types = {"intron"};
        ResultSet rs2=null;
        ResultSet rs=null;
        for (String typename:types){
            infoFrame.infText.append("Track: "+typename+" loaded successfully.\nDONE.\n");
            infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
            HelpDesk.bar.setValue(HelpDesk.bar.getValue()+10);
             int clusterflag=0,lastflag=0;
            rs=statment.executeQuery("select `chr` from  `"+name+"_"+sp+"_data` where `type` like '"+typename+"' group by `chr` ");
            ArrayList<String> chrget = new ArrayList<String >();
        
        while(rs.next()){
            chrget.add(rs.getString(1));
        }
        rs.close();
        int lines=0;
        int index=0;
        int limit =100000;
        int i,startind=0,endind=0,strandind=0,chrind=0,nameind=0;

        String namep=null,strp=null,chrp=null,namec=null,chr=null,str=null,chrc=null,na=null,lastchr=null;
        int start,end,startp=0,endp=0,startc=0,endc=0;
      
        TreeMap<Integer,Integer> bloqslist =new TreeMap<Integer,Integer>();
        TreeMap<String,Integer []> nmlist =new TreeMap<String,Integer []>();
        for (int j=0;j<chrget.size();j++){
           rs2=statment.executeQuery("select * from  `"+name+"_"+sp+"_data` where `chr` like  '"+chrget.get(j)+"' and `type` like '"+typename+"' order by `start` asc ");
//           System.out.println(chrget.get(j));
           lines=0;
           PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+name+typename+"_"+sp+"/track_"+name+typename+"_"+sp+"_"+index)));

           bloqslist.clear();
           nmlist.clear();
        while(rs2.next()) {

           start=rs2.getInt(2);
           end=rs2.getInt(3);
           chr=rs2.getString(1);
           str=rs2.getString(4);
           na=rs2.getString(5)+":"+str;
//           System.out.println("data: "+chr+" "+start+" "+end+ " " + str+ " "+na);
//           if (rs2.getString(5).matches("NM_033178")){
//                System.out.println("data: "+chr+" "+start+" "+end+ " " + str+ " "+na+"\n");
//           }
               if (endp < start & clusterflag == 0 & endp>0) {
//            temp.write(chrp+"\t"+startp+"\t"+endp+"\t"+namep+"_"+strp+"\n");
//                   System.out.println("alone "+startp + "\t" + endp + "\t" + namep);
                   outdb.write(startp+"\t"+endp+"\t"+namep+",\n");
                   lines++;
                    if (lines>=limit){
                        lines=0;
//                        System.out.println(chrget.get(j)+",new index for the chromosome,"+index);
                        statment2.executeUpdate("insert into `"+name+typename+"_"+sp+"` values('"+chrget.get(j)+"',"+startp+","+index+")");
                        index++;
                        outdb.close();
//                        System.out.println("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index);
                        outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+name+typename+"_"+sp+"/track_"+name+typename+"_"+sp+"_"+index)));

                    }

                   lastflag=0;
               } else if (endp < start & clusterflag == 1 & endp>0) {
//            escribir cluster
                   clusterflag = 0;
                   namec=null;
                   chrc=null;
                   startc=0;
                   endc=0;
                   lastflag=1;
//                   System.out.println("write cluster");
                   //go through the treemap, and check how of the genes are in the different ranges
                   int flag=0;
                   int poss=0;
                   int pose=0;
                   for (Integer ind : bloqslist.keySet()) {
//                    System.out.println(index);

                    if (flag==1){
                        String genetotal="";
                        pose=ind;
                        for (String gene : nmlist.keySet()){
//                           System.out.println(gene);
                           Integer [] tempos= nmlist.get(gene);
                           if ((poss>=tempos[0] & poss<=tempos[1]) | (pose>=tempos[0] & pose<=tempos[1])){
                                genetotal+=gene+",";
                           }

                        }

//                        System.out.println(poss+"\t"+pose+"\t"+genetotal);
                        outdb.write(poss+"\t"+pose+"\t"+genetotal+"\n");
                        lines++;
                        if (lines>=limit){
                            lines=0;
//                            System.out.println(chrget.get(j)+",new index for the chromosome,"+index);
                           statment2.executeUpdate("insert into `"+name+typename+"_"+sp+"` values('"+chrget.get(j)+"',"+start+","+index+")");
                           index++;
                           outdb.close();
//                            System.out.println("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index);
                            outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+name+typename+"_"+sp+"/track_"+name+typename+"_"+sp+"_"+index)));
                        }


                    }
                    poss=ind;
                    flag=1;
                   }
               } else if (endp > start & clusterflag == 0 & endp>0) {
                   //overlap, create new cluster, define start, end point clusters
                   startc = startp;
                   if (start < startp) {
                       startc = start;
                   }
                   endc = end;
                   if (end < endp) {
                       endc = endp;
                   }
                   namec=na+","+namep;
                   chrc=chr;
                   lastflag=2;
                   bloqslist.clear();
                   nmlist.clear();
//                   System.out.println("create new "+chrc+" "+namec+" "+startc+ " "+ endc);
                   clusterflag=1;
                   bloqslist.put(start,1);
                   bloqslist.put(startp,1);
                   bloqslist.put(end,1);
                   bloqslist.put(endp,1);
                   Integer [] pos = new Integer [2];
                   pos[0]=start;pos[1]=end;
                   nmlist.put(na, pos);
                   pos[0]=startp;pos[1]=endp;
                   nmlist.put(namep, pos);
               } else if (endp > start & clusterflag == 1 & endp>0) {

                   //add new line to cluster
                   startc = start;
                   if (startc > start) {
                       startc = start;
                   }

                   if (endc > end) {
                       endc = end;
                   }
                   namec=namec+","+na;

//                   System.out.println("add new" + chrc+" "+namec+" "+startc+ " "+ endc);
                   bloqslist.put(start,1);
                   bloqslist.put(end,1);
                   Integer [] pos = new Integer [2];
                   pos[0]=start;pos[1]=end;
                   nmlist.put(na, pos);
               }

           //statment.executeUpdate("insert into `"+name+"_"+sp+"_data` values ('"+col[chrind]+"',"+start+","+end+",'"+col[strandind]+"','"+col[nameind]+"')");
           startp=start;
           endp=end;
           namep=na;
           strp=str;
           chrp=chr;
        }
            if (lastflag<2){
       //        System.out.println(startp + "\t" + endp + "\t" + namep+" end\n");
//               System.out.println(chrget.get(j)+"',"+startp+","+index);
               outdb.write(startp+"\t"+endp+"\t"+namep+",\n");
               //statment2.executeUpdate("insert into `"+name+"_"+sp+"` values('"+chrget.get(j)+"',"+startp+","+index+")");
               //index++;

            }else if (lastflag==2) {
                   int flag=0;
                   int poss=0;
                   int pose=0;
                   for (Integer ind : bloqslist.keySet()) {
//                    System.out.println(index);
                    if (flag==1){
                        String genetotal="";
                        pose=ind;
                        for (String gene : nmlist.keySet()){
//                           System.out.println(gene);
                           Integer [] tempos= nmlist.get(gene);
                           if ((poss>=tempos[0] & poss<=tempos[1]) | (pose>=tempos[0] & pose<=tempos[1])){
                                genetotal+=gene+",";
                           }

                        }
//                        System.out.println(poss+"\t"+pose+"\t"+genetotal);
                        outdb.write(poss+"\t"+pose+"\t"+genetotal+"\n");
                        lines++;
//                        System.out.println(chrget.get(j)+","+poss+","+index);

                    }
                    poss=ind;
                    flag=1;
                   }


            }
            statment2.executeUpdate("insert into `"+name+typename+"_"+sp+"` values('"+chrget.get(j)+"',"+startp+","+index+")");
            index++;
            lines=0;
            outdb.close();
            bloqslist.clear();
            nmlist.clear();
//            System.out.println("cerrando cromosoma con indice "+index);
           //add the last index to the table
           lastchr=chr;
            }

            JOptionPane.showMessageDialog(null,"Track: "+name+"::"+typename+" loaded successfully.\nDONE.\n");
            infoFrame.infText.append("Track: "+name+"::"+typename+" loaded successfully.\nDONE.\n");
            infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        }
            rs2.close();
            statment2.close();
            statment.close();
            con.close();

            Integer val=Integer.parseInt(NonMiRNAFrame.NumTraText.getText())+6;
            if (exists & repeatfirst==true){ NonMiRNAFrame.NumTraText.setText(val.toString());}
            showinfo();
        } catch (SQLException ex) {

            tools.showerror(ex.getLocalizedMessage(),ex.toString());
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        HelpDesk.helptext.setText("Processed finished at: "+new Date()+"\n");
        HelpDesk.bar.setValue(100);
        tools.showinfo("The process has finished successfully");
        //end msyql
        info_obj.dispose();


    }
    private void load2genedata(String name, String sp, int tsss,int tsse, int pro, int ss,String file) throws IOException{
          PrintWriter temp=new PrintWriter(new BufferedWriter(new FileWriter("DB/temp")));
          BufferedReader in= new BufferedReader(new FileReader(file));
          String l=null;
          String [] col=null;
          int startind=4;
          int endind=5;
          int  strandind=3;
          int  chrind=2;
          int  nameind=1;
          int start=0,end=0;
          String chr=null,str=null;
          String [] exonstart=null,exonend=null;
          while ((l=in.readLine())!=null){
           col = l.split("\t");
           start=Integer.parseInt(col[startind]);
           end=Integer.parseInt(col[endind]);
           exonstart=col[9].split(",");
           exonend=col[10].split(",");
           temp.write(col[chrind]+"\t"+start+"\t"+end+"\t"+col[strandind]+"\t"+col[nameind]+"\tgene\n");
           int starttss=start-tsss;
           int endtss=start+tsse;
           temp.write(col[chrind]+"\t"+starttss+"\t"+endtss+"\t"+col[strandind]+"\t"+col[nameind]+"\ttss\n");
           int prostart=start-pro;
           temp.write(col[chrind]+"\t"+prostart+"\t"+starttss+"\t"+col[strandind]+"\t"+col[nameind]+"\tpromoter\n");
           int starte=Integer.parseInt(exonstart[0]);
           int ende=Integer.parseInt(exonend[0]);
           temp.write(col[chrind]+"\t"+starte+"\t"+ende+"\t"+col[strandind]+"\t"+col[nameind]+"\texon\n");
           for (int i=1;i<exonstart.length;i++){
            int starti=Integer.parseInt(exonend[i-1]);
            int endi=Integer.parseInt(exonstart[i]);
            temp.write(col[chrind]+"\t"+starti+"\t"+endi+"\t"+col[strandind]+"\t"+col[nameind]+"\tintron\n");
            starte=Integer.parseInt(exonstart[i]);
            ende=Integer.parseInt(exonend[i]);
            temp.write(col[chrind]+"\t"+starte+"\t"+ende+"\t"+col[strandind]+"\t"+col[nameind]+"\texon\n");
            int startss1=Integer.parseInt(exonend[i-1])-ss;
            int endss1=Integer.parseInt(exonend[i-1])+ss;
            temp.write(col[chrind]+"\t"+startss1+"\t"+endss1+"\t"+col[strandind]+"\t"+col[nameind]+"\tss\n");
            int startss2=Integer.parseInt(exonstart[i])-ss;
            int endss2=Integer.parseInt(exonstart[i])+ss;
            temp.write(col[chrind]+"\t"+startss2+"\t"+endss2+"\t"+col[strandind]+"\t"+col[nameind]+"\tss\n");
           }
        }
        temp.close();
        in.close();

    }
    public void loadtrack (String name,String dbfile,String header,String sp,int skip) throws IOException, SQLException{
        int ret=0;
        //variable definition
        JDialog info_obj;
        info_obj = new infoFrame("Processing data");
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();        
        info_obj.setVisible(true);
        HelpDesk.reload();
        HelpDesk.helptext.setText("Processed started at: "+new Date()+"\n");
        HelpDesk.bar.setValue(0);
        HelpDesk.bar.setStringPainted(true);
         try {
                    Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        Connection con = tools.docon("seqbuster");
        Statement statment = (Statement) con.createStatement();
        Statement statment2 = (Statement) con.createStatement();
        int multiloci=0;
        String [] col =new String [20];
        int i,startind=0,endind=0,strandind=0,chrind=0,nameind=0;
        double startt,endt;
        String namep=null,strp=null,chrp=null,namec=null,chr=null,str=null,chrc=null,na=null,lastchr=null;
        int start,end,startp=0,endp=0,startc=0,endc=0;
        String l,strand;
        TreeMap<String,Integer> chrlist =new TreeMap<String,Integer>();
        TreeMap<Integer,Integer> bloqslist =new TreeMap<Integer,Integer>();
        TreeMap<String,Integer []> nmlist =new TreeMap<String,Integer []>();
        boolean exists = new File("DB/"+name+"_"+sp).exists();
        boolean repeatfirst=true;
        //end variable definition
        //////////////////////
        if(LoaNewTraFrame.starttext.getText().matches("")){
            tools.showcerror("You need tu put the column number for the 'start' parameters.");
            repeatfirst=false;
        }else{
            startind=Integer.parseInt(LoaNewTraFrame.starttext.getText())-1;
        }
        if(LoaNewTraFrame.endtext.getText().matches("")){
            tools.showcerror("You need tu put the column number for the 'end' parameters.");
            repeatfirst=false;
        }else{
            endind=Integer.parseInt(LoaNewTraFrame.endtext.getText())-1;
        }
       
       if(LoaNewTraFrame.sttext.getText().matches("")){
        strand="+";
       }else{
           strandind=Integer.parseInt(LoaNewTraFrame.sttext.getText())-1;
           strand=col[strandind];
       }
        if(LoaNewTraFrame.chrtext.getText().matches("")){
            tools.showcerror("You need tu put the column number for the 'chr' parameters.");
            repeatfirst=false;
        }else{
            chrind=Integer.parseInt(LoaNewTraFrame.chrtext.getText())-1;
        }
        if(LoaNewTraFrame.nametext.getText().matches("")){
            tools.showcerror("You need tu put the column number for the 'name' parameters.");
            repeatfirst=false;
        }else{
            nameind=Integer.parseInt(LoaNewTraFrame.nametext.getText())-1;
        }

        ///////////////////////////
        
        if (exists & repeatfirst==true){

          Object[] options = {"Yes","No"};
          repeatfirst=true;
          int n = JOptionPane.showOptionDialog(null,"This table exists "+name+"_"+sp+", would you like to repeat the analysis?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
          if (n==1){
                   repeatfirst=false;
                   //create folder
                   
           }else{
                    //remove folder, create again
                    boolean delDir = deleteDirectory(new File("DB/"+name+"_"+sp));
                    if (delDir == false){
                        JOptionPane.showMessageDialog(null,"Problem detected while deleting the current track to be replaced.\n","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"_"+sp+"`");
                    statment.executeUpdate("drop table if exists `tracks`.`"+name+"_"+sp+"_data`");
                    statment.executeUpdate("delete from `seqbuster`.`track` where `name` like  '"+name+"' and `specie` like '"+sp+"'");
//                    System.out.println("removing "+labeldb+"' and `specie` like '"+sp);

           }
        }

       if (repeatfirst==true){
         BufferedReader in= new BufferedReader(new FileReader(dbfile));
         boolean creDir =new File("DB/"+name+"_"+sp).mkdir();
        if (creDir == false){
            JOptionPane.showMessageDialog(null,"Problem detected while creating the DB, but keep going with the process.\n","Error",JOptionPane.ERROR_MESSAGE);
        }


        try{
            //statment.executeUpdate("create database `"+name+"`");
            statment.executeUpdate("use `tracks`");
            statment.executeUpdate("insert into `seqbuster`.`track` values ('"+name+"','"+sp+"','0')");
            statment.executeUpdate("create table `"+name+"_"+sp+"_data` (`chr` varchar (25),`start` int unsigned,`end` int unsigned,`strand` varchar(1),`name` varchar (20), index(`start`,`chr`))");
            statment.executeUpdate("create table `"+name+"_"+sp+"` (`chr` varchar (25),`start` int unsigned,`index` tinyint unsigned)");

               
            
        
        if(skip==1){
            l=in.readLine();
        }
         infoFrame.infText.append("Preparing file\n");
         infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
         PrintWriter temp=new PrintWriter(new BufferedWriter(new FileWriter("DB/temp")));
          while ((l=in.readLine())!=null){
           col = l.split("\t");
           start=Integer.parseInt(col[startind]);
           end=Integer.parseInt(col[endind]);
           strand=col[strandind];
           temp.write(col[chrind]+"\t"+start+"\t"+end+"\t"+strand+"\t"+col[nameind]+"\n");
//           //statment.executeUpdate("insert into `"+name+"_"+sp+"_data` values ('"+col[chrind]+"',"+start+","+end+",'"+col[strandind]+"','"+col[nameind]+"')");
        }
        temp.close();
//         //in another function
        infoFrame.infText.append("Uploading temporal data to database\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
         HelpDesk.bar.setValue(30);
        statment.executeUpdate("load data local infile 'DB/temp' into table `"+name+"_"+sp+"_data`");

         int clusterflag=0,lastflag=0;
        ResultSet rs=statment.executeQuery("select `chr` from  `"+name+"_"+sp+"_data` group by `chr` ");
        ArrayList<String> chrget = new ArrayList<String >();
        //ResultSet rs=statment.executeQuery("select `chr` from `siRNA`.`fcinfo` where `chr` like 'chr2' group by `chr`");
        while(rs.next()){
            chrget.add(rs.getString(1));
        }

        int lines=0;
        int index=0;
        int limit =100000;
        infoFrame.infText.append("Loading new track\n");
        infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);
        HelpDesk.bar.setValue(70);
        for (int j=0;j<chrget.size();j++){
           ResultSet rs2=statment.executeQuery("select * from  `"+name+"_"+sp+"_data` where `chr` like  '"+chrget.get(j)+"' order by `start` asc ");
//           System.out.println(chrget.get(j));
           lines=0;
           PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index)));
           bloqslist.clear();
           nmlist.clear();
        while(rs2.next()) {
           
           start=rs2.getInt(2);
           end=rs2.getInt(3);
           chr=rs2.getString(1);
           str=rs2.getString(4);
           na=rs2.getString(5)+":"+str;
           
//           System.out.println("data: "+chr+" "+start+" "+end+ " " + str+ " "+na);
//           if (rs2.getString(5).matches("NM_033178")){
//                System.out.println("data: "+chr+" "+start+" "+end+ " " + str+ " "+na+"\n");
//           }
               if (endp < start & clusterflag == 0 & endp>0) {
//            temp.write(chrp+"\t"+startp+"\t"+endp+"\t"+namep+"_"+strp+"\n");
                   //System.out.println(startp + "\t" + endp + "\t" + namep);
                   outdb.write(startp+"\t"+endp+"\t"+namep+",\n");
                   lines++;
                    if (lines>=limit){
                        lines=0;
//                        System.out.println(chrget.get(j)+",new index for the chromosome,"+index);
                        statment2.executeUpdate("insert into `"+name+"_"+sp+"` values('"+chrget.get(j)+"',"+startp+","+index+")");
                        index++;
                        outdb.close();
                        //System.out.println("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index);
                        outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index)));
                        
                    }
                   
                   lastflag=0;
               } else if (endp < start & clusterflag == 1 & endp>0) {
//            escribir cluster
                   clusterflag = 0;
                   namec=null;
                   chrc=null;
                   startc=0;
                   endc=0;
                   lastflag=1;
//                   System.out.println("write cluster");
                   //go through the treemap, and check how of the genes are in the different ranges
                   int flag=0;
                   int poss=0;
                   int pose=0;
                   for (Integer ind : bloqslist.keySet()) {
//                    System.out.println(index);
                    
                    if (flag==1){
                        String genetotal="";
                        pose=ind;
                        for (String gene : nmlist.keySet()){
//                           System.out.println(gene);
                           Integer [] tempos= nmlist.get(gene);
                           if ((poss>=tempos[0] & poss<=tempos[1]) | (pose>=tempos[0] & pose<=tempos[1])){
                                genetotal+=gene+",";
                           }

                        }
 
                        //System.out.println(poss+"\t"+pose+"\t"+genetotal);
                        outdb.write(poss+"\t"+pose+"\t"+genetotal+"\n");
                        lines++;
                        if (lines>=limit){
                            lines=0;
//                            System.out.println(chrget.get(j)+",new index for the chromosome,"+index);
                           statment2.executeUpdate("insert into `"+name+"_"+sp+"` values('"+chrget.get(j)+"',"+start+","+index+")");
                           index++;
                           outdb.close();
                            //System.out.println("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index);
                            outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+name+"_"+sp+"/track_"+name+"_"+sp+"_"+index)));
                        }
                        
                        
                    }
                    poss=ind;
                    flag=1;
                   }
               } else if (endp > start & clusterflag == 0 & endp>0) {
                   //overlap, create new cluster, define start, end point clusters
                   startc = startp;
                   if (start < startp) {
                       startc = start;
                   }
                   endc = end;
                   if (end < endp) {
                       endc = endp;
                   }
                   namec=na+","+namep;
                   
                   chrc=chr;
                   lastflag=2;
                   bloqslist.clear();
                   nmlist.clear();
//                   System.out.println("create new "+chrc+" "+namec+" "+startc+ " "+ endc);
                   clusterflag=1;
                   multiloci=1;
                   bloqslist.put(start,1);
                   bloqslist.put(startp,1);
                   bloqslist.put(end,1);
                   bloqslist.put(endp,1);
                   Integer [] pos = new Integer [2];
                   pos[0]=start;pos[1]=end;
                   nmlist.put(na, pos);
                   pos[0]=startp;pos[1]=endp;
                   nmlist.put(namep, pos);
               } else if (endp > start & clusterflag == 1 & endp>0) {

                   //add new line to cluster
                   startc = start;
                   if (startc > start) {
                       startc = start;
                   }

                   if (endc > end) {
                       endc = end;
                   }
                   namec=namec+","+na;
                   
//                   System.out.println("add new" + chrc+" "+namec+" "+startc+ " "+ endc);
                   bloqslist.put(start,1);
                   bloqslist.put(end,1);
                   Integer [] pos = new Integer [2];
                   pos[0]=start;pos[1]=end;
                   nmlist.put(na, pos);
                   
               }
           
           //statment.executeUpdate("insert into `"+name+"_"+sp+"_data` values ('"+col[chrind]+"',"+start+","+end+",'"+col[strandind]+"','"+col[nameind]+"')");
           startp=start;
           endp=end;
           namep=na;
           strp=str;
           chrp=chr;
        }
            if (lastflag<2){
       //        System.out.println(startp + "\t" + endp + "\t" + namep+" end\n");
     //          System.out.println(chrget.get(j)+"',"+startp+","+index);
               outdb.write(startp+"\t"+endp+"\t"+namep+",\n");
               //statment2.executeUpdate("insert into `"+name+"_"+sp+"` values('"+chrget.get(j)+"',"+startp+","+index+")");
               //index++;

            }else if (lastflag==2) {
                   int flag=0;
                   int poss=0;
                   int pose=0;
                   for (Integer ind : bloqslist.keySet()) {
//                    System.out.println(index);
                    if (flag==1){
                        String genetotal="";
                        pose=ind;
                        for (String gene : nmlist.keySet()){
//                           System.out.println(gene);
                           Integer [] tempos= nmlist.get(gene);
                           if ((poss>=tempos[0] & poss<=tempos[1]) | (pose>=tempos[0] & pose<=tempos[1])){
                                genetotal+=gene+",";
                           }

                        }
                        //System.out.println(poss+"\t"+pose+"\t"+genetotal);
                        outdb.write(poss+"\t"+pose+"\t"+genetotal+"\n");
                        lines++;
                        //System.out.println(chrget.get(j)+","+poss+","+index);

                    }
                    poss=ind;
                    flag=1;
                   }
                   

            }
            statment2.executeUpdate("insert into `"+name+"_"+sp+"` values('"+chrget.get(j)+"',"+startp+","+index+")");
            index++;
            lines=0;
            outdb.close();
            bloqslist.clear();
            nmlist.clear();
//            System.out.println("cerrando cromosoma con indice "+index);
           //add the last index to the table
           lastchr=chr;
        }


            if (multiloci==1){
                statment.executeUpdate("update  `seqbuster`.`track` set `type`= '1' where `name` like '"+name+"' and `specie` like '"+sp+"' ");
            }
            
            infoFrame.infText.append("Track: "+name+" loaded successfully.\nDONE.\n");
            infoFrame.infText.setCaretPosition(infoFrame.infText.getText().length() - 1);

            rs.close();
            statment2.close();
            statment.close();
            con.close();
//            //DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date2 = new Date();
//            System.out.println(date2);
//            //System.out.println("DONE");
            Integer val=Integer.parseInt(NonMiRNAFrame.NumTraText.getText())+1;
            if (exists & repeatfirst==true){NonMiRNAFrame.NumTraText.setText(val.toString());}
            showinfo();
            ret=1;
            tools.showinfo("The process has finished successfully");
        } catch (SQLException ex) {

            tools.showerror(ex.getLocalizedMessage(),ex.toString());
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//
        HelpDesk.helptext.setText("Processed finished at: "+new Date()+"\n");
        HelpDesk.bar.setValue(100);
        
        info_obj.dispose();
        
        
    }


    static public boolean deleteDirectory(File path) {
    if( path.exists() ) {
      File[] files = path.listFiles();
      for(int i=0; i<files.length; i++) {
         if(files[i].isDirectory()) {
           deleteDirectory(files[i]);
         }
         else {
           files[i].delete();
         }
      }
    }
    return( path.delete() );
  }
    
    public void showinfo() throws SQLException{

        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
        Statement statment = (Statement) con.createStatement();

       ResultSet rs=statment.executeQuery("select `specie` from `seqbuster`.`genome`");

        while (rs.next()) {
            NonMiRNAFrame.SpeList.addItem(rs.getString(1));

        }


        //int numtrack=Integer.parseInt(rs.getString(1));
         String readsamples="select * from `seqbuster`.`track`";
//        Vector listtrack=new Vector();
        rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        //int p=tralist.getModel().getSize();
        int p=0;
        NonMiRNAFrame.tralist.removeAll();
        while (rs.next()) {
            model.add(p,rs.getString(1));
            p++;

        }

        NonMiRNAFrame.tralist.setModel(model);
        NonMiRNAFrame.tralist.repaint();


        rs.close();
        statment.close();
        con.close();
    }

    public void DoRuntime (String cmd) throws IOException {

//       if (cmd.matches("")) {
//         System.err.println("Need command to run");
//         System.exit(-1);
//       }
       //System.out.printf("running %s is:",cmd);
       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
//       InputStream is = process.getInputStream();
//       InputStreamReader isr = new InputStreamReader(is);
//       BufferedReader br = new BufferedReader(isr);
//       String line;
//
//       System.out.printf("Output of running %s is:",
//           cmd);
//
//       while ((line = br.readLine()) != null) {
//         System.out.println(line);
//       }

    }

    public String changeNT (String seq){
        //String changeseq;

        seq=seq.replace("U", "T");
        seq=seq.replace("u", "t");


        return seq;
    }


}
