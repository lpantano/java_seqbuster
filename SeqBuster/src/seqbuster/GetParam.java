

package seqbuster;



import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;


public class GetParam {
    String namepro=SeqBusterView.loaprotext.getText();
    String pathout=SeqBusterView.pathout.getText()+"/";

    private boolean deletedir (File f){
        String[] children = f.list();
        for (int i=0; i<children.length; i++) {
            boolean success = (new File(f,children[i])).delete();
            if (!success) {
                return false;
            }
        }
        boolean success =f.delete();
        if (!success) {
           return false;
        }
        return true;
    }

    public boolean get (JPanel d) throws FileNotFoundException, IOException{
//        d.getcom
        ///////////////////////////check errors!!!!!
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("temp/script_param")));
        out.write("project:"+namepro+"\n");
        int group=0;
        boolean done=true;
        java.util.Date utilDate = new java.util.Date(); //current date
//        long lnMilisegundos = utilDate.getTime();
//        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
        out.write("path:"+pathout+namepro+"/miRNAscripts/last/\n");
        boolean exists = new File(pathout+namepro+"/miRNAscripts/last/").exists();
        if (exists){
            
          boolean success= deletedir(new File(pathout+namepro+"/miRNAscripts/last"));

        
        }
         (new File(pathout+namepro+"/miRNAscripts/last/")).mkdir();
        //System.out.println("in doaction");
        Component [] comp=d.getComponents();
        for (int i=0;i<comp.length;i++){
            //System.out.println("in component: "+i);
            Component component = comp[i];
            if (component instanceof JPanel){
                JPanel panel= (JPanel) component;
                //System.out.println("jpanel: ");
                Component [] comppanel=panel.getComponents();
                for (int j=0;j<comppanel.length;j++){
                    Component component2 = comppanel[j];
                    if (component2 instanceof JTextField) {
                        JTextField text = (JTextField) component2;
                        //System.out.println("text: " + text.getName() + " " + text.getText());
                        if (!text.getText().isEmpty()){
                            out.write(text.getName()+":"+text.getText()+"\n");
                        }
                    }
                    if (component2 instanceof JComboBox) {
                        JComboBox list = (JComboBox) component2;
                        //System.out.println("sel: " + list.getName() + " " + list.getSelectedItem());
                        if (!list.getSelectedItem().toString().contains("...")){
                            out.write(list.getName()+":"+list.getSelectedItem()+"\n");
                        }
                    }
                    
                    if (component2 instanceof JCheckBox) {
                        JCheckBox check = (JCheckBox) component2;
                        //System.out.println("check: " + check.getName() + " " + check.isSelected());
                        if (check.isSelected()){
                            out.write(check.getName()+":"+check.getName()+"\n");
                        }
                    }
                    if (component2 instanceof JScrollPane) {
                        
                        JScrollPane scroll = (JScrollPane) component2;
                        Component listc=scroll.getComponent(0);
                        JViewport listv=(JViewport) listc;
                        Component listvc=listv.getComponent(0);

                        JList list=(JList) listvc;
                        int[] selectedIx = list.getSelectedIndices();
                        String samples="";
                         for (int s=0; s<selectedIx.length; s++) {
                            group=1;
                            Object sel = list.getModel().getElementAt(selectedIx[s]);
                            samples=samples+sel.toString()+",";
                         }
                        //System.out.println("list: " +list.getName() + " " + samples);
                        out.write(list.getName()+":"+samples+"\n");
                    }
                }
            }
  
        }
        if (group==0){
            done=false;
        }
        out.close();
        return done;
    }

    public void runpackage(final String script,final JDialog parent) throws IOException{

            //JOptionPane.showMessageDialog(null, "Your analysis is running...");

            //System.out.println("name script "+script);
            final  WaitFrame frame = new WaitFrame(parent);
            frame.setVisible(true);
            
            Runnable view = new Runnable() {

            public void run() {

                String file= "temp/script_param";
                String path=null;
                try {
                    path = getpathfromfile(file);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GetParam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(GetParam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                }

                Runtime runtime = Runtime.getRuntime();
                Process process = null;

                try {
//                    //check parameters
//                    //DB exist that db in the sample more than 10 rows??
//                    //same for freq and len, variation
                    tools check=new tools();
                    check.readparameters("seqbuster.op");
                    String r=check.r;
                    if (!r.matches("")){
                        r=r+"/";
                    }
                    process = runtime.exec(r+"R CMD BATCH Rscripts/R/" + script + ".R temp/" + script + ".Rout");
//                    System.out.println("/usr/bin/R CMD BATCH Rscripts/R/"+script+".R temp/"+script+".Rout");
                    try {

                        process.waitFor();

                    } catch (InterruptedException ex) {
                        Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex);
                    }
                    //check finished correcly??
                    try {
                            String type=getoutputformat(script);
                            //System.out.println(type);
                            if (type.matches("1")){
                                //show browser
                                BrowDEmFrame viewresult = new BrowDEmFrame(parent);
                                viewresult.setVisible(true);
                            }else{
                                if (!new File(path+"result.html").exists()){

                                    JOptionPane.showMessageDialog(null, "The analysis finished with an error,\nsee temp/" + script + ".Rout\n ","Error",JOptionPane.ERROR_MESSAGE);
                                }else{
                                    //JOptionPane.showMessageDialog(null, "Your analysis has finished!");
                                    CopyFile.copyfile("Rscripts/R/showtableid.js",path+"showtableid.js");
                                    tools.showinfo("Your analysis has finished!\nIf your browser doesn't open,the result are in: "+path);
                                    OpenBrowser.openURL("file://"+path+"result.html");
                                }
                               
                            }
                     } catch (SQLException ex) {
                            Logger.getLogger(GetParam.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                     }
                    
//                    ResultViewXML resultview= new ResultViewXML(parent);

//                    resultview.infoxml("file://"+file+"result.html");
//                    resultview.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(GetParam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                }
            
            //check if results files has been created, if not weird error!!! Rout
           //dialog.dispose();
                frame.dispose();
             }
             };
             Thread nThread= new Thread(view);
             nThread.start();
    }

    public void copyDirectory(String source , String target) throws IOException {

        File targetLocation=new File(target);
        File sourceLocation=new File(source);
        if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
 
            InputStream in = new FileInputStream(source+"/"+children[i]);
            OutputStream out = new FileOutputStream(target+"/"+children[i]);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
         }
    }

    public void savelast () throws FileNotFoundException, IOException{
                String file= "temp/script_param";
                String path = getpathfromfile(file);
                String namevar = JOptionPane.showInputDialog("Write a name for the new project: (only alphanumeric characters. max 15)" );
                Pattern p = Pattern.compile("\\W");
                Matcher m = p.matcher(namevar);
        if (namevar!=null & !m.find()){
                try {
                    String pathcopy = SeqBusterView.pathout.getText()+"/"+SeqBusterView.loaprotext.getText()+"/miRNAscripts";
                    //remove last folder in path
                    copyDirectory(path,pathcopy+"/"+namevar);
                    JOptionPane.showMessageDialog(null, "Your result has been saved in: "+pathcopy+"/"+namevar);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GetParam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(GetParam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es:\n " + ex,"Error",JOptionPane.ERROR_MESSAGE);
                }
        }else{
                JOptionPane.showMessageDialog(null, "You need a valid name, only alphanumeric characters.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getoutputformat (String scriptname) throws SQLException{
        String typeoutput=null;

        String host = SeqBusterView.HosMysText.getText();
        String user = SeqBusterView.UseMysText.getText();
        String pssw = SeqBusterView.PswMysText.getText();
        String readsamples="select `link` from `seqbuster`.`scriptsGUI` where `script` like '"+scriptname+"'";
//        Vector listtrack=new Vector();
//        System.out.println(readsamples);
        Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
        Statement statment = con.createStatement();
        ResultSet rs=statment.executeQuery(readsamples);
        rs.next();
        typeoutput=rs.getString(1);
        
        rs.close();
        statment.close();
        con.close();
        return typeoutput;
    }
    private String getpathfromfile (String filein) throws FileNotFoundException, IOException{
        String path=null;
        BufferedReader in= new BufferedReader(new FileReader(filein));
        String l=null;
        l=in.readLine();
        l=in.readLine();
        if (l.contains("path")){
            String [] col=l.split(":");
        //SeqBusterView.pathblast.setText(col[1]);
            if (col.length==2){path=col[1];}
        
        }
//        System.out.println(path);
        return path;
    }


}
