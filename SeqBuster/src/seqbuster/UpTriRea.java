

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

package seqbuster;

import java.io.*;
import java.sql.SQLException;
import java.lang.Math.*;
import javax.swing.*;
import java.lang.Thread.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpTriRea  extends JFrame implements Runnable {

    public void run()
	{
        try {
                    tools check =new tools();
                   String pathout=SeqBusterView.pathout.getText()+"/";
                   String filename;
                   
                   filename=UpTriReaFrame.input.getText();

                   boolean Einput=check.fileexist(filename);
                   if (!Einput){
//                        JOptionPane.showMessageDialog(null,setForeground(Color.RED);
                        tools.showcerror("Error reading: "+filename+", \nAre you sure this file exists?...\n");
//                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
                   }else{
                try {
//                  System.out.println("go to read file\n");
                   int f= checkformat(filename);
                   if (f==1) {
                       readfile(filename, pathout);
                   }else if (f==0) {
                       readfilefasta(filename, pathout);
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(UpTriRea.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
                   }
        }catch (IOException ex){
            tools.showerror(ex.getLocalizedMessage(),ex.toString()); 

        }
    }

    private int checkformat(String namein) throws FileNotFoundException, IOException{
        BufferedReader in= new BufferedReader(new FileReader(namein));
        String l="";
        l=in.readLine();
        if (l.contains(">")){
            return 0;
        }
       
        return 1;
    }
    private int readfile( String namein,String pathout)  throws IOException, SQLException{
       

        JDialog info_obj;
        info_obj = new infoFrame("Processing data");
        Date date = new Date();
        HelpDesk.reload();
        HelpDesk.addinfo("process initiated at: "+date.toString()+"\n");
                   
        Integer mincount=Integer.parseInt(UpTriReaFrame.MinCouUTRText.getText());
        String pathoutvar=SeqBusterView.pathout.getText();
//        boolean infoad;
//        infoad=false;
//        //if (AdapRecFrame.infoad.isSelected()){
          //          infoad=true;
        //}
//        System.out.println("inside read file\n");
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        infoFrame.changemsg("Data recognized as tab file\n");
        try {

        String nameexp=UpTriReaFrame.nameexp.getText();
        //statment.executeUpdate("use "+namepro);
        try{
            String createexp="CREATE TABLE IF NOT EXISTS `"+nameexp+"` ( `id` INT UNSIGNED NOT NULL ,`seq` VARCHAR(50) NOT NULL, `freq` MEDIUMINT UNSIGNED NOT NULL,`len` TINYINT NOT NULL ,`tag` VARCHAR(15) NOT NULL,`chr` VARCHAR(30) NOT NULL,`start` INT UNSIGNED NOT NULL,`end` INT UNSIGNED NOT NULL,`strand` ENUM('-','+') NOT NULL,`name` VARCHAR(30) NOT NULL,`DB` VARCHAR(30) NOT NULL,`sp` VARCHAR(30) NOT NULL,`priority` TINYINT NOT NULL,`trimmed5` VARCHAR(10) NOT NULL,`trimmed3` VARCHAR(40) NOT NULL,`addition3` VARCHAR(10) NOT NULL,`addition5` VARCHAR(40) NOT NULL,`mut` VARCHAR(20) NOT NULL,`amb` TINYINT NOT NULL,`trimmed5ref` VARCHAR(10) NOT NULL,`trimmed3ref` VARCHAR(10) NOT NULL,`infoadap3` VARCHAR(255) NOT NULL,`infoadap5` VARCHAR(255) NOT NULL,`r` DOUBLE (6,3) UNSIGNED  NOT NULL,`cont` DOUBLE (4,1) UNSIGNED NOT NULL,`zs` INT(1) UNSIGNED NOT NULL,index (id),index(freq),index(chr),index(len), PRIMARY KEY   (seq) ) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
            statment.executeUpdate(createexp);
            String addexp="insert into `experiments` values ('"+nameexp+"','"+namepro+"','desc','miRNA')";
            statment.executeUpdate(addexp);
            boolean success = (new File(pathoutvar+"/"+namepro+"/"+nameexp)).mkdir();

        String l;
        String [] col;
        int count=0;
        
        //BufferedWriter out=new BufferedWrited(out);
	BufferedReader in= new BufferedReader(new FileReader(namein));
        
        int countseq=0;
        while ((l=in.readLine())!=null)	{

//           boolean t=tools.seqis(l);
//            System.out.println(t);
            if (l.contains("\t") & tools.seqis(l)){
         	count++;
//                System.out.println(l);
                col = l.split("\t");
                if (!col[0].matches("^[ATNUGC]+$") & count==1){
//                    statment.executeUpdate("drop table `"+nameexp+"`");
//                    statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
                    infoFrame.changemsg("The first column in the line : "+count+"\n is not a sequence: "+col[0]);
//                    break;
                }
            if (count%100000==0){
                infoFrame.changemsg(count+"...\n");
                
            }
	    //System.out.println("ind:"+seqind+" "+col[seqind]);
            if( !(col[0].contains(">")) & col[0].matches("^[ATUGC]+$")) {

            int len=col[0].length();
             String freq=col[1];

            if (Integer.parseInt(freq)>=mincount & col[0].length()<=40 ){
             countseq++;

//             String insertreads="insert ignore into `reads` values ('','"+col[0]+"')";
//             statment.executeUpdate(insertreads);
             String insert="insert into `"+nameexp+"` values ("+countseq+",'"+col[0]+"',"+freq+","+len+",1,'na',0,0,'+','na','na','na',100,'na','na','na','na','na','1','na','na','na','na',0,0,0)";
             statment.executeUpdate(insert);
                }
             }
            }
            }
        in.close();


         con.close();
         infoFrame.changemsg("Adapter recognition and removal DONE.\nGo to the next step\n");

         HelpDesk.addinfo("process initiated at: "+new Date()+"\n");
         HelpDesk.addinfo("Number of sequences added: "+countseq+"\n");
         tools.showinfo("Your have uploaded the data in the project\n");
         } catch (SQLException ex) {
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
            tools.showcerror("Maybe the name already exists");
            statment.executeUpdate("drop table `"+nameexp+"`");
            statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
            
            
        }
         }catch (Exception ex){
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
            tools.showcerror("Some lines in the file are corrupted. The sane information has been keeped.");
         }

        info_obj.dispose();
        Runtime.getRuntime().gc();
        return(1);
	}

    private int readfilefasta( String namein,String pathout)  throws IOException, SQLException{
        

        JDialog info_obj;
        info_obj = new infoFrame("Processing data");

        Date date = new Date();
        HelpDesk.reload();
        HelpDesk.addinfo("process initiated at: "+date.toString()+"\n");
        
        Integer mincount=Integer.parseInt(UpTriReaFrame.MinCouUTRText.getText());
        String pathoutvar=SeqBusterView.pathout.getText();
        boolean infoad;
        infoad=false;
        HashMap<String,Integer> seq = new HashMap<String,Integer>();
        //if (AdapRecFrame.infoad.isSelected()){
          //          infoad=true;
        //}
//        System.out.println("inside read file\n");
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        infoFrame.changemsg("Data recognized as fasta file\n");
        try {

        String nameexp=UpTriReaFrame.nameexp.getText();
        //statment.executeUpdate("use "+namepro);
        try{
            String createexp="CREATE TABLE IF NOT EXISTS `"+nameexp+"` ( `id` INT UNSIGNED NOT NULL ,`seq` VARCHAR(50) NOT NULL, `freq` MEDIUMINT UNSIGNED NOT NULL,`len` TINYINT NOT NULL ,`tag` VARCHAR(15) NOT NULL,`chr` VARCHAR(30) NOT NULL,`start` INT UNSIGNED NOT NULL,`end` INT UNSIGNED NOT NULL,`strand` ENUM('-','+') NOT NULL,`name` VARCHAR(30) NOT NULL,`DB` VARCHAR(30) NOT NULL,`sp` VARCHAR(30) NOT NULL,`priority` TINYINT NOT NULL,`trimmed5` VARCHAR(10) NOT NULL,`trimmed3` VARCHAR(40) NOT NULL,`addition3` VARCHAR(10) NOT NULL,`addition5` VARCHAR(40) NOT NULL,`mut` VARCHAR(20) NOT NULL,`amb` TINYINT NOT NULL,`trimmed5ref` VARCHAR(10) NOT NULL,`trimmed3ref` VARCHAR(10) NOT NULL,`infoadap3` VARCHAR(255) NOT NULL,`infoadap5` VARCHAR(255) NOT NULL,`r` DOUBLE (6,3) UNSIGNED  NOT NULL,`cont` DOUBLE (4,1) UNSIGNED NOT NULL,`zs` INT(1) UNSIGNED NOT NULL,index (id),index(freq),index(chr),index(len), PRIMARY KEY   (seq) ) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
            statment.executeUpdate(createexp);
            String addexp="insert into `experiments` values ('"+nameexp+"','"+namepro+"','desc','miRNA')";
            statment.executeUpdate(addexp);
            boolean success = (new File(pathoutvar+"/"+namepro+"/"+nameexp)).mkdir();

        String l;
        int count=0;

        //BufferedWriter out=new BufferedWrited(out);
	BufferedReader in= new BufferedReader(new FileReader(namein));

        int countseq=0;
        while ((l=in.readLine())!=null)	{
       
         	count++;

                if (count%100000==0){
                    infoFrame.changemsg(count+"...\n");

                }
                
                 if(!(l.contains("N")) & !(l.contains(".")) & !(l.contains(">")) & l.length()<30) {
                     
                 if (!l.matches("^[ATGUC]+$")){
//                    statment.executeUpdate("drop table `"+nameexp+"`");
//                    statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
                    infoFrame.changemsg("The line : "+count+"\n is not a sequence: "+l);
//                    break;
                 }else{
                    int len=l.length();

                   if (seq.containsKey(l)){
                        int ft=seq.get(l);
                        seq.put(l,ft+1);
                   }else{
                       seq.put(l,1);
                   }

                 }
               
                

               }

            }
        in.close();
         for (String s : seq.keySet()) {

             int freq=seq.get(s);
             if (freq>0 & !(s.contains("."))){
    		//System.out.println(s+":"+seq.get(s)+",ad:"+weblogo+" ad5:"+weblogo5 );

             int len=s.length();
             if (seq.get(s)>=mincount){
                 countseq++;
//                 String insertreads="insert ignore into `reads` values ('','"+s+"')";
//                 statment.executeUpdate(insertreads);
                 String insert="insert into `"+nameexp+"` values ("+countseq+",'"+s+"',"+seq.get(s)+","+len+",1,'na',0,0,'+','na','na','na',100,'na','na','na','na','na','1','na','na','0','0',0,0,0)";
                 statment.executeUpdate(insert);
             }
             }
         }
         seq.clear();

	 
         statment.close();
         con.close();
         infoFrame.changemsg("Adapter recognition and removal DONE.\nGo to the next step\n");

         HelpDesk.addinfo("process initiated at: "+new Date()+"\n");
         HelpDesk.addinfo("Number of sequences added: "+countseq+"\n");
         tools.showinfo("Your have uploaded the data in the project\n");
         } catch (SQLException ex) {
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
            statment.executeUpdate("drop table `"+nameexp+"`");
            statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
        }
         }catch (Exception ex){
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
         }

        info_obj.dispose();
        Runtime.getRuntime().gc();
        return(1);
	}


   
}
