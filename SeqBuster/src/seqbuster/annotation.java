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
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
//import java.lang.*;
/**
 *
 * @author lo
 */
public class annotation extends JFrame implements Runnable {

    public void run()
	{
//        try {
//             tools check =new tools();
//             String pathout=seqbusterView.pathout.getText()+"/";
//            if (seqbusterView.option.matches("preDB")){
//
//                   String filename,megablast,db;
//                   String wst=CusAnnFrame.ws.getText();
//                   int ws=Integer.parseInt(wst);
//                   String mmt=CusAnnFrame.mm.getText();
//                   int mm=Integer.parseInt(mmt);
//                   //String trt=SeqBusterView.tr.getText();
//                   int tr=0;
//                   String adt=CusAnnFrame.ad.getText();
//                   int ad=Integer.parseInt(adt);
//                   String priort=CusAnnFrame.prior.getText();
//                   int prior=Integer.parseInt(priort);
//                   //String check=checkdbann.getText();
//                   String label=CusAnnFrame.dblabel.getText();
//                   String pdb="DB/";
//                   String filter=CusAnnFrame.filterann.getText();
//
//                   filename=CusAnnFrame.inputann.getText();
//
//                   megablast=seqbusterView.pathblast.getText()+"/bin/megablast";
//                   db=CusAnnFrame.dbann.getText();
//
//
//                   boolean Einput=check.fileexist(filename);
//                   boolean Emegablast=check.fileexist(megablast);
//                   if (!Einput){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+filename+", \nAre you sure this file exists?...\n");
//
//                   }else if (!Emegablast){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+megablast+", \nAre you sure blast is installed here?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else{
//                        annBig(pdb,db,megablast,filename,ws,mm,tr,ad,prior,label,filter,pathout);
//                   }
//            }
//
//            if (seqbusterView.option.matches("smallDB")){
//                   String filename,coorsdb,labelsdb;
//                   String mmt=CusAnnFrame.mmSDB.getText();
//                   int mm=Integer.parseInt(mmt);
//                   String trt=CusAnnFrame.trSDB.getText();
//                   int tr=Integer.parseInt(trt);
//                   String adt=CusAnnFrame.addSDB.getText();
//                   int ad=Integer.parseInt(adt);
//                   String priort=CusAnnFrame.priorSDB.getText();
//                   int prior=Integer.parseInt(priort);
//                   //String check=checkdbann.getText();
//                   labelsdb=CusAnnFrame.labelSDB.getText();
//                   String pdb="DB/";
//                   String bigdb=CusAnnFrame.digdbSDB.getText();
//                   String filter=CusAnnFrame.filterSDB.getText();
//                   //String adseq;
//                   filename=CusAnnFrame.annfileSDB.getText();
//                   coorsdb=CusAnnFrame.coorfileSDB.getText()+".coor";
//                   //outputann.setText(filename+".parse");
//                   //filenameout=filename+".out";
//                   coorsdb=pdb+coorsdb;
//                   bigdb=pdb+bigdb;
//                   //if (check.matches("")){
//                   boolean Einput=check.fileexist(filename);
//                   boolean Ebigdb=check.fileexist(bigdb+".db");
//                   boolean Ecoorsdb=check.fileexist(coorsdb);
//                   if (!Einput){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+filename+", \nAre you sure this file exists?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else if (!Ebigdb){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+bigdb+", \nAre you sure blast is installed here?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else if (!Ecoorsdb){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+coorsdb+", \nAre you sure this file exists?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else{
//                        annSmall(filename,coorsdb,mm,tr,ad,prior,labelsdb,bigdb,filter,pathout);
//                   }
//
//                   //}else{
//
//            }if (seqbusterView.option.matches("coorDB")){
//                 int overlap,strandsens=0,strandoposite=0,skip=0;
//                   String filename,coorsdb,labelsdb;
//                   String mmt=CusAnnFrame.mmCDB.getText();
//                   int mm=Integer.parseInt(mmt);
//
//                   String adt=CusAnnFrame.addCDB.getText();
//                   int ad=Integer.parseInt(adt);
//                   String priort=CusAnnFrame.priorCDB.getText();
//                   int prior=Integer.parseInt(priort);
//                   //String check=checkdbann.getText();
//                   labelsdb=CusAnnFrame.labelCDB.getText();
//                   //String pdb=pathdb.getText();
//                   String pdb="DB/";
//                   String filter=CusAnnFrame.filterann.getText();
//
//                   filename=CusAnnFrame.annfileCDB.getText();
//                   coorsdb=CusAnnFrame.coorfileCDB.getText();
//                   String header=CusAnnFrame.headerDB.getText();
//                   overlap=Integer.parseInt(CusAnnFrame.overlapCDB.getText());
//                   if (CusAnnFrame.strandsensitiveDB.isSelected()){
//                    strandsens=1;
//                   }
//                   if (CusAnnFrame.strandopositeDB.isSelected()){
//                    strandoposite=1;
//                   }
//                    if (CusAnnFrame.skipDB.isSelected()){
//                    skip=1;
//                   }
//
//
//                   boolean Einput=check.fileexist(filename);
//
//                   boolean Ecoorsdb=check.fileexist(coorsdb);
//                   if (!Einput){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+filename+", \nAre you sure this file exists?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else if (!Ecoorsdb){
//                        JOptionPane.showMessageDialog(null,"Error reading: "+coorsdb+", \nAre you sure this file exists?...\n");
////                        seqbusterView.log.setCaretPosition(seqbusterView.log.getText().length() - 1);
//                   }else{
//
//                    anndb(filename,coorsdb,mm,ad,prior,labelsdb,strandsens,strandoposite,overlap,filter,skip,header,pathout);
//                   }
//
//            }
//        }catch (IOException ie){
//
//        }
    }

