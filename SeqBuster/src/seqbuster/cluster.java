/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;


import java.io.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class cluster {

    public int docluster2(String file,String expname) throws FileNotFoundException, IOException, SQLException{
       
        String l,word,seed;
        int indc=0,index=0;
        BufferedReader in= new BufferedReader(new FileReader(file));
        //hash for the seed tree => two levels
        HashMap<String,HashMap<String,HashMap<String,Integer>>> seeds1 = new HashMap<String,HashMap<String,HashMap<String,Integer>>>();
        HashMap<String,HashMap<String,Integer>> seeds2 = new HashMap<String,HashMap<String,Integer>>();

        HashMap<String,Integer> tempseed = new HashMap<String,Integer>();
        TreeMap<String,Integer> indclus = new TreeMap<String,Integer>();
        TreeMap<Integer,TreeMap<String,Integer>> clusters = new TreeMap<Integer,TreeMap<String,Integer>>();
        TreeMap<String,Integer> tempclus = new TreeMap<String,Integer>();
        TreeMap<Integer,String> cluspat = new TreeMap<Integer,String>();
        TreeMap<String,Integer> indcluspat = new TreeMap<String,Integer>();
        TreeMap<String,String> freq = new TreeMap<String,String>();
        Boolean found=false;
        
        ArrayList <String> seqs = new ArrayList<String>();
        ArrayList <Integer> idseqs = new ArrayList<Integer>();

        String freqtemp=null;
        int count=0;
        while ((l=in.readLine())!=null){
            //Matcher fitfasta = fastaformat.matcher(l);
            if (!l.contains(">")){
                    count++;
                   seqs.add(l);
                   //idseqs.add(count);
                   freq.put(l,freqtemp);
            }else{
                String [] data=l.split("_");
                freqtemp=data[1];
            }
         }

        

        Collections.sort(seqs, new byLineLength());
        Iterator stepper = seqs.iterator();
        //int  count=0;
         while (stepper.hasNext()) {
             found=false;
             String seq = (String)stepper.next();
             if (seq.length()>=15){
//             System.out.println("Seq "+seq);
             int sc=1;
             for (int i=0;i<=seq.length()-7;i++){
                    word=seq.substring(i,i+7);
                    String word1=word.substring(0,3);
                    String word2=word.substring(3);
                    //System.out.println(word+" pos "+i );
                    if (seeds1.containsKey(word1)){
                        seeds2=seeds1.get(word1);
//                        System.out.println(word1+" word1 in seeds1 pos "+i );
                        if (seeds2.containsKey(word2)){
                            tempseed=seeds2.get(word2);
//                            System.out.println(word2+" word2 in seeds2  pos "+i );
                            for (String s : tempseed.keySet()) {

                                //see mismatches
                                int indclustem=indclus.get(s);
                                String seqpat=cluspat.get(indclustem);
                                int posseqpat=seqpat.indexOf(word);
                                //System.out.println("founded: "+word+" in "+i+" seq "+seqpat+" "+posseqpat+" seq s: "+s);
                                sc=align(seqpat,seq,posseqpat,i);

                                 //System.out.println("mism "+sc);
                                 //check diff lenght between max len cluster and current. if >7 new cluster
                                 if (sc==0){
                                    found=true;
                                    //get seq from clusters pattern


                                    //getpattern

                                    int indcluspattem=indcluspat.get(seqpat);
//                                    System.out.println("founded  "+word+" in pos "+i+" seq "+seqpat+" in "+seqpat.indexOf(word));
                                    //String seqpat=cluspat.get(indclus);
                                    //get new pattern(long string)
                                    //get index word in pattern
                                    String pattern=pattern(seqpat,seq,seqpat.indexOf(word),i);
                                    //tempseed.put(pattern, Math.max(tempseed.get(s), i));
                                    //tempseed.remove(s);
                                    indcluspat.remove(seqpat);
                                    indcluspat.put(pattern,indcluspattem);
                                    cluspat.remove(indclustem);
                                    cluspat.put(indclustem, pattern);
                                    //add seq to cluster
                                    //int tempind=indclus.get(s);
                                    tempclus=clusters.get(indcluspattem);
                                    tempclus.put(seq, i);
                                    clusters.put(indcluspattem,tempclus);
                                    indclus.put(seq,indcluspattem);
                                    //seq, pos in pattern

                                    //System.out.println("pattern "+pattern);
                                    break;
                                 }else if(sc==1){
                                    found=true;
                                    //System.out.println("mism "+sc);
                                    int indcluspattem=indcluspat.get(seqpat);
                                    tempclus=clusters.get(indcluspattem);
                                    tempclus.put(seq, 1);
                                    clusters.put(indcluspattem,tempclus);
                                    indclus.put(seq,indcluspattem);

                                    //get new pattern(long string)

                                    //seq, pos in pattern
                                    //System.out.println("founded  "+word+" in pos"+i+" seq "+s+" in "+tempseed.get(s));

                                    break;

                                 }
                            }

                        }
                        //System.out.println("founded");
                        
                        
                    }
                    if (sc==0){
                        break;
                    }
             }
             
             if (found==false){
                //add new seed, new cluster
                 indc++;
                 indclus.put(seq,indc);
                 indcluspat.put(seq,indc);
                 cluspat.put(indc,seq);
                 seed=seq.substring(3,10);
                 String seed1=seed.substring(0,3);
                 String seed2=seed.substring(3);
                 TreeMap<String,Integer> newclus = new TreeMap<String,Integer>();
                 newclus.put(seq,1);
                 clusters.put(indc,newclus);
//                 System.out.println("add "+seed);
                 if (seeds1.containsKey(seed1)){
                    seeds2=seeds1.get(seed1);
                    if (seeds2.containsKey(seed2)){
//                        System.out.println("exists seed1 "+seed1+" exists seed2 "+seed2);
                        tempseed=seeds2.get(seed2);
                        tempseed.put(seq,3);
                        seeds2.put(seed,tempseed);
                        seeds1.put(seed1,seeds2);
                    }else{
//                        System.out.println("exists seed1 "+seed1+" new seed2 "+seed2);
                        HashMap<String,Integer> newseed = new HashMap<String,Integer>();
                        newseed.put(seq, 3);
                        HashMap<String,HashMap<String,Integer>> newseeds2 = new HashMap<String,HashMap<String,Integer>>();
                        newseeds2.put(seed2,newseed);
                        seeds1.put(seed1, newseeds2);
                    }
                    
                 }else{
//                     System.out.println("new seed1 "+seed1+" new seed2 "+seed2);
                     HashMap<String,Integer> newseed = new HashMap<String,Integer>();
                     newseed.put(seq, 3);
                     HashMap<String,HashMap<String,Integer>> newseeds2 = new HashMap<String,HashMap<String,Integer>>();
                     newseeds2.put(seed2,newseed);
                     seeds1.put(seed1, newseeds2);
                 }
                 seed=seq.substring(seq.length()-10,seq.length()-3);
                 seed1=seed.substring(0,3);
                 seed2=seed.substring(3);
                 //System.out.println("add "+seed);
                 if (seeds1.containsKey(seed1)){
                    seeds2=seeds1.get(seed1);
                    if (seeds2.containsKey(seed2)){
                        tempseed=seeds2.get(seed2);
                        tempseed.put(seq,seq.length()-10);
                        seeds2.put(seed,tempseed);
                    }else{
                        HashMap<String,Integer> newseed = new HashMap<String,Integer>();
                        newseed.put(seq, seq.length()-10);
                        seeds2.put(seed2,newseed);
                    }
                    seeds1.put(seed1,seeds2);
                 }else{
                     HashMap<String,Integer> newseed = new HashMap<String,Integer>();
                     newseed.put(seq, seq.length()-10);
                     HashMap<String,HashMap<String,Integer>> newseeds2 = new HashMap<String,HashMap<String,Integer>>();
                     newseeds2.put(seed2,newseed);
                     seeds1.put(seed1, newseeds2);
                 }
             }
         }
         }


        
       
        String namepro=SeqBusterView.loaprotext.getText();
        String pathout=SeqBusterView.pathout.getText()+"/";
        
        Connection con = null;
        con =  tools.docon(namepro);
        
        String createtable="create table `"+expname+"clusraw` (`id` int ,`seq` varchar(100),`nc` int unsigned,`freq` int unsigned,`type` TINYINT unsigned,index (`nc`),index (`seq`)) ";

        Statement statment =  con.createStatement();
        statment.executeUpdate("insert into `experiments` values ('"+expname+"clusraw','"+namepro+"','noshow','usRNAraw')");
        statment.executeUpdate(createtable);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/cluster1.fa")));
        PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/cluster.fa")));
        int countlines=0;
        int numfiles=1;
        for (int ind : clusters.keySet()) {
            tempclus=clusters.get(ind);
            if (countlines>300){
                numfiles++;
                out.close();
                out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname + "/cluster"+ind+".fa")));
                countlines=0;
            }

            //if (cluspat.get(ind).length()<100){
                countlines++;
                //statment.executeUpdate("insert into `" + expname + "clusraw` values (0,'" + cluspat.get(ind) + "'," + ind + "," + 0 + ",0)");
                out.write(">seq_"+ind+"\n"+cluspat.get(ind)+"\n");
                out2.write(">seq_"+ind+"\n"+cluspat.get(ind)+"\n");
               // System.out.println("pattern "+cluspat.get(ind));
                for (String s : tempclus.keySet()) {
                    index = cluspat.get(ind).indexOf(s);
                    //System.out.println("insert into `" + expname + "clusraw` values ('" + s + "'," + ind + "," + freq.get(s) + "," + index + ")");
                    statment.executeUpdate("insert into `" + expname + "clusraw` values (0,'" + s + "'," + ind + "," + freq.get(s) + "," + index + ")");

                }
            //}
        }
        //HelpDesk.helptext.append("Number of pre-usRNAs detected: \n"+ind);
        out.close();
        out2.close();
        statment.close();
        con.close();
        tempseed.clear();
        clusters.clear();
        cluspat.clear();
        seeds1.clear();
        seeds2.clear();
        return (numfiles);
