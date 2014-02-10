

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
import java.util.*;
import java.lang.Math.*;
import javax.swing.*;
import java.lang.Thread.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AdaptorRecognition  extends JFrame implements Runnable {
    String namepro=(String)SeqBusterView.nameproject.getSelectedItem();
    public void run()
	{
        try {
                   tools check =new tools();
                   String pathout=SeqBusterView.pathout.getText()+"/";
                   String filename;
                   String adseq;
                   String header=AdapRecFrame.headerad.getText();
                   int skip=0,fasta=0;
                   Double com=Double.parseDouble(AdapRecFrame.comad.getText());
                   int lenad=Integer.parseInt(AdapRecFrame.lenad.getText());
                   int mmad=Integer.parseInt(AdapRecFrame.mmad.getText());
                   filename=AdapRecFrame.input.getText();
                   //output.setText(filename+".parse");
                   //filenameout=output.getText();
                   //filename="/Users/lo/Documents/test.solexa";
                   adseq=AdapRecFrame.adapterseq.getText();
                   String adseq5=AdapRecFrame.adapter5seq.getText();
                   if (AdapRecFrame.skipad.isSelected()){
                    skip=1;
                   }
                   if (AdapRecFrame.fastaad.isSelected()){
                    fasta=1;
                   }

                   boolean Einput=check.fileexist(filename);
                   if (!Einput){
//                        seqbusterView.log.setForeground(Color.RED);
                        tools.showcerror("Error reading: "+filename+", \nAre you sure this file exists?...\n");
//                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
                   }else{
                try {
                        readfilesimple(filename, pathout, adseq, adseq5, fasta, header, skip, com, lenad, mmad);
                   
                } catch (SQLException ex) {
                    Logger.getLogger(AdaptorRecognition.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
                   }
        }catch (IOException ex){
//            seqbusterView.log.append("Error:...\n"+ie);
//            seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
    }


    public int readfile( String namein,String pathout, String ad,String ad5,int fasta,String header,int skip, Double com,int lenad, int mmad)  throws IOException, SQLException{
       
        
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        JDialog info_obj;
        info_obj = new infoFrame("Processing data");
        HelpDesk.reload();
        Date date = new Date();
        HelpDesk.addinfo("Process initiated at: "+date+"\n");
        HelpDesk.bar.setValue(0);
        boolean infoad;
        infoad=false;
        String nameexp=AdapRecFrame.nameexp.getText();
//        if (AdapRecFrame.infoad.isSelected()){
//                    infoad=true;
//        }
//        System.out.println(infoad);
        Connection con =   tools.docon(namepro);
        Statement statment = con.createStatement();
        try {

        
        statment.executeUpdate("use "+namepro);
        boolean success = (new File(pathout+"/"+namepro+"/"+nameexp)).mkdir();
        try{
            
        
        String l;
        String [] col;
        String  prefix="";
        String sufix="";
        //String  prefix5="";
        String sufix5="";
        String weblogo,weblogo5;
        String [] r;
        int seqind=0,i,count=0,detected=0;
        Double comseq=0.0;
        int [] logo = new int [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int [] logotemp2;
        int [] logotemp;
        int mincount=Integer.parseInt(AdapRecFrame.MinCouARText.getText());
        col = namein.split("\\/");
        String nameannfile=col[col.length-1];
        
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+".ad")));
        //BufferedWriter out=new BufferedWrited(out);
	BufferedReader in= new BufferedReader(new FileReader(namein));
        //String ad;
		//StringBuilder sd=new StringBuilder();
	HashMap<String,Integer> seq = new HashMap<String,Integer>();
        HashMap<String,int []> adaseq = new HashMap<String,int []>();
        HashMap<String,int []> adaseq5 = new HashMap<String,int []>();
	Integer freq;
        Integer [] lendist = new Integer [100];

        infoFrame.changemsg("reading header file in order to localize the sequence column\n");


        col = header.split("-");
        for ( i=0;i<col.length;i++){
            if (col[i].matches("seq")){
                seqind=i;
            }
         }
        HelpDesk.addinfo("Column detected: "+seqind+1+"\n");
        HelpDesk.bar.setValue(25);
        infoFrame.changemsg("column detected: "+(seqind+1)+" \n");
        infoFrame.changemsg("reading file\n");
        if (skip==1){
            l=in.readLine();
        }
        if (fasta==1){
            seqind=0;
        }
	while ((l=in.readLine())!=null)	{
            if (fasta==1){
                l=in.readLine();
                col[0] = l;
                seqind=0;
            }else{

                col = l.split("\t");
                if (count==1){
                    HelpDesk.addinfo("Column detected: "+col[seqind]+"\n");
                }
                if (!l.contains("\t")){
                statment.executeUpdate("drop table `"+nameexp+"`");
                statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
                tools.showcerror("the file is not separated by tab delimeter.");
                break;
                }
            }
            
	    count++;
            if (count%100000==0){
                infoFrame.changemsg(count+"...\n");

            }
	    //System.out.println("ind:"+seqind+" "+col[seqind]);
            if(!(col[seqind].contains("N")) & !(col[seqind].contains(".")) & !(col[seqind].contains(">"))) {
                if (!col[seqind].matches("^[ATGC]+$")){
                    statment.executeUpdate("drop table `"+nameexp+"`");
                    statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
                    tools.showcerror("The first column is not a sequence: "+col[seqind]);
                    break;
                }
            detected=1;
            prefix=col[seqind];
            if (!ad5.matches("")){
                //System.out.println("inside 5 adapter");
                r=getadapter5(col[seqind],ad5,ad5.length(),0,ad5.length(),mmad);

                if (r[0].length()==0){
                    //System.out.println("no detected");
                    detected=0;
                    //if (infoad==true){
                        adaseq5.put(prefix,logo);
                    //}
                }else{
                    prefix=r[0];
                    sufix5=r[1];
                    //System.out.println("detected "+prefix);

                }
            }
            if(detected==1){
            //System.out.println(col[seqind]);
            r=getadapter3(prefix,ad,lenad,15,lenad,mmad);
            prefix=r[0];
            sufix=r[1];

		
		if (prefix.length()==0){
                prefix=col[seqind];
                //System.out.println("seq:"+col[4]);
                //System.out.println("s:"+prefix+",a:"+sufix );

                sufix="";
                
            }
            //else{
            //get adapter infor
            comseq=complex(prefix);
           // System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
            if (comseq>=com){
                //System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
            if (seq.containsKey(prefix)){
                freq=(Integer)seq.get(prefix);
                seq.put(prefix,freq+1);
            }else{
                //System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
                seq.put(prefix,new Integer(1));
            }

            //freq=(Integer)seq.get(prefix);
            //System.out.println("s:"+prefix+"f: "+freq);
            if(ad5.matches("")){
                adaseq5.put(prefix,logo);
            }else{
                        if (adaseq5.containsKey(prefix) & infoad==true){
                           logotemp=(int [])adaseq5.get(prefix);
                           logotemp2=getlogo(logotemp,sufix5,ad5.length());
                           adaseq5.put(prefix,logotemp2);
                        }else if (infoad==true) {

                            int [] newlogo=getlogo(logo,sufix5,ad5.length());
                            //System.out.println("add to 5 s:"+prefix+" ad "+newlogo);
                            adaseq5.put(prefix,newlogo);
                       }else{
                            adaseq5.put(prefix,logo);
                       }

            }
              if (adaseq.containsKey(prefix) & infoad==true){


                   logotemp=(int [])adaseq.get(prefix);
                    logotemp2=getlogo(logotemp,sufix,lenad);
                   adaseq.put(prefix,logotemp2);
                }else if (infoad==true) {
                   // System.out.println("s:"+prefix+"f: "+freq);
                    int [] newlogo=getlogo(logo,sufix,lenad);
                   adaseq.put(prefix,newlogo);
               }else{
                    adaseq.put(prefix,logo);
               }
            }

         }//detected if
         }//nucleotides


		}

	in.close();
        infoFrame.changemsg("Writing file to upload to the SeqBuster Server...\n");
        int countseq=0;
        String createexp="CREATE TABLE `"+nameexp+"` ( `id` INT UNSIGNED NOT NULL ,`seq` VARCHAR(50) NOT NULL, `freq` MEDIUMINT UNSIGNED NOT NULL,`len` TINYINT NOT NULL ,`tag` VARCHAR(15) NOT NULL,`chr` VARCHAR(30) NOT NULL,`start` INT UNSIGNED NOT NULL,`end` INT UNSIGNED NOT NULL,`strand` ENUM('-','+') NOT NULL,`name` VARCHAR(30) NOT NULL,`DB` VARCHAR(30) NOT NULL,`sp` VARCHAR(30) NOT NULL,`priority` TINYINT NOT NULL,`trimmed5` VARCHAR(10) NOT NULL,`trimmed3` VARCHAR(40) NOT NULL,`addition3` VARCHAR(10) NOT NULL,`addition5` VARCHAR(40) NOT NULL,`mut` VARCHAR(20) NOT NULL,`amb` TINYINT NOT NULL,`trimmed5ref` VARCHAR(10) NOT NULL,`trimmed3ref` VARCHAR(10) NOT NULL,`infoadap3` VARCHAR(255) NOT NULL,`infoadap5` VARCHAR(255) NOT NULL,`r` DOUBLE (6,3) UNSIGNED  NOT NULL,`cont` DOUBLE (4,1) UNSIGNED NOT NULL,`zs` INT(1) UNSIGNED NOT NULL,index (id),index(freq),index(chr),index(len), PRIMARY KEY   (seq) ) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        statment.executeUpdate(createexp);
        String addexp="insert into `experiments` values ('"+nameexp+"','"+namepro+"','desc','miRNA')";
        statment.executeUpdate(addexp);

        HelpDesk.addinfo("Writing sequences: "+seq.size()+"\n");

        HelpDesk.bar.setValue(75);

	for (String s : seq.keySet()) {

             freq=seq.get(s);
             if (freq>0 & !(s.contains("."))){
             logo=(int [])adaseq.get(s);
             //System.out.println("s "+s);
             logo=proportion(logo,seq.get(s));
             weblogo=Arrays.toString(logo).replace(",","");
             logo=(int [])adaseq5.get(s);
             //System.out.println("logo5 "+logo);
             logo=proportion(logo,seq.get(s));
             weblogo5=Arrays.toString(logo).replace(",","");
			//System.out.println(s+":"+seq.get(s)+",ad:"+weblogo+" ad5:"+weblogo5 );
             out.write(s+"\t"+seq.get(s)+"\t"+weblogo.substring(1,weblogo.length()-1)+"\t"+weblogo5.substring(1,weblogo5.length()-1)+"\n" );
             int len=s.length();
             if (seq.get(s)>=mincount){
                 countseq++;
                 //String insertreads="insert ignore into `reads` values ('','"+s+"')";
                 //statment.executeUpdate(insertreads);
                 String insert="insert into `"+nameexp+"` values ((select `id` from `reads` where `seq` like '"+s+"' limit 0,1),'"+s+"',"+seq.get(s)+","+len+",1,'na',0,0,'+','na','na','na',100,'na','na','na','na','na','1','na','na','"+weblogo.substring(1,weblogo.length()-1)+"','"+weblogo5.substring(1,weblogo5.length()-1)+"',0,0,0)";
                 statment.executeUpdate(insert);
             }
             }
         }
         seq.clear();
         adaseq5.clear();
         adaseq.clear();
	 out.close();

         infoFrame.changemsg("Adapter recognition and removal DONE.\n");
         tools.showcerror("You can upload this file to SeqBuster Server: "+pathout+namepro+"/"+nameexp+".ad");
         HelpDesk.addinfo("Number of sequences added: "+countseq+"\n");
         HelpDesk.addinfo("Process finished at: "+new Date()+"\n");
         HelpDesk.bar.setValue(100);

         } catch (SQLException ex) {
            tools.showcerror("this experiment already exists, please try with other name.");
//            seqbusterView.log.append("this experiment already exists, please try with other name. "+ex+"\n");
//            seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
        }
         }catch (Exception ie){
            tools.showcerror("Check that you file is in the correct format. \nIf the problem continue send a mail to lorena.pantano@crg.es with this: "+ie);
//            seqbusterView.log.append("Error in the process: "+ie);
            statment.executeUpdate("drop table `"+nameexp+"`");
            statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
//            seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
         }
         statment.close();
         con.close();
         info_obj.dispose();

        return(1);
	}

        public int readfilesimple ( String namein,String pathout, String ad,String ad5,int fasta,String header,int skip, Double com,int lenad, int mmad)  throws IOException, SQLException{

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //CUT ADAPTER TO MINLEN PARAMETER
        ad=ad.substring(0,lenad);
        JDialog info_obj;
        info_obj = new infoFrame("Processing data\n");
        HelpDesk.reload();
        Date date = new Date();
        HelpDesk.addinfo("Process initiated at: "+date+"\n");
        HelpDesk.bar.setValue(0);
        boolean infoad;
        infoad=false;
        String nameexp=AdapRecFrame.nameexp.getText();
        Integer [] lendist = new Integer [100];
        for (int p=0;p<lendist.length;p++){
            lendist[p]=0;
         }
        int comxseq=0;
        int longseq=0;
        int noadseq=0;
//        System.out.println(infoad);
        Connection con =   tools.docon(namepro);
        Statement statment = con.createStatement();

        HashMap<String,Integer> seq = new HashMap<String,Integer>();

        try {
       
        
        boolean success = (new File(pathout+"/"+namepro+"/"+nameexp)).mkdir();
        try{
              statment.executeUpdate("use "+namepro);
            String createexp="CREATE TABLE `"+nameexp+"` ( `id` INT UNSIGNED NOT NULL ,`seq` VARCHAR(50) NOT NULL, `freq` MEDIUMINT UNSIGNED NOT NULL,`len` TINYINT NOT NULL ,`tag` VARCHAR(15) NOT NULL,`chr` VARCHAR(30) NOT NULL,`start` INT UNSIGNED NOT NULL,`end` INT UNSIGNED NOT NULL,`strand` ENUM('-','+') NOT NULL,`name` VARCHAR(30) NOT NULL,`DB` VARCHAR(30) NOT NULL,`sp` VARCHAR(30) NOT NULL,`priority` TINYINT NOT NULL,`trimmed5` VARCHAR(10) NOT NULL,`trimmed3` VARCHAR(40) NOT NULL,`addition3` VARCHAR(10) NOT NULL,`addition5` VARCHAR(40) NOT NULL,`mut` VARCHAR(20) NOT NULL,`amb` TINYINT NOT NULL,`trimmed5ref` VARCHAR(10) NOT NULL,`trimmed3ref` VARCHAR(10) NOT NULL,`infoadap3` VARCHAR(255) NOT NULL,`infoadap5` VARCHAR(255) NOT NULL,`r` DOUBLE (6,3) UNSIGNED  NOT NULL,`cont` DOUBLE (4,1) UNSIGNED NOT NULL,`zs` INT(1) UNSIGNED NOT NULL,index (id),index(freq),index(chr),index(len), PRIMARY KEY   (seq) ) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
            statment.executeUpdate(createexp);
            String addexp="insert into `experiments` values ('"+nameexp+"','"+namepro+"','desc','miRNA')";
            statment.executeUpdate(addexp);

        String l;
        String [] col;
        String  prefix="";
        String sufix="";
        //String  prefix5="";
        String sufix5="";
        
        String [] r;
        int seqind=0,i,count=0,detected=0;
        Double comseq=0.0;
        
        int mincount=Integer.parseInt(AdapRecFrame.MinCouARText.getText());
        col = namein.split("\\/");
        String nameannfile=col[col.length-1];
        PrintWriter outinfo=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/"+nameexp+".info")));
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/"+nameexp+".ad")));
        PrintWriter outcom=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/"+nameexp+".complex")));
        PrintWriter outnoad=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/"+nameexp+".noad")));
        PrintWriter outlong=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/"+nameexp+".longer")));
	BufferedReader in= new BufferedReader(new FileReader(namein));
        //String ad;
		//StringBuilder sd=new StringBuilder();
     
	Integer freq,totalseq=0;

        infoFrame.changemsg("reading header file in order to localize the sequence column\n");


        col = header.split("-");
        for ( i=0;i<col.length;i++){
            if (col[i].matches("seq")){
                seqind=i;
            }
         }
        HelpDesk.addinfo("column detected: "+(seqind+1)+" \n");
        HelpDesk.bar.setValue(25);
//        log.append("column detected: "+(seqind+1)+" \n");
//        log.append("reading file\n");
        if (skip==1){
            l=in.readLine();
        }
        if (fasta==1){
            seqind=0;
        }
	while ((l=in.readLine())!=null)	{
            if (fasta==1){
                l=in.readLine();
                col[0] = l;
                seqind=0;
            }else{

                col = l.split("\t");
                if (count==1){
                    HelpDesk.addinfo("column detected: "+col[seqind]+" \n");
//                     HelpDesk.bar.setValue(0);
                }
                if (!l.contains("\t")){
                statment.executeUpdate("drop table `"+nameexp+"`");
                statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
                tools.showcerror("the file is not separated by tab delimeter.");
                break;
                }
            }
	    count++;
            if (count%100000==0){
                infoFrame.changemsg("lines: "+count+" -> detected "+totalseq+" ...\n");

            }
	    //System.out.println("ind:"+seqind+" "+col[seqind]);
            if(col[seqind].matches("^[ATGUC]+$") & !(col[seqind].contains("N")) & !(col[seqind].contains(".")) & !(col[seqind].contains(">") )) {
//                if (!col[seqind].matches("^[ATGC]+$")){
//                    statment.executeUpdate("drop table `"+nameexp+"`");
//                    statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
//                    tools.showcerror("The sequence column is not a sequence: "+col[seqind]);
//                    break;
//                }
            totalseq++;
            detected=1;
            prefix=col[seqind];
            if (!ad5.matches("")){
                //System.out.println("inside 5 adapter");
                r=getadapter5(col[seqind],ad5,ad5.length(),0,ad5.length(),mmad);

                if (r[0].length()==0){
                    //System.out.println("no detected");
                    detected=0;
                    //if (infoad==true){
                    
                    //}
                }else{
                    prefix=r[0];
                   
                    //System.out.println("detected "+prefix);

                }
            }
            if(detected==1){
            //System.out.println(col[seqind]);
            r=getadapter3(prefix,ad,lenad,0,lenad,mmad);
            prefix=r[0];
            sufix=r[1];


		if (prefix.length()==0){
                prefix=col[seqind];
                //System.out.println("seq:"+col[4]);
                //System.out.println("s:"+prefix+",a:"+sufix );

                sufix="";

                }
            //else{
            //get adapter infor
            comseq=complex(prefix);
            
           // System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
            if (comseq>=com & prefix.length()<col[seqind].length()){
                lendist[prefix.length()]=lendist[prefix.length()]+1;
                if (seq.containsKey(prefix)){
                    int ft=seq.get(prefix);
                    seq.put(prefix,ft+1);
               }else{
                   seq.put(prefix,1);
               }

            }else if (comseq<com){
                //save more complex sequences
                outcom.write(prefix+"\n");
                comxseq=comxseq+1;
            }else if (prefix.length()==col[seqind].length()){
                //print no adapter removal
                outnoad.write(prefix+"\n");
                noadseq=noadseq+1;
            }

         }//detected if
         }

        }

	in.close();

        infoFrame.changemsg("Writing file to upload to the SeqBuster Server...\n");
        HelpDesk.addinfo("Writing sequences: "+seq.size()+" \n");
        int countseq=0,smallseq=0,rnaseq=0;
        for (String s : seq.keySet()) {

             freq=seq.get(s);
             if (freq>0 & !(s.contains("."))){
                    //System.out.println(s+":"+seq.get(s)+",ad:"+weblogo+" ad5:"+weblogo5 );
                 
                 int len=s.length();
                 
                 if (seq.get(s)>=mincount & len>=15 & len<=40 ){
                     countseq++;
                    // String insertreads="insert ignore into `reads` values ('','"+s+"')";
                     //statment.executeUpdate(insertreads);
                     rnaseq=rnaseq+seq.get(s);
                     out.write(s+"\t"+seq.get(s)+"\n");
                     String insert="insert into `"+nameexp+"` values ("+countseq+",'"+s+"',"+seq.get(s)+","+len+",1,'na',0,0,'+','na','na','na',100,'na','na','na','na','na','1','na','na','0','0',0,0,0)";
                     statment.executeUpdate(insert);
                 }else if(len>40){
                    //save longer sequences
                     outlong.write(s+"\t"+seq.get(s)+"\t");
                     longseq=longseq+seq.get(s);
                 }else if(len<15){
                    smallseq=smallseq+seq.get(s);
                 }
             }
         }
         seq.clear();
         outinfo.write("Number of sequences read: "+totalseq+"\n");
         outinfo.write("Number of sequences with adapter removal at positions 0 to 14: "+smallseq+"\n");
	 outinfo.write("Number of sequences with adapter removal at positions 15 to 39: "+rnaseq+"\n");
         outinfo.write("These sequences are in the file: "+pathout+namepro+"/"+nameexp+"/"+nameexp+".ad"+"\n");
         outinfo.write("Number of sequences longer than 40 and with adapter "+longseq+"\n");
         outinfo.write("These sequences are in the file: "+pathout+namepro+"/"+nameexp+"/"+nameexp+".longer"+"\n");
         outinfo.write("Number of sequences without adapter "+noadseq+"\n");
         outinfo.write("These sequences are in the file: "+pathout+namepro+"/"+nameexp+"/"+nameexp+".noad"+"\n");
         outinfo.write("Number of sequences with high complexity (AAAAAA) "+comxseq+"\n");
         outinfo.write("These sequences are in the file: "+pathout+namepro+"/"+nameexp+"/"+nameexp+".complex"+"\n");
         outinfo.write("Distribution of the adapter position in sequences:\n\n");
         for (int p=1;p<=50;p++){
            outinfo.write("Position: "+p+" number of sequence: "+lendist[p]+"\n");
         }
         outinfo.close();

         out.close();
         outnoad.close();
         outlong.close();
         outcom.close();
         
        HelpDesk.bar.setValue(75);
       
         infoFrame.changemsg("Adapter recognition and removal DONE.\n");
         HelpDesk.addinfo("Number of sequences added: "+countseq+"\n");
         HelpDesk.addinfo("A complete report is in: "+pathout+namepro+"/"+nameexp+"/"+nameexp+".info");
         HelpDesk.addinfo("Process finished at: "+new Date()+"\n");
         HelpDesk.bar.setValue(100);
         tools.showinfo("You can upload this file to SeqBuster Server: "+pathout+namepro+"/"+nameexp+"/"+nameexp+".ad");
         info_obj.dispose();
         ShowInfoFile showsummary = new ShowInfoFile();
         showsummary.setinfo(pathout+namepro+"/"+nameexp+"/"+nameexp+".info");
         } catch (SQLException ex) {
             
             tools.showerror(ex.getLocalizedMessage(),ex.toString());
             statment.executeUpdate("drop table `"+nameexp+"`");
             statment.executeUpdate("delete from `experiments` where `name` like '"+nameexp+"'");
//            seqbusterView.log.append("this experiment already exists, please try with other name: "+ex+"\n");
//            seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
        }
         }catch (Exception ex){
            tools.showcerror("Check that you file is in the correct format. \n");
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
//            seqbusterView.log.append("Error in the process: "+ie+",Is it a fasta file?...\n");
//            seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
            
         }
        statment.close();
        con.close();
        info_obj.dispose();
        return(1);
	}

    public Double complex (String seq){
        Double com=0.0;
        Double len=seq.length()*1.0;
        String [] a=seq.split("A");
        int lena=a.length-1;
        Double ca=lena/len;
        String [] t=seq.split("T");
        int lent=t.length-1;
        Double ct=lent/len;
        String [] c=seq.split("C");
        int lenc=c.length-1;
        Double cc=lenc/len;
        String [] g=seq.split("G");
        int leng=g.length-1;
        Double cg=leng/len;
        Double comvalue=(1.0-ca-ct-cc-cg);
        if (comvalue==0){
            com=999.0;
        }else{
            com=Math.abs(Math.log(comvalue));
        }
        //System.out.println(seq+" "+com);
        return (com);

    }

    public int [] proportion (int [] ar,double f){
        int [] newar=new int [40];
        int i;
        double temp;

        for (i=0;i<40;i++){
            temp=ar[i]/f*100;
            newar[i]=(int)temp;
            //System.out.println("f:"+f+",old:"+ar[i]+",new:"+newar[i]+",temp"+temp);

        }


        return newar;

    }
    public int [] getlogo (int oldlog[],String ad,int lenad){

        int log []=new int[40];
        String [] adNT=ad.split("");
        int i=0;
        //String temp =Arrays.toString(oldlog).replace(","," ");
        //System.out.println("seq:"+ad+" oldlogo");
        if (ad.length()>0){
        for (i=0;i<10;i++){
            log[i]=oldlog[i];
            log[i+10*1]=oldlog[i+10*1];
            log[i+10*2]=oldlog[i+10*2];
            log[i+10*3]=oldlog[i+10*3];
            //System.out.println("nt:"+adNT[i]+",i:"+i+",log:"+oldlog[i+10*2]);
            if (adNT[i+1].matches("A")){
                log[i]=oldlog[i]+1;
                //System.out.println(i+" A "+log[i]);
            }else if(adNT[i+1].matches("T")){
                log[i+10*1]=oldlog[i+10*1]+1;
                //System.out.println(i+" T "+log[i+10*1]);
            }else if(adNT[i+1].matches("C")){
                log[i+10*2]=oldlog[i+10*2]+1;
                //System.out.println(i+" C "+log[i+10*2]);
            }else if(adNT[i+1].matches("G")){
                log[i+10*3]=oldlog[i+10*3]+1;
                //System.out.println(i+" G "+log[i+10*3]);
            }

        }
        }
        String temp =Arrays.toString(log).replace(","," ");
        //System.out.println("seq:"+ad+" newlogo"+temp);
        return log;
    }
    public void stop () throws InterruptedException{
        Thread.sleep(10000);

    }
    public String[] getadapter3(String seq,String ad,int minad,int minseq,int lenad, int mmad){
        String result[]=new String[2];
        int cutsc=lenad-mmad;
        String seqwad="";
        String candidate="";
        String finalcandidate="";
        int i;
        int sc=0;
        int bestsc;
        bestsc=0;
        if (seq.contains(ad)){
            int s=seq.indexOf(ad);
            result[0]=seq.substring(0,s);
            result[1]=seq.substring(s,seq.length());
        }else{
            //System.out.println("minseq:"+minseq+",minad:"+minad+",lene"+seq.length());
            for (i=minseq;i<=seq.length()-minad;i++){
                //substring candidate adapter
                candidate=seq.substring(i, seq.length());
                //System.out.println(i+":"+candidate);
                sc=alignment(candidate,ad,mmad);

                if (sc>bestsc){
                    bestsc=sc;
                    seqwad=seq.replace(candidate,"");
                    finalcandidate=candidate;
                }

            }
            if (bestsc>=cutsc){
                //System.out.println("sc:"+bestsc+",seq:"+seqwad);
                result[0]=seqwad;
                result[1]=finalcandidate;
            }else{
                result[0]="";
                result[1]="";
            }
        }
        //System.out.println("cutsc :"+cutsc+" scorem:"+sc+" best"+bestsc+"  candidate "+seqwad+" finalcandidate "+finalcandidate);

        //System.out.println("score:"+sc);
        return result;

    }
public String[] getadapter5(String seq,String ad,int minad,int minseq,int lenad, int mmad){
        String result[]=new String[2];
        String seqwad="";
        int cutsc=lenad-mmad;
        String candidate="";
        String finalcandidate="";
        int i;
        int sc=0;
        int bestsc;
        bestsc=0;
        //System.out.println("minseq:"+minseq+",minad:"+minad+",lene"+seq.length());
        //for (i=0;i<=minad-1;i++){
            //substring candidate adapter
            candidate=seq.substring(0, minad);
            //System.out.println("seq:"+candidate+" ad: "+ad);
            sc=alignment(candidate,ad,mmad);

            //if (sc>bestsc){
                bestsc=sc;

                seqwad=seq.substring(minad,seq.length());
                finalcandidate=candidate;
            //}
            //if (sc==bestsc){
             //   seqwad="";
               // break;
            //}
            //System.out.println("sc:"+sc);
        //}
        if (bestsc>=cutsc){
            //System.out.println("sc:"+bestsc+",seq:"+seqwad);
            result[0]=seqwad;
            result[1]=finalcandidate;
        }else{
            result[0]="";
            result[1]="";
        }
        //System.out.println("cutsc :"+cutsc+" scorem:"+sc+" best"+bestsc+"  candidate "+seqwad+" finalcandidate "+finalcandidate);

        //System.out.println("score:"+sc+" seq: "+result[0]);
        return result;

    }
    public int substitution (String a,String b,int penalty,int reward){
        int score=penalty;
        if (a.matches(b)){
            score=reward;
        }
        return score;

    }

    public int maxvalue (int a,int b,int c){
        int score=a;
        if (a<b){
            score=b;
        }
        if (score<c){
            score=c;
        }


        return score;
    }
    public int minvalue (int a,int b){
        int score=a;
        if (a>b){
            score=b;
        }



        return score;
    }

     public int alignment (String seq,String ad,int mmad){
        int score=0;
        int i=0;

        int g;
        g=-3;
        int mm=0;
        int pe=0;
        int re=1;
        String [] seqNT=seq.split("");
        String [] adNT=ad.split("");
        int minlen;
        minlen=minvalue(seq.length(),ad.length());
        int seql=minlen+1;
        int adl=minlen+1;

        for (i=1;i<=minlen;i++){
            //System.out.println(i+" "+seqNT[i]+" "+adNT[i]);
            int sc=substitution(seqNT[i],adNT[i],pe,re);
            if (sc==0){
                mm++;
            }
            score+=sc;
            if (i>score-mmad){
                score=0;
                i=minlen+10;
            }
        }
        //System.out.println(score+" "+seq+" "+ad);


        return score;
     }


    public int alignment2(String seq,String ad){

        int i=0;
        int j=0;
        int score1;
        int score2;
        int score3;
        int score;
        //int scoreup;
        //int scoreleft;
        //int scorediag;
       // int finalscore;
        int g;
        g=-3;
        int mm;
        int subs;
        mm=3;
        int ms=-1;
        String [] seqNT=seq.split("");
        String [] adNT=ad.split("");
        int minlen;
        minlen=minvalue(seq.length(),ad.length());
        int seql=minlen+1;
        int adl=minlen+1;
        int F[][]=new int[seql][adl];

        for (i=0;i<seql;i++){
            //i//ni matrix
               //System.out.println("i:"+i+",g:"+i*g);
               F[i][0]=i*g;
        }
        for (j=0;j<adl;j++){
            //ini matrix
               F[0][j]=j*g;
        }
        //System.out.println("before:"+F[0][0]);
        //System.out.println("1:"+seqNT[0]+",2:"+adNT[0]);
        for (i=1;i<seql;i++){
            for (j=1;j<adl;j++){
            //ini matrix

                subs=substitution(seqNT[i],adNT[j],ms,mm);
                score1=F[i-1][j-1]+subs;
                score2=F[i-1][j]+g;
                score3=F[i][j-1]+g;
                F[i][j]=maxvalue(score1,score2,score3);
                //System.out.println("before:"+F[i-1][j-1]+"gap:"+score2+",gap2:"+score3+",subs:"+score1+",i:"+i+"j,:"+j);
            }
        }
            //calculae score
        i=seql;
        j=adl;
        score=F[i-1][j-1];
        //System.out.println("ad:"+ad+",seq:"+seq+",sc:"+score+",min:"+minlen);


        return score;
    }


}