    public void anndb(String annfile, String db,int mm,int ad,int prior,String label,int strandsens,int strandopo,int overlap,String filter,int skip,String header,String pathout) throws IOException{
        String col [];
        String chr,name,strand;
        int startind=0,endind=0,strandind=0,chrind=0,nameind=0,i,start,end,lenoverlap=0,ok=0,lenad=0,numann=0;
        Integer lencol=0;
        String [] temp=new String [5];
        String l;
        int startname=0,endname=0,startover=0,endover=0,amb;
        col = annfile.split("\\/");
        String nameannfile=col[col.length-1];
        List<String> listemp=new ArrayList<String>();
        HashMap<String,String []> dbcor = new HashMap<String,String []>();
        HashMap<String,String []> dbcortemp = new HashMap<String,String []>();
        HashMap<String,List<String>> dbcorstart = new HashMap<String,List<String>>();
        HashMap<String,Integer> ambcount = new HashMap<String,Integer>();
        //System.out.println("Reading..."+db);
//        log.append("Reading annotated file"+db+"...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        BufferedReader dbfile= new BufferedReader(new FileReader(db));
        // System.out.println("done");
         //read small db annotated in precursor=>each precurso with a hash of small seqs
        //l=dbfile.readLine();
        col = header.split("-");
        //lencol=col.length();
        for (i=0;i<col.length;i++){
           
            if (col[i].matches("start")){
                startind=i;
            }
            if (col[i].matches("end")){
                endind=i;
            }
            if (col[i].matches("strand")){
                strandind=i;
            }
            if (col[i].matches("chr")){
                chrind=i;
            }
            if (col[i].matches("name")){
                nameind=i;
            }
        }
        if(skip==1){
            l=dbfile.readLine();
        }
//        log.append("name "+nameind+" chr "+chrind+" strand "+strandind+" start "+startind+" end "+endind+" ...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        while ((l=dbfile.readLine())!=null)
		{
            col = l.split("\t");
            name=col[nameind];
            chr=col[chrind];
            
            strand=col[strandind];
            String [] coor=new String [5];
            coor[0]=chr;
            coor[4]=name;
            coor[1]=strand;
            coor[2]=col[startind];
            coor[3]=col[endind];
            
            dbcor.put(name, coor);
            if (dbcorstart.containsKey(chr)){
                listemp=dbcorstart.get(chr);
                listemp.add(name);
                dbcorstart.put(chr, listemp);
            }else{
                List<String> tl=new ArrayList<String>();
                tl.add(name);
                dbcorstart.put(chr, tl);
            }

           
            
            //System.out.println(name+":"+strand+":"+chr+":"+col[startind]+":"+col[endind]);
        }
        dbfile.close();
        //for (String c : dbcorstart.keySet()) {
        //    Integer [] starts=dbcorstart.get(c);

        //}
        //System.out.println("reading file..."+annfile);
        //cross predb with coor hashmap
//        log.append("writing output file in"+pathout+nameannfile+".annotated"+label+"...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+nameannfile+".annotated"+label)));
//        log.append("Reading annotated file...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        //file read annotated agains preDB
        BufferedReader dbpre= new BufferedReader(new FileReader(annfile));
        l=dbpre.readLine();
         out.write("seq\tname\tDB\tpriority\tamb\n");
//         log.append("same sensitive:"+strandsens+",strandopo:"+strandopo+"...\n");
//         log.setCaretPosition(log.getText().length() - 1);
        //out.write("seq\tchr\tstrand\tstart\tend\tDB\tpriority\ttrimmed3\ttrimmed5\ttrimmed3ref\ttrimmed5ref\n");
        while ((l=dbpre.readLine())!=null)
		{
            col = l.split("\t");
            lenad=col[7].length()-1;
            //System.out.println(col[3]);
            start=Integer.parseInt(col[3]);
            end=Integer.parseInt(col[4]);
            chr=col[1];

            strand=col[2];
            //temp=dbcor.get(col[1]);
            //get hash of this preDB locus
            if (dbcorstart.containsKey(chr)){
            listemp=dbcorstart.get(chr);
            //for i=0 to len hash
            ok=0;

            //System.out.println(col[0]+" chr "+chr+" "+listemp.size());
            for (i=0;i<listemp.size();i++) {
                //if (dbcor.containsKey(names[i])){
                    
                    temp=dbcor.get(listemp.get(i));
                    //.out.println("mapped n "+listemp.get(i)+" "+temp[0]+" "+temp[1]+" "+temp[2]);
                    //check if has to be the same strand (start)
                    startname=Integer.parseInt(temp[2]);
                    endname=Integer.parseInt(temp[3]);
                    
                    if ((start>=startname & start<=endname) | (end>=startname & end<=endname)){
                        if (start>=startname){
                            startover=start;
                        }else{
                            startover=startname;
                        }
                        if (end<=endname){
                            endover=end;
                        }else{
                            endover=endname;
                        }
                        
                        lenoverlap=(endover-startover)/(end-start)*100;
                    if (strandsens==1 & (temp[1].matches("\\+") & strand.matches("\\+")) | (temp[1].matches("\\-") & strand.matches("\\-"))){
                        
                        ok=1;
                    }
                    if (strandopo==1 & (temp[1].matches("\\-") & strand.matches("\\+")) | (temp[1].matches("\\+") & strand.matches("\\-"))){
                        
                        ok=1;
                    }
                    if (strandsens==0 & strandopo==0){
                        
                        ok=1;
                    }
                    if (ok==1){
                       
                        //System.out.println("len "+lenoverlap+" over "+overlap);
                        //


                        //System.out.println("len "+lenoverlap+" over "+overlap+" "+col[0]+"\t"+chr+"\t"+start+"\t"+end+"\t"+strand+"\t"+temp[4]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[0]);

                        if (lenoverlap>=overlap & lenad<=ad){
                            //if (col[4].matches(filter)){
                                if(ambcount.containsKey(col[0])){
                                amb=ambcount.get(col[0])+1;
                                }else{
                                    amb=1;
                                }

                                ambcount.put(col[0],amb);
                                out.write(col[0]+"\t"+temp[4]+"\t"+label+"\t"+prior+"\t"+amb+"\n");
                                 numann++;
                                 //System.out.println(col[0]+" chr "+chr+" "+listemp.size());
                                //System.out.println("len "+lenoverlap+" over "+overlap+" "+col[0]+"\t"+chr+"\t"+start+"\t"+end+"\t"+strand+"\t"+temp[4]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[0]);
                            //}
                            //write
                        }
                        break;
                    }
                    
                }
               //check if has to be the same strand (end)
                
                
            }
        }
        }
        dbpre.close();
        out.close();
//        log.append("A total of "+numann+" of sequence were annotated...\n");
//        log.append("DONE...\n");
//         log.setCaretPosition(log.getText().length() - 1);
    }

