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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;
import java.util.Date;
import java.util.TreeMap;

//import seqbuster.SeqBusterView.*;
/**
 *
 * @author lo
 */
public class map {

    
        public static int readseq (String namein,String namedb,String posmicros) throws FileNotFoundException, IOException, SQLException{
        String l="";
        int annotate=0;
        Integer namecode=0;
        String last="";
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(namein + ".mirna.ann")));
        PrintWriter outp = new PrintWriter(new BufferedWriter(new FileWriter(namein + ".mirna.opt")));
        TreeMap<String,TreeMap<Integer,Integer>> clusters = new TreeMap<String,TreeMap<Integer,Integer>>();
        TreeMap<String,TreeMap<String,Integer[]>> micropos = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<Integer,Integer> tempclus = new TreeMap<Integer,Integer>();
        TreeMap<Integer,String> hashseq = new TreeMap<Integer,String>();
        TreeMap<String,Integer> checkamb = new TreeMap<String,Integer>();
        TreeMap<String,String> listmirna =  new TreeMap<String,String>();
        TreeMap<String,String> listinfo =  new TreeMap<String,String>();
        TreeMap<Integer,String> nameseq = new TreeMap<Integer,String>();
        
        String sp=(String)MiRNAannFrame.speMirAnnList.getSelectedItem();
        int mm=Integer.parseInt(MiRNAannFrame.MisMirAnnText.getText());
        int add=Integer.parseInt(MiRNAannFrame.AddMirAnnText.getText());
        int tri=Integer.parseInt(MiRNAannFrame.TriMirAnnText.getText());
        outp.printf("Initiated at: %s\n",new Date());
        outp.printf("Mismatches allowed: %s\n",mm);
        outp.printf("Addition allowed: %s\n",add);
        outp.printf("Trimming allowed: %s\n",tri);
        outp.close();
        TreeMap<Integer,TreeMap<String,alignment>> mapinfo = new TreeMap<Integer,TreeMap<String,alignment>>();
        TreeMap<Integer,Double> scoreseq = new TreeMap<Integer,Double>();
        TreeMap<String,String> preseq = new TreeMap<String,String>();
        //String pathout=seqbusterView.pathout.getText()+"/";
        String nameexp = (String)MiRNAannFrame.nameexp.getSelectedItem();
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con =   tools.docon(namepro);
        Statement statment = con.createStatement();
        //System.out.println(new Date()+"\n");

        BufferedReader inmi= new BufferedReader(new FileReader(posmicros));
         while ((l=inmi.readLine())!=null){
            if (l.contains(">")){
                String[] pre=l.split(" ");
                pre[0]=pre[0].replace(">", "");
                l=l.replaceAll("\\]",",");
                l=l.replaceAll("\\[",",");
                String [] name=l.split(",");
                for (int i=1;i<name.length;i++){
                  if (name[i].contains("-")){
                    //System.out.println("#"+name[i]);
                    String [] namepos=name[i].split(":");
                    //System.out.println(namepos[1]);
                    String [] pos=namepos[1].split("-");
                    Integer [] posi= new Integer[2];
                    posi[0]= Integer.parseInt(pos[0]);
                    posi[1]= Integer.parseInt(pos[1]);
                    if (micropos.containsKey(pre[0])){
                        TreeMap<String,Integer[]> mi=micropos.get(pre[0]);
                        mi.put(namepos[0],posi);
                        micropos.put(pre[0],mi);
                    }else{
                        TreeMap<String,Integer[]> mi= new TreeMap<String,Integer[]>();
                        mi.put(namepos[0],posi);
                        micropos.put(pre[0],mi);
                    }
                    //System.out.println(pre[0]+" "+namepos[0]+" "+pos[0]+" "+pos[1]+"\n");
                  }
                }
            }
        }
