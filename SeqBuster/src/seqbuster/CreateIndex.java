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

package seqbuster;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import seqbuster.tools.*;
import seqbuster.CusAnnFrame.*;
/**
 *
 * @author lo
 */
public class CreateIndex extends JFrame implements Runnable  {

    public void run()
	{
        
//        try {

//            if (seqbusterView.option.matches("createDB")){
//                   int list=0;
//                   tools check =new tools();
//                   String filename,formatdb,labeldb;
//                   filename=CusAnnFrame.dbfastafile.getText();
//                   String pathblast=seqbusterView.pathblast.getText();
//                   String pathout=seqbusterView.pathout.getText()+"/";
//                   formatdb=pathblast+"/makeblastdb";
//                   labeldb=CusAnnFrame.labeldbLOAD.getText();
//                   String pathdb="DB/";
//                   if (CusAnnFrame.listLOAD.isSelected()){
//                    list=1;
//                   }
//                   boolean Einput=check.fileexist(filename);
//                   boolean Eformat=check.fileexist(formatdb);
//                   if (!Einput & list==0){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+filename+", Are you sure this file exists?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else if (!Eformat){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+formatdb+", Are you sure blast is installed here?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else{
//
//                        createindexdb(filename,formatdb,labeldb,list,pathdb,pathout);
//                   }
//            }
//            if (seqbusterView.option.matches("createsmallDB")){
//                tools check =new tools();
//               String filename,filenamedb,megablast;
//               String pathdb="DB/";
//               String pathblast=seqbusterView.pathblast.getText();
//               String pathout=seqbusterView.pathout.getText()+"/";
//               filename=CusAnnFrame.smalldb.getText();
//               filenamedb=pathdb+CusAnnFrame.predb.getText()+".db";
//               megablast=pathblast+"/blastn";
//
//               String labeldb=CusAnnFrame.labelsdbLOAD.getText();
//
//                boolean Einput=check.fileexist(filename);
//                boolean Emegablast=check.fileexist(megablast);
//                boolean Efilenamedb=check.fileexist(filenamedb);
//                if (!Einput){
//
//                    JOptionPane.showMessageDialog(null,"Error reading: "+filename+", Are you sure this file exists?...\n");
////                    seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                }else if (!Emegablast){
//                    JOptionPane.showMessageDialog(null,"Error reading: "+megablast+", Are you sure blast is installed here?...\n");
////                    seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//
//                }else if (!Efilenamedb){
//                    JOptionPane.showMessageDialog(null,"Error reading: "+filenamedb+", Are you sure this DB exists?...\n");
////                    seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//
//                }else{
//                    createsmalldb(filename,filenamedb,megablast,pathdb,labeldb,pathout);
//                }
//            }
//        }catch (IOException ie){
//
//        }
    }

    public void createindexdb(String namein,String formatdb,String labeldb,int list,String pathdb,String pathout) throws IOException{
        String l,dirpath="";
        String name="";
        String namefilter="";
        String [] col,path,pathtemp;
        long pos=0;
        long len=0;
        int numlist=0,i,flag=0,lenseq=0;
        
        path=namein.split("\\/");
        namefilter=path[path.length-1];

        for (i=0;i<path.length-1;i++){
            dirpath+=path[i]+"/";
        }
        //System.out.println("passes");
//        log.append("Creating index database:"+dirpath+labeldb+".index...\n");
//        log.append("Creating fasta database files:"+dirpath+labeldb+".db...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        HashMap<String,Integer> namelist = new HashMap<String,Integer>();
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+labeldb+".index")));
        PrintWriter outdb=new PrintWriter(new BufferedWriter(new FileWriter("DB/"+labeldb+".db")));
        
        
        File dir = new File(dirpath);
        
        String[] children = dir.list();

        //String[] children = dir.list();
        System.out.println(namefilter);
        for (i=0; i<children.length; i++) {
            // Get filename of file or directory
            String filename = children[i];

            Pattern filter = Pattern.compile(namefilter);

            if (list==1){
                filter = Pattern.compile(namefilter+".*");
            }

            Matcher fitfilter = filter.matcher(filename);
            //System.out.println(filename);
        if (fitfilter.matches()){
              //System.out.println("ok");
        

        BufferedReader in= new BufferedReader(new FileReader(dirpath+filename));
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
                    outdb.write("\n");
                    len++;
                }
                flag=1;
                //System.out.println("flag");
                col = l.split(" ");
                //check only [a-z0-9] no more thant 20 letters
                name=col[0].replace(">", "");

                if (col[0].length()>20){
                    
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
        in.close();
        
        }
        }
        out.close();
        outdb.close();
        
