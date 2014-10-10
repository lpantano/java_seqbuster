/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adrec;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.beust.jcommander.*;

/**
 *
 * @author k3j
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Options jct = new Options();
        //String[] argv = { "-s", "hsa", "-trim", "3" };
        new JCommander(jct, args);
        if (jct.help || args.length == 0){
            System.out.println("\njava -jar adrec.jar -i file -a adapter -m 1 -l 8 -c 0.3 -s 15 -e 40 -o output");
            System.out.println("\nexample:java -jar adrec.jar -i test/test.fastq -a ATCTCGTATGCCGTCTTCTGCTTG -m 1 -l 8 -c 0.3 -s 15 -e 40 -o test/out");
            System.out.println("example: see output at adrec/test/out.ad & adrec/test/seq.noad");
            System.out.println("\n");
            System.exit(1);
        }
        //check directory
        String ad=jct.adapt;

        //check species
        int mm=1;
        if (jct.mut>=3){
           System.out.println("Only allowed 0-3 mismatch");
            System.exit(1);
            
        }else{
            mm=jct.mut;
        }
        int len=8;
        if (jct.len<5){
           System.out.println("Adapter length should be > 5 nts");
           System.exit(1);
        }else{
            len=jct.len;
        }
        double com=0.3;
        if (jct.com<0.3){
           System.out.println("Complexity should be > 0.3");
           System.exit(1);
        }else{
            com=jct.com;
        }
        int cutsmall=15;
        if (jct.start<10){
           System.out.println("Only longer than 10");
            System.exit(1);
            
        }else{
            cutsmall=jct.start;
        }
        int cutlong=40;
        if (jct.end>60){
           System.out.println("Only less than 60");
            System.exit(1);
            
        }else{
            cutlong=jct.end;
        }
        if (cutsmall>cutlong){
           System.out.println(cutsmall+" can not be greater than "+cutlong);
            System.exit(1);
            
        }
            System.out.println("Go to adapter recognition...");
            System.out.println("Mismatches: "+mm);
            System.out.println("Length to be recognized: "+len);
            System.out.println("Complexity threshold: "+com);
            System.out.println("Adapter: "+ad);
            System.out.println("Minimum size seqs: "+cutsmall);
            System.out.println("Maximum size seqs: "+cutlong);
            AdaptorRecognition.readfilesimple(jct.input, ad, com, len, mm, 1,cutsmall, cutlong,jct.output);
    }

}
