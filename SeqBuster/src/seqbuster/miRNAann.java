
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



import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class miRNAann  extends JFrame  {

       String namepro=SeqBusterView.loaprotext.getText();
       final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       public void miRNAann(final String tdb) throws SQLException, IOException{

                HelpDesk.bar.setValue(0);
                HelpDesk.reload();
                HelpDesk.addinfo("Alignment initiated at: "+new Date()+"\nWait...\n");
                Runnable view = new Runnable() {

                public void run() {
               
                    try {
                        int numfiles=doinput();
                        //int numfiles=0;
                        //alignment(tdb,numfiles);
                        HelpDesk.bar.setValue(50);
                        //HelpDesk.addinfo("Annotation finished at: "+new Date()+"\n");
                        
                        JDialog info_obj;
                        String pathout=SeqBusterView.pathout.getText()+"/";
                        String nameexp = (String)MiRNAannFrame.nameexp.getSelectedItem();
                        int mm=Integer.parseInt(MiRNAannFrame.MisMirAnnText.getText());
                        int add=Integer.parseInt(MiRNAannFrame.AddMirAnnText.getText());
                        int tri=Integer.parseInt(MiRNAannFrame.TriMirAnnText.getText());
                        String sp=(String)MiRNAannFrame.speMirAnnList.getSelectedItem();
                        info_obj = new infoFrame("Processing data\n");
                        int ann=0;
                        if (tdb.matches("mb") & mm<=1 & add<=3 & tri<=3 & sp.length()>=2){
                            ann=map.readseq(pathout+namepro+"/"+nameexp+"/input.fa","DB/mbhairpinRNA.db","DB/miRNA.str");
                            //ann=map.readseq("temp/test.fa","DB/mbhairpinRNA.db","DB/miRNA.str");
                        }else{
                            tools.showinfo("Please, change the parameters.");
                        }
                        //for (int i=1;i<=numfiles;i++){
                        //    parse(tdb,"blast"+i+".fa","blast"+i+".megablast");
                        //}
                         info_obj.dispose();
                         HelpDesk.addinfo("Parameters saved at: "+pathout+namepro+"/"+nameexp+"/input.fa.mirna.opt\n");
                         HelpDesk.addinfo("Number of sequences annotated: "+ann+"\n");
                         HelpDesk.addinfo("File with the annotation saved at: "+pathout+namepro+"/"+nameexp+"/input.fa.mirna.ann\n");

                    } catch (IOException ex) {
                        Logger.getLogger(miRNAann.class.getName()).log(Level.SEVERE, null, ex);
                        Logger logger = Logger.getLogger(miRNAann.class.getName());
                        tools.writelog(logger, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(miRNAann.class.getName()).log(Level.SEVERE, null, ex);
                        Logger logger = Logger.getLogger(miRNAann.class.getName());
                        tools.writelog(logger, ex);
                   } 
                   
               

                }
                };
                Thread nThread2 = new Thread(view);
                nThread2.start();
                
//                setCursor(Cursor.getPredefinedCursor(Cursor.CUSTOM_CURSOR));
//                info.setVisible(false);
            
       }

    private int doinput () throws SQLException, IOException{
        String pathout=SeqBusterView.pathout.getText()+"/";

        JDialog info_obj;
        info_obj = new infoFrame("Getting sequences...");

        Connection con =   tools.docon(namepro);
        Statement statment = con.createStatement();

        String nameexp = (String)MiRNAannFrame.nameexp.getSelectedItem();
        statment.executeUpdate("use "+namepro);
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/input.fa")));
        int count=0;
        int id=0;
        int file=1;
        try{
            String getseqs="select `id`,`seq` from `"+nameexp+"`";
            ResultSet rs=statment.executeQuery(getseqs);

            while (rs.next()) {

                String seqs= rs.getString(2);
                out.write(">seq"+id+"\n"+seqs+"\n");
                count++;
                id++;
            }
            rs.close();
            out.close();
            info_obj.dispose();
         }catch (SQLException ex){
            Logger.getLogger(miRNAann.class.getName()).log(Level.SEVERE, null, ex);
            Logger logger = Logger.getLogger(miRNAann.class.getName());
            tools.writelog(logger, ex);
        }

        statment.close();
        con.close();
        return file;
    }
    private int dofiles () throws SQLException, IOException{
        String pathout=SeqBusterView.pathout.getText()+"/";
     
        JDialog info_obj;
        info_obj = new infoFrame("Getting sequences...");
 
        Connection con =   tools.docon(namepro);
        Statement statment = con.createStatement();
        

        String nameexp = (String)MiRNAannFrame.nameexp.getSelectedItem();
        statment.executeUpdate("use "+namepro);
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/blast1.fa")));
        int count=0;
        int id=0;
        int file=1;
        try{
            String getseqs="select `id`,`seq` from `"+nameexp+"`";
            ResultSet rs=statment.executeQuery(getseqs);
            
            while (rs.next()) {
               
                if (count==10000){
                    file++;
                    count=0;
                    out.close();
                    out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/blast"+file+".fa")));
                }
                String seqs= rs.getString(2);
                out.write(">seq"+id+"\n"+seqs+"\n");
                count++;
                id++;
            }
            rs.close();
            out.close();
            info_obj.dispose();
         }catch (SQLException ex){
            tools.showcerror("This experiment does not exist, please try with other name.");
            Logger.getLogger(miRNAann.class.getName()).log(Level.SEVERE, null, ex);
            Logger logger = Logger.getLogger(miRNAann.class.getName());
            tools.writelog(logger, ex);
        }

        statment.close();
        con.close();
        return file;
    }
    public int alignment (String tdb,int nf) throws SQLException, IOException, InterruptedException{
        String pathout=SeqBusterView.pathout.getText()+"/";
        
        JDialog info_obj;
        info_obj = new infoFrame("Mapping data against miRNA database\n");


            String nameexp = (String)MiRNAannFrame.nameexp.getSelectedItem();
            String megablast=SeqBusterView.pathblast.getText()+"/blastn";
            String nucor = JOptionPane.showInputDialog("Number of cores to use in the alignment process: " );
            if (nucor==null){
                nucor="1";
            }
         for (int i=1;i<=nf;i++){
            String cmdmegablast=megablast+" -task megablast -num_threads "+Integer.parseInt(nucor)+" -db DB/"+tdb+"hairpinRNA.db -query "+pathout+namepro+"/"+nameexp+"/blast"+i+".fa"+" -ungapped -word_size 7 -evalue 1"+" -outfmt 6"+" -perc_identity 85 -out "+pathout+namepro+"/"+nameexp+"/blast"+i+".megablast";
            //System.out.println(cmdmegablast);
            infoFrame.changemsg("Mapping file number: "+i+" of "+nf+" \n");
            DoRuntime(cmdmegablast);
//            log.append(cmdmegablast);
         }
            
           


        info_obj.dispose();
        return (1);
    }

    public int parse (String tdb,String namefasta,String namemegablast) throws SQLException, IOException{
        String pathout=SeqBusterView.pathout.getText()+"/";
         
       
        infoFrame.clearmsg();
        Connection con = tools.docon(namepro);
        Statement statment = con.createStatement();
        String nameexp = (String)MiRNAannFrame.nameexp.getSelectedItem();
        statment.executeUpdate("use "+namepro);
        
        String l;
         Pattern header = Pattern.compile("^#.*");
         //Matcher fit = header.matcher("# kak");
         //System.out.println(pathout+namepro+"/"+nameexp+"/"+nameexp+".megablast");
        infoFrame.changemsg("Reading alignment file..."+namemegablast+"\n");
     
        String strand="+";
        int start,end,ad,str;
        int startseq,endseq,len;
        int mism,gaps,hit,score;
        long posindex;
        int lenindex,ambmir,ambmirs,ambhmir;
        int i,count=0;
        int mismpar=Integer.parseInt(MiRNAannFrame.MisMirAnnText.getText());
        int adpar=Integer.parseInt(MiRNAannFrame.AddMirAnnText.getText());
        int tripar=Integer.parseInt(MiRNAannFrame.TriMirAnnText.getText());
        String sps=(String)MiRNAannFrame.speMirAnnList.getSelectedItem();
        Integer startseqmir,startmirseq;
        //String seqread;
        String seqdb,seq,mismtag,variation3tag,trimmed3,trimmed5;
         String col[];
        TreeMap<String,String> seqmapped =new TreeMap<String,String>();
        TreeMap<String,TreeMap<String,Integer[]>> seqinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<String,Integer[]> tempinfo = new TreeMap<String,Integer[]>();
        Integer [] tempdata=new Integer [9];
        TreeMap<String,Integer> tempscoreindex = new TreeMap<String,Integer>();
        TreeMap<String,Integer[]> index = new TreeMap<String,Integer[]>();
        //TreeMap<String,Integer[]> tempindexinfo = new TreeMap<String,Integer[]>();
        Integer [] indexdata = new Integer[2];
        Integer [] indexmirdata = new Integer[2];
        //TreeMap<String,String> hairmirinfo = new TreeMap<String,String>();
        TreeMap<String,TreeMap<String,Integer[]>> mirinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<String,Integer[]> tempmirinfo = new TreeMap<String,Integer[]>();
        //TreeMap<String,TreeMap<String,Integer[]>> mirsinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
       // TreeMap<String,Integer[]> tempmisinfo = new TreeMap<String,Integer[]>();
        //get sequence from fasta file
        BufferedReader infasta= new BufferedReader(new FileReader(pathout+namepro+"/"+nameexp+"/"+namefasta));
        Pattern fastaformat = Pattern.compile(">.*");
        String nameseqfasta=null;
        String sl=null;
        while ((sl=infasta.readLine())!=null){
             Matcher fitfasta = fastaformat.matcher(sl);
            if (fitfasta.matches()){
                nameseqfasta=sl.replace(">", "");
                //if (sl.matches(">seq99608")){
                    //System.out.println("first: " +sl);
                    //System.out.println("parse: "+nameseqfasta);
                //}
                
                //System.out.println("nameseq");
            }else{
                //if (nameseqfasta.matches("seq99608")){
                    //System.out.println("seqfasta "+sl+" nameseqfasta "+nameseqfasta);
                    //System.out.println(nameseqfasta);
                //}
                seqmapped.put(nameseqfasta,sl);
                
            }
        }
         //get miRNA coordinates

        ResultSet res =statment.executeQuery("SELECT * FROM  `seqbuster`.`"+tdb+"DBhairpinRNA`");
        while (res.next()) {
             Integer [] indexdatanew = new Integer[2];
             indexdatanew[0]=res.getInt("len");
             indexdatanew[1]=res.getInt("pos");
             index.put( res.getString("id"),indexdatanew);
          }
         res.close();
         //get miRNA star coordinate
         
        res =statment.executeQuery("SELECT * FROM  `seqbuster`.`"+tdb+"miRNA`");
        while (res.next()) {
            
             Integer [] indexdatanew = new Integer[2];
             indexdatanew[0]=res.getInt("start");
             indexdatanew[1]=res.getInt("end");
             if (mirinfo.containsKey(res.getString("hRNA"))){
                 tempmirinfo=mirinfo.get(res.getString("hRNA"));
                 tempmirinfo.put(res.getString("miRNA"),indexdatanew);
                 mirinfo.put( res.getString("hRNA"),tempmirinfo);
//                 if (res.getString("hRNA").matches("hsa-mir-1249")){
//                    System.out.println(res.getString("hRNA"));
//                 }
             }else{
                TreeMap<String,Integer[]> premir = new TreeMap<String,Integer[]>();
                premir.put(res.getString("miRNA"),indexdatanew);
                mirinfo.put( res.getString("hRNA"),premir);
             }
          }
         res.close();
         //get index fasta sequences
        //System.out.println("inititatin reading file "+sps);
         //BufferedReader inblast= new BufferedReader(new FileReader(pathout+namepro+"/"+nameexp+"/"+nameexp+".megablast"));
        FileInputStream fstream = new FileInputStream(pathout+namepro+"/"+nameexp+"/"+namemegablast);
        DataInputStream inblast = new DataInputStream(fstream);
        BufferedReader brblast = new BufferedReader(new InputStreamReader(inblast));
        //System.out.println("2 inititatin reading file "+sps);
 
        while ((l=brblast.readLine())!=null)
        {
               //System.out.println("line");
               Matcher fit = header.matcher(l);
               //System.out.println(l+"nn");
            if (!(fit.matches())){
               count++;
               if (count%100000==0){
                   infoFrame.changemsg(count+"...\n");
                  }  
                col = l.split("\t");
//                if (col[0].matches("seq99608")){
//                    System.out.println("name:"+col[0]);
//                    System.out.println("seq "+seqmapped.get(col[0]));
//                    System.out.println("hit:"+col[3]);
//                }
                start=Integer.parseInt(col[8]);
                end=Integer.parseInt(col[9]);
                mism=Integer.parseInt(col[4]);
                gaps=Integer.parseInt(col[5]);
                hit=Integer.parseInt(col[3]);
                startseq=Integer.parseInt(col[6]);
                endseq=Integer.parseInt(col[7]);

                strand="+";
                len=seqmapped.get(col[0]).length();
                ad=len-hit;
                str=1;
                //is strand -,change position
                
               if (start>end){
                    strand="-";
                    str=2;
                    startseq=endseq;
                    endseq=Integer.parseInt(col[6]);
                    start=end;
                    end=Integer.parseInt(col[8]);
                }


               //start at 2 but not mm
               if (startseq==2 & mism==0 & strand.matches("\\+")){
                startseq=1;
                start--;
                mism=1;
                hit++;
               }
                if (startseq==2 & mism==0 & strand.matches("-")){
                startseq=1;
                end++;
                mism=1;
                hit++;

               }
                //calculate score
                score=hit-mism-(ad/2);
                Integer [] tempdata2=new Integer [9];
                tempdata2[0]=start; tempdata2[1]=end;tempdata2[2]=endseq;tempdata2[3]=endseq;tempdata2[4]=str;tempdata2[5]=hit;tempdata2[6]=mism;tempdata2[7]=ad;tempdata2[8]=len;
                //if not exist treemap for this seq
                if (col[1].contains(sps) & gaps==0 & startseq==1 & mism<=mismpar & ad<=adpar){
                    //System.out.println(sps+" len "+len+" hit "+hit+" ad "+ad+" score "+score+" start "+start+" end "+end);
                if (tempscoreindex.containsKey(col[0]) ){
                  //  System.out.println(col[1]);
                    
                    Integer tsc=tempscoreindex.get(col[0]);
                    //if score is better than score in key, remove all keys, add the new key
                    if (tsc<score){
                       //  System.out.println("reqrite: "+col[1]);
                        tempscoreindex.remove(col[0]);
                        tempscoreindex.put(col[0],score);
                        seqinfo.remove(col[0]);
                        TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();
                        

                        newscore.put(col[1],tempdata2);
                        seqinfo.put(col[0],newscore);
                    }
                    //if score is equal, add
                    if (tsc==score){
                         //System.out.println("add: "+col[1]);
                        //TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();
                       tempinfo=seqinfo.get(col[0]);
                       tempinfo.put(col[1],tempdata2);
                        //tempscore.put(col[1],tempdata);
                        seqinfo.put(col[0],tempinfo);
                    }
                }else{
                //System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
                    //System.out.println("new: "+col[1]+" "+col[0]);
                    TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();
                    
                    newscore.put(col[1],tempdata2);
                    tempscoreindex.put(col[0], score);
                    seqinfo.put(col[0],newscore);
                }
                }
               
                

            }
        }
        //read treemap seq (calculate variation) and upload seq to mysql database
        Boolean flag;
        int annm=0,annms=0,annh=0;
        for (String s : seqinfo.keySet()) {
            tempinfo=seqinfo.get(s);
            flag=true;
            ambmir=0;
            ambmirs=0;
            ambhmir=0;
            //System.out.println(s);
            for (String h : tempinfo.keySet()) {
                tempdata=tempinfo.get(h);
                indexdata=index.get(h);
                tempmirinfo=mirinfo.get(h);
                startseq=tempdata[0];
                endseq=tempdata[1];
                hit=tempdata[5];
                posindex=indexdata[1];
                lenindex=indexdata[0];
                //System.out.println("hairpin "+h);
               if (tempmirinfo!=null){
               for (String hm : tempmirinfo.keySet()) {
                //System.out.println("micro:"+hm);
                //overlap with miRNA + (miRNA table <=> mirinfo treemap)
                    if (tempdata[4]==1){
                        //System.out.println("contain in mirna<=>hairpin "+tempdata[4]);
                        
                        indexmirdata=tempmirinfo.get(hm);
                       
                        
                       // System.out.println("seq coor:"+tempdata[0]+"-"+tempdata[1]);
                        //System.out.println("seq hit:"+tempdata[7]);
                        //System.out.println("mirna reference coord: "+indexmirdata[0]+" - "+indexmirdata[1]);
                        //overlap positions
                        if (tempdata[0]>=indexmirdata[0]-tripar & tempdata[0]<=indexmirdata[0]+tripar & tempdata[1]>=indexmirdata[1]-tripar & tempdata[1]<=indexmirdata[1]+tripar){
                            //System.out.println("mirna variant");
                            ambmir++;
                            flag=false;
                        //variation
                            start=indexmirdata[0];
                            end=indexmirdata[1];
                            posindex=indexdata[1];
                            lenindex=indexdata[0];
                            hit=tempdata[5];
                            //System.out.println(posindex+" "+hit+" "+start);
                            seqdb=getsequence(posindex,lenindex,"+",tdb+"hairpinRNA",hit,start);
                            seq=seqmapped.get(s);
                            ////ref trimmed
                            String tr3ref=getsequence(posindex,lenindex,"+",tdb+"hairpinRNA",8,end-3);
                            String tr5ref=getsequence(posindex,lenindex,"+",tdb+"hairpinRNA",8,start-4);
                            if (tempdata[0]==indexmirdata[0]){
                                trimmed5="0";
                            }else{
                                trimmed5=trimmed5(posindex,lenindex,"+",tdb+"hairpinRNA",tempdata[0],start);
                            }
                            if (tempdata[1]==indexmirdata[1]){
                                trimmed3="0";
                            }else{
                                trimmed3=trimmed3(posindex,lenindex,"+",tdb+"hairpinRNA",tempdata[1],end);
                            }
                            //System.out.println("var "+tempdata[7]);
                            if (tempdata[7]==0){
                                variation3tag="0";
                            }else{
                                variation3tag=variation3(seq,tempdata[7]);
                            }
                            if (tempdata[6]==0){
                                mismtag="0";
                            }else{
                                 startseqmir=start-startseq;
                                 startmirseq=0;
                                 //System.out.println(startseqmir+" "+startmirseq+" "+start+" "+startseq);
                                 if (startseqmir<0){
                                        startmirseq=-1*startseqmir;
                                        startseqmir=0;
                                        

                                 }
                                 //System.out.println(startseqmir+" "+startmirseq+" "+start+" "+startseq);
                                 //System.out.println(seq+"\n"+seqdb);
                                 String seqhairpin=getsequence(posindex,lenindex,"+",tdb+"hairpinRNA",hit,startseq);
                                 /////hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
                                 mismtag=subs(seq,seqhairpin,startseq,start);
                                 //System.out.println("startseq "+startseq+" start db "+start);
                                 //System.out.println("seq "+seq+" seqdb "+seqhairpin);
                                 //System.out.println("??  mm "+mismtag);
                                 

                            }
//                            System.out.println("mir "+hm+" tr5: "+trimmed5+" tr3: "+trimmed3+" ad: "+variation3tag+" mism: "+mismtag+" tr5ref "+tr5ref+" tr3ref "+tr3ref);
//                            System.out.println("update `"+namepro+"`.`"+nameexp+"` set `sp`='"+sps+"',`chr`='"+hm+"',`start`="+startseq+",`end`="+endseq+",`strand`='+',`trimmed5`='"+trimmed5+"',`trimmed3`='"+trimmed3+"',`addition3`='"+variation3tag+"',`mut`='"+mismtag+"',`amb`="+ambmir+",`DB`='miRNA',`priority`=1 where `seq` like '"+seq+"' and `priority`>1");
//                            update proname.expname set chr=col[0],start=startseq,end=endseq,strand=+,trimmed5=trimmed5,trimmed3=trimmed3,addition3=ad,mut=mism,amb=ambmir,DB=miRNA where `seq` like 'seq';
                            statment.executeUpdate("update ignore `"+namepro+"`.`"+nameexp+"` set `sp`='"+sps+"',`chr`='"+hm+"',`start`="+startseq+",`end`="+endseq+",`strand`='+',`trimmed5`='"+trimmed5+"',`trimmed3`='"+trimmed3+"',`addition3`='"+variation3tag+"',`mut`='"+mismtag+"',`amb`="+ambmir+",`trimmed3ref`='"+tr3ref+"',`trimmed5ref`='"+tr5ref+"',`DB`='miRNA',`priority`=1 where `seq` like '"+seq+"' and `priority`>=1");
                            annm++;
                        }
                       
                    }
                
              
            }
            }
                 
           
                if (flag){
                    strand="+";
                    ambhmir++;
                    if(tempdata[4]==2){strand="-";}
                    seq=seqmapped.get(s);
                    //System.out.println(s);
                    if (tempdata[7]==0){
                                variation3tag="0";
                    }else{
                             variation3tag=variation3(seq,tempdata[7]);
                    }
                    if (tempdata[6]==0){
                                mismtag="0";
                    }else{

                        seqdb=getsequence(posindex,lenindex,"+",tdb+"hairpinRNA",hit,startseq);
                        //if (s.matches("seq99608")){
                            //System.out.println(seq+"\n"+seqdb);
                        //}
                        mismtag=subsh(seq,seqdb,1,seqdb.length());
                    }
                    //System.out.println("update `"+namepro+"`.`"+nameexp+"` set `sp`='"+sps+"',`chr`='"+h+"',`start`="+startseq+",`end`="+endseq+",`strand`='+',`addition3`='"+variation3tag+"',`mut`='"+mismtag+"',`amb`="+ambhmir+",`DB`='hRNA',`priority`=3 where `seq` like '"+seq+"' and `priority`>3");
                    statment.executeUpdate("update ignore `"+namepro+"`.`"+nameexp+"` set `sp`='"+sps+"',`chr`='"+h+"',`start`="+tempdata[0]+",`end`="+tempdata[1]+",`strand`='"+strand+"',`addition3`='"+variation3tag+"',`mut`='"+mismtag+"',`amb`="+ambhmir+",`DB`='hRNA',`priority`=3 where `seq` like '"+seq+"' and `priority`>3");
                    annh++;
//                    update proname.expname set chr=col[0],start=startseq,end=endseq,strand=tempdata[4],trimmed5=trimmed5,trimmed3=trimmed3,addition3=ad,mut=mism,amb=ambhmir,DB=hairpin where `seq` like 'seq';
                }
            }
        }
       inblast.close();
        HelpDesk.addinfo("Number of miRNA deteted: "+annm+"\n");
        //HelpDesk.helptext.append("Number of miRNA* deteted: "+annms+"\n");
        HelpDesk.addinfo("Number of precursors deteted: "+annh+"\n");
        statment.close();
        con.close();
        infoFrame.changemsg("Annotation done...\n");
       
        return(1);

    }
         
public String reverse (String seq){
                seq=ReverseString.reverseIt(seq);
                //complementary
                seq=seq.replace("A", "1");
                seq=seq.replace("T", "2");
                seq=seq.replace("C", "3");
                seq=seq.replace("G", "4");
                seq=seq.replace("1", "T");
                seq=seq.replace("2", "A");
                seq=seq.replace("3", "G");
                seq=seq.replace("4", "C");

         return (seq);

    }
    public String getsequence(long pos,int len,String strand,String name,int lenhit,int start) throws IOException {
           //char seqc []=new char [1];
           String seq="";
           byte b [] = new byte [lenhit];
           RandomAccessFile file = new RandomAccessFile ("DB/"+name+".db.bin", "r");
           if (strand.matches("-")){
                start=len-start-lenhit;
                file.seek(pos+start);
                file.readFully(b);
                //reverse
                seq=ReverseString.reverseIt(new String(b));
                //complementary
                seq=seq.replace("A", "1");
                seq=seq.replace("T", "2");
                seq=seq.replace("C", "3");
                seq=seq.replace("G", "4");
                seq=seq.replace("1", "T");
                seq=seq.replace("2", "A");
                seq=seq.replace("3", "G");
                seq=seq.replace("4", "C");


           }else{
               //if (pos+start-1<lenhit){
               //System.out.println(pos+" "+start+" "+lenhit);
                file.seek(pos+start-1);
                file.readFully(b);
                seq=new String(b);
               //}
           }
           //seq=seq.replace("\n","");
           //seqc= (char) b;
           //System.out.println("getseq p: "+pos+" lenhit: "+lenhit+" seq: "+seq+" len:"+len);
           file.close();
           return seq;
    }
    public String variation3(String seqs, int ss){
        String v3="0";
        int len=seqs.length();
        //if (len>es-ss+1){
            //System.out.println("inside, es: "+ss+" len: "+len);
            v3="q"+seqs.substring(len-ss,len);
        //}
        return v3;

    }
    public String variation5(int sl,int ss,String seqs,String seql){
        String v5="";

        return v5;

    }
    public String trimmed3(long posindex,int lenindex, String strandpre,String dbbin,int endpre,int endsmall) throws IOException{
        String t3="0";
        int dif;
        if (endpre>endsmall){
            dif=endpre-endsmall;
            t3="q"+getsequence(posindex,lenindex,strandpre,dbbin,dif,endpre-dif);
        }else if(endsmall>endpre){
            dif=endsmall-endpre;
            t3="t"+getsequence(posindex,lenindex,strandpre,dbbin,dif,endpre+1);
        }
        return t3;

    }

    public String trimmed5(long posindex,int lenindex, String strandpre,String dbbin,int startpre,int startsmall) throws IOException{
        String t5="0";
        int dif;
        //System.out.println(startpre+" small "+startsmall);
        if (startpre>startsmall){
            dif=startpre-startsmall;
            t5="t"+getsequence(posindex,lenindex,strandpre,dbbin,dif,startsmall);
        }else if(startsmall>startpre){
            dif=startsmall-startpre;
            t5="q"+getsequence(posindex,lenindex,strandpre,dbbin,dif,startpre);
        }
        return t5;

    }
    public String subs(String seqs,String seql,int sseq,int ss){
        String s="";
        String [] seqNT=seqs.split("");
        String [] seqdbNT=seql.split("");
        //System.out.println("s: "+sseq+" db: "+ss);
        int pos,pos1,pos2;

        for (pos1=0;pos1<seql.length();pos1++){
            
            //System.out.println("pos1: "+pos1);
            //System.out.println(seqNT[pos1]+" "+seqdbNT[pos1]);
              if (!seqNT[pos1].matches(seqdbNT[pos1])){
                  pos=pos1;

                 if (pos+sseq-1>ss){
                  pos=(pos+sseq)-ss;
                 }else if (pos+sseq-1==ss){
                    pos=1;
                 }else if (pos+sseq-1<ss){
                   pos=(pos+sseq-1)-ss;
                  }
                  s+=pos+seqNT[pos1]+seqdbNT[pos1];
               }
           
        }
        if (s.matches("")){
            s="0";
        }
        return s;

    }
    public String subsh(String seqs,String seql,int sseq,int ss){
        String s="";
        String [] seqNT=seqs.split("");
        String [] seqdbNT=seql.split("");
        //System.out.println("s: "+sseq+" db: "+ss);
        int pos,pos1,pos2;
        for (pos1=0;pos1<seql.length();pos1++){
              if (!seqNT[pos1].matches(seqdbNT[pos1])){
                  pos=pos1;
                  s+=pos+seqNT[pos1]+seqdbNT[pos1];
               }
        }
        if (s.matches("")){
            s="0";
        }
        return s;

    }
public void DoRuntime (String cmd) throws IOException, InterruptedException {


       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       process.waitFor();


    }
}

