
package miraligner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;


public class tools {


     public static boolean checkinput(String namein) throws FileNotFoundException, IOException{
        String l="";
        BufferedReader in= new BufferedReader(new FileReader(namein));
        l=in.readLine();
        if (!l.contains(">")){
            System.out.println("Format is not fasta, guessing tabular");
           return false;
        }
        l=in.readLine();
        if (!l.matches("[NUATCG]+")){
             System.out.println(l+"No sequence found at line 2, check your format");
            return false;
        }
        

        return true;
    }

     public static boolean checkinputtab(String namein) throws FileNotFoundException, IOException{
        String l="";
        BufferedReader in= new BufferedReader(new FileReader(namein));
        l=in.readLine();
        if (!l.contains("\t")){
            System.out.println("Format is not tabular,guessing fasta");
           return false;
        }

        return true;
    }

     public static boolean checkdir(String namein){
       if ( !new File(namein+"/hairpin.fa").exists()){
           System.out.println("hairpin.fa not found");
            return false;
       }
       if ( !new File(namein+"/miRNA.str").exists()){
           System.out.println("miRNA.str not found");
            return false;
       }
        return true;
    }

     public static boolean checksp(String namein,String sps) throws FileNotFoundException, IOException{
        BufferedReader in= new BufferedReader(new FileReader(namein+"/hairpin.fa"));
        String l="";
        while ((l=in.readLine())!=null)	{
            if (l.contains(sps)){
                System.out.println("species found");
                return true;
            }
        }
        return false;
     }
     
     public static TreeMap<String,String> getseq(String namein,String f) throws FileNotFoundException, IOException{
        Integer namecode=0;
        String l="";
        System.out.println("Reading reads");
 
        BufferedReader in= new BufferedReader(new FileReader(namein));

         TreeMap<String,String> seq = new TreeMap<String,String>();
         if ("fasta".equals(f)){
             while ((l=in.readLine())!=null){
                if (l.contains(">")){

                    namecode++;
                    String name=l.replace(">","");

                    //String [] col=l.split("\t");

                    seq.put(name, in.readLine());
                }

            }
         }else{
             Integer idx=0;
            while ((l=in.readLine())!=null){
                    
                    idx++;
                    String [] col=l.split("\t");
                    String name="seq_"+idx+"_x"+col[1];
                    seq.put(name, col[0]);
                    //System.out.println(name);

            }
 
         
         }
         in.close();
         return seq;
     }
}
