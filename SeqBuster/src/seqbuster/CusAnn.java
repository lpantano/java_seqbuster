/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.Comparator.*;
//import java.lang.String.*;
//import seqbuster.byLineLength;

/**
 *
 * @author lpantano
 */

public class CusAnn {


   public int parse(String expname,String name,String gen,String spe) throws FileNotFoundException, IOException, SQLException{
        String pathout=SeqBusterView.pathout.getText()+"/";
        String namepro=SeqBusterView.loaprotext.getText();

        
        String l=null;
        Pattern header = Pattern.compile("^#.*");
        String strand="+";
        int start,end,ad,str;
        int startseq,endseq,len;
        int mism,gaps,hit,score;
        long posindex;
        int ambmir;
        Long lenindex;
        int i,count=0;
        int mismpar=Integer.parseInt(NonMiRNAFrame.mistext.getText());
        int adpar=Integer.parseInt(NonMiRNAFrame.addtext.getText());
        
       
        //String sps=miRNAannFrame.speMirAnnText.getText();
        Integer startseqmir,startmirseq;
        //String seqread;
        String seqdb,seq,mismtag,variation3tag;
        String col[];
        //get genome index
        String host=SeqBusterView.HosMysText.getText();
        String db=SeqBusterView.loaprotext.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        TreeMap<String,Long[]> index = new TreeMap<String,Long[]>();
        TreeMap<String,TreeMap<String,Integer[]>> seqinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<String,Integer[]> tempinfo = new TreeMap<String,Integer[]>();
        TreeMap<String,Integer> tempscoreindex = new TreeMap<String,Integer>();
        TreeMap<String,String> seqmapped =new TreeMap<String,String>();
        Long [] indexdata = new Long[2];
        Integer [] tempdata=new Integer [9];

        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment = (Statement) con.createStatement();
        ResultSet res =statment.executeQuery("SELECT * FROM  `genome`.`"+gen+"_"+spe+"`");
        while (res.next()) {
             Long [] indexdatanew = new Long[2];
             indexdatanew[0]=res.getLong("len");
             indexdatanew[1]=res.getLong("pos");
             index.put( res.getString("name"),indexdatanew);
          }
         res.close();
         //hashtree cluster pattern seq
        BufferedReader infasta= new BufferedReader(new FileReader(pathout+namepro+"/"+expname+"/seqstemp.fa"));
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

//            System.out.println(children[ic]);
        BufferedReader inblast= new BufferedReader(new FileReader(pathout+namepro+"/"+expname+"/seqstemp.megablast"));
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

        Boolean flag;
        int num=0;
        //createtable clusmap
        statment.executeUpdate("insert into `"+namepro+"`.`experiments` values ('"+expname+name+"','"+namepro+"','noshow "+name+"','custom:"+name+"')");
        statment.executeUpdate("create table `"+namepro+"`.`"+expname+name+"` (`id` int unsigned,`seq` varchar(50),`freq` int unsigned,`chr` varchar(15),`db` text(),`start` int unsigned,`end` int unsigned,`strand` enum('+','-'),`mut` int(1),`add` tinyint unsigned,`amb` tinyint unsigned,index (`id`) )");
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
                ambmir++;
                String seqnt=seqmapped.get(s);
                statment.executeUpdate("insert into `"+namepro+"`.`"+expname+name+"` values ("+id[1]+",'"+seqnt+"',"+id[2]+",'"+chr[0]+"','"+db+"',"+startseq+","+endseq+",'"+st+"',"+tempdata[6]+","+tempdata[7]+","+ambmir+")");
            }
        }
        statment.close();
        res.close();
        con.close();
        return num ;

   }


   public int parsecusalg(String file,String expname,String gen,String spe,int chrin, int startin,int endin,int stin,int seqin, int idin, int mmin, int gapin,int startseqin, int endseqin) throws FileNotFoundException, IOException, SQLException{
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
        String host=SeqBusterView.HosMysText.getText();
        String db=SeqBusterView.loaprotext.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();

        if (!port.matches("")){host=host+":"+port;}
        TreeMap<String,Long[]> index = new TreeMap<String,Long[]>();
        TreeMap<String,TreeMap<String,Integer[]>> seqinfo = new TreeMap<String,TreeMap<String,Integer[]>>();
        TreeMap<String,Integer[]> tempinfo = new TreeMap<String,Integer[]>();
        TreeMap<String,Integer> tempscoreindex = new TreeMap<String,Integer>();
        TreeMap<String,String> seqmapped =new TreeMap<String,String>();
        Long [] indexdata = new Long[2];
        Integer [] tempdata=new Integer [9];

        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment = (Statement) con.createStatement();
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
//               System.out.println(l);
                col = l.split("\t");
                //col[0].replace("lcl|","");
                start=Integer.parseInt(col[startin-1]);
                end=Integer.parseInt(col[endin-1]);
                mism=Integer.parseInt(col[mmin-1]);
                gaps=Integer.parseInt(col[gapin-1]);
                hit=end-start+1;
                startseq=Integer.parseInt(col[startseqin-1]);
                endseq=Integer.parseInt(col[endseqin-1]);



                 if (stin==-1){
                    strand="+";
                    str=1;
                 }else{
                    strand=col[stin];
                    str=1;
                    if (strand.matches("-")){str=2;}
                 }
//                System.out.println(col[0]);
                len=seqmapped.get(col[0]).length();
                ad=len-hit;
               
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
       



   

        Boolean flag;
        int num=0;
        //createtable clusmap
        statment.executeUpdate("insert into `"+namepro+"`.`experiments` values ('"+expname+"clusmap','"+namepro+"','noshow','user')");
        statment.executeUpdate("create table `"+namepro+"`.`"+expname+"clusmap` (`id` int unsigned,`nc` int unsigned,`ns` int unsigned,`nl` int unsigned,`freq` int unsigned,`len` smallint unsigned,`seq` varchar (40),`chr` varchar(15),`start` int unsigned,`end` int unsigned,`strand` enum('+','-'),`mut` int(1),`add` tinyint unsigned,index (`nc`) )");
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
                statment.executeUpdate("insert into `"+namepro+"`.`"+expname+"clusmap` values (0,'"+id[1]+"',0,0,0,0,'na','"+chr[0]+"',"+startseq+","+endseq+",'"+st+"',"+tempdata[6]+","+tempdata[7]+")");
            }
        }
        statment.close();
        res.close();
        con.close();
        return num ;

   }



    public void mapping (String expname,String name,String proname,String db,String sp) throws IOException{
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            String host=SeqBusterView.HosMysText.getText();
            //String db=ProMysText.getText();
            String user=SeqBusterView.UseMysText.getText();
            String pssw=SeqBusterView.PswMysText.getText();
            String pathout=SeqBusterView.pathout.getText()+"/";
            String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
            Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
            Statement statment = (Statement) con.createStatement();
            Statement statment2 = (Statement) con.createStatement();
            ResultSet rs=null;
            int id=0;
            ArrayList<Integer> indexstart = new ArrayList<Integer >();
             ArrayList<Integer> index = new ArrayList<Integer >();
            //reeMap<Integer, Integer> data = new TreeMap<Integer,Integer>();
            ArrayList<Integer[]> data = new ArrayList<Integer[] >();
            ArrayList<String[]> datainfo = new ArrayList<String[] >();
            ArrayList<String> chrlist = new ArrayList<String >();
            BufferedReader in=null;

            rs=statment.executeQuery("select `chr` from `"+proname+"`.`"+expname+name+"` where `chr` like '%chr%' group by `chr`");

            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + proname + "/" + expname + "/"+db+".class")));

           

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
            //System.out.println(chrlist.get(j));
            if (index.size()>0){
            rs=statment.executeQuery("select * from `"+proname+"`.`"+expname+name+"` where `chr` like '"+chrlist.get(j)+"' order by `start` asc ");
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
                if (p>current_pos & current_pos_index==index.size()-1){
                    //System.out.println("break");
                    break;
                }
                if (p>current_pos ){
                    //current_pos=indexstart.get(current_pos_index);
                    //what index corresponding
                    //System.out.printf("load file \n");
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
                                Integer [] temp={Integer.parseInt(col[0]),Integer.parseInt(col[1])};
                                data.add(temp);
                                String [] temp2={col[3],col[2]};
                                datainfo.add(temp2);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //startdata=1;
                //System.out.printf("search ind1  %d\n",ind1);
                int newind=searchb(p,ind1,data);

                ind1=newind;
                if (ind1<0){
                    //not mapped
                    //System.out.printf("non mapped %d pos %d start %d\n",ind1,p,start);

                    ind1=-1*ind1;
                    if (ind1==-1){ind1=0;}
//                    break;
                }else{
                    //System.out.printf("mapped %d\n",ind1);
                    //+/-,name,from start%
                    String [] tempdata=datainfo.get(ind1);
                    Integer [] tempdata2=data.get(ind1);
                    String sthit=tempdata[0];
                    String nmhit=tempdata[1];
                    String sense="+";
                    //if + coord-start
                    int pos=0;
                    if (sthit.matches("-") & st.matches("-")){
                        pos=start-tempdata2[0];
                        double pos2=((pos*1.0)/(tempdata2[1]-tempdata2[0])*100.0);
                        pos=      (int) Math.round(pos2);
                    }else if (!sthit.matches("-") & !st.matches("-") ) {
                        pos=start-tempdata2[0];
                        double pos2=((pos*1.0)/(tempdata2[1]-tempdata2[0])*100.0);
                        pos=      (int) Math.round(pos2);
                        
                    }else {
                        pos=start-tempdata2[0];
                        double pos2=((pos*1.0)/(tempdata2[1]-tempdata2[0])*100.0);
                        pos=      (int) Math.round(pos2);
                        sense="-";
                    }
//                    System.out.println(pos+" ");
                    String result=sense+"_"+nmhit;
                    
                    statment2.executeUpdate("update `"+proname+"`.`"+expname+name+"` set `"+db+"`='"+result+"' where `id`="+id);
                   
                    out.write(id+"\t"+sense+"\t"+nmhit+"\t"+pos+"\t"+chrlist.get(j)+":"+tempdata2[0]+"-"+tempdata2[1]+"\n");
                    //mapped
                    ind1--;
                    mapped++;
                }

            }
            }
        }


//            System.out.printf("num mapped %d\n",mapped);
            out.close();
            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }


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

//                System.out.printf("i1 %d i2 %d mid %d f %d\n",i1,i2,indm,f);
                Integer [] data = d.get(indm);
//                Integer [] datap = d.get(indm-1);
//                System.out.printf("pos %d start %d end %d\n",pos,data[0],data[1]);

                if (pos>data[0] & pos<data[1]){
                    f=indm;
                    stop=true;
//                    System.out.printf("mapped f %d\n",f);
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
//                System.out.printf("return f %d\n",f);

            }

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
                //System.out.printf("return f %d\n",f);

            }

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

}