//        log.append("Converting DB in megablast format...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        String cmdmegablast=formatdb+" -i "+pathdb+labeldb+".db -p F -o T";
        DoRuntime(cmdmegablast);
//        log.append("DB loaded.DONE.\n");
//        log.setCaretPosition(log.getText().length() - 1);
    }

    public void createsmalldb (String namein,String namedb,String megablast,String pathdb,String labeldb,String pathout) throws IOException{

        String l,cmdmegablast,strand,name;
        name="";
        String locus;
        //double ide;
        String [] col;
        int start,end,len,hit;
        HashMap<String,Integer> seqinfo = new HashMap<String,Integer>();
//        log.append("Creating fasta file of small DB...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathdb+labeldb+".fa")));
        BufferedReader in= new BufferedReader(new FileReader(namein));
        while ((l=in.readLine())!=null)
		{
            Pattern header = Pattern.compile("^>.*");
            Matcher fit = header.matcher(l);
            if ((fit.matches())){
                col = l.split(" ");
                name=col[0].replace(">", "");
            }else{
                l=l.replace("U","T");
                l=l.replace("u","t");
                 out.write(">"+name+"\n"+l.toUpperCase()+"\n");
                 seqinfo.put(name,l.length());
            }
        }

        out.close();
        in.close();
//        log.append("Mapping small DB to the Big DB...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        cmdmegablast=megablast+" -d "+namedb+" -i "+pathdb+labeldb+".fa  -W 9 -e 1 -D 3 -p 100 -o "+pathdb+labeldb+".megablast";
        DoRuntime(cmdmegablast);

        BufferedReader inblast= new BufferedReader(new FileReader(pathdb+labeldb+".megablast"));
        Pattern header = Pattern.compile("^#.*");
         //Matcher fit = header.matcher("# kak");
         //System.out.println(" b: "+fit.matches());
//        log.append("Creating small DB...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        PrintWriter outblast=new PrintWriter(new BufferedWriter(new FileWriter(pathdb+labeldb+".coor")));
        while ((l=inblast.readLine())!=null)
		{
            Matcher fit = header.matcher(l);
            if (!(fit.matches())){
                col = l.split("\t");
                len=seqinfo.get(col[0]);
                start=Integer.parseInt(col[8]);
                end=Integer.parseInt(col[9]);
                hit=Integer.parseInt(col[3]);
                locus=col[1];
                locus=locus.replace("lcl|","");
                strand="+";
                
                if (hit==len & start<end){
                    name=col[0];
                    
                    
                    //System.out.println(name+"\t"+locus+"\t"+strand+"\t"+start+"\t"+end);
                    outblast.write(name+"\t"+locus+"\t"+strand+"\t"+start+"\t"+end+"\n");
                }

            }

        }
        outblast.close();
        inblast.close();
//        log.append("DONE...\n");
//        log.setCaretPosition(log.getText().length() - 1);
    }


    public void DoRuntime (String cmd) throws IOException {

       if (cmd.matches("")) {
         System.err.println("Need command to run");
         System.exit(-1);
       }

       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       InputStream is = process.getInputStream();
       InputStreamReader isr = new InputStreamReader(is);
       BufferedReader br = new BufferedReader(isr);
       String line;

       System.out.printf("Output of running %s is:",
           cmd);

       while ((line = br.readLine()) != null) {
         System.out.println(line);
       }

    }

    public String changeNT (String seq){
        //String changeseq;

        seq=seq.replace("U", "T");
        seq=seq.replace("u", "t");


        return seq;
    }
}
