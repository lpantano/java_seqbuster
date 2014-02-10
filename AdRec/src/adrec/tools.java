/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adrec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class tools {


     public static boolean checkinput(String namein) throws FileNotFoundException, IOException{
        String l="";
        BufferedReader in= new BufferedReader(new FileReader(namein));
        l=in.readLine();
        if (!l.contains(">")){
            System.out.println("Format is not normal fasta");
           //return false;
        }
        l=in.readLine();
        if (!l.matches("[NUATCG]+")){
             System.out.println(l+"No sequence found at line 2");
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
}
