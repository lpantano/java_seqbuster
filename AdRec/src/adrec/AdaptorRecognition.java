

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

package adrec;
import java.io.*;
import java.util.*;
import java.lang.Math.*;
import java.lang.Thread.*;


public  class AdaptorRecognition   {
    
                   
         //readfilesimple(filename, pathout, adseq, adseq5, fasta, header, skip, com, lenad, mmad);
  

   
        public static int readfilesimple (String namein, String ad, Double com,int lenad, int mmad,int mincount,int cutsmall,int cutlong,String nameo)  throws IOException{

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //CUT ADAPTER TO MINLEN PARAMETER
        ad=ad.substring(0,lenad);
        Date date = new Date();
        boolean infoad;
        infoad=false;
        Integer [] lendist = new Integer [100];
        for (int p=0;p<lendist.length;p++){
            lendist[p]=0;
         }
        int comxseq=0;
        int longseq=0;
        int noadseq=0;
//        System.out.println(infoad);

        HashMap<String,Integer> seq = new HashMap<String,Integer>();

    
        String l;
        String [] col;
        String  prefix="";
        String sufix="";
        //String  prefix5="";
        String sufix5="";
        int lenadtotal=ad.length();
        String [] r;
        int seqind=0,i,count=0,detected=0;
        Double comseq=0.0;
        
        //col = namein.split("\\/");
        //String nameannfile=col[col.length-1];
        PrintWriter outinfo=new PrintWriter(new BufferedWriter(new FileWriter(nameo+".info")));
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(nameo+".ad")));
        PrintWriter outcom=new PrintWriter(new BufferedWriter(new FileWriter(nameo+".complex")));
        PrintWriter outnoad=new PrintWriter(new BufferedWriter(new FileWriter(nameo+".noad")));
        PrintWriter outlong=new PrintWriter(new BufferedWriter(new FileWriter(nameo+".longer")));
	BufferedReader in= new BufferedReader(new FileReader(namein));
        //String ad;
		//StringBuilder sd=new StringBuilder();
     
	Integer freq,totalseq=0,totalseqdect=0;
        String name="";


        System.out.print(" lines: 0 -> sequences 0...");
	while ((l=in.readLine())!=null)	{
            //System.out.println("ind:"+seqind+" "+col[seqind]+" "+l+" "+fasta);
                //l=in.readLine();
                
           seqind=0;
                //System.out.println("ind:"+seqind+" "+col[seqind]+" "+l+" "+fasta);
           
	    
	    
            if(l.matches("^[ATGUCN]+$") & !(l.contains(".")) & !(l.contains(">") )) {
            //System.out.println("ind:"+seqind+" "+col[seqind]);
            count++;
            if (count%500000==0){
                System.out.print("\r lines: "+count+" -> sequences "+totalseqdect+"...");

            }
            totalseq++;
            detected=1;
            prefix=l;
            
            if(detected==1){
            //System.out.println(col[seqind]);
            r=getadapter3(prefix,ad,lenad,0,lenadtotal,mmad);
            prefix=r[0];
            sufix=r[1];


            //System.out.println("s:"+prefix+",a:"+sufix );

            //get adapter infor
            comseq=complex(prefix);
            
           // System.out.println("s:"+prefix+" f: "+sufix+" com "+comseq);
            if (comseq>=com & prefix.length()<l.length()){
                totalseqdect++;
                lendist[prefix.length()+1]=lendist[prefix.length()+1]+1;
                if (seq.containsKey(prefix)){
                    int ft=seq.get(prefix);
                    seq.put(prefix,ft+1);
               }else{
                   seq.put(prefix,1);
               }

            }else if (prefix.length()<l.length()){
                totalseqdect++;
                lendist[prefix.length()+1]=lendist[prefix.length()+1]+1;
            
            }else if (comseq<com){
                //save more complex sequences
                outcom.write(prefix+"\n");
                comxseq=comxseq+1;
            }else if (prefix.length()==l.length()){
                //print no adapter removal
                outnoad.write(name+"\n"+prefix+"\n");
                noadseq=noadseq+1;
            }

         }//detected if
         }
            name=l;

        }

	in.close();

        System.out.println("Writing file to upload to the SeqBuster Server...\n");
        System.out.println("Writing sequences: "+seq.size()+" \n");
        int countseq=0,smallseq=0,rnaseq=0;
        for (String s : seq.keySet()) {

             freq=seq.get(s);
             if (freq>0 & !(s.contains("."))){
                    //System.out.println(s+":"+seq.get(s)+",ad:"+weblogo+" ad5:"+weblogo5 );
                 //out.write(s+"\t"+seq.get(s)+"\n");
                 int len=s.length();
                 //System.out.println(s+"\t"+freq+"\n");
                 if (seq.get(s)>=mincount & len>=cutsmall & len<=cutlong ){
                     countseq++;
                    // String insertreads="insert ignore into `reads` values ('','"+s+"')";
                     //statment.executeUpdate(insertreads);
                     rnaseq=rnaseq+seq.get(s);
                     out.write(s+"\t"+seq.get(s)+"\n");
                     
                     //System.out.println="insert into `"+nameexp+"` values ("+countseq+",'"+s+"',"+seq.get(s)+","+len+",1,'na',0,0,'+','na','na','na',100,'na','na','na','na','na','1','na','na','0','0',0,0,0)";
                 }else if(len>cutlong){
                    //save longer sequences
                     outlong.write(s+"\t"+seq.get(s)+"\t");
                     longseq=longseq+seq.get(s);
                 }else if(len<cutsmall){
                    smallseq=smallseq+seq.get(s);
                 }
             }
         }
         seq.clear();
         outinfo.write("Number of sequences read: "+totalseq+"\n");
         outinfo.write("Number of sequences with adapter removal at positions 0 to "+cutsmall+": "+smallseq+"\n");
	 outinfo.write("Number of sequences with adapter removal at positions "+cutsmall+" to lenseq - "+lenad+": "+rnaseq+"\n");
         outinfo.write("These sequences are in the file: "+nameo+".ad"+"\n");
         outinfo.write("Number of sequences longer than "+cutlong+" and with adapter "+longseq+"\n");
         outinfo.write("These sequences are in the file: "+nameo+".longer"+"\n");
         outinfo.write("Number of sequences without adapter "+noadseq+"\n");
         outinfo.write("These sequences are in the file: "+nameo+".noad"+"\n");
         outinfo.write("Number of sequences with high complexity (AAAAAA) "+comxseq+"\n");
         outinfo.write("These sequences are in the file: "+nameo+".complex"+"\n");
         outinfo.write("Distribution of the adapter position in sequences:\n\n");
         for (int p=1;p<lendist.length;p++){
            outinfo.write("Position: "+p+" number of sequence: "+lendist[p]+"\n");
         }
         outinfo.close();

         out.close();
         outnoad.close();
         outlong.close();
         outcom.close();
         
       
        System.out.println("Number of sequences added: "+countseq+"\n");
        System.out.println("A complete report is in: "+nameo+".info");
        System.out.println("Process finished at: "+new Date()+"\n");
             
        return(1);
	}

    public static Double complex (String seq){
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

    public static int [] proportion (int [] ar,double f){
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
    public static int [] getlogo (int oldlog[],String ad,int lenad){

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
    public static void stop () throws InterruptedException{
        Thread.sleep(10000);

    }
    public static String[] getadapter3(String seq,String ad,int minad,int minseq,int lenad, int mmad){
        String result[]=new String[2];
        int cutsc=minad-mmad;
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
                result[0]=seq;
                result[1]="";
            }
        }
        //System.out.println("cutsc :"+cutsc+" scorem:"+sc+" best"+bestsc+"  candidate "+seqwad+" finalcandidate "+finalcandidate);

        //System.out.println("score:"+sc);
        return result;

    }
public static String[] getadapter5(String seq,String ad,int minad,int minseq,int lenad, int mmad){
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
    public static int substitution (String a,String b,int penalty,int reward){
        int score=penalty;
        if (a.matches(b)){
            score=reward;
        }
        return score;

    }

    public static int maxvalue (int a,int b,int c){
        int score=a;
        if (a<b){
            score=b;
        }
        if (score<c){
            score=c;
        }


        return score;
    }
    public static int minvalue (int a,int b){
        int score=a;
        if (a>b){
            score=b;
        }



        return score;
    }

     public static int alignment (String seq,String ad,int mmad){
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


    public static int alignment2(String seq,String ad){

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