     public void annSmall(String annfile, String smalldb,int mm,int tr,int ad,int prior,String label,String bigdb,String filter,String pathout) throws IOException{

         int startpre,endpre,startsmall,endsmall,startcor,endcor,count=0,mut=0;
         String muttag="",mutpos,mutnt="",strandpre,strandsmall,locuspre,locussmall,seqdb,seqtr3,seqtr5,ptr5,ptr3,seq;
         String col [];
         String [] temp=new String [4];
         String l;
         col = annfile.split("\\/");
         String nameannfile=col[col.length-1];
         //String dbbin="/Users/lo/Desktop/blast/DB/hairpinRNA.db";
         int i,trimmed3,trimmed5,addition3,posindex,lenindex,startplus,endplus,amb=0;
         int [] tempindex=new int [2];
         HashMap<String,String []> tempsmallDB = new HashMap<String,String []>();
         HashMap<String,HashMap<String,String []>> preDB = new HashMap<String,HashMap<String,String []>>();
        //read index
         HashMap<String,int []> dbindexinfo = new HashMap<String,int []>();
         HashMap<String,Integer> ambcount = new HashMap<String,Integer>();
//         log.append("Indexing database...\n");
//         log.setCaretPosition(log.getText().length() - 1);
         BufferedReader dbindex= new BufferedReader(new FileReader(bigdb+".index"));
        while ((l=dbindex.readLine())!=null)
		{
            col = l.split("\t");
            //
            int [] infodbindex=new int [2];
            infodbindex[0]=Integer.parseInt(col[1]);
            infodbindex[1]=Integer.parseInt(col[2]);
            //System.out.println(col[0]+"-"+infodbindex[0]+"-"+infodbindex[1]);
            dbindexinfo.put(col[0],infodbindex);
        }
        dbindex.close();
//           log.append("indexing small database...\n");
//         log.setCaretPosition(log.getText().length() - 1);
         BufferedReader dbsmall= new BufferedReader(new FileReader(smalldb));
         //read small db annotated in precursor=>each precurso with a hash of small seqs
        while ((l=dbsmall.readLine())!=null)
		{
            String [] tempsmalldbdata=new String [4];
            col = l.split("\t");
            tempsmalldbdata[0]=col[1];
            tempsmalldbdata[1]=col[2];
            tempsmalldbdata[2]=col[3];
            tempsmalldbdata[3]=col[4];
            //System.out.println("pre "+col[1]+" mi "+col[0]);
            if (preDB.containsKey(col[1])){
                tempsmallDB=preDB.get(col[1]);
                tempsmallDB.put(col[0],tempsmalldbdata);
                preDB.put(col[1],tempsmallDB);
                //System.out.println("old "+tempsmallDB);

            }else{
                HashMap<String,String []> smallDB = new HashMap<String,String []>();
                smallDB.put(col[0],tempsmalldbdata);
                preDB.put(col[1],smallDB);
                //System.out.println("new "+smallDB);
            }
            

        }
        dbsmall.close();
//        log.append("writing output file in"+pathout+nameannfile+".annotated"+label+"...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+nameannfile+".annotated"+label)));

        //file read annotated agains preDB
//        log.append("Writing output file...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        BufferedReader dbpre= new BufferedReader(new FileReader(annfile));
        l=dbpre.readLine();
        out.write("seq\tchr\tstrand\tstart\tend\tDB\tpriority\ttrimmed3\ttrimmed5\ttrimmed3ref\ttrimmed5ref\tmut\tamb\n");
        while ((l=dbpre.readLine())!=null)
		{
            col = l.split("\t");
            locuspre=col[1];
            if(locuspre.contains(filter)){
            seq=col[0];
            //System.out.println(col[8]);
            if (!col[8].contains("0")){
                Pattern pattern = Pattern.compile("[ATGC]");
                Matcher matcher = pattern.matcher(col[8]);
                mutpos=matcher.replaceAll("");
                
                mut=Integer.parseInt(mutpos);
                pattern = Pattern.compile("[0-9]");
                matcher = pattern.matcher(col[8]);
                mutnt=matcher.replaceAll("");

                
            }else{
                muttag="0";
            }
            //System.out.println(muttag);
            count++;
            if (count%100000==0){
//                   log.append(count+"...\n");
//                   log.setCaretPosition(log.getText().length() - 1);

             }
            //System.out.println("locuspre: "+locuspre+" pos "+col[3]+" "+col[4]+" "+col[2]+" "+col[7]+" "+col[8]);
            startpre=Integer.parseInt(col[3]);
            endpre=Integer.parseInt(col[4]);
            strandpre=col[2];
            if(preDB.containsKey(locuspre)){
            tempsmallDB=preDB.get(locuspre);
            //get hash of this preDB locus
            //for i=0 to len hash

            for (String s : tempsmallDB.keySet()) {
                temp=tempsmallDB.get(s);

                
                startsmall=Integer.parseInt(temp[2]);
                startcor=startpre-startsmall+1;
                if (startcor<1){
                    startcor=1;
                }
                endsmall=Integer.parseInt(temp[3]);
                endcor=(endsmall-startsmall+1)-(endsmall-endpre+1);
                if (endcor<0){
                    endcor=endsmall-startsmall+1;
                }
                strandsmall=temp[1];
                tempindex=(int []) dbindexinfo.get(locuspre);
                posindex=tempindex[0];
                lenindex=tempindex[1];
                //overlap seqs?
                if (strandsmall.matches("\\+") ){
                trimmed5=Math.abs(startsmall-startpre);
                trimmed3=Math.abs(endsmall-endpre);
                if ( trimmed5<=tr & trimmed3<=tr & s.contains(filter)){
                    //System.out.println("annotated");
                    //System.out.println("tempsmallDB "+s+" "+temp[0]+" "+temp[1]+" "+temp[2]+" "+temp[3]);
                    if (!muttag.contains("0")){
                            muttag=mut+(startsmall-startpre)+mutnt;
                    }
                    //System.out.println((startsmall-startpre)+" "+muttag);
                    //get trimming5
                    //get trimming3
                    seqtr3=trimmed3(posindex,lenindex,strandpre,bigdb,endpre,endsmall);
                    //System.out.println("tr3");
                    ptr3=getsequence(posindex,lenindex,strandpre,bigdb,tr*2,endsmall-tr);
                    seqtr5=trimmed5(posindex,lenindex,strandpre,bigdb,startpre,startsmall);
                    //System.out.println("tr5");
                    ptr5=getsequence(posindex,lenindex,strandpre,bigdb,tr*2,startsmall-tr);
                    //System.out.println(seqtr3);
                    //System.out.println(seqtr5);
                    if(ambcount.containsKey(seq)){
                       amb=ambcount.get(seq)+1;
                    }else{
                        amb=1;
                    }

                    ambcount.put(seq,amb);

                    out.write(seq+"\t"+s+"\t+\t"+startcor+"\t"+endcor+"\t"+label+"\t"+prior+"\t"+seqtr3+"\t"+seqtr5+"\t"+ptr3+"\t"+ptr5+"\t"+muttag+"\t"+amb+"\n");


                    //??change mut position

                    //annotated and break->print
                }
                }else{
                    startplus=lenindex-startpre;
                    endplus=startplus+(endpre-startpre);
                    trimmed5=Math.abs(startsmall-startplus);
                    trimmed3=Math.abs(endsmall-endplus);
                    if ( trimmed5<=tr & trimmed3<=tr & s.contains(filter)){
                        if (!muttag.contains("0")){
                            muttag=mut+(startsmall-startplus)+mutnt;
                        }
                        //System.out.println((startsmall-startplus)+" "+muttag);
                        //System.out.println("tempsmallDB "+s+" "+temp[0]+" "+temp[1]+" "+temp[2]+" "+temp[3]);
                        seqtr5=trimmed3(posindex,lenindex,"+",bigdb,endplus,endsmall);
                        seqtr5=reverse(seqtr5);
                        ptr5=getsequence(posindex,lenindex,"+",bigdb,tr*2,endsmall-tr);
                        ptr5=reverse(ptr5);
                        seqtr3=trimmed5(posindex,lenindex,"+",bigdb,startplus,startsmall);
                        seqtr3=reverse(seqtr3);
                        ptr3=getsequence(posindex,lenindex,"+",bigdb,tr*2,startsmall-tr);
                        ptr3=reverse(ptr3);
                        //System.out.println(seqtr3);
                        //System.out.println(seqtr5);
                        if(ambcount.containsKey(seq)){
                       amb=ambcount.get(seq)+1;
                        }else{
                            amb=1;
                        }

                        ambcount.put(seq,amb);

                        out.write(seq+"\t"+s+"\t+\t"+startcor+"\t"+endcor+"\t"+label+"\t"+prior+"\t"+seqtr3+"\t"+seqtr5+"\t"+ptr3+"\t"+ptr5+"\t"+muttag+"\t"+amb+"\n");


                    }
                }

            }
            
            }
        }
        }
        dbpre.close();
        out.close();
//         log.append("annotated DONE. Go to the next step...\n");
//         log.setCaretPosition(log.getText().length() - 1);

     }
     public void annBig(String pathdb,String db,String megablast,String namein,int ws,int mm,int tr,int ad,int prior,String label,String filter,String pathout) throws IOException{
		String tempout=namein;
        tools tool=new tools();
		//String namein;
		//namein=fileIn.getText();
		//nameout=fileOut.getText();
		PrintWriter outblast=new PrintWriter(new BufferedWriter(new FileWriter(tempout+".fa")));
        //BufferedWriter out=new BufferedWrited(out);
		BufferedReader in= new BufferedReader(new FileReader(namein));
		String l,seq,locus,cmdmegablast;
        String [] col;
        String [] info=new String [12];
        String [] tempinfo=new String [12];
        String [] infoBigDB=new String [12];
        long [] temp=new long [2];
        long [] infodbindexBigDB=new long [2];
        //variables read megabalst
        String strand="+";
        int start,end,tempstart;
        int startseq,endseq,len;
        int mism,gaps,hit,score;
        long posindex;
        int lenindex;
        int tempmism,i,count=0,amb=0;
        //String seqread;
        String seqdb,mismtag,variation3tag;
        db=pathdb+db;
        col = namein.split("\\/");
        String nameannfile=col[col.length-1];
        //
        //int mis,gap,ss,es,sl,el;
        double iden;
        
        //String ad;
		//StringBuilder sd=new StringBuilder();
		HashMap<String,String []> seqinfo = new HashMap<String,String []>();
        HashMap<String,Integer> ambcount= new HashMap<String,Integer>();
        //HashMap<String,String []> seqinfoBigDB = new HashMap<String,String []>();
		HashMap<String,long []> dbindexinfo = new HashMap<String,long []>();
        //HashMap<String,int []> dbindexinfoBigDB = new HashMap<String,int []>();
//        log.append("Creating input file for megablast...\n");
//         log.setCaretPosition(log.getText().length() - 1);
		while ((l=in.readLine())!=null)
		{
            
			col = l.split("\t");
            //tempinfo[10]=col[2];
            //info[1]=col[1];
            //seqinfo.put(col[0],info);
            //adaseq.put(col[0],col[3]);
            if (Integer.parseInt(col[1])>2){
                outblast.write(">"+col[0]+"\n"+col[0]+"\n");
            }
		}
		in.close();
        outblast.close();
        

        //if check is ok, read in hash Big DB
//        if (check.matches("[aA-zZ]")){
//            BufferedReader inBigDB= new BufferedReader(new FileReader(check));
//            while ((l=inBigDB.readLine())!=null)
//            {
//                col = l.split("\t");
//                infoBigDB[10]=col[2];//variation3
//                infoBigDB[1]=col[1];//mutation
//                //start
//                //end
//                //chr
//                //strand
//                seqinfoBigDB.put(col[0],infoBigDB);
//            }
//            inBigDB.close();
//            BufferedReader dbindexBigDB= new BufferedReader(new FileReader(check+".index"));
//            while ((l=dbindexBigDB.readLine())!=null)
//            {
//                col = l.split("\t");
//                infodbindexBigDB[0]=Integer.parseInt(col[1]);
//                infodbindexBigDB[1]=Integer.parseInt(col[2]);
//                dbindexinfoBigDB.put(col[0],infodbindexBigDB);
//             }
//            dbindexBigDB.close();
//        }

        //read index file to get
//        log.append("Indexing database..."+db+".index \n");
//         log.setCaretPosition(log.getText().length() - 1);
        BufferedReader dbindex= new BufferedReader(new FileReader(db+".index"));
        while ((l=dbindex.readLine())!=null)
		{
            col = l.split("\t");
            //
            long [] infodbindex=new long [2];
            infodbindex[0]=Long.parseLong(col[1]);
            infodbindex[1]=Long.parseLong(col[2]);
            //System.out.println(col[0]+"-"+infodbindex[0]+"-"+infodbindex[1]);
            dbindexinfo.put(col[0],infodbindex);
        }
        dbindex.close();
        //temp=(int []) dbindexinfo.get("hsa-mir-590");
        //System.out.println("p: "+temp[0]+" l: "+temp[1]+" s: "+dbindexinfo.values());
        //run megablast
//        log.append("Running megablast...\n");
//         log.setCaretPosition(log.getText().length() - 1);
        cmdmegablast=megablast+" -d "+db+".db -i "+namein+".fa -W "+ws+" -p -2"+" -r 3"+" -e 1"+" -D 3"+" -H 20 -o "+tempout+".megablast";
        DoRuntime(cmdmegablast);
        //tool.removefile(namein+".fa");
        //megablast -D -i -w -p -r -e 1 -D 3 -o namein+.megablat
		 //parse megablast
//        log.append("writing output file in"+pathout+nameannfile+".annotated"+label+"...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(pathout+nameannfile+".annotated"+label)));
        BufferedReader inblast= new BufferedReader(new FileReader(tempout+".megablast"));
        Pattern header = Pattern.compile("^#.*");
         //Matcher fit = header.matcher("# kak");
         //System.out.println(filter);
//        log.append("Writing output file...\n");
//         log.setCaretPosition(log.getText().length() - 1);
        out.write("seq\tchr\tstrand\tstart\tend\tDB\tpriority\taddition3\tmut\tamb\n");
        while ((l=inblast.readLine())!=null)
		{
            Matcher fit = header.matcher(l);
            if (!(fit.matches())){
               count++;
               if (count%100000==0){
//                   log.append(count+"...\n");
//                   log.setCaretPosition(log.getText().length() - 1);

                  }
               //System.out.println(l);
                col = l.split("\t");
                start=Integer.parseInt(col[8]);
                end=Integer.parseInt(col[9]);
                strand="+";
                seq=col[0];
                len=seq.length();
                locus=col[1];
                if (locus.contains(filter)){
                locus=locus.replace("lcl|","");
                mism=Integer.parseInt(col[4]);
                gaps=Integer.parseInt(col[5]);
                hit=Integer.parseInt(col[3]);
                //System.out.println(locus+" "+start+" "+end);
                //infodbindex=(0,0);
                temp=(long []) dbindexinfo.get(locus);
                posindex=temp[0];
                lenindex=(int)temp[1];
                startseq=Integer.parseInt(col[6]);
                endseq=Integer.parseInt(col[7]);
                //is strand -,change position
                //System.out.println(locus+" "+start+" "+end);
               if (start>end){
                    strand="-";
                    start=lenindex-start;
                    end=start+hit;
                }
                   
                if (startseq>1 & start>1 & start-startseq>=0 & mism<mm){
                   //System.out.println("correction s:"+seq+" len: "+len+" h: "+hit+" locus: "+locus+" s: "+startseq+" start: "+start+" e: "+endseq+" end: "+end+" strand: "+strand+" mism: "+mism);
                   tempmism=0;
                   tempstart=start-startseq+1;
                   //System.out.println("starttemp: "+tempstart);
                   seqdb=getsequence(posindex,lenindex,strand,db,startseq-1,tempstart);
                   String [] seqdbNT=seqdb.split("");
                   String [] seqNT=seq.split("");
                   
                   for (i=((int)startseq)-1;i>0;i--){
                      //System.out.println("posi: "+i+" tempmism: "+tempmism+" ntseq: "+seqNT[i]+" ntdb: "+seqdbNT[i]);
                        if (seqdbNT[i].matches(seqNT[i])){
                            startseq--;
                            start--;
                            hit++;
                        }else if(!seqdbNT[i].matches(seqNT[i]) & tempmism==0){
                            startseq--;
                            start--;
                            hit++;
                            tempmism++;
                            mism++;
                        }else if(!seqdbNT[i].matches(seqNT[i]) & tempmism==1){
                            break;
                        }
                   }
                }

                score=hit-mism;
                if (mism==0 & len-hit<=ad){
                    score=len+(ad-(len-hit));

                }
                //System.out.println("s:"+seq+" len: "+len+" h: "+hit+" locus: "+locus+" s: "+startseq+" e: "+endseq+" gaps: "+gaps+" mism: "+mism);
               if(startseq==1 & mism<=mm & gaps==0 & len-ad-mm<=hit){
               // if(startseq==1 ){
                    //get mut
                   tempinfo[1]=locus;
                   tempinfo[2]=Integer.toString(start);
                   tempinfo[3]=Integer.toString(end);
                   tempinfo[4]=strand;
                   String seqtarget=seq.substring((int)startseq-1,(int)endseq);
                   seqdb=getsequence(posindex,lenindex,strand,db,hit,start);
                   //System.out.println("s:"+seq+" len: "+len+" h: "+hit+" locus: "+locus+" s: "+startseq+" start: "+start+" e: "+endseq+" end: "+end+" strand: "+strand+" mism: "+mism);
                   
                   //System.out.println(seq);
                   //System.out.println(seqdb);
                   mismtag=subs(seqtarget,seqdb,startseq,endseq,start,end);
                   tempinfo[7]=mismtag;
                   //System.out.println(mismtag);
                    //get adition
                   variation3tag=variation3(seq,startseq,endseq);
                   tempinfo[8]=variation3tag;
                   //System.out.println(variation3tag);
                   if (seqinfo.containsKey(seq)){
                        info=(String []) seqinfo.get(seq);
                        tempinfo[9]=info[9]+1;
                   }
                   seqinfo.put(seq,tempinfo);
                    //check if exist, ad amb ++
                   if(ambcount.containsKey(seq)){
                       amb=ambcount.get(seq)+1;
                    }else{
                        amb=1;
                    }

                    ambcount.put(seq,amb);
                    if (strand.matches("-")){
                    //System.out.println("inside");
                    start=Integer.parseInt(col[9]);
                    end=Integer.parseInt(col[8]);
                   }
                   //System.out.println(locus+" "+start+" "+end);

                    out.write(seq+"\t"+locus+"\t"+strand+"\t"+start+"\t"+end+"\t"+label+"\t"+prior+"\t"+variation3tag+"\t"+mismtag+"\t"+amb+"\n");
                   //System.out.println("in, s:"+seq+" len: "+len+" locus: "+locus+" s: "+start+" e: "+end+" strand: "+strand);
                   //System.out.println("in, p: "+posindex+" l: "+lenindex);
                   //System.out.println(mismtag);

                }
                   //fix 5 prime end
            }
            }
                //get variation in ends
        }
        inblast.close();
        out.close();
        //tool.removefile(namein+".megablast");
//        log.append("Annotated DONE. Go to the next step...\n");
//        log.setCaretPosition(log.getText().length() - 1);
        //create file readin hash

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
           RandomAccessFile file = new RandomAccessFile (name+".db", "r");
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
    public String variation3(String seqs, int ss,int es){
        String v3="0";
        int len=seqs.length();
        if (len>es){
            //System.out.println("inside, es: "+es+" len: "+len);
            v3="q"+seqs.substring(es,len);
        }
        return v3;

    }
    public String variation5(int sl,int ss,String seqs,String seql){
        String v5="";

        return v5;

    }
    public String trimmed3(int posindex,int lenindex, String strandpre,String dbbin,int endpre,int endsmall) throws IOException{
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

    public String trimmed5(int posindex,int lenindex, String strandpre,String dbbin,int startpre,int startsmall) throws IOException{
        String t5="0";
        int dif;
        if (startpre>startsmall){
            dif=startpre-startsmall;
            t5="t"+getsequence(posindex,lenindex,strandpre,dbbin,dif,startpre);
        }else if(startsmall>startpre){
            dif=startsmall-startpre;
            t5="q"+getsequence(posindex,lenindex,strandpre,dbbin,dif,startpre-dif);
        }
        return t5;

    }
    public String subs(String seqs,String seql,int ss,int es,int sl,int el){
        String s="";
        String [] seqNT=seqs.split("");
        String [] seqdbNT=seql.split("");
        //System.out.println("s: "+seqs+" db: "+seql);
        int i,pos;
        for (i=0;i<seql.length();i++){
            pos=(int)ss+i-1;
            if (!seqNT[i].matches(seqdbNT[i])){
                s+=pos+seqNT[i]+seqdbNT[i];
            }
        }
        if (s.matches("")){
            s="0";
        }
        return s;

    }

    public void DoRuntime (String cmd) throws IOException {

       if (cmd.matches("")) {
         System.err.println("Need command to run");
         System.exit(-1);
           }
       System.out.printf("Output of running %s is:",
           cmd);
       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       InputStream is = process.getInputStream();
       InputStreamReader isr = new InputStreamReader(is);
       BufferedReader br = new BufferedReader(isr);
       String line;

       

       while ((line = br.readLine()) != null) {
         System.out.println(line);
       }

    }


}

    class ReverseString {
         public static String reverseIt(String source) {
           int i, len = source.length();
           StringBuffer dest = new StringBuffer(len);

           for (i = (len - 1); i >= 0; i--)
             dest.append(source.charAt(i));
         return dest.toString();
         }
    }
