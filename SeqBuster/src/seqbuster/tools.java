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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import seqbuster.SeqBusterView.*;
/**
 *
 * @author lo
 */
public class tools {

    String dbpre="";
    String blastpre="";
    String foldpre="";
    String ps2pdfpre="";
    String outpre="";
    String user="";
    String host="";
    String passw="";
    String port="";
    String mysql="";
    String r="";
    
    public static void showerror(String e,String e2){
       
        JOptionPane.showMessageDialog(null, "Error: send log file inside seqbuster folder to lorena.pantano@crg.es:\n " 
                + e+"\n"+e2,"Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void showerror(String e){

        JOptionPane.showMessageDialog(null, "Error: send log file inside seqbuster folder to lorena.pantano@crg.es:\n " 
                + e,"Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void showcerror(String e){

        JOptionPane.showMessageDialog(null,  e,"Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void showinfo(String e){
        Icon icon= new ImageIcon("lib/logo.png");
        JOptionPane.showMessageDialog(null, e,"SeqBuster",JOptionPane.INFORMATION_MESSAGE,icon);
    }
    public boolean fileexist (String filename){
     File file=new File(filename);
    boolean exists = file.exists();
    return exists;

    }

    public static Connection docon (String namepro) throws SQLException{
        
        String host=SeqBusterView.HosMysText.getText();
        String db=SeqBusterView.loaprotext.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);

        return con;
    }

    public static void freememory(){

        Runtime.getRuntime().gc();
    }
    public void readparameters (String fileoptions){
        try {
            String l;
            String col[];
            BufferedReader in= new BufferedReader(new FileReader(fileoptions));
            while ((l=in.readLine())!=null)
            {
//                if (l.contains("pathdb")){
//                    col=l.split("=");
//                    //SeqBusterView.pathdb.setText("kk");
//                    //System.out.println(col[1]);
//                    if (col.length==2){dbpre=col[1];}
//                }
                if (l.contains("pathblast")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){blastpre=col[1];}
                }
                if (l.contains("pathfold")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){foldpre=col[1];}
                }
                if (l.contains("pathps2pdf")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){ps2pdfpre=col[1];}
                }
                if (l.contains("pathout")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){outpre=col[1];}
                }
                if (l.contains("user")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){user=col[1];}else{
                    user="root";
                    }
                }
                if (l.contains("host")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){host=col[1];}else{
                        host="localhost";
                    }
                }
                if (l.contains("passw")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){passw=col[1];}
                }
                if (l.contains("port")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){port=col[1];}
                }
                if (l.contains("mysql")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){mysql=col[1];}
                }
                if (l.matches("r")){
                    col=l.split("=");
                    //SeqBusterView.pathblast.setText(col[1]);
                    if (col.length==2){r=col[1];}
                }

            }
        }catch (IOException ie){

         }

    }

    public void saveparameters (String fileoptions){
        try {

            PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(fileoptions)));
//            out.write("pathdb="+dbpre+"\n");
            out.write("pathblast="+blastpre+"\n");
            out.write("pathfold="+foldpre+"\n");
            out.write("pathps2pdf="+ps2pdfpre+"\n");
            out.write("pathout="+outpre+"\n");
            out.write("user="+user+"\n");
            out.write("host="+host+"\n");
            out.write("passw="+passw+"\n");
            out.write("port="+port+"\n");
            out.write("mysql="+mysql+"\n");
            out.write("r="+r+"\n");
            out.close();
        }catch (IOException ie){

         }

    }

    public void removefile(String name){
        File n = new File(name);
        n.delete();
    }

    public static boolean seqis (String str){
        Pattern p = Pattern.compile("[ATGCU]+");
        Matcher m = p.matcher(str);
        return (m.find());

    }

   public static boolean removefiles (String str,String pathout){

    File dir = new File(pathout);
    String[] children = dir.list();
    for (int i = 0; i < children.length; i++) {

        if (children[i].contains(str)) {
            //System.out.println(children[i]);
            File fileremv = new File(pathout+"/"+children[i]);
            fileremv.delete();
        }
    }
    return true;
   }

   public static boolean removedir (String pathout){

    File dir = new File(pathout);
    dir.delete();
    return true;
   }
   
   public static boolean app(String cmd) {
    try {
      String line,out="";
      Process p = Runtime.getRuntime().exec(cmd);
      //System.out.println("running "+cmd);
      BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        out=out+(line);
      }
      input.close();
     
      //System.out.println("ending "+cmd);
    }
    catch (Exception err) {
      err.printStackTrace();
    }
    return true;
  }
   
   public static boolean writelog (Logger logger,SQLException ex){
    try {
    // Create a file handler that write log record to a file called my.log
    FileHandler handler = new FileHandler("logs");

    // Add to the desired logger
    
    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    SimpleFormatter formatter = new SimpleFormatter();
    handler.setFormatter(formatter);

      // the following statement is used to log any messages   
    logger.log(Level.SEVERE, null, ex);;
      } catch (IOException e) {
          e.printStackTrace();
    }
    return true;
   }
   
    public static boolean writelog (Logger logger,IOException ex){
    try {
    // Create a file handler that write log record to a file called my.log
    FileHandler handler = new FileHandler("logs");

    // Add to the desired logger
    
    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    SimpleFormatter formatter = new SimpleFormatter();
    handler.setFormatter(formatter);
    
      // the following statement is used to log any messages   
    logger.log(Level.SEVERE, null, ex);
    tools.showerror(ex.toString());
      } catch (IOException e) {
          e.printStackTrace();
    }
    return true;
   }
   
    public static boolean writelog (Logger logger,InterruptedException ex){
    try {
    // Create a file handler that write log record to a file called my.log
    FileHandler handler = new FileHandler("logs");

    // Add to the desired logger

    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    SimpleFormatter formatter = new SimpleFormatter();
    handler.setFormatter(formatter);

      // the following statement is used to log any messages
    logger.log(Level.SEVERE, null, ex);
    tools.showerror(ex.toString());
      } catch (IOException e) {
          e.printStackTrace();
    }
    return true;
   }

   public static boolean appwait(String cmd) {
    try {
      String line,out="";
      Process p = Runtime.getRuntime().exec(cmd);
      //System.out.println("running "+cmd);
      BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        out=out+(line);
      }
      input.close();
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(NonMiRNAanaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      //System.out.println("ending "+cmd);
    }
    catch (Exception err) {
      err.printStackTrace();
    }
    return true;
  } 

public static boolean existfile (String file){
 if (new File(file).exists()){
  tools.showcerror(file+" doesn't found. Check the location in preferences.");
  return false;

 }
 return true;
}

}