//        System.out.println("end");
    }

   public int parse(String file,String expname,String gen,String spe) throws FileNotFoundException, IOException, SQLException{
        String pathout=SeqBusterView.pathout.getText()+"/";
        String namepro=SeqBusterView.loaprotext.getText();

        
        String l=null;
        Pattern header = Pattern.compile("^#.*");
        String strand="+";
        int start,end,ad,str;
        int startseq,endseq,len;
        int mism,gaps,hit,score;
        long posindex;
        int ambmir,ambmirs,ambhmir;
        Long lenindex;
        int i,count=0;
        int mismpar=Integer.parseInt(NonMiRNAFrame.mistext.getText());
        int adpar=Integer.parseInt(NonMiRNAFrame.addtext.getText());
        
       
        //String sps=miRNAannFrame.speMirAnnText.getText();
        Integer startseqmir,startmirseq;
        //String seqread;
        String seqdb,seq,mismtag,variation3tag,trimmed3,trimmed5;
        String col[];
        //get genome index
        
        TreeMap<String,Long[]> index = new TreeMap<String,Long[]>();
        TreeMap<String,TreeMap<String,Integer[]>> seqinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<String,Integer[]> tempinfo = new TreeMap<String,Integer[]>();
        TreeMap<String,Integer> tempscoreindex = new TreeMap<String,Integer>();
        TreeMap<String,String> seqmapped =new TreeMap<String,String>();
        Long [] indexdata = new Long[2];
        Integer [] tempdata=new Integer [9];

        Connection con = tools.docon("seqbuster");
        Statement statment = con.createStatement();
        ResultSet res =statment.executeQuery("SELECT * FROM  `genome`.`"+gen+"_"+spe+"`");
        while (res.next()) {
             Long [] indexdatanew = new Long[2];
             indexdatanew[0]=res.getLong("len");
             indexdatanew[1]=res.getLong("pos");
             index.put( res.getString("name"),indexdatanew);
          }
         res.close();
         //hashtree cluster pattern seq
        BufferedReader infasta= new BufferedReader(new FileReader(pathout+namepro+"/"+expname+"/cluster.fa"));
//        Pattern fastaformat = Pattern.compile(">.*");
        String nameseqfasta=null;
        while ((l=infasta.readLine())!=null){
//             Matcher fitfasta = fastaformat.matcher(l);
            if (l.contains(">")){

                nameseqfasta=l.replace(">", "");
                //System.out.println("nameseq");
            }else{
                //System.out.println(nameseqfasta+" "+l);
                seqmapped.put(nameseqfasta,l);

            }
        }
        File dir = new File(pathout+namepro+"/"+expname);

        String[] children = dir.list();

        for (int ic=0; ic< children.length; ic++) {
            if (children[ic].matches("cluster[0-9]+.fa.megablast")){
//            System.out.println(children[ic]);
            BufferedReader inblast= new BufferedReader(new FileReader(pathout+namepro+"/"+expname+"/"+children[ic]));
        while ((l=inblast.readLine())!=null)
        {
            Matcher fit = header.matcher(l);
            if (!(fit.matches())){
               count++;
               if (count%100000==0){
                   //log.append(count+"...\n");
                   //log.setCaretPosition(log.getText().length() - 1);

                  }
//               System.out.println(l);
                col = l.split("\t");
                col[0].replace("lcl|","");
                start=Integer.parseInt(col[8]);
                end=Integer.parseInt(col[9]);
                mism=Integer.parseInt(col[4]);
                gaps=Integer.parseInt(col[5]);
                hit=Integer.parseInt(col[3]);
                startseq=Integer.parseInt(col[6]);
                endseq=Integer.parseInt(col[7]);

                strand="+";
//                System.out.println(col[0]);
                len=seqmapped.get(col[0]).length();
                ad=len-hit;
                str=1;
                //is strand -,change position
                //System.out.println(locus+" "+start+" "+end);
               if (start>end){
                    strand="-";
                    str=2;
                    //startseq=endseq;
                    //endseq=Integer.parseInt(col[6]);
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
//                System.out.println("len "+len+" hit "+hit+" gap "+gaps+" score "+score+" start "+startseq+" mispar "+mismpar);
                if ( gaps==0 & startseq==1 & mism<=mismpar & ad<=adpar){
//                  System.out.println("passed");
                if (tempscoreindex.containsKey(col[0]) ){
//                    System.out.println("add "+col[1]);

                    Integer tsc=tempscoreindex.get(col[0]);
                    //if score is better than score in key, remove all keys, add the new key
                    if (tsc<score){
//                         System.out.println("rewrite: "+col[1]);
                        tempscoreindex.remove(col[0]);
                        tempscoreindex.put(col[0],score);
                        seqinfo.remove(col[0]);
                        TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();


                        newscore.put(col[1]+"-"+start,tempdata2);
                        seqinfo.put(col[0],newscore);
                    }
                    //if score is equal, add
                    if (tsc==score){
//                         System.out.println("add score: "+col[1]);
                        //TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();
                       tempinfo=seqinfo.get(col[0]);
                       tempinfo.put(col[1]+"-"+start,tempdata2);
                        //tempscore.put(col[1],tempdata);
                        seqinfo.put(col[0],tempinfo);
                    }
                }else{
//                System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
//                    System.out.println("new: "+col[1]+" "+col[0]);
                    TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();

                    newscore.put(col[1]+"-"+start,tempdata2);
                    tempscoreindex.put(col[0], score);
                    seqinfo.put(col[0],newscore);
                }
                }



            }
        }
            inblast.close();
       }


       
   }

        Boolean flag;
        int num=0,ncl=0;
        //createtable clusmap
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname+"/preusRNA.txt")));
        statment.executeUpdate("insert into `"+namepro+"`.`experiments` values ('"+expname+"clusmap','"+namepro+"','usRNA','usRNA')");
        statment.executeUpdate("create table `"+namepro+"`.`"+expname+"clusmap` (`id` int ,`ncl` int unsigned,`nc` int unsigned,`ns` int unsigned,`nl` int unsigned,`freq` int unsigned,`len` smallint unsigned,`seq` varchar (40),`chr` varchar(100),`start` int unsigned,`end` int unsigned,`strand` enum('+','-'),`mut` int(1),`add` tinyint unsigned,index (`nc`) )");
        for (String s : seqinfo.keySet()) {
            tempinfo=seqinfo.get(s);
            String [] id=s.split("_");
            flag=true;
            ambmir=0;

            //System.out.println(s);
           // System.out.println(id[0]);
            
            for (String h : tempinfo.keySet()) {
                tempdata=tempinfo.get(h);
                String [] chr=h.split("-");
                //System.out.println(h);
                indexdata=index.get(chr[0]);
                startseq=tempdata[0];
                endseq=tempdata[1];
                hit=tempdata[5];
                //posindex=indexdata[1];
                //lenindex=indexdata[0];
                char st='+';
                if (tempdata[4]==2){
                    st='-';
                }
                //tempdata[4]==2 => -
                //insert into table
                //System.out.println("insert into `"+namepro+"`.`"+expname+"clusmap` values ('"+id[1]+"','"+chr[0]+"',"+tempdata[4]+","+startseq+","+endseq+","+tempdata[6]+","+tempdata[7]+")");
                num++;
//                if (id[1].matches("145")){
//                    System.out.println("insert into `"+namepro+"`.`"+expname+"clusmap` values (0,'"+id[1]+"',0,0,0,0,'na','"+chr[0]+"',"+startseq+","+endseq+",'"+st+"',"+tempdata[6]+","+tempdata[7]+")\n");
//                }
                ncl++;
                statment.executeUpdate("insert into `"+namepro+"`.`"+expname+"clusmap` values (0,"+ncl+",'"+id[1]+"',0,0,0,0,'na','"+chr[0]+"',"+startseq+","+endseq+",'"+st+"',"+tempdata[6]+","+tempdata[7]+")");
                out.write(ncl+"\t"+id[1]+"\t"+chr[0]+"\t"+startseq+"\t"+endseq+"\t"+chr[0]+"\n");
            }
        }
        statment.close();
        res.close();
        con.close();
        return num ;

   }


   public int parsecusalg(String file,String expname,String gen,String spe,int chrin, int startin,int endin,int stin,int seqin,  int mmin, int gapin) throws FileNotFoundException, IOException, SQLException{
        String pathout=SeqBusterView.pathout.getText()+"/";
        String namepro=SeqBusterView.loaprotext.getText();


        String l=null;
        Pattern header = Pattern.compile("^#.*");
        String strand="+";
        int start,end,ad,str;
        int startseq,endseq,len;
        int mism,gaps,hit,score;
        long posindex;
        int ambmir,ambmirs,ambhmir;
        Long lenindex;
        int i,count=0;
        int mismpar=Integer.parseInt(NonMiRNAFrame.mistext.getText());
        int adpar=Integer.parseInt(NonMiRNAFrame.addtext.getText());


        //String sps=miRNAannFrame.speMirAnnText.getText();
        Integer startseqmir,startmirseq;
        //String seqread;
        String seqdb,seq,mismtag,variation3tag,trimmed3,trimmed5;
        String col[];
        //get genome index
        
        TreeMap<String,TreeMap<String,Integer[]>> seqinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<String,Integer[]> tempinfo = new TreeMap<String,Integer[]>();
        TreeMap<String,Integer> tempscoreindex = new TreeMap<String,Integer>();
        TreeMap<String,String> seqmapped =new TreeMap<String,String>();
        Long [] indexdata = new Long[2];
        Integer [] tempdata=new Integer [9];

        Connection con =tools.docon("seqbuster");
        Statement statment = con.createStatement();
        
         //hashtree cluster pattern seq
        BufferedReader infasta= new BufferedReader(new FileReader(pathout+namepro+"/"+expname+"/cluster.fa"));
//        Pattern fastaformat = Pattern.compile(">.*");
        String nameseqfasta=null;
        while ((l=infasta.readLine())!=null){
//             Matcher fitfasta = fastaformat.matcher(l);
            if (l.contains(">")){

                nameseqfasta=l.replace(">", "");
                //System.out.println("nameseq");
            }else{
                //System.out.println(nameseqfasta+" "+l);
                seqmapped.put(nameseqfasta,l);

            }
        }
          
        BufferedReader inblast= new BufferedReader(new FileReader(file));
        while ((l=inblast.readLine())!=null)
        {
            Matcher fit = header.matcher(l);
            if (!(fit.matches())){
               count++;
               if (count%100000==0){
                   //log.append(count+"...\n");
                   //log.setCaretPosition(log.getText().length() - 1);

                  }
               //System.out.println(l);
                col = l.split("\t");

                if (seqmapped.containsKey(col[seqin-1])){
                            col[chrin-1].replace("lcl|","");
                            start=Integer.parseInt(col[startin-1]);
                            end=Integer.parseInt(col[endin-1]);
                            if (mmin!=-1){
                                mism=Integer.parseInt(col[mmin-1]);
                            }else{
                                mism=0;
                            }
                            if (gapin!=-1){
                                gaps=Integer.parseInt(col[gapin-1]);
                            }else{
                                gaps=0;
                            }
                            hit=end-start+1;


                             if (stin==-1){
                                strand="+";
                                str=1;
                             }else{
                                strand=col[stin-1];
                                str=1;
                                 //System.out.println("before="+str+" real="+strand);
                                if (strand.contains("-")){str=2;}
                                 //System.out.println("after="+str+" real="+strand);
                             }
            //                System.out.println(col[0]);
                            len=seqmapped.get(col[seqin-1]).length();
                            ad=len-hit;
                            endseq=len-ad;

                            //is strand -,change position
                            //System.out.println(locus+" "+start+" "+end);
                           if (start>end){
                                strand="-";
                                str=2;
                                //startseq=endseq;
                                //endseq=Integer.parseInt(col[6]);
                                start=end;
                                end=Integer.parseInt(col[endin-1]);
                                hit=end-start+1;
                                ad=len-hit;
                            }


                           //start at 2 but not mm
                           if ( mism==0 & strand.matches("\\+")){

                            start--;
                            mism=1;
                            hit++;

                           }
                            if (mism==0 & !strand.matches("\\+")){

                            end++;
                            mism=1;
                            hit++;

                           }
                            //calculate score
                            score=hit-mism-(ad/2);
                            Integer [] tempdata2=new Integer [9];
                            tempdata2[0]=start; tempdata2[1]=end;tempdata2[2]=1;tempdata2[3]=endseq;tempdata2[4]=str;tempdata2[5]=hit;tempdata2[6]=mism;tempdata2[7]=ad;tempdata2[8]=len;
                            //if not exist treemap for this seq
                            //System.out.println(str+"len "+len+" hit "+hit+" gap "+gaps+" score "+score+" start "+start+" mispar "+mismpar+" adpar: "+adpar);
                            if ( gaps==0 & mism<=mismpar & ad<=adpar){
            //                  System.out.println("passed");
                            if (tempscoreindex.containsKey(col[seqin-1]) ){
            //                    System.out.println("add "+col[1]);

                                Integer tsc=tempscoreindex.get(col[seqin-1]);
                                //if score is better than score in key, remove all keys, add the new key
                                if (tsc<score){
            //                         System.out.println("rewrite: "+col[1]);
                                    tempscoreindex.remove(col[seqin-1]);
                                    tempscoreindex.put(col[seqin-1],score);
                                    seqinfo.remove(col[seqin-1]);
                                    TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();
                                    newscore.put(col[chrin-1]+"-"+start,tempdata2);
                                    seqinfo.put(col[seqin-1],newscore);
                                }
                                //if score is equal, add
                                if (tsc==score){
            //                         System.out.println("add score: "+col[1]);
                                    //TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();
                                   tempinfo=seqinfo.get(col[seqin-1]);
                                   tempinfo.put(col[chrin-1]+"-"+start,tempdata2);
                                    //tempscore.put(col[1],tempdata);
                                    seqinfo.put(col[seqin-1],tempinfo);
                                }
                            }else{
            //                System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
            //                    System.out.println("new: "+col[1]+" "+col[0]);
                                TreeMap<String,Integer[]> newscore = new TreeMap<String,Integer[]>();

                                newscore.put(col[chrin-1]+"-"+start,tempdata2);
                                tempscoreindex.put(col[seqin-1], score);
                                seqinfo.put(col[seqin-1],newscore);
                            }
                            }



                        }
            }
        }
            inblast.close();

        Boolean flag;
        int num=0,ncl=0;
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + expname+"/preusRNA.txt")));
        //createtable clusmap
        statment.executeUpdate("insert into `"+namepro+"`.`experiments` values ('"+expname+"clusmap','"+namepro+"','usRNA','usRNA')");
        statment.executeUpdate("create table `"+namepro+"`.`"+expname+"clusmap` (`id` int ,`ncl` int unsigned,`nc` int unsigned,`ns` int unsigned,`nl` int unsigned,`freq` int unsigned,`len` smallint unsigned,`seq` varchar (40),`chr` varchar(100),`start` int unsigned,`end` int unsigned,`strand` enum('+','-'),`mut` int(1),`add` int(1),index (`nc`) )");
        for (String s : seqinfo.keySet()) {
            tempinfo=seqinfo.get(s);
            String [] id=s.split("_");
            flag=true;
            ambmir=0;

            //System.out.println(s);
            //System.out.println(id[0]);

            for (String h : tempinfo.keySet()) {
                tempdata=tempinfo.get(h);
                String [] chr=h.split("-");
                //System.out.println(h);
                startseq=tempdata[0];
                endseq=tempdata[1];
                hit=tempdata[5];
                //posindex=indexdata[1];
                //lenindex=indexdata[0];
                char st='+';
                if (tempdata[4]==2){
                    st='-';
                }
                //tempdata[4]==2 => -
                //insert into table
//                System.out.println("insert into `"+namepro+"`.`"+expname+"clusmap` values ('"+id[1]+"','"+chr[0]+"',"+tempdata[4]+","+startseq+","+endseq+","+tempdata[6]+","+tempdata[7]+")");
                num++;
//                if (id[1].matches("145")){
                    //System.out.println("insert into `"+namepro+"`.`"+expname+"clusmap` values (0,'"+id[1]+"',0,0,0,0,'na','"+chr[0]+"',"+startseq+","+endseq+",'"+st+"',"+tempdata[6]+","+tempdata[7]+")\n");
//                }
                ncl++;
                statment.executeUpdate("insert into `"+namepro+"`.`"+expname+"clusmap` values (0,"+ncl+",'"+id[1]+"',0,0,0,0,'na','"+chr[0]+"',"+startseq+","+endseq+",'"+st+"',"+tempdata[6]+","+tempdata[7]+")");
                out.write(ncl+"\t"+id[1]+"\t"+chr[0]+"\t"+startseq+"\t"+endseq+"\n");
            }
        }
        out.close();
        statment.close();
       
        con.close();
        return num ;

   }

   public int clusterpos (String expname) throws SQLException{

        
        String namepro=SeqBusterView.loaprotext.getText();
        
        String chr,st,tchr,tst,cchr,cst;
        int start,end,tstart,tend,cstart,cend,newf,cind=1,overf=0,tnc,nc=0;
        int minstart=0,maxend=0,tempid=0;

        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        statment.executeUpdate("drop  table  if exists `"+namepro+"`.`"+expname+"clus`");
        statment.executeUpdate("create table `"+namepro+"`.`"+expname+"clus` (`nl` int unsigned,`nc` int unsigned,`cons` int,index (`nc`),`id` int not null,index(`nl`) )");
       //get data from table clusmap (get different chr names,and get each chr separatly and sort by position)
        ResultSet rs2=null;
        ResultSet rs=statment.executeQuery("select `chr`  from `"+namepro+"`.`"+expname+"clusmap` group by `chr`");
        Statement statment2 =(Statement) con.createStatement();
        ArrayList<String>  chrs=new ArrayList<String>();

        while (rs.next()) {
            chrs.add(rs.getString(1));
        
        }
        Iterator stepper = chrs.iterator();

         while (stepper.hasNext()) {
            String locus = (String)stepper.next();
//            System.out.printf("chr %s\n", locus);
            rs2=statment.executeQuery("select `ncl`,`chr`,`start`,`end`,`strand`,`nc` from `"+namepro+"`.`"+expname+"clusmap` where `chr` like '"+locus+"' order by start asc");
            rs2.next();
            tnc=rs2.getInt(1);
            tchr=rs2.getString(2);
            tstart=Integer.parseInt(rs2.getString(3));
            tend=Integer.parseInt(rs2.getString(4));
            tst=rs2.getString(5);
            newf=0;
            overf=1;
            int nclast=rs2.getInt(6);int ncname=0;
            int tncname=rs2.getInt(6);
            while (rs2.next()) {
                nc=rs2.getInt(1);
                
                chr=rs2.getString(2);
                start=Integer.parseInt(rs2.getString(3));
                end=Integer.parseInt(rs2.getString(4));
                st=rs2.getString(5);
                ncname=rs2.getInt(6);
                nclast=ncname;
                //if overlap current=before
//                System.out.printf("previous pos %d %d %s \n" ,tstart,tend,tst );
//                System.out.printf("current pos %d %d %s \n" ,start,end,st );
                if (st.contains(tst) & start>=tstart & start<tend){
                   minstart=Math.min(start,tstart);
                   maxend=Math.max(end,tend);
                    overf=0;
                    if (newf==0){
                        //create new cluster
                        cind++;

                        newf=1;
//                        System.out.printf(" new cluster id %d , current pos %d %d %s %d\n" ,cind,start,end,st,nc );
//                        System.out.printf(" second cluster id %d , current pos %d %d %s %d\n" ,cind,tstart,tend,tst,tnc );
                        //ncname=nc;
                        //if (tncname==2030){System.out.println("tncname "+tncname);}
                        //if (ncname==2030){System.out.println("ncname "+ncname+" info "+chr+start+" "+end+" ncl "+nc);}
                        statment2.executeUpdate("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+tncname+",0,0)");
                        statment2.executeUpdate("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+ncname+",0,0)");

                        statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `id`="+cind+"  where `ncl`="+nc+" or ncl="+tnc);
                        
                    }else{
                        //if (ncname==2030){System.out.println("add ncname "+ncname);}
                        //add to new cluster\
//                        System.out.printf("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+nc+")");
                        statment2.executeUpdate("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+ncname+",0,0)");
                        statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `id`="+cind+"  where `ncl`="+nc);
                    }

                }else {
                    //end cluster
                    if (overf==0){
//                        System.out.printf(" no overlap ,break cluster\n" );
                        //statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `start`="+minstart+",`end`="+maxend+" where `id`="+cind);
                    }else{
                        cind++;
                        //if (tncname==2030){System.out.println("tncname overf==1"+tncname);}
                        statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `id`="+cind+"  where `ncl`="+tnc);
                        statment2.executeUpdate("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+tncname+",0,0)");
                    }
                    newf=0;

                    overf=1;
                }
                //add uRNAs nor overlapping
                tnc=nc;
                tchr=chr;
                tstart=start;
                tend=end;
                tst=st;
                tncname=ncname;
            }
            if (overf==1){
                cind++;
                //if (nclast==2030){System.out.println("nclast "+nclast);}
//                System.out.println("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+nclast+",0)");
                statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `id`="+cind+"  where `ncl`="+nc);
                statment2.executeUpdate("insert into `"+namepro+"`.`"+expname+"clus` values ("+cind+","+nclast+",0,0)");
                
            }else{
//                System.out.println("update `"+namepro+"`.`"+expname+"clusmap` set `start`="+minstart+",`end`="+maxend+" where `id`="+cind+"\n");
                //statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `start`="+minstart+",`end`="+maxend+" where `id`="+cind);
            }
            
        }
        statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `ncl`=`id`");
        statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusmap` set `id`=-1*`id`");
        rs.close();
        if (rs2!=null){rs2.close();}
        statment.close();
        statment2.close();
        con.close();
        return cind;
 
    }

   public int consistency (String expname) throws SQLException{
        
        String namepro=SeqBusterView.loaprotext.getText();
        
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        ArrayList<Integer>  nctemp=new ArrayList<Integer>();
        TreeMap<Integer,ArrayList<Integer>> nc = new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<Integer,ArrayList<Integer>> nl = new TreeMap<Integer,ArrayList<Integer>>();
         ArrayList<Integer> idlist=new ArrayList<Integer>();
         int idind=0;
        ArrayList<Integer>  nltemp=new ArrayList<Integer>();
        ArrayList<Integer>  nltemp2=new ArrayList<Integer>();
        ArrayList<Integer>  nlcommon=new ArrayList<Integer>();
        TreeMap<Integer,Integer> seqlist = new TreeMap<Integer,Integer>();
        ResultSet rs=statment.executeQuery("select `nc`,count(*),max(freq) as freq from `"+namepro+"`.`"+expname+"clusraw`  where `freq`>0 group by `nc`");
        while(rs.next()){
//            System.out.println(rs.getInt(1)+" "+rs.getInt(2));
            seqlist.put(rs.getInt(1),rs.getInt(2));
        }
        rs=statment.executeQuery("select * from `"+namepro+"`.`"+expname+"clus` ");
        while(rs.next()){
            //nc nl<=>ncs
 //           System.out.println("nl 79 "+rs.getInt(1)+" "+rs.getInt(2));
            if (nl.containsKey(rs.getInt(1))){
//                System.out.println(" add to nl "+rs.getInt(1)+" new cluster "+rs.getInt(2));
                nltemp=nl.get(rs.getInt(1));
                nltemp.add(rs.getInt(2));
                nl.put(rs.getInt(1),nltemp);
            }else{
//                System.out.println(" new nl "+rs.getInt(1)+" new cluster "+rs.getInt(2));
                ArrayList<Integer>  nlnew=new ArrayList<Integer>();
                nlnew.add(rs.getInt(2));
                nl.put(rs.getInt(1),nlnew);
            }
            //nl nc<=>nls
            if (nc.containsKey(rs.getInt(2))){
//                System.out.println(" add to nc "+rs.getInt(2)+" new loci "+rs.getInt(1));
                nctemp=nc.get(rs.getInt(2));
                nctemp.add(rs.getInt(1));
                nc.put(rs.getInt(2),nctemp);
            }else{
//                System.out.println(" new nc "+rs.getInt(2)+" new loci "+rs.getInt(1));
                ArrayList<Integer>  ncnew=new ArrayList<Integer>();
                ncnew.add(rs.getInt(1));
                nc.put(rs.getInt(2),ncnew);
            }
        }
        rs.close();

        
       
   
        for (Integer ncs : nc.keySet()) {
        //System.out.println("nc "+ncs);
        boolean cons=true;
        //foreach nl in nc
        nltemp=nc.get(ncs);
        
        for (Integer nls : nltemp) {
//            System.out.println("nl "+nls);
            nctemp=nl.get(nls);
            Iterator steppernl = nctemp.iterator();

            cons=true;
            //if num loci >=20 only compare num loci
            Integer nclist = (Integer)steppernl.next();
            nltemp=nc.get(nclist);
            int numloci=nltemp.size();
//            System.out.println("clus in that loci 1 "+nclist+" len "+numloci);
            Object [] listnlarray1=nltemp.toArray();
            nlcommon=nltemp;
            Arrays.sort(listnlarray1);
            while (steppernl.hasNext() & cons==true) {
                nclist = (Integer)steppernl.next();
                //get nl of the next nc
                
                nltemp2=nc.get(nclist);
                int numloci2=nltemp2.size();
//                System.out.println("clus in that loci 2 "+nclist+" len "+numloci2);
                if (numloci==15){
                    if (numloci2==15){
                        //ok
                    }else{
                        cons=false;
                    }

                }else{

                        Object [] listnlarray=nltemp2.toArray();
                        Arrays.sort(listnlarray);
                        boolean b=Arrays.equals(listnlarray1,listnlarray);
                        if (b==true){
                            //ok
//                            System.out.println("num loci same in all clusters");
                        }else{
                            cons=false;
                            //remove the no common loci
                            //nlcommon=commonelem(nlcommon,nltemp2);
                        }

                }

            }
            if (cons==false){break;}
        }
//        for (Integer nlschange : nltemp) {
////            System.out.println("nl "+nls);
//            nctemp=nl.get(nlschange);
//            for (Integer ncschange : nctemp) {
//                nc.put(ncschange,nlcommon);
//            }
//        }
        
            //System.out.println(cons+" cons");
            if (cons==true){
                //add to table
                //System.out.println(ncs);
                nltemp=nc.get(ncs);
                //System.out.println(nltemp.toString());
                nctemp=nl.get(nltemp.get(0));
                //System.out.println(nctemp.toString());
                
                if (!(idlist.contains(nctemp.get(0)))){
                    idind++;

                }
                nltemp=nc.get(nctemp.get(0));
                //System.out.println(nctemp.get(0));
                //System.out.println(nltemp.toString());
                int num_loci_=nltemp.size();
                int num_seq=0;
                String seqmax=null;
                int maxfreqseq=0;
                int flag=0;
                Iterator steppernladd = nctemp.iterator();
                while (steppernladd.hasNext() ) {
                    Integer nclistadd = (Integer)steppernladd.next();
                    //System.out.println(nclistadd);
                    //update query, add cons info to table expnameclus, cons=1 where nc==nclistadd
                    if (!(idlist.contains(nclistadd))){
                        idlist.add(nclistadd);
                        num_seq+=seqlist.get(nclistadd);

                        ResultSet rsseqs=statment.executeQuery("select `seq`,`freq` from `"+namepro+"`.`"+expname+"clusraw` where `nc`="+nclistadd);
                        while(rsseqs.next()){
                            if (maxfreqseq<rsseqs.getInt(2)){
                                maxfreqseq=rsseqs.getInt(2);
                                seqmax=rsseqs.getString(1);
                            }
                        }
//                        System.out.println(nclistadd);
                        statment.executeUpdate("update  `"+namepro+"`.`"+expname+"clus` set `cons`=1,`id`="+idind+" where `nc`="+nclistadd);
                        statment.executeUpdate("update  `"+namepro+"`.`"+expname+"clusmap` set `id`="+idind+",`nl`="+num_loci_+" where `nc`="+nclistadd);
                        statment.executeUpdate("update `"+namepro+"`.`"+expname+"clusraw` set `id`="+idind+" where `nc`="+nclistadd);
                        
                        flag=1;
                    }
                }
                //update id==idind numseq,numloci
                if (flag==1){
                //System.out.println(" "+num_seq_);
                statment.executeUpdate("update  `"+namepro+"`.`"+expname+"clusmap` set `ns`="+num_seq+",`seq`='"+seqmax+"',`freq`='"+maxfreqseq+"',`len`="+seqmax.length()+" where `id`="+idind);
                }
                
                
            }

        }

        rs=statment.executeQuery("select count(*) from (select `id` from `"+namepro+"`.`"+expname+"clusmap` where `id`>0 group by `id`) as t");
        rs.next();
        int num=rs.getInt(1);
        statment.close();
        con.close();
        seqlist.clear();
        nctemp.clear();
        nltemp.clear();
        nc.clear();
        nl.clear();

        return num;
   }
   public static int dobedfile (String expname,String pathout) throws SQLException, IOException{

        String namepro=SeqBusterView.loaprotext.getText();
        //String namepro="spsmir";
        //track name="ItemRGBDemo" description="Item RGB demonstration" itemRgb="On"
        //chr7  127471196  127472363  Pos1  0  +  127471196  127472363  255,0,0
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        TreeMap<Integer,Integer> ncinfo=new TreeMap<Integer,Integer>();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+expname+"/cluster.bed")));
        String black="0,0,0";
        String green="50,205,50";
        String blue="0,0,255";
        String grey="211,211,211";
        ResultSet rs=statment.executeQuery("select `nc`,`cons` from `"+namepro+"`.`"+expname+"clus`");
        while (rs.next()){
            ncinfo.put(rs.getInt(1),rs.getInt(2));
        }
        out.write("track name=\""+expname+"\" description=\"usRNAs positions\" itemRgb=\"On\"\n");
        rs=statment.executeQuery("select `id`,`nc`,`chr`,`start`,`end`,`strand` as freq from `"+namepro+"`.`"+expname+"clusmap`  order by `id` DESC ");
       while (rs.next()){
        if (rs.getInt(1)==0){
            //System.out.printf("%s\t%s\t%s\t%s\t0\t%s\t%s\t%s\t%s\n",rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(2),rs.getString(6),rs.getInt(4),rs.getInt(5),black);
            out.printf("%s\t%s\t%s\t%s\t0\t%s\t%s\t%s\t%s\n",rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(2),rs.getString(6),rs.getInt(4),rs.getInt(5),black);
        }else{
            String color="";
            if(ncinfo.get(rs.getInt(2))==1){
                color=green;
            }else if(ncinfo.get(rs.getInt(2))>1 & ncinfo.get(rs.getInt(2))<10){
                color=blue;
            }else {
                color=grey;
            }
            //System.out.printf("%s\t%s\t%s\t%s\t0\t%s\t%s\t%s\t%s\n",rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(2),rs.getString(6),rs.getInt(4),rs.getInt(5),color);
            out.printf("%s\t%s\t%s\t%s\t0\t%s\t%s\t%s\t%s\n",rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(2),rs.getString(6),rs.getInt(4),rs.getInt(5),color);
        }

       }
       ncinfo.clear();
        out.close();
        return 1;
   }
   public int consistency2 (String expname) throws SQLException{

        String namepro=SeqBusterView.loaprotext.getText();
        //String namepro="spsmir";
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        ArrayList<Integer>  nctemp=new ArrayList<Integer>();
        TreeMap<Integer,ArrayList<Integer>> nc = new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<Integer,ArrayList<Integer>> nl = new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<Integer,ArrayList<Integer>> nlcons = new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<Integer,TreeMap<Integer,Integer[]>> nlncpos = new TreeMap<Integer,TreeMap<Integer,Integer[]>>();
         ArrayList<Integer> idlist=new ArrayList<Integer>();
         int idind=0;
         TreeMap<String,Integer[]> ncnlpostemp = new TreeMap<String,Integer[]>();
        ArrayList<Integer>  ncingroup=new ArrayList<Integer>();
        ArrayList<Integer>  nlconstemp=new ArrayList<Integer>();
         ArrayList<Integer>  nltemp=new ArrayList<Integer>();
        Map<Integer,Integer> nlsort = new HashMap<Integer,Integer>();
        Map<Integer,Integer> nlsorttemp = new HashMap<Integer,Integer>();
        ArrayList<Integer>  nlcommon=new ArrayList<Integer>();
        TreeMap<Integer,Integer> seqlist = new TreeMap<Integer,Integer>();
        ResultSet rs=statment.executeQuery("select `nc`,count(*),max(freq) as freq from `"+namepro+"`.`"+expname+"clusraw`  where `freq`>0 group by `nc`");
        while(rs.next()){
//            System.out.println(rs.getInt(1)+" "+rs.getInt(2));
            seqlist.put(rs.getInt(1),rs.getInt(2));
        }

        rs=statment.executeQuery("select `id`,`nc`,`nl`,`chr`,`start`,`end` from `"+namepro+"`.`"+expname+"clusmap` where `id`<0");
        while(rs.next()){
//            System.out.println(rs.getInt(1)+" "+rs.getInt(2));

                //rs.getInt(1)
                String name=-1*rs.getInt(1)+"-"+rs.getInt(2);
                Integer [] position= new Integer[2];
                position[0]=rs.getInt(5);
                position[1]=rs.getInt(6);
                ncnlpostemp.get(name);
                ncnlpostemp.put(name,position);
                
            
        }
        rs=statment.executeQuery("select * from `"+namepro+"`.`"+expname+"clus` where `id`=0");
        while(rs.next()){
            //nc nl<=>ncs
 //           System.out.println("nl 79 "+rs.getInt(1)+" "+rs.getInt(2));
            if (nl.containsKey(rs.getInt(1))){
                //System.out.println(" add to nl "+rs.getInt(1)+" new cluster "+rs.getInt(2));
                nltemp=nl.get(rs.getInt(1));
                nltemp.add(rs.getInt(2));
                nl.put(rs.getInt(1),nltemp);

                int update=nlsort.get(rs.getInt(1))+1;
                nlsort.put(rs.getInt(1),update);
            }else{
                //System.out.println(" new nl "+rs.getInt(1)+" new cluster "+rs.getInt(2));
                ArrayList<Integer>  nlnew=new ArrayList<Integer>();
                nlnew.add(rs.getInt(2));
                nl.put(rs.getInt(1),nlnew);
                nlsort.put(rs.getInt(1),1);
            }
            //nl nc<=>nls
            if (nc.containsKey(rs.getInt(2))){
                //System.out.println(" add to nc "+rs.getInt(2)+" new loci "+rs.getInt(1));
                nctemp=nc.get(rs.getInt(2));
                nctemp.add(rs.getInt(1));
                nc.put(rs.getInt(2),nctemp);
            }else{
                //System.out.println(" new nc "+rs.getInt(2)+" new loci "+rs.getInt(1));
                ArrayList<Integer>  ncnew=new ArrayList<Integer>();
                ncnew.add(rs.getInt(1));
                nc.put(rs.getInt(2),ncnew);
            }
        }
        rs.close();

        clustertype ct=new clustertype();
        clustertype ct2=new clustertype();
        ct.nc=nc;
        ct.nl=nl;
        ct.ncnlp=ncnlpostemp;
        for (Integer ncs : nc.keySet()) {
             if (!ct.nccons.containsKey(ncs)){

                    ct.ncname=ncs;
                    ct.consflag=1;
                    
                    int cycles=0;
                    //System.out.println("ncs "+ct.ncname+" cycles "+cycles);
                    while (ct.consflag==1){
                        //System.out.println("ncs "+ct.ncname+" cycles "+cycles);
                        ct2=constcycle(ct);
                        ct=ct2;
                        cycles++;
                        if (cycles>=100){
                            ct.consflag=10;
                            break;
                        }
                        //tools.showcerror("flag "+ct.consflag);
                    }
                    
                    ct.cycles.put(ncs, cycles);
                    
                    //if all ncs related are in the nlcons -> cons

                    //else remove that nc
                }
            }
            nl=ct.nlcons;
            nc=ct.nccons;
            //SELECT max(id) FROM `hsa25yclusmap`
            rs=statment.executeQuery("SELECT max(id) from `"+namepro+"`.`"+expname+"clusmap` ");
            rs.next();
            idind=rs.getInt(1);
            for (int ncs : nc.keySet()) {
                  //System.out.println(ncs+" "+nc.get(ncs).toString()+"\n");
                if (!(idlist.contains(ncs))){
                    idind++;

                
                nltemp=nc.get(ncs);
//                //System.out.println(nctemp.get(0));
//                //System.out.println(nltemp.toString());
                int num_loci=nltemp.size();
                int num_seq=0;
                String seqmax=null;
                int maxfreqseq=0;
                int maxcycles=2;
                for (int nls : nltemp){
                    nctemp=nl.get(nls);
                    num_seq=0;
                    seqmax=null;
                    maxfreqseq=0;
                    maxcycles=2;
                    //int flag=0;
                    for (int ncs2:nctemp){

                        idlist.add(ncs2);
                        num_seq+=seqlist.get(ncs2);
                        if (ct.cycles.get(ncs)>maxcycles){
                            maxcycles=ct.cycles.get(ncs);
                        }
                        ResultSet rsseqs=statment.executeQuery("select `seq`,`freq` from `"+namepro+"`.`"+expname+"clusraw` where `nc`="+ncs2);
                        while(rsseqs.next()){
                            if (maxfreqseq<rsseqs.getInt(2)){
                                maxfreqseq=rsseqs.getInt(2);
                                seqmax=rsseqs.getString(1);
                            }
                        }
//                        System.out.println(nclistadd);
                        statment.executeUpdate("update  `"+namepro+"`.`"+expname+"clus` set `cons`="+maxcycles+",`id`="+idind+" where `nc`="+ncs2 );
                        statment.executeUpdate("update  `"+namepro+"`.`"+expname+"clusmap` set `id`="+idind+",`nl`="+num_loci+" where `nc`="+ncs2+" and `ncl`="+nls);
                        statment.executeUpdate("update `"+namepro+"`.`"+expname+"clusraw` set `id`="+idind+" where `nc`="+ncs2);
                        //System.out.println("update  `"+namepro+"`.`"+expname+"clus` set `cons`=1,`id`="+idind+" where `nc`="+ncs2+" and `ncl`="+nls+"\n");
                        //System.out.println("update `"+namepro+"`.`"+expname+"clusraw` set `id`="+idind+" where`nc`="+ncs2+" and `ncl`="+nls+"\n");
                        //System.out.println("update  `"+namepro+"`.`"+expname+"clusmap` set `id`="+idind+",`nl`="+num_loci+" where `nc`="+ncs2+" and `ncl`="+nls+"\n");

                        //flag=1;


                    }
                    //System.out.println(nclistadd);
                    //update query, add cons info to table expnameclus, cons=1 where nc==nclistadd
                    
                }
                //update id==idind numseq,numloci
                //if (flag==1){
                //System.out.println("update  `"+namepro+"`.`"+expname+"clusmap` set `ns`="+num_seq+",`seq`='"+seqmax+"',`freq`='"+maxfreqseq+"',`len`="+seqmax.length()+" where `id`="+idind);
                statment.executeUpdate("update  `"+namepro+"`.`"+expname+"clusmap` set `ns`="+num_seq+",`seq`='"+seqmax+"',`freq`='"+maxfreqseq+"',`len`="+seqmax.length()+" where `id`="+idind);
                //tools.showcerror("wait");
                //}
            }
//
            }
  

        rs=statment.executeQuery("select count(*) from (select `id` from `"+namepro+"`.`"+expname+"clusmap` where `id`>0 group by `id`) as t");
        rs.next();
        //System.out.println(rs.getInt(1)+"\n");
        int num=rs.getInt(1);
        statment.close();
        con.close();
        seqlist.clear();
        nctemp.clear();
        nltemp.clear();
        nc.clear();
        nl.clear();

        return num;
   }
   public int nocons(String expname) throws SQLException{
       String namepro=SeqBusterView.loaprotext.getText();
       Connection con =  tools.docon(namepro);
       Statement statment =  con.createStatement();
       Statement statment2 =  con.createStatement();
       ResultSet rs=statment.executeQuery("select `nc`,`id` from `"+namepro+"`.`"+expname+"clusmap` where `id`<0 group by `nc`");
       while (rs.next()){
           int nclistadd=rs.getInt(1);
           int nl=rs.getInt(2);
            int num_seq=0;
            String seqmax=null;
            int maxfreqseq=0;
            ResultSet rsseqs=statment2.executeQuery("select `seq`,`freq` from `"+namepro+"`.`"+expname+"clusraw` where `nc`="+nclistadd);
            while(rsseqs.next()){
                if (maxfreqseq<rsseqs.getInt(2)){
                    maxfreqseq=rsseqs.getInt(2);
                    seqmax=rsseqs.getString(1);
                }
            }
            rsseqs.close();
            statment2.executeUpdate("update  `"+namepro+"`.`"+expname+"clusmap` set `ns`="+num_seq+",`seq`='"+seqmax+"',`freq`='"+maxfreqseq+"',`len`="+seqmax.length()+" where `nc`="+nclistadd+" and `id`<0");
            statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clusraw` set `id`="+nl+" where `id`=0 and `nc`="+nclistadd);
            statment2.executeUpdate("update `"+namepro+"`.`"+expname+"clus` set `id`="+nl+" where `id`=0 and `nc`="+nclistadd);
     }
     rs.close();

     statment.close();
     statment2.close();
            return 1;
   }


    public void mapping (String expname,String proname,String db,String sp) throws IOException{
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            
            String pathout=SeqBusterView.pathout.getText()+"/";
            
            Connection con =  tools.docon("seqbuster");
            Statement statment =  con.createStatement();
            Statement statment2 = con.createStatement();
            ResultSet rs=null;
            int id=0;
            ArrayList<Integer> indexstart = new ArrayList<Integer >();
            ArrayList<Integer> index = new ArrayList<Integer >();
            //reeMap<Integer, Integer> data = new TreeMap<Integer,Integer>();
            ArrayList<Integer[]> data = new ArrayList<Integer[] >();
            ArrayList<String[]> datainfo = new ArrayList<String[] >();
            ArrayList<String> chrlist = new ArrayList<String >();
            BufferedReader in=null;

            rs=statment.executeQuery("select `chr` from `"+proname+"`.`"+expname+"clusmap` where `chr` like '%chr%' group by `chr`");

            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + proname + "/" + expname + "/"+db+"_"+sp+".class")));

           

            while(rs.next()){
                chrlist.add(rs.getString(1));
            }
            int mapped=0;
            for (int j=0;j<chrlist.size();j++){
            rs=statment.executeQuery("select * from `tracks`.`"+db+"_"+sp+"` where `chr` like '"+chrlist.get(j)+"' order by `start` asc");
            indexstart.clear();
            index.clear();
            while(rs.next()){
                indexstart.add(Integer.parseInt(rs.getString(2)));
                index.add(Integer.parseInt(rs.getString(3)));
                //System.out.printf("load file %d\n",Integer.parseInt(rs.getString(3)));
            }
//            System.out.println(chrlist.get(j));
            if (index.size()>0){
            rs=statment.executeQuery("select * from `"+proname+"`.`"+expname+"clusmap` where `chr` like '"+chrlist.get(j)+"' group by `id` order by `start` asc ");
            int ind1=0,ind2=0;
            int indexfile=index.get(0);
            //int startdata=0;
            Integer current_pos=0,current_pos_index=0;
            while(rs.next()){
                int start=rs.getInt("start");
                int end=rs.getInt("end");
                String st=rs.getString("strand");
                id=rs.getInt("id");
                int p=(start+end)/2;
                //what index, has to change
//                System.out.printf("pos %d indexfile %d start %d  current_pos_index %d \n",p,indexfile,current_pos,current_pos_index);
                if (p>current_pos & current_pos_index==index.size()-1 & index.size()>1){
                    //System.out.println("break");
                    break;
                }
                if (p>current_pos ){
                    //current_pos=indexstart.get(current_pos_index);
                    //what index corresponding
//                    System.out.printf("load file \n");
                    for (int i=current_pos_index;i<index.size();i++){
                        //System.out.printf("i %d \n",i);
                        if (p<indexstart.get(i)){
                            ind1=0;
                            indexfile=index.get(i);
                            current_pos_index=i;
                            current_pos=indexstart.get(i);
//                            System.out.printf("index founded %d start %d \n",indexfile,indexstart.get(i) );
                            break;
                        }
                    }
                    try {
                        //read new data
                        //System.out.printf("read data \n");
                        in = new BufferedReader(new FileReader("DB/"+db+"_"+sp+"/track_"+db+"_"+sp+"_" + indexfile));
                        String l;
                        data.clear();
                        datainfo.clear();
                        try {
                            while ((l = in.readLine()) != null) {
                                String[] col = l.split("\t");
//                                System.out.printf("data %s\n",l);
                                if (col.length==3){
                                    Integer [] temp={Integer.parseInt(col[0]),Integer.parseInt(col[1])};
                                    data.add(temp);
                                    String [] temp2={"a",col[2]};
                                    datainfo.add(temp2);
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Error!!!!.Copy this message and send to lorena.pantano@crg.es : " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error!!!!.Copy this message and send to lorena.pantano@crg.es : " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                //startdata=1;
//                System.out.printf("search ind1  %d\n",ind1);
                int newind=searchb(p,ind1,data);
//                System.out.printf("search new ind  %d\n",newind);
                ind1=newind;
                if (ind1<0){
                    //not mapped
                    //System.out.printf("non mapped %d pos %d start %d\n",ind1,p,start);

                    ind1=-1*ind1;
                    if (ind1==-1){ind1=0;}
//                    break;
                }else{
                    
                    //+/-,name,from start%
                    String [] tempdata=datainfo.get(ind1);
                    Integer [] tempdata2=data.get(ind1);
                    //String sthit=tempdata[0];
                    String nmhit=tempdata[1];
                    String nmhitfinal="";
//                    System.out.printf("mapped %s\n",nmhit);
                    int countnm=0;
                    if (nmhit.contains(",")){
                        String [] nmt = nmhit.split(",");
                        for (int c=0;c<nmt.length;c++){
                            if (!nmt[c].isEmpty() & countnm<7){
                                //check sense
//                                System.out.printf("mapped %s\n",nmt[c]);
                                String [] sensenmt = nmt[c].split(":");
                                String sensenmtcurrent=getsense(sensenmt[1],st);
                                nmhitfinal=nmhitfinal+sensenmt[0]+":"+sensenmtcurrent+",";
                                countnm++;
                            }

                        }

                    }
                    //write matches for this locus
                    
                    //if + coord-start
                    int pos=0;
                    
                        pos=start;
                        //double pos2=((pos*1.0)/(tempdata2[1]-tempdata2[0])*100.0);
                        //pos=(int) Math.round(pos2);
                        
                    int nc=rs.getInt("id");
                    //System.out.println("update `"+proname+"`.`"+expname+"clusmap` set `"+db+"_"+sp+"`='"+nmhitfinal+"' where `nc`="+nc +" and `start` = "+start+"\n");
//                    if (nc==90){
//                       System.out.println("update `"+proname+"`.`"+expname+"clusmap` set `"+db+"`='"+nmhitfinal+"' where `nc`="+nc+"and `start` = "+start+"\n");
//                    }
                    statment2.executeUpdate("update `"+proname+"`.`"+expname+"clusmap` set `"+db+"_"+sp+"`='"+nmhitfinal+"' where `id`="+nc +" and `start` = "+start);
                   
                    out.write(nc+"\t"+nmhitfinal+"\t"+pos+"\t"+chrlist.get(j)+":"+tempdata2[0]+"-"+tempdata2[1]+":"+st+"\n");
                    //mapped
                    ind1--;
                    mapped++;
                }

            }
            }
        }


//          System.out.printf("num mapped %d\n",mapped);
            out.close();
            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }


   }

    public static int doclusinfo(String expname,int idnum,int idunum,int idflag) throws SQLException, IOException{
       String namepro=SeqBusterView.loaprotext.getText();
       int done=0;
       Connection con =  tools.docon(namepro);
       Statement statment =  con.createStatement();
       Statement statment2 =  con.createStatement();
       PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("temp/cluster.info")));
       String idname="idu";
       int id=idunum;
       if (idflag==1){
            idname="id";
            id=idnum;
        }
       String readsamples = "select `id`,`ncl`,`nc` from `"+namepro+"`.`"+expname+"clusmap` where `"+idname+"`="+id;
       
       ResultSet rs=statment.executeQuery(readsamples);
       if (rs.next()){
           done=1;
           int nl=rs.getInt(2);
           //System.out.println(nl);
           rs=statment.executeQuery("select `nc`,`chr`,`start`,`end` from `"+namepro+"`.`"+expname+"clusmap` where `"+idname+"`="+id+" and `ncl`="+nl);
           while (rs.next()){
               int nclistadd=rs.getInt(1);
               int start=rs.getInt(3);

                ResultSet rsseqs=statment2.executeQuery("select `seq`,`freq`,`type` from `"+namepro+"`.`"+expname+"clusraw` where `freq`>0 and `nc`="+nclistadd );
                while(rsseqs.next()){
                    int pos=start+rsseqs.getInt(3);
                    int len=rsseqs.getString(1).length();
                    int end=pos+len;
                    //System.out.println(pos+"\t"+rsseqs.getInt(2)+"\t"+rsseqs.getString(1)+"\n");
                    out.write(pos+"\t"+end+"\t"+rsseqs.getInt(2)+"\t"+rsseqs.getString(1)+"\n");
                }
                rsseqs.close();

            }
           out.close();
       }
     rs.close();

     statment.close();
     statment2.close();

     //R scripts
     return done;
   }

    private String getsense (String mother, String child){
        String result=null;
        if (mother.contains(child)){
            result="+";
        }else{
            result="-";
        }
        return result;
    }

    private boolean compare (String mother,String child){
        boolean result=false;
        return result;
    }
    private String getmaxcoin (String mother,String child){
        String result=null;
        
        return result;
    }
    public int classify(String db,String expname, String proname,String sp) throws SQLException{
        

          
            TreeMap<Integer,TreeMap<String,Integer>> data = new TreeMap<Integer,TreeMap<String,Integer>>();
            TreeMap<String,Integer> tempdata =new TreeMap<String,Integer>();
            HashMap<Integer,Integer> totalids = new HashMap<Integer,Integer>();
            Connection con =  tools.docon("seqbuster");
            Statement statment =  con.createStatement();
            ResultSet rs = statment.executeQuery("select `id`,`"+db+"_"+sp+"` from `"+proname+"`.`"+expname+"clusmap` ");
            while (rs.next()){
                String name = rs.getString(2);
//                System.out.println(rs.getString(1)+" "+rs.getString(2)+"\n");
//                if (rs.getInt("id")==15){
//                        System.out.println(rs.getString(1)+" "+rs.getString(2)+"\n");
//                    }
                if (data.containsKey(rs.getInt("id"))){
                    tempdata=data.get(rs.getInt("id"));
                    totalids.put(rs.getInt("id"),totalids.get(rs.getInt("id"))+1);
                    
                    if (name.contains(",")){
                        String [] nmt = name.split(",");
                        for (int c=0;c<nmt.length;c++){
                            if (!nmt[c].isEmpty()){
                                //check sense
                                
                                String [] sensenmt = nmt[c].split(":");
//                                System.out.printf("mapped %s\n",sensenmt[0]);
                                if (tempdata.containsKey(nmt[c])){
                                    tempdata.put(nmt[c],tempdata.get(nmt[c])+1);
                                }else{
                                    tempdata.put(nmt[c],1);
                                }
                            }

                        }

                    }
                    
                    
                    data.put(rs.getInt("id"),tempdata);
                }else{
                    totalids.put(rs.getInt("id"),1);
//                    System.out.println("adding to new id\n");
                    TreeMap<String,Integer> temp =new TreeMap<String,Integer>();
                    if (name.contains(",")){
                        String [] nmt = name.split(",");
                        for (int c=0;c<nmt.length;c++){
                            if (!nmt[c].isEmpty()){
                                //check sense
                                
                                String [] sensenmt = nmt[c].split(":");
//                                System.out.printf("mapped %s\n",sensenmt[0]);
                                temp.put(nmt[c],1);
                            }

                        }

                    }
                    
                    data.put(rs.getInt("id"),temp);
                }
            }


            for (Integer id : data.keySet()) {
                tempdata= data.get(id);
                int max=0;
                String classrna="0";
                int total=0;
                for (String n : tempdata.keySet()) {
                    if (max<tempdata.get(n)){
                        max=tempdata.get(n);
                        classrna=n;
                    }
                    
                }
                total=totalids.get(id);

                double r=max*1.0/total;
                double limit=2*1.0/3;
                if (r>=limit){
                    statment.executeUpdate("update `"+proname+"`.`"+expname+"clusmap` set `"+db+"_"+sp+"`='$"+classrna+"' where `id`="+id);
//                    System.out.println("update `"+proname+"`.`"+expname+"clusmap` set `"+db+"`='$"+classrna+"' where `id`="+id);

                }

            }

            rs=statment.executeQuery("select count(*) from (select `id` from `"+proname+"`.`"+expname+"clusmap` where `"+db+"_"+sp+"` not like 'na' and `"+db+"_"+sp+"` not like '0'  and `id`>0 group by `id`) as t");
            rs.next();
            totalids.clear();
            tempdata.clear();
            data.clear();
            int num=rs.getInt(1);
            statment.close();
            con.close();
            return num;

    }

    public void idunique(String expname,String proname) throws SQLException{




            TreeMap<Integer,TreeMap<String,Integer>> data = new TreeMap<Integer,TreeMap<String,Integer>>();
            TreeMap<String,Integer> tempdata =new TreeMap<String,Integer>();


            Connection con =  tools.docon(proname);
            Statement statment = con.createStatement();
//            statment.executeUpdate("update `"+proname+"`.`"+expname+"clusmap` set `idu`=0");
//            ResultSet rs=statment.executeQuery("select `id` from `"+proname+"`.`"+expname+"clusmap` where `id`0");
//            while(rs.next()){
                statment.executeUpdate("update `"+proname+"`.`"+expname+"clusmap` set `idu`=`id`" );

//            }
//            rs.close();
            statment.close();
            con.close();
    
    }

    public void iduniqueadd(String expname,String proname) throws SQLException{
             ArrayList<String> explist = new ArrayList<String >();
            int maxidu=0;
            TreeMap<String,ArrayList<Integer[]>> data = new TreeMap<String,ArrayList<Integer[]>>();
            TreeMap<String,ArrayList<Integer[]>> dataset = new TreeMap<String,ArrayList<Integer[]>>();
            ArrayList<Integer[]> tempdata =new ArrayList<Integer[]>();
            ArrayList<Integer[]> tempdataset =new ArrayList<Integer[]>();
            TreeMap<Integer,Integer> doit = new TreeMap<Integer,Integer>();

            Connection con =  tools.docon(proname);
            Statement statment =  con.createStatement();
            Statement statment2 = con.createStatement();
            ResultSet rs=statment.executeQuery("select `idu` from `"+proname+"`.`"+expname+"` where `idu` >0'");
            if (!rs.next()){
            
                statment.executeUpdate("update `"+proname+"`.`"+expname+"clusmap` set `idu`=0");
                rs=statment.executeQuery("select `name` from `"+proname+"`.`experiments` where `name` like '%clusmap%'");
                while(rs.next()){
                    if (!rs.getString(1).matches(expname+"clusmap")){
                        explist.add(rs.getString(1));
                    }
                    //System.out.println(rs.getString(1));
                    ResultSet rs2=statment2.executeQuery("select max(`idu`) from `"+proname+"`.`"+rs.getString(1)+"`");
                    rs2.next();
                    if (rs2.getInt(1)>maxidu){
                          maxidu=rs2.getInt(1);
                    }
                    rs2.close();
                }

                //read data new sample
                rs=statment.executeQuery("select `idu`,`id`,`chr`,`start`,`end`,`strand` from `"+proname+"`.`"+expname+"clusmap` where `id`>0 order by `chr` asc,`start` asc");
                while (rs.next()){
                    int strand=1;
                        if (rs.getString(6).matches("-")){
                            strand=2;
                        }
                    if (data.containsKey(rs.getString(3))){
                        tempdata=data.get(rs.getString(3));
                        Integer [] newdata2={rs.getInt(4),rs.getInt(5),strand,rs.getInt(1),rs.getInt(2)};
                        tempdata.add(newdata2);
                        data.put(rs.getString(3),tempdata);
                    }else{
                        ArrayList<Integer []> newdata =new ArrayList<Integer []>();
                        Integer [] newdata2={rs.getInt(4),rs.getInt(5),strand,rs.getInt(1),rs.getInt(2)};
                        newdata.add(newdata2);
                        data.put(rs.getString(3),newdata);
                    }

                }

                for (int i=0;i<explist.size();i++){
                    //read data samples
                    rs=statment.executeQuery("select `idu`,`id`,`chr`,`start`,`end`,`strand` from `"+proname+"`.`"+explist.get(i)+"` where `idu`>0 order by `chr` asc,`start` asc");
    //                System.out.println(explist.get(i));
                    while (rs.next()){
                        int strand=1;
                        if (rs.getString(6).matches("-")){
                            strand=2;
                        }
                    if (dataset.containsKey(rs.getString(3))){
                        tempdata=dataset.get(rs.getString(3));

                        Integer [] newdata2={rs.getInt(4),rs.getInt(5),strand,rs.getInt(1),rs.getInt(2)};
                        tempdata.add(newdata2);
                        dataset.put(rs.getString(3),tempdata);
                    }else{
                        ArrayList<Integer []> newdata =new ArrayList<Integer []>();
                        Integer [] newdata2={rs.getInt(4),rs.getInt(5),strand,rs.getInt(1),rs.getInt(2)};
                        newdata.add(newdata2);
                        dataset.put(rs.getString(3),newdata);
    //                    System.out.printf("%s %d %d\n",rs.getString(3),rs.getInt(4),rs.getInt(5));
                    }

                    }
                    //foreach chR in new sample
                    Boolean empty=false;
                    for (String c : data.keySet()) {
                        if (dataset.containsKey(c)){
                            tempdataset=dataset.get(c);
                            empty=false;
                        }else{
                            empty=true;
                        }
                        //foreach locus
                        tempdata=data.get(c);
                        for (int j=0;j<tempdata.size();j++){
                            //get pos

                            Integer [] datapos = tempdata.get(j);
                            //System.out.printf("%s %d \n", c, datapos[4]);
                            int id=datapos[4];
                            if (!doit.containsKey(id)){
                                int pos = datapos[0] + (datapos[1] - datapos[0]) / 2;
                               // System.out.printf("%s %d %d\n", c, datapos[3], pos);
                                int ind=-1;
                                if (empty==false){
                                   // System.out.printf("searching \n");
                                    ind = searchbidu(pos, 0, tempdataset);
                                }
                                //System.out.println(ind);
                                if (ind > -1) {
                                    Integer[] datasetarray = tempdataset.get(ind);
                                    if (datasetarray[2] == datapos[2]) {
                                        int idu = datasetarray[3];
                                        doit.put(id, 0);
                                       // System.out.println("add "+idu+"to "+id);
                                        statment.executeUpdate("update `" + proname + "`.`" + expname + "clusmap` set `idu`=" + idu + " where `id`=" + id);

                                    }
                                 } else {
                                        maxidu++;
                                        doit.put(id, 0);
                                        //System.out.println("new add "+maxidu+"to "+id);
                                        statment.executeUpdate("update `" + proname + "`.`" + expname + "clusmap` set `idu`=" + maxidu + " where `id`=" + id);
                                 }
                            }
                        }
                    }



                }
            }
            doit.clear();
            data.clear();
            dataset.clear();
            tempdata.clear();
            tempdataset.clear();
            rs.close();
//            rs2.close();
            statment2.close();
            statment.close();
            con.close();

    }
    public static int removepred (String expname,String namein) throws SQLException, IOException{

        String namepro=SeqBusterView.loaprotext.getText();
        //String namepro="spsmir";
        //track name="ItemRGBDemo" description="Item RGB demonstration" itemRgb="On"
        //chr7  127471196  127472363  Pos1  0  +  127471196  127472363  255,0,0
        Connection con =  tools.docon(namepro);
        Statement statment =  con.createStatement();
        String l="";
        BufferedReader in= new BufferedReader(new FileReader(namein));
        ResultSet rs=null;
        int count=0;
        while ((l=in.readLine())!=null){
            statment.executeUpdate("update `"+namepro+"`.`"+expname+"` set `DB`='pred' where `seq` like  '"+l+"'");
            count++;
        }
        HelpDesk.addinfo("Number of sequences removed: "+count);
        tools.showinfo("Remove: "+count+ " sequences.");
        statment.close();


        return count;
   }
    public int searchbidu(int pos, int i1, ArrayList<Integer[]> d){

            int f=0;
            Boolean stop=false;
            int i2=d.size();
            int num=0;
            int indtemp=0;
//            int ind=0;
//            Integer [] ini= {0,0,0,0,0};
//            d.add(0,ini);

            while (stop==false ){
                num++;

                int indm=Math.round((i1+i2)/2);

                //System.out.printf("i1 %d i2 %d mid %d f %d\n",i1,i2,indm,f);
                Integer [] data = d.get(indm);
//                Integer [] datap = d.get(indm-1);
               // System.out.printf("pos %d start %d end %d\n",pos,data[0],data[1]);

                if (pos>data[0] & pos<data[1]){
                    f=indm;
                    stop=true;
                //    System.out.printf("mapped f %d\n",f);
                }else{

                    if ((i1 == indm | i2 == indm | indtemp == indm) & f == 0) {
                        f = -indm;

                        if (f == 0) {
                            f = -1;
                        }
                        stop = true;
                    }

                    if (pos > data[1]) {
                        i1 = indm;

                    } else if (pos < data[0]) {
                        i2 = indm;

                    }
                }
                indtemp=indm;
                

            }
            //System.out.printf("return f %d\n",f);
            return f;
        }
    public int searchb(int pos, int i1, ArrayList<Integer[]> d){

            int f=0;
            int i2=d.size();
            int num=0;
            int indtemp=0;
//            int ind=0;
            while (f==0 ){
                num++;

                int indm=Math.round((i1+i2)/2);

//                System.out.printf("i1 %d i2 %d mid %d f %d\n",i1,i2,indm,f);
                Integer [] data = d.get(indm);
//                Integer [] datap = d.get(indm-1);
//                System.out.printf("pos %d start %d end %d\n",pos,data[0],data[1]);

                if (pos>data[0]-10 & pos<data[1]+10){
                    f=indm;
//                    System.out.printf("mapped f %d\n",f);
                }
//                if (pos>datap[0] & pos<datap[1]){
//                    f=indm-1;
//                    //System.out.printf("mapped f %d\n",f);
//                }
                if ((i1==indm | i2==indm | indtemp==indm) & f==0){
                    f=-indm;
                    if (f==0){f=-1;}
                }

                if (pos>data[1]){
                    i1=indm;

                }else if (pos<data[0]){
                    i2=indm;

                }
                indtemp=indm;
                

            }
//            System.out.printf("return f %d\n",f);
            return f;
        }
    
    public String pattern (String seq1,String seq2,int p1,int p2){
        String p=null;
        if (p1>p2){
            p=seq1.substring(0,p1);
            if (seq1.length()-p1>seq2.length()-p2){
                p=p+seq1.substring(p1,seq1.length());
            }else{
                p=p+seq2.substring(p2,seq2.length());
            }

        }else{
            p=seq2.substring(0,p2);
            if (seq1.length()-p1>seq2.length()-p2){
                p=p+seq1.substring(p1,seq1.length());
            }else{
                p=p+seq2.substring(p2,seq2.length());
            }
        }



       return p;
    }


    public int align (String seq1,String seq2,int pos1,int pos2){
        int mism=0;
        //pos1++;
        //pos2++;
        int m=pos2;
        int n=pos1;
        //System.out.println(seq1+" "+seq2);
        //System.out.println(pos1+" "+pos2);
        //System.out.println(seq1.length()+" "+seq2.length());
        while (n>=0 & m>=0){
            //System.out.println("n "+n);

            //for (int m=pos2;m>0;m--){
                //System.out.println("m "+m);
                

                
                //System.out.println("seq1 "+n+" : "+seq1.substring(n, n+1));
                //System.out.println("seq2 "+m+" : "+seq2.substring(m, m+1));
               // System.out.println("mism  : "+mism);
                if (!seq1.substring(n, n+1).matches(seq2.substring(m, m+1))){
                    mism=2;
                }
                m--;
                n--;

                if (m<0 | n<0 | mism==2 ){n=-1;m=-1;}
            //}
        }
        //pos1+=5;
        //pos2+=5;
        m=pos2;
        n=pos1;
        while (n<seq1.length() & m<seq2.length()){
            
            //System.out.println("seq1 "+n+" : ");
            //System.out.println("seq2 "+m+" : ");
            //System.out.println("mism  : "+mism);
            if (!seq1.substring(n, n+1).matches(seq2.substring(m, m+1))){
                    
                    mism=2;
                    //if (m>seq2.length()-3){
                    //    mism=1;
                    //}
            }
            m++;
            n++;
            if (m==seq2.length() | n==seq1.length() | mism>0 ){n=100;m=100;}
        }

        return mism;
    }


    private  ArrayList<Integer> commonelem (ArrayList<Integer> list1, ArrayList<Integer> list2){
        ArrayList<Integer> commone = new ArrayList<Integer>();
        for (Integer e1 : list1) {
            if (!list2.contains(e1)){
                commone.add(e1);
            }
        }
        return commone;
    }

    private  int getoverlap (Integer[] p1,Integer[] p2){
        int overlap=0;
        int start=p1[0];
        int end=p1[1];
        if (p1[0]<p2[0]){
            start=p2[0];
        }
        if (p1[1]>p2[1]){
            end=p2[1];
        }
        overlap=end-start;
        return overlap;
    }

    public  List sortByValue(final Map m) {
        List keys = new ArrayList();
        keys.addAll(m.keySet());
        Collections.sort(keys, new Comparator() {
            public int compare(Object o1, Object o2) {
                Object v1 = m.get(o1);
                Object v2 = m.get(o2);
                if (v1 == null) {
                    return (v2 == null) ? 0 : 1;
                }
                else if (v1 instanceof Comparable) {
                    return ((Comparable) v2).compareTo(v1);
                }
                else {
                    return 0;
                }
            }
        });
        return keys;
    }

    public    clustertype constcycle (clustertype ct){

        TreeMap<Integer,ArrayList<Integer>> nc = new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<Integer,ArrayList<Integer>> nl = new TreeMap<Integer,ArrayList<Integer>>();
       TreeMap<String,Integer[]> ncnlpostemp = new TreeMap<String,Integer[]>();
        ArrayList<Integer>  ncingroup=new ArrayList<Integer>();
         ArrayList<Integer>  nctemp=new ArrayList<Integer>();
        ArrayList<Integer>  nltemp=new ArrayList<Integer>();
        Map<Integer,Integer> nlsort = new HashMap<Integer,Integer>();
        Map<Integer,Integer> nlsorttemp = new HashMap<Integer,Integer>();
        ArrayList<Integer>  nlcommon=new ArrayList<Integer>();
        int constflag=0;

        int ncs=ct.ncname;
        nc=ct.nc;
        nl=ct.nl;
        ncnlpostemp=ct.ncnlp;
        //System.out.printf("nc %s\n", ncs);
                    //get all lc of this nc
                    nltemp=nc.get(ncs);
                    nlsorttemp.clear();
                    //rank nl, to see if all ncs are in a common nls
                    for (Integer nls : nltemp) {
                        nctemp=nl.get(nls);
                       // System.out.printf("##nl %s \n", nls);
                        for (Integer ncst : nctemp) {
                            //save ncs in a list
                            ncingroup.add(ncst);
                            //System.out.printf("###nc %s \n", ncst);
                                if (nlsorttemp.containsKey(nls)){
                                    int update=nlsorttemp.get(nls)+1;
                                    nlsorttemp.put(nls,update);
                                }else{
                                    nlsorttemp.put(nls,1);
                                }
                        }
                    }

                    //tools.showcerror("check consistency");
                    for (Iterator j = sortByValue(nlsorttemp).iterator(); j.hasNext(); ) {
                        //tools.showcerror("nl with morencs");
                     Integer nlranktemp = (Integer) j.next();

                     //System.out.printf("Temporal nl: %s, points: %s\n", nlranktemp, nlsorttemp.get(nlranktemp));
                     //read nc in nksortemp
                     ArrayList<Integer> nctempcon=nl.get(nlranktemp);
                     //System.out.println("ncs in nl: "+nctempcon.toString()+"\n");
                     for (Integer ncstempcon : nctempcon) {
                        //read al nl of this ncstempcom
                         ArrayList<Integer> nltempcon=nc.get(ncstempcon);
                         for (Integer nlstempcon : nltempcon) {
                             // System.out.println("##nl secondary: "+nlstempcon+"in "+ncstempcon+"\n");
                         //read al nc in this nl
                            ArrayList<Integer> nctempcon2=nl.get(nlstempcon);
                            //read nc2 with ncstempcon
                            for (Integer ncstempcon2 : nctempcon2) {
                               // System.out.println("##nc secondary: "+ncstempcon2+"\n");
                               if (!nctempcon.contains(ncstempcon2)){
                                //new nc not in the first list of nc
                                   //System.out.println("$$$$$$$$$$$$$$$new nc: "+ncstempcon2+"\n");

                                   constflag=1;
                                  //nc here but not in nltempcon, nctempcon2 - nctempcon
                                   ArrayList<Integer> dif =commonelem(nctempcon2,nctempcon);
                                   Integer [] postemp=ncnlpostemp.get(nlstempcon+"-"+ncstempcon);
                                    int overlapm=0;
                                   for (Integer difel : dif) {
                                    //get coordinates of difel, and compare to ncltempcon
                                       //System.out.println("-----nctempcon2 - nctempcon calc overlap nc: "+difel+" and: "+ncstempcon+"\n");
                                       Integer [] postemp2=ncnlpostemp.get(nlstempcon+"-"+difel);
                                       int overlap=getoverlap(postemp,postemp2);
                                       if (overlap>overlapm){
                                        overlapm=overlap;
                                       }
                                   }
                                   //nc in nltempcon but not here, nctempcon - nctempcon2
                                   dif =commonelem(nctempcon,nctempcon2);
                                   int overlapm2=0;
                                   postemp=ncnlpostemp.get(nlranktemp+"-"+ncstempcon);
                                   for (Integer difel : dif) {
                                    //get coordinates of difel, and compare to ncltempcon
                                       //System.out.println("-----nctempcon - nctempcon2 calc overlap nc: "+difel+" and: "+ncstempcon+"\n");
                                       Integer [] postemp2=ncnlpostemp.get(nlranktemp+"-"+difel);
                                       int overlap=getoverlap(postemp,postemp2);
                                       if (overlap>overlapm2){
                                        overlapm2=overlap;
                                       }
                                   }
                                  // System.out.println("-----origin "+overlapm2+" secondary "+overlapm+"\n");
                                   if (overlapm2>overlapm){
                                    //System.out.println("------remove "+ncstempcon+" from  "+nlstempcon+"\n");
                                    ArrayList<Integer> t=nc.get(ncstempcon);
                                    t.remove(nlstempcon);
                                    nc.put(ncstempcon,t);
                                    t=nl.get(nlstempcon);
                                    t.remove(ncstempcon);
                                    nl.put(nlstempcon,t);
                                   }else if (overlapm2<overlapm){
                                    //System.out.println("------remove "+ncstempcon+" from  "+nlranktemp+"\n");
                                    ArrayList<Integer> t=nc.get(ncstempcon);
                                    t.remove(nlranktemp);
                                    nc.put(ncstempcon,t);
                                    t=nl.get(nlranktemp);
                                    t.remove(ncstempcon);
                                    nl.put(nlranktemp,t);
                                   }else{
                                    //System.out.println("------remove "+ncstempcon+" from  "+nlranktemp+" and "+nlstempcon+"\n");
                                    ArrayList<Integer> t=nc.get(ncstempcon);
                                    t.remove(nlranktemp);
                                    nc.put(ncstempcon,t);
                                    t=nl.get(nlranktemp);
                                    t.remove(ncstempcon);
                                    nl.put(nlranktemp,t);
                                    
                                    t=nc.get(ncstempcon);
                                    t.remove(nlstempcon);
                                    nc.put(ncstempcon,t);
                                    t=nl.get(nlstempcon);
                                    t.remove(ncstempcon);
                                    nl.put(nlstempcon,t);
                                   }

                                   //tools.showcerror("wait");
                               }
                                if (constflag==1){break;}
                            }
                            if (constflag==1){break;}
                         }
                         if (constflag==1){break;}
                     }
                     if (constflag==1){break;}
                    }
                    //tools.showcerror("end first generation consistency");
        if (constflag==0){
            //load all nc consitency in a list to avoid repeat.
            int max=0;
            for (Iterator j = sortByValue(nlsorttemp).iterator(); j.hasNext(); ) {
                        //tools.showcerror("nl with morencs");
                     Integer nlranktemp = (Integer) j.next();

                     //System.out.printf("rank nl: %s, points: %s\n", nlranktemp, nlsorttemp.get(nlranktemp));
                     //read nc in nksortemp
                     ArrayList<Integer> nctempcon=nl.get(nlranktemp);
                     //System.out.println("ncs in nl: "+nctempcon.toString()+"\n");
                     for (Integer ncstempcon : nctempcon) {
                        //ncstemocon in nlranktemp
                         if (ct.nccons.containsKey(ncstempcon)){
                            ArrayList<Integer> temp=ct.nccons.get(ncstempcon);
                            temp.add(nlranktemp);
                            ct.nccons.put(ncstempcon,temp);
                         }else{
                            ArrayList<Integer> temp=new ArrayList<Integer>();
                            temp.add(nlranktemp);
                            ct.nccons.put(ncstempcon,temp);
                            
                         }
                         if (ct.nlcons.containsKey(nlranktemp)){
                            ArrayList<Integer> temp=ct.nlcons.get(nlranktemp);
                            temp.add(ncstempcon);
                            ct.nlcons.put(nlranktemp,temp);
                         }else{
                            ArrayList<Integer> temp=new ArrayList<Integer>();
                            temp.add(ncstempcon);
                            ct.nlcons.put(nlranktemp,temp);

                         }
                     }
                     if (nlsorttemp.get(nlranktemp)>=max){
                        max=nlsorttemp.get(nlranktemp);
                     }else{
                        break;
                     }
            }
            //tools.showcerror("Find consistency");
        }
        ct.nc=nc;
        ct.nl=nl;
        ct.consflag=constflag;
        return ct;

    }


}
