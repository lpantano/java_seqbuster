/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seqbuster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class nonMirnaAna {
    
    static String namepro=SeqBusterView.loaprotext.getText();
    static String pathout=SeqBusterView.pathout.getText();
    static String host=SeqBusterView.HosMysText.getText();
    static String user=SeqBusterView.UseMysText.getText();
    static String pssw=SeqBusterView.PswMysText.getText();
    static String pathps2pdf=SeqBusterView.ps2pdfText.getText();
    //Get  genome database
    public static boolean gettable (String namepro,String nameexp,String db,String sp) throws SQLException{
     Connection con =  tools.docon(namepro);
     String pathout=SeqBusterView.pathout.getText()+"/";
     Statement statment =  con.createStatement();
     int id=0;
     TreeMap<String,Long> chrindex = new TreeMap<String,Long>();
     
     if (db!=null){
         ResultSet rs=statment.executeQuery("select *  from `genome`.`"+db+"_"+sp+"` ");
         
         boolean success = (new File(pathout+namepro+"/"+nameexp+"/results_usRNAs/html")).mkdir();
           //System.out.println(success);
         success = (new File(pathout+namepro+"/"+nameexp+"/results_usRNAs/html/ss")).mkdir();

         PrintWriter out;

         while (rs.next()) {
           chrindex.put(rs.getString(1),rs.getLong(2));

         }
         rs.close();
     }
     ResultSet rs=statment.executeQuery("select *  from `"+nameexp+"clusmap` where `ns` >0 ;");
     try {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout+namepro+"/"+nameexp+"/results_usRNAs/html/index.html")));
        out.write("<style type=\"text/css\">"
                + "table.sample {"
                + "border-width: 2px;"
                + "border-spacing: 2px;"
                + "border-style: solid;"
                + "border-color: black;"
                + "border-collapse: collapse;"
                + "background-color: white;"
                + "}"
                + "table.sample th {"
                + "border-width: 1px;"
                + "padding: 1px;"
                + "border-style: inset;"
                + "border-color: gray;"
                + "background-color: #79BAEC;"
                + "-moz-border-radius: ;"
                + "}"
                + "table.sample td {"
                + "border-width: 1px;"
                + "padding: 1px;"
                + "border-style: inset;"
                + "border-color: gray;"
                + "background-color: white;"
                + "-moz-border-radius: ;"
                + "}"
                + "</style>"
                + "");
        out.write("Summary of the clusters detected.<br> ");
        out.write("If you upload to the UCSC browser the file: <b>cluster.bed</b> "
                +" (it is in the folder corresponding this experiment) and click on the"
                +" position link, you will see the cluster information in the genome browser.<br> ");

         while (rs.next()) {
           //System.out.println(rs.toString());
           int idrow=rs.getInt(1);
           int nclrow=rs.getInt(2);
           int frow=rs.getInt(6);
           int lrow=rs.getInt(7);
           String srow=rs.getString(8);
           String chr=rs.getString(9);
           String strand=rs.getString(12);
           String prow=rs.getInt(10)+"-"+rs.getInt(11);
           Integer [] pos = {rs.getInt(10),rs.getInt(11)};
           if (id!=idrow){
   
            out.write("<br><div style=\"width:100%;border:1px solid blue;\" name="+idrow+">id usRNA: "+idrow+"<br><table  width=100% class=\"sample\">");
            out.write("<tr><th>num seqs</td><th>num loci</th><th>freq</th><th>position</th><th>Strand</th>"+
                "<td>RNA fold ss</th><th>Detection score</th></tr>");

           }
           out.write("<tr><td>"+srow+"</td><td>"+lrow+"</td><td>"+frow+"</td><td>"+chr+":"+prow+"</td><td>"+
                   strand+"</td><td><a href= \'ss/"+idrow+"_"+nclrow+".pdf\' >See secondary structure</a></td><td><a href=fdr.html#"+idrow+" >Go...</a></td></tr>");
           String seq="";
                try {
                    seq=precursor(db,sp,chr,pos,strand,chrindex.get(chr));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(nonMirnaAna.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(nonMirnaAna.class.getName()).log(Level.SEVERE, null, ex);
                }
           //do rnafold and ranplot
           rnafold(seq,pos);

           String cmd=pathps2pdf+"/ps2pdfwr rna.ps "+pathout+namepro+"/"+nameexp+"/results_usRNAs/html/ss/"+idrow+"_"+nclrow+".pdf";
           tools.app(cmd);
           id=idrow;
           out.write("</table></div>");
         }
         rs.close();
      
      out.close();
      tools.showinfo("You results are in:"+pathout+"/"+namepro+"/"+nameexp+"/results_usRNAs/html/index.html");

    } catch (IOException ex) {
        Logger.getLogger(nonMirnaAna.class.getName()).log(Level.SEVERE, null, ex);
    }
     return true;
    }
    
    //Do html table with FDR and RNA SS plots
    public static boolean pvalue (String expname) throws IOException{


                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("temp/usRNA")));
                String cmd = namepro + "\n" + expname+"\n";
                out.write(cmd);
                out.close();
                tools check = new tools();
                check.readparameters("seqbuster.op");
                String r = check.r;
                if (!r.matches("")) {
                    r = r + "/";
                }
                cmd = r + "R CMD BATCH --vanilla --no-save Rscripts/R/fdr.R  temp/fdr.Rout";

                tools.appwait(cmd);
                //create html

                out = new PrintWriter(new BufferedWriter(new FileWriter(pathout +"/"+ namepro + "/" + expname + "/results_usRNAs/html/fdr.html")));
                BufferedReader infasta = new BufferedReader(new FileReader("temp/fdr"));
                String l = null;
                out.write("<style type=\"text/css\">" + "table.sample {"
                        + "border-width: 2px;" + "border-spacing: 2px;"
                        + "border-style: solid;" + "border-color: black;"
                        + "border-collapse: collapse;" + "background-color: white;"
                        + "}" + "table.sample th {" + "border-width: 1px;"
                        + "padding: 1px;" + "border-style: inset;"
                        + "border-color: gray;" + "background-color: #79BAEC;"
                        + "-moz-border-radius: ;" + "}" + "table.sample td {"
                        + "border-width: 1px;" + "padding: 1px;" + "border-style: inset;"
                        + "border-color: gray;" + "background-color: white;"
                        + "-moz-border-radius: ;" + "}" + "</style>" + "");
                out.write("<div>Any usRNA with a pval<= 0.05"
                        +" is statistically significant that sequences half expressed or more "
                        +" are detected with the current coverage/deeph.</div><br>");
                out.write("<table   class=\"sample\">");
                out.write("<tr ><th width=50 >id</td><th width=80>p-value</td></tr>");
                while ((l = infasta.readLine()) != null) {
                    String[] col = l.split("\t");
                    out.write("<tr><td><a name=" + col[0] + " >" + col[0] + "</td><td>" + col[1] + " </td></tr>");
                }
                out.write("</table>");
                out.close();
     return true;
    }
    //Function RNAfold and RNAplot
    public static boolean rnafold(String seq, Integer[] pos) {
        String rnafold=SeqBusterView.pathfold.getText();
        
        //$rnaplot_ps_macro = '10 15 8 255 0 0 omark ';
        //my $cmd = "echo '$long_seq' | $RNAfold_cmd > $RNAfold_out_file" ; # run RNAfold
        //$cmd = "$RNAplot_cmd --pre \"$rnaplot_ps_macro\" < $RNAfold_out_file " ; #run RNAplot
        int len=pos[1]-pos[0]+51;
        String macro="50 "+len+" 8 255 0 0 omark ";
        PrintWriter out;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("temp/rnafold_in")));
            out.write(seq);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(nonMirnaAna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("temp/rnafold_bash")));
            String cmd=rnafold+"/RNAfold < temp/rnafold_in > temp/rnafold_out";
            out.write(cmd+"\n");
            cmd=rnafold+"/RNAplot  --pre \""+macro+"\" < temp/rnafold_out";
            out.write(cmd+"\n");
            out.close();
        } catch (IOException ex) {
            Logger log =Logger.getLogger(nonMirnaAna.class.getName());
            tools.writelog(log,ex);
        }
        
        String cmd="bash temp/rnafold_bash";
        tools.app(cmd);
     return true;
    }
    //function to FDR
    
    
    //ss in each track
    public static boolean sstrack ( String track, String spe,String expname,String org, String version,String db) throws SQLException{
      Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
      Statement statment = (Statement) con.createStatement();
      TreeMap<String,ArrayList<String>> geneslist = new TreeMap<String,ArrayList<String>>();
      String rnafold=SeqBusterView.pathfold.getText();
      //genome index
      TreeMap<String,Long> chrindex = new TreeMap<String,Long>();
      TreeMap<String,Integer []> genepos = new TreeMap<String,Integer []>();
      ResultSet rs=statment.executeQuery("select *  from `genome`.`"+db+"_"+spe+"`;");
     

      while (rs.next()) {
       chrindex.put(rs.getString(1),rs.getLong(2));
  
      }
      //genes positions
      rs=statment.executeQuery("select *  from `tracks`.`"+track+"_"+spe+"_data`;");
      while (rs.next()) {
       Integer [] pos= {rs.getInt(2),rs.getInt(3)};
       genepos.put(rs.getString(5),pos);
  
      }
      
      //create folder
      boolean success = (new File(pathout+"/"+namepro+"/"+expname+"/results_usRNAs/html/genes")).mkdir();
      //create bash file
      
      try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("temp/rnapdist_bash")));
            String cmd=rnafold+"/RNApdist -Xm < temp/rnapdist_in > temp/rnapdist_out";
            out.write(cmd+"\n");
            out.close();
            out = new PrintWriter(new BufferedWriter(new FileWriter("temp/random_bash")));
            cmd=rnafold+"/RNApdist -Xm < temp/random_in > temp/random_out";
            out.write(cmd+"\n");
            cmd="rm *ps";
            out.write(cmd+"\n");
            out.close();
        } catch (IOException ex) {
            Logger log =Logger.getLogger(nonMirnaAna.class.getName());
            tools.writelog(log,ex);
        }
      //get usRNa in genes
      try {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout+"/"+namepro+"/"+expname+"/results_usRNAs/html/genes.html")));
        out.write("<style type=\"text/css\">"
                + "table.sample {"
                + "border-width: 2px;"
                + "border-spacing: 2px;"
                + "border-style: solid;"
                + "border-color: black;"
                + "border-collapse: collapse;"
                + "background-color: white;"
                + "}"
                + "table.sample th {"
                + "border-width: 1px;"
                + "padding: 1px;"
                + "border-style: inset;"
                + "border-color: gray;"
                + "background-color: #79BAEC;"
                + "-moz-border-radius: ;"
                + "}"
                + "table.sample td {"
                + "border-width: 1px;"
                + "padding: 1px;"
                + "border-style: inset;"
                + "border-color: gray;"
                + "background-color: white;"
                + "-moz-border-radius: ;"
                + "}"
                + "</style>"
                + "");
        
        rs=statment.executeQuery("select `id`,`ncl`,`nc`,`"+track+"_"+spe
                +"`,`chr`,`start`,`end`,`strand`,`freq`,`idu` from `"+expname+"clusmap` where `id`>0 and `"+track+"_"+spe+"` not like 0;");
 
      while (rs.next()) {
        int idrow=rs.getInt(1);
        int nclrow=rs.getInt(2);
        int nc=rs.getInt(3);
        int exp=rs.getInt(9);
        int idu=rs.getInt(10);
        String chr=rs.getString(5);
        String strand=rs.getString(8);
        String prow=rs.getInt(6)+","+rs.getInt(7);
        //System.out.println(rs.getString(1));
        String genes=rs.getString(4);
        String [] g = genes.split(",");
        for (String gname : g) {
            if (geneslist.containsKey(gname)){
              ArrayList<String> srnalist=geneslist.get(gname);
              srnalist.add(idrow+","+nclrow+","+nc+","+exp+","+chr+","+strand+","+prow+","+idu);
              geneslist.put(gname, srnalist);
            }else{
              ArrayList<String> srnalist = new ArrayList<String>();
              srnalist.add(idrow+","+nclrow+","+nc+","+exp+","+chr+","+strand+","+prow+","+idu);
              geneslist.put(gname, srnalist);
            }
        }

      }
      out.write("<div>All usRNA in the same gene are together here."
              +" The graphic showing the structure probability represents a distance measures between:"
              +" the usRNA structure and 100 random structures of the same gene and 99 random structure"
              +" coming from the same gene. If the two groups are highly overlapped, the usRNA structure"
              +" appears on other region of the gene (without usRNA detection) and it is unlikely that"+
              " its structure is the unique factor involved in the generation of the usRNA.</div>");
      for (String gname : geneslist.keySet()){
          int len=geneslist.get(gname).size();
          out.write("<br><div style=\"width:100%;border:1px solid blue;\">gene: "
                  +gname+" with "+len+" usRNAs <br><table  width=100% class=\"sample\">");
       
        out.write("<tr><th>id</td><th>id locus</th><th>id cluster</th><th>position</th>"+
                "<th>id inter-sample</th><td>RNA fold ss</th><th>RNAfold</th><th>RNAdist</th></tr>");
       
       
       PrintWriter srnaseq = new PrintWriter(new BufferedWriter(new FileWriter("temp/rnapdist_in")));
       for (String srna : geneslist.get(gname)){
           String [] data = srna.split(",");
           String pos=data[4]+":"+data[6]+"-"+data[7];
           Integer [] posx  = {Integer.parseInt(data[6]),Integer.parseInt(data[7])};
           String [] garray=gname.split(":");
           garray[0]=garray[0].replace("$","");
           
           //function to get random 100 position and histograme score
           PrintWriter outrandom = new PrintWriter(new BufferedWriter(new FileWriter("temp/random_in")));
           get_random_pos_gene(outrandom,genepos.get(garray[0]),data[5],db,spe,data[4],chrindex.get(data[4]),posx[1]-posx[0]+1);
           outrandom.close();
           String cmd="bash temp/random_bash";
           tools.app(cmd);
           //create figures in R
           parse("temp/random_out");
           tools check=new tools();
                    check.readparameters("seqbuster.op");
                    String r=check.r;
                    if (!r.matches("")){
                        r=r+"/";
                    }
           cmd=r+"R CMD BATCH --vanilla --no-save Rscripts/R/gene-sRNA.R  temp/gene-sRNA.Rout";
           tools.appwait(cmd);
           cmd="mv temp/random-srna.png "+pathout+"/"+namepro+"/"+expname+"/results_usRNAs/html/genes/"+garray[0]+"_"+data[0]+"_"+data[1]+".png";
           tools.app(cmd);
           out.write("<tr><td><a href=index.html#"+data[0]+" >"+data[0]+"</a></td><td>"+data[1]+"</td><td>"+data[1]+"</td><td>"+data[3]
                   +"</td><td><a href=\"http://genome.ucsc.edu/cgi-bin/hgTracks?org="+org+"?db="+version+"&position="+pos+"\" >"+
                   pos+"</a></td><td>"+data[8]+"</td><td><a href= \'ss/"
                   +data[0]+"_"+data[1]+".pdf\' >See secondary structure</a></td>"+"<td><a href=genes/"+garray[0]+"_"+data[0]+"_"+data[1]+".png >See structure probability</a></td></tr>");
           
           String seq=precursor(db,spe,data[4],posx,data[5],chrindex.get(data[4]));
           srnaseq.write(seq+"\n");
            
           
       }
       out.write("</table><br>");
       srnaseq.close();
       
       if (len>1){
         String cmd="bash temp/rnapdist_bash";
         tools.app(cmd);
         gname=gname.replace(":","");
         gname=gname.replace("+","plus");
         gname=gname.replace("-","minus");
         cmd="mv temp/rnapdist_out "+pathout+"/"+namepro+"/"+expname+"/results_usRNAs/html/genes/"+gname+".txt";
         tools.app(cmd);
         out.write("See matrix similarity between secondary structure of previous usRNAs <a href=genes/"+gname+".txt >Read</a><br>");
        
       }
       out.write("</div>");
      }
           
      tools.showinfo("You results are in:"+pathout+"/"+namepro+"/"+expname+"/results_usRNAs/html/genes.html");
      rs.close();
      statment.close();
      con.close();
      //out.write("</table></div>");
      out.close();
    } catch (IOException ex) {
        Logger.getLogger(nonMirnaAna.class.getName()).log(Level.SEVERE, null, ex);
    }
      
     
     
     return true;
    }
    
    private static boolean parse(String file) throws FileNotFoundException, IOException{
     BufferedReader infasta= new BufferedReader(new FileReader(file));
     String l=infasta.readLine();
     PrintWriter outrandom1 = new PrintWriter(new BufferedWriter(new FileWriter("temp/random_g1")));
     PrintWriter outrandom2 = new PrintWriter(new BufferedWriter(new FileWriter("temp/random_g2")));
     
      while (( l=infasta.readLine())!=null){
        //System.out.print(l+"\n");
       if (l.contains(" ")){
        //System.out.print(l+"\n");
        String col []=l.split(" ");
        //System.out.print(col[0]+"\n");
        outrandom1.write(col[0]+"\n");
        if (col.length>1){
            for (int i=1;i<col.length;i++){
             outrandom2.write(col[i]+"\n");
            }
        }
        //System.out.print("\n");
       }
      }
      outrandom1.close();
      outrandom2.close();
     return true;
    }
    private static boolean get_random_pos_gene (PrintWriter  out,Integer pos [],String strand,String db, String sp, String c, Long cpos,int len) throws FileNotFoundException, IOException{
     long ts = (new Date()).getTime();
     Random generator = new Random( ts );
     for (int i=0;i<100;i++){
      int r = generator.nextInt(pos[1]-pos[0])+pos[0];
      Integer [] posr={r,r+len};
      String seq=precursor(db,sp,c,posr,strand,cpos);
      //wirte seq
      out.write(seq+"\n");
     }
     
     return true;
    }
    
    public static String precursor ( String db,String sp,String chr, Integer [] pos,String strand,Long chrpos) throws FileNotFoundException, IOException{

     RandomAccessFile rand = new RandomAccessFile("DB/genome/"+db+"_"+sp+".db","r");
     String seq="";  
     
     int bon=80-(pos[1]-pos[0]+1);
     int side=bon/2;
     rand.seek(pos[0]-side+chrpos-1); 
     for(int ct = 0; ct < 100; ct++){
      byte b = rand.readByte();
      seq=seq+((char)b); 
     }
     
     if(strand.matches("-")){
         //reverse string
         //System.out.println("minus strand "+seq);
         seq=seq.replace("A", "1");
         seq=seq.replace("T", "2");
         seq=seq.replace("C", "3");
         seq=seq.replace("G", "4");
         seq=seq.replace("1", "T");
         seq=seq.replace("2", "A");
         seq=seq.replace("3", "G");
         seq=seq.replace("4", "C");
         seq=reverseIt(seq);
         //System.out.println("reverse "+seq);
     }
     return seq;
   }
    
    public static String reverseIt(String source) {
    int i, len = source.length();
    StringBuffer dest = new StringBuffer(len);

    for (i = (len - 1); i >= 0; i--)
      dest.append(source.charAt(i));
    return dest.toString();
  }
    
}