//        TreeMap<String,Integer[]> mtt= micropos.get("hsa-let-7b");
//         for (String namemi : mtt.keySet()) {
//            Integer temp[]=mtt.get(namemi);
//            System.out.println(namemi+" "+temp[0]+" "+temp[1]+"\n");
//         }
        inmi.close();
        BufferedReader in= new BufferedReader(new FileReader(namein));
        namecode=0;
        while ((l=in.readLine())!=null)	{
            String name="";
            if (l.contains(">")){
               
                namecode++;
                //namecode=Integer.parseInt(name);
            }else if (l.length()>=16){
                //System.out.println(l+"\n");
                hashseq.put(namecode,l);
                String seed1=l.substring(0,8);
                String seed2=l.substring(8,16);
             
                if (clusters.containsKey(seed1)){
                    
                    tempclus=clusters.get(seed1);
                    tempclus.put(namecode, 1);
                    clusters.put(seed1,tempclus);
                }else{
                    
                    TreeMap<Integer,Integer> newclus = new TreeMap<Integer,Integer>();
                    newclus.put(namecode,1);
                    clusters.put(seed1,newclus);
                }
                if (clusters.containsKey(seed2)){
                    
                    tempclus=clusters.get(seed2);
                    tempclus.put(namecode, 2);
                    clusters.put(seed2,tempclus);
                }else{
                    
                    TreeMap<Integer,Integer> newclus = new TreeMap<Integer,Integer>();
                    newclus.put(namecode,2);
                    clusters.put(seed2,newclus);
                }
            }
        }
        in.close();


        int bi=0;
        int ei=4,numlines=0;
        last="";
        String name="";
        int pospre=0;
        //System.out.println(new Date()+"\n");
        BufferedReader indb= new BufferedReader(new FileReader(namedb));
        while ((l=indb.readLine())!=null){
            if (!l.contains(">") & name.contains(sp)){
                l=l.replaceAll("U","T");
                if (preseq.containsKey(name)){

                    String seqt=preseq.get(name);
                    seqt=seqt+l;
                    preseq.put(name, seqt);
                }else{
                     String seqt=l;
                     preseq.put(name, seqt);
                }
            }else{
                
                String [] pre=l.split(" ");
                name=pre[0];
                name=name.replaceAll(">","");
                //System.out.println(name+"\n");
                
            }

        }
        for (String namechr : preseq.keySet()) {
                l=preseq.get(namechr);
                l=l.toUpperCase();
                
                pospre=1;
                
                numlines++;
//                if (numlines % 100000 ==0){
//                    System.out.println(numlines);
//                }
                //System.out.println(namechr+"\n");
                //System.out.println(namechr+" "+l+"\n");
                for (int i=bi;i<l.length()-8;i++){

                    ei=i+8;
                    String nt=l.substring(i, ei);
                    //System.out.println(i+" seed db: "+nt+"\n");
                    if (clusters.containsKey(nt)){
                        tempclus=clusters.get(nt);

                        //System.out.println("matched\n");
                        for (int codeseq : tempclus.keySet()) {
                            //go to alignments
                            
                            int pospretemp=pospre+i;
                            String seqdb=l.substring(i, l.length())+"NNN";
                            int posseed=tempclus.get(codeseq);
                            String seq=hashseq.get(codeseq);
                            //System.out.println(hashseq.get(codeseq)+" "+seqdb+" go to alignment "+i+" "+posseed+"\n");
                            
                            if ((posseed==1 ) | (posseed==2 & i>8)){
                                //System.out.println(hashseq.get(codeseq)+" "+seqdb+" posseed "+posseed +"go to alignment\n");
                                if (posseed==2 ){
                                    seqdb=l.substring(i-8, l.length())+"NNN";
                                    pospretemp=pospre+i-8;
                                }
                                if (seqdb.length()>=seq.length()){
                                alignment alg=align2(hashseq.get(codeseq),seqdb,posseed);
                                //System.out.print(namechr+" "+pospretemp+" "+alg.scmut+" "+alg.mut+" "+alg.add+"\n");
                                if (alg.scmut<=mm & alg.add.length()-1<=add){
                                    //System.out.print("mapped "+alg.scmut+" "+alg.mut+" "+alg.add+"\n");
                                    alg.pospre=pospretemp;
                                    //do micro alignment
                                    //alg=getmicroalig(alg,l.substring(i-5,i+alg.hit+5),micropos.get(namechr));
                                    if (!scoreseq.containsKey(codeseq)){
                                        scoreseq.put(codeseq,alg.sc);
                                        TreeMap<String,alignment> prealg = new TreeMap<String,alignment>();
                                        prealg.put(namechr,alg);
                                        mapinfo.put(codeseq, prealg);
                                        //System.out.print("put new score "+alg.sc+"\n");
                                    }else{
                                        if (alg.sc==scoreseq.get(codeseq)){
                                            TreeMap<String,alignment> prealg = mapinfo.get(codeseq);
                                            prealg.put(namechr,alg);
                                            mapinfo.put(codeseq, prealg);
                                            //System.out.print("put equal score "+alg.sc+"\n");
                                        }else if (alg.sc>scoreseq.get(codeseq)){
                                            scoreseq.put(codeseq,alg.sc);
                                            mapinfo.remove(codeseq);
                                            TreeMap<String,alignment> prealg = new TreeMap<String,alignment>();
                                            prealg.put(namechr,alg);
                                            mapinfo.put(codeseq, prealg);
                                            //System.out.print("put higher score "+alg.sc+"\n");
                                        }
                                    }
                                    //System.out.print(namechr+" "+pospretemp+" "+alg.sc+" "+alg.mut+" "+alg.add+"\n");
                                }
                                }
                            }
                        }
                    }
                }
                //last=l.substring(l.length()-26, l.length());

            
        }


        annotate=mapinfo.size();
        indb.close();
        statment.executeUpdate("update ignore `"+namepro+"`.`"+nameexp+"` set `priority`=3,`amb`=0,`DB`='na'");
        out.printf("seq\tmir\tmism\tadd\tt5\tt3\ts5\ts3\tDB\n");
        for (int nc : mapinfo.keySet()) {
            TreeMap<String,alignment> pret=mapinfo.get(nc);
            int overlapp=0;
            
            String seq=hashseq.get(nc);
            int ambmir=pret.size();
            for (String p : pret.keySet()) {
                overlapp=0;
                
                alignment at=pret.get(p);
                int end=at.pospre+at.hit;
                //System.out.println(p+" "+at.pospre+" "+at.hit+" "+end+"\n");
                if (micropos.containsKey(p)){
                TreeMap<String,Integer[]> mi = micropos.get(p);
                
                for (String m : mi.keySet()) {
                   int overlap5=0;int overlap3=0;
                   
                   Integer [] pos=mi.get(m);
                   //System.out.println(m+" "+pos[0]+" "+pos[1]+"\n");
                   //overlap
                   if (pos[0]-at.pospre<=tri & pos[0]-at.pospre>0){
                        //get t5
                        at.t5="q"+preseq.get(p).substring(at.pospre-1,pos[0]-1);
                        //System.out.println("q"+t5);
                        overlap5=1;
                   }
                   if (at.pospre-pos[0]<=tri & at.pospre-pos[0]>0){
                        //get t5
                        at.t5="t"+preseq.get(p).substring(pos[0],at.pospre);
                        //System.out.println("t"+t5);
                        overlap5=1;
                   }
                   
                   if (pos[1]-end<=tri & pos[1]-end>0){
                        //get t5
                        at.t3="t"+preseq.get(p).substring(end,pos[1]);
                        //System.out.println("t"+t3);
                        overlap3=1;
                   }
                   if (end-pos[1]<=tri & end-pos[1]>0){
                        //get t5
                        at.t3="q"+preseq.get(p).substring(pos[1],end);
                        //System.out.println("q"+t3);
                        overlap3=1;
                   }
                   
                  if ( at.pospre-pos[0]==0){
                        
                        overlap5=1;
                        at.t5="0";
                   }
                   if(end-pos[1]==0){
                        overlap3=1;
                        at.t3="0";
                   }
                   
                   if (overlap3==1 & overlap5==1 ){
                       overlapp=overlap3;
                       int min=5;
                       int max=4;
                       if (pos[0]-5<0){min=0;}
                       if (pos[1]+4>preseq.get(p).length()){max=preseq.get(p).length()-pos[1];}
                       at.s5=preseq.get(p).substring(pos[0]-min,pos[0]+3);
                       at.s3=preseq.get(p).substring(pos[1]-4,pos[1]+max);
                       if (!checkamb.containsKey(seq+m+at.t5+at.t3+at.mut+at.add)){
                        checkamb.put(seq+m+at.t5+at.t3+at.mut+at.add,1);
                        //System.out.printf("MICR seq %s mir %s mut %s add %s t5 %s t3 %s s5 %s s3 %s \n",hashseq.get(nc),m,at.mut,at.add,at.t5,at.t3,at.s5,at.s3);
                        out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\tmiRNA\n",seq,m,at.pospre,end,at.mut,at.add,at.t5,at.t3,at.s5,at.s3);
                        statment.executeUpdate("update ignore `"+namepro+"`.`"+nameexp+"` set `sp`='"+sp+"',`name`='"+m+"',`chr`='"+m+"',`start`="+at.pospre+",`end`="+end+",`strand`='+',`trimmed5`='"+at.t5+"',`trimmed3`='"+at.t3+"',`addition3`='"+at.add+"',`mut`='"+at.mut+"',`amb`=`amb`+1,`trimmed3ref`='"+at.s3+"',`trimmed5ref`='"+at.s5+"',`DB`='miRNA',`priority`=1 where `seq` like '"+seq+"' and `priority`>=3");
                       }
                   }
                }
            }
            if (overlapp==0){
                  out.printf("%s\t%s\t%s\t%s\t%s\t%s\t0\t0\t0\t0\tprecursor\n",hashseq.get(nc),p,at.pospre,end,at.mut,at.add);
                  statment.executeUpdate("update ignore `"+namepro+"`.`"+nameexp+"` set `sp`='"+sp+"',`name`='"+p+"',`chr`='"+p+"',`start`="+at.pospre+",`end`="+end+",`strand`='+',`trimmed5`='0',`trimmed3`='0',`addition3`='"+at.add+"',`mut`='"+at.mut+"',`amb`="+ambmir+",`trimmed3ref`='0',`trimmed5ref`='0',`DB`='hRNA',`priority`=1 where `seq` like '"+seq+"' and `priority`>=1");
                  //System.out.printf("PRE %s\t%s\t%s\t%s\t0\t0\t0\t0\tprecursor\n",hashseq.get(nc),p,at.mut,at.add);
            }
            }
            
        }
        
            for (int nc : mapinfo.keySet()) {
            TreeMap<String,alignment> pret=mapinfo.get(nc);
            int overlapp=0;
            String seq=hashseq.get(nc);
            //int ambmir=pret.size();
            int ambmir=pret.size();
            listmirna.clear();
            listinfo.clear();
            for (String p : pret.keySet()) {
                overlapp=0;
                alignment at=pret.get(p);
                int end=at.pospre+at.hit;
                //System.out.println(p+" "+at.pospre+" "+at.hit+" "+end+"\n");
                if (micropos.containsKey(p)){
                TreeMap<String,Integer[]> mi = micropos.get(p);
                

                for (String m : mi.keySet()) {
                   int overlap3=0;
                   int overlap5=0;
                   Integer [] pos=mi.get(m);
                   //System.out.println(m+" "+pos[0]+" "+pos[1]+"\n");
                   //overlap
                   if (pos[0]-at.pospre<=tri & pos[0]-at.pospre>0){
                        //get t5
                        at.t5="q"+preseq.get(p).substring(at.pospre-1,pos[0]-1);
                        //System.out.println("q"+at.t5);
                        overlap5=1;
                   }
                   if (at.pospre-pos[0]<=tri & at.pospre-pos[0]>0){
                        //get t5
                        at.t5="t"+preseq.get(p).substring(pos[0],at.pospre);
                        //System.out.println("t"+at.t5);
                        overlap5=1;
                   }

                   if (pos[1]-end<=tri & pos[1]-end>0){
                        //get t5
                        at.t3="t"+preseq.get(p).substring(end,pos[1]);
                        //System.out.println("t"+at.t3);
                        overlap3=1;
                   }
                   if (end-pos[1]<=tri & end-pos[1]>0){
                        //get t5
                        at.t3="q"+preseq.get(p).substring(pos[1],end);
                        //System.out.println("q"+at.t3);
                        overlap3=1;
                   }
                   if ( at.pospre-pos[0]==0){
                        
                        overlap5=1;
                        at.t5="0";
                   }
                   if(end-pos[1]==0){
                        overlap3=1;
                        at.t3="0";
                   }
                   
                   if (overlap3==1 & overlap5==1 ){
                       overlapp=overlap3;
                       int min=5;
                       int max=4;
                       if (pos[0]-5<0){min=0;}
                       if (pos[1]+4>preseq.get(p).length()){max=preseq.get(p).length()-pos[1];}
                       at.s5=preseq.get(p).substring(pos[0]-min,pos[0]+3);
                       at.s3=preseq.get(p).substring(pos[1]-4,pos[1]+max);
                       String ann=seq+"\t"+nameseq.get(nc)+"\t"+m+"\t"+at.pospre+"\t"+end+"\t"+at.mut+"\t"+at.add+"\t"+at.t5+"\t"+at.t3+"\t"+at.s5+"\t"+at.s3+"\tmiRNA\t";      
                       //String sql="update ignore `"+namepro+"`.`"+nameexp+"` set `sp`='"+sp+"',`name`='"+m+"',`chr`='"+m+"',`start`="
                       //        +at.pospre+",`end`="+end+",`strand`='+'"+ ",`trimmed5`='"+at.t5+"',`trimmed3`='"+at.t3+"',`addition3`='"
                       //        +at.add+"',`mut`='"+at.mut+"',`amb`=`amb`+1,`trimmed3ref`='"+at.s3+"',`trimmed5ref`='"+at.s5
                       //        +"',`DB`='miRNA',`priority`=1 where `seq` like '"+seq+"' and `priority`>=3";
                       listinfo.put(seq+m,ann);
                       listmirna.put(m,seq);
                        
                       
                     }
                }
            }
            if (overlapp==0){
                  //out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t0\t0\t0\t0\tprecursor\t%s\n",hashseq.get(nc),p,nameseq.get(nc),at.pospre,end,at.mut,at.add,ambmir);
                  //System.out.printf("PRE %s\t%s\t%s\t%s\t0\t0\t0\t0\tprecursor\n",hashseq.get(nc),p,at.mut,at.add);
            }
            }
            
            ambmir=listmirna.size();
            for (String mirann: listinfo.keySet()){
                //System.out.printf(listinfo.get(mirann)+ambmir+"\n");
                out.printf(listinfo.get(mirann)+ambmir+"\n");
                String cols[] = listinfo.get(mirann).split("\t");
                statment.executeUpdate("update ignore `"+namepro+"`.`"+nameexp+"` set `sp`='"+sp+"',`name`='"+cols[1]+"',`chr`='"
                        +cols[2]+"',`start`="+cols[3]+",`end`="+cols[4]+",`strand`='+'"+ ",`trimmed5`='"+cols[7]+"',`trimmed3`='"+cols[8]+"',`addition3`='"
                        +cols[6]+"',`mut`='"+cols[5]+"',`amb`="+ambmir+",`trimmed3ref`='"+cols[10]+"',`trimmed5ref`='"+cols[9]
                        +"',`DB`='miRNA',`priority`=1 where `seq` like '"+cols[0]+"' and `priority`>=3");

            }

        }
        
        
       

        out.close();
        clusters.clear();
        tempclus.clear();
        mapinfo.clear();
        hashseq.clear();
        micropos.clear();
        scoreseq.clear();
        preseq.clear();
        HelpDesk.addinfo("Annotation finished at: "+new Date()+"\n");
        HelpDesk.bar.setValue(100);
         tools.showinfo("The miRNA annotation has finished.");
        //System.out.println(new Date()+"\n"+" num items "+annotate+"\n");
        return(annotate);
    }

    public static alignment getmicroalig(alignment alg,String db,TreeMap<String,Integer[]> mi){

        for (String m : mi.keySet()) {
           System.out.println(alg.pospre+" "+alg.hit+"\n");
          Integer [] pos=mi.get(m);
          System.out.println(m+" "+pos[0]+" "+pos[1]+"\n");
                    //overlap

        }

        return alg;
    }

    public static String seq2code (String seq){
        String code="";


        return code;
    }

    public static int substitution (String a,String b,int penalty,int reward){
        int score=penalty;
        if (a.matches(b)){
            score=reward;
        }
        return score;

    }
    private static alignment align2(String seq, String db,int seed){
        alignment alg=new alignment();
        int sc=0,mism=0,lastscore=0;
        int ns=0,lp=0,seedmism=0;
        //System.out.println(seq+"\n"+db +"\n");
        for (int p=0;p<=seq.length()-11;p+=8){

            ns++;
            lp=p;
            String seed2=seq.substring(p,p+8);
            String seeddb2=db.substring(p,p+8);
            if (seed2.matches(seeddb2)){
                sc++;
            }else{
                seedmism=p;
            }
            //System.out.println("ns "+ns+" lp "+lp+" sc "+sc+" seedmism "+seedmism+" "+"\n");
        }
        lp+=8;
        //System.out.println("ns misma "+ns +" sc: "+sc+"\n");
        if (ns-sc==1){
         //go to look for mism
            String seed2=seq.substring(seedmism,seedmism+8);
            String seeddb2=db.substring(seedmism,seedmism+8);
            alg=alignment(seed2,seeddb2,alg,seedmism);
            //System.out.println("alg scmut "+alg.scmut +"\n");
            //alg.scmut=mism;
            if (alg.scmut==1){
                //System.out.println("1 misma "+mism +" p: "+lp+"\n");
                String t=seq.substring(lp,seq.length());
                String tdb=db.substring(lp,lp+t.length());
                alg=align3end(t,tdb,mism,alg,lp);
                //System.out.println("triming score "+sc +"\n");
                //lastscore=seq.length()-sct;
            }else{
               alg.scmut=10; 
            }
        }else if (ns-sc==0){
        //go to trimming
            //System.out.println("0 misma "+mism +"\n");
            String t=seq.substring(lp,seq.length());
            String tdb=db.substring(lp,lp+t.length());
            alg=align3end(t,tdb,mism,alg,lp);
            //System.out.println("triming score "+sc +"\n");
            //lastscore=seq.length()-sct;
        }else{
            alg.scmut=10;
        }
        alg.hit=seq.length()-alg.add.length();
        alg.sc=seq.length()-alg.scmut-alg.add.length()*0.5;
        //System.out.println("alg sc"+alg.sc +"\n");
        return alg;
    }
    
    
    public static alignment align3end (String seq,String db,int mm,alignment alg,int pos){
        String [] seqNT=seq.split("");
        String [] adNT=db.split("");
        //System.out.println("triming align seq: "+seq+" db "+db +"\n");
        int score=0;

        int minlen=seq.length()-3;
        alg=alignment(seq.substring(0,minlen),db.substring(0,minlen),alg,pos);
        //System.out.println("align sc:"+alg.scmut+" before end seq: "+seq.substring(0,minlen)+" db "+db.substring(0,minlen) +"\n");
        //alg.scmut+=scb;
        for (int i=seq.length();i>minlen;i--){
            int sc=substitution(seqNT[i],adNT[i],0,1);
            //System.out.println(i+" triming subs seq: "+seqNT[i]+" db "+adNT[i] +"\n");
            //score+=sc;
            if (sc==0){
                mm++;
                score=i;
            }
        }
        //System.out.println("#score: "+score+"\n");
        if (score>0){
            alg.add="q"+seq.substring(score-1,seq.length());
        }
        //System.out.println("#add: "+alg.add+"\n");
        return alg;
    }
    public static alignment alignment (String seq,String db,alignment alg,int pos){
        int score=0;
        int i=0;

        int g;
        g=-3;
        int mm=0;
        int pe=0;
        int re=1;
        String [] seqNT=seq.split("");
        String [] adNT=db.split("");
        int minlen;
        minlen=seq.length();

        for (i=1;i<=minlen;i++){
            //System.out.println(i+" "+seqNT[i]+" "+adNT[i]);
            int sc=substitution(seqNT[i],adNT[i],pe,re);
            if (sc==0){
                alg.scmut++;
                int tpos=i+pos;
                alg.mut=tpos+seqNT[i]+""+adNT[i];
            }
            score+=sc;
            if (i>score+2){
                score=0;
                i=minlen+10;
            }
        }
        //System.out.println(alg.scmut+" "+alg.mut);
        
        return alg;
     }

    public static boolean removefiles (String str,String pathout){

    File dir = new File(pathout);
    String[] children = dir.list();
    for (int i = 0; i < children.length; i++) {
        
        if (children[i].contains(str)) {
            System.out.println(children[i]);
            File fileremv = new File(pathout+"/"+children[i]);
            fileremv.delete();
        }
    }
    return true;
   }
}

  
