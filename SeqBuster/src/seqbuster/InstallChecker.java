
package seqbuster;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import seqbuster.tools.*;

/**
 *
 * @author lpantano
 */
public class InstallChecker extends javax.swing.JDialog {
    Color col =new Color(1,68,2);
    boolean m=false,r=false,rpack=false,java=false,blast=false,rnafold=false;
    /** Creates new form InstallFirstRun */
    public InstallChecker() throws MalformedURLException, IOException, InterruptedException {
        super(SeqBusterApp.getApplication().getMainFrame(), true);
        Point  p= SeqBusterApp.getApplication().getMainFrame().getLocation();
        this.setLocation(p.x+500, p.y+100);
        initComponents();
        //conbut.setEnabled(false);
        solvr.setEnabled(false);
        solvmysql.setEnabled(false);
        solvjava.setEnabled(false);
        solvb.setEnabled(false);
        m=checkmyslq();
        r=checkr();
        rpack=checkrpack();
        java=checkjava("");
        blast=checkblast("");
        rnafold=checkrnafold();
        
        if (!java){
            solvjava.setEnabled(true);
        }
        if (m & r & rpack){
            conbut.setEnabled(true);
            //close.setEnabled(false);
        }
        if (!m){
           solvmysql.setEnabled(true);
        }
        if (!r){
            solvr.setEnabled(true);
        }
        if (!rpack){
            tools.showcerror("The R packages: RMySQL or XML or SAM are not installed.\n" +
                    "Press the HELP button to install them.");
        }
        if (!blast){
            boolean bm=macpath();
            if (!bm){
                solvb.setEnabled(true);
            }
        }
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rmysqlcheck = new javax.swing.JLabel();
        javacheck = new javax.swing.JLabel();
        mysqlcheck = new javax.swing.JLabel();
        conbut = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rcheck = new javax.swing.JLabel();
        rxmlcheck = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        solvr = new javax.swing.JButton();
        solvmysql = new javax.swing.JButton();
        solvjava = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        blastcheck = new javax.swing.JLabel();
        solvb = new javax.swing.JButton();
        checkragain = new javax.swing.JButton();
        checkmagainbut = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        samcheck = new javax.swing.JLabel();
        rnafoldcheck = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(418, 315));
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(InstallChecker.class);
        close.setText(resourceMap.getString("close.text")); // NOI18N
        close.setName("close"); // NOI18N
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 100, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 70, 30));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 107, 80, 30));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 60, 20));

        rmysqlcheck.setBackground(resourceMap.getColor("rmysqlcheck.background")); // NOI18N
        rmysqlcheck.setFont(resourceMap.getFont("rmysqlcheck.font")); // NOI18N
        rmysqlcheck.setForeground(resourceMap.getColor("rmysqlcheck.foreground")); // NOI18N
        rmysqlcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rmysqlcheck.setText(resourceMap.getString("rmysqlcheck.text")); // NOI18N
        rmysqlcheck.setName("rmysqlcheck"); // NOI18N
        rmysqlcheck.setOpaque(true);
        getContentPane().add(rmysqlcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 40, 30));

        javacheck.setBackground(resourceMap.getColor("javacheck.background")); // NOI18N
        javacheck.setFont(resourceMap.getFont("javacheck.font")); // NOI18N
        javacheck.setForeground(resourceMap.getColor("javacheck.foreground")); // NOI18N
        javacheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        javacheck.setText(resourceMap.getString("javacheck.text")); // NOI18N
        javacheck.setName("javacheck"); // NOI18N
        javacheck.setOpaque(true);
        getContentPane().add(javacheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 40, 30));

        mysqlcheck.setBackground(resourceMap.getColor("mysqlcheck.background")); // NOI18N
        mysqlcheck.setFont(resourceMap.getFont("mysqlcheck.font")); // NOI18N
        mysqlcheck.setForeground(resourceMap.getColor("mysqlcheck.foreground")); // NOI18N
        mysqlcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mysqlcheck.setText(resourceMap.getString("mysqlcheck.text")); // NOI18N
        mysqlcheck.setName("mysqlcheck"); // NOI18N
        mysqlcheck.setOpaque(true);
        getContentPane().add(mysqlcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 40, 30));

        conbut.setText(resourceMap.getString("conbut.text")); // NOI18N
        conbut.setName("conbut"); // NOI18N
        conbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conbutActionPerformed(evt);
            }
        });
        getContentPane().add(conbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 100, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 60, 30));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 70, 30));

        rcheck.setBackground(resourceMap.getColor("rcheck.background")); // NOI18N
        rcheck.setFont(resourceMap.getFont("rcheck.font")); // NOI18N
        rcheck.setForeground(resourceMap.getColor("rcheck.foreground")); // NOI18N
        rcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rcheck.setText(resourceMap.getString("rcheck.text")); // NOI18N
        rcheck.setName("rcheck"); // NOI18N
        rcheck.setOpaque(true);
        getContentPane().add(rcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 40, 30));

        rxmlcheck.setBackground(resourceMap.getColor("rxmlcheck.background")); // NOI18N
        rxmlcheck.setFont(resourceMap.getFont("rxmlcheck.font")); // NOI18N
        rxmlcheck.setForeground(resourceMap.getColor("rxmlcheck.foreground")); // NOI18N
        rxmlcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rxmlcheck.setText(resourceMap.getString("rxmlcheck.text")); // NOI18N
        rxmlcheck.setName("rxmlcheck"); // NOI18N
        rxmlcheck.setOpaque(true);
        getContentPane().add(rxmlcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 40, 30));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 110, -1));

        solvr.setText(resourceMap.getString("solvr.text")); // NOI18N
        solvr.setName("solvr"); // NOI18N
        solvr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvrActionPerformed(evt);
            }
        });
        getContentPane().add(solvr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 169, 80, 30));

        solvmysql.setText(resourceMap.getString("solvmysql.text")); // NOI18N
        solvmysql.setName("solvmysql"); // NOI18N
        solvmysql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvmysqlActionPerformed(evt);
            }
        });
        getContentPane().add(solvmysql, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 30));

        solvjava.setText(resourceMap.getString("solvjava.text")); // NOI18N
        solvjava.setName("solvjava"); // NOI18N
        solvjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvjavaActionPerformed(evt);
            }
        });
        getContentPane().add(solvjava, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, 30));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 167, 80, 30));

        blastcheck.setBackground(resourceMap.getColor("blastcheck.background")); // NOI18N
        blastcheck.setFont(resourceMap.getFont("blastcheck.font")); // NOI18N
        blastcheck.setForeground(resourceMap.getColor("blastcheck.foreground")); // NOI18N
        blastcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blastcheck.setText(resourceMap.getString("blastcheck.text")); // NOI18N
        blastcheck.setName("blastcheck"); // NOI18N
        blastcheck.setOpaque(true);
        getContentPane().add(blastcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 40, 30));

        solvb.setText(resourceMap.getString("solvb.text")); // NOI18N
        solvb.setName("solvb"); // NOI18N
        solvb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvbActionPerformed(evt);
            }
        });
        getContentPane().add(solvb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 239, 80, 30));

        checkragain.setText(resourceMap.getString("checkragain.text")); // NOI18N
        checkragain.setName("checkragain"); // NOI18N
        checkragain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkragainActionPerformed(evt);
            }
        });
        getContentPane().add(checkragain, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, -1));

        checkmagainbut.setText(resourceMap.getString("checkmagainbut.text")); // NOI18N
        checkmagainbut.setName("checkmagainbut"); // NOI18N
        checkmagainbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkmagainbutActionPerformed(evt);
            }
        });
        getContentPane().add(checkmagainbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 197, 60, 20));

        samcheck.setBackground(resourceMap.getColor("samcheck.background")); // NOI18N
        samcheck.setFont(resourceMap.getFont("samcheck.font")); // NOI18N
        samcheck.setForeground(resourceMap.getColor("samcheck.foreground")); // NOI18N
        samcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        samcheck.setText(resourceMap.getString("samcheck.text")); // NOI18N
        samcheck.setName("samcheck"); // NOI18N
        samcheck.setOpaque(true);
        getContentPane().add(samcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 40, 30));

        rnafoldcheck.setBackground(resourceMap.getColor("rnafoldcheck.background")); // NOI18N
        rnafoldcheck.setFont(resourceMap.getFont("rnafoldcheck.font")); // NOI18N
        rnafoldcheck.setForeground(resourceMap.getColor("rnafoldcheck.foreground")); // NOI18N
        rnafoldcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rnafoldcheck.setText(resourceMap.getString("rnafoldcheck.text")); // NOI18N
        rnafoldcheck.setName("rnafoldcheck"); // NOI18N
        rnafoldcheck.setOpaque(true);
        getContentPane().add(rnafoldcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 40, 30));

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 70, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean checkmyslq (){
        String s = null;

        try {

	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("which mysql");

            BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("mysql")){
                    mysqlcheck.setText("Ok");
                    mysqlcheck.setBackground(col);
                    return true;
                }
                //System.out.println(s);
            }
            if (new File("/usr/local/mysql").exists()){
                mysqlcheck.setText("Ok");
                mysqlcheck.setBackground(col);
                return true;
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                tools.showerror(s);
            }

            
        }
        catch (IOException e) {
            tools.showerror(e.toString());
            //e.printStackTrace();
            
        }

        return false;
    }
    private boolean checkrpack () throws InterruptedException{
        String s = null;
        boolean result=false;
        try {

	    
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("R CMD BATCH lib/checkrpack.R temp/checkrpack.Rout");
            p.waitFor();
            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));
            try {
                FileInputStream fstream = new FileInputStream("temp/checkrpack.Rout");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;

                while ((strLine = br.readLine()) != null )   {
                    if (strLine.contains("RMySQL")){
                        result=true;
                        rmysqlcheck.setText("Ok");
                        rmysqlcheck.setBackground(col);
                    }
                    if (strLine.contains("XML")){
                        result=true;
                        rxmlcheck.setText("Ok");
                        rxmlcheck.setBackground(col);
                    }
                    if (strLine.contains("samr")){
                        result=true;
                        samcheck.setText("Ok");
                        samcheck.setBackground(col);
                    }
                }
            } catch (IOException ex) {
                //Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.toString());
            }
            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");


            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                tools.showerror(s);
            }


        }
        catch (IOException e) {
            tools.showerror(e.toString());
            //e.printStackTrace();

        }

        return result;
    }
    private boolean checkr () throws InterruptedException{
        String s = null;
        boolean result=false;
        try {

	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("which R");
            p.waitFor();
            BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("R")){
                    rcheck.setText("Ok");
                    rcheck.setBackground(col);
                    result=true;
                }
                //System.out.println(s);
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                tools.showerror(s);
            }


        }
        catch (IOException e) {
            tools.showerror(e.toString());
            //e.printStackTrace();

        }

        return result;
    }
    private boolean checkrnafold () throws InterruptedException{
        String s = null;
        boolean result=false;
        try {

	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("which RNAfold");
            p.waitFor();
            BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("RNAfold")){
                    rnafoldcheck.setText("Ok");
                    rnafoldcheck.setBackground(col);
                    result=true;
                }
                //System.out.println(s);
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                tools.showerror(s);
            }


        }
        catch (IOException e) {
            tools.showerror(e.toString());
            //e.printStackTrace();

        }

        return result;
    }
    private boolean checkblast (String path){
         String s = null;

        try {

	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(path+"blastx -version");

            BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("blastx")){
                    blastcheck.setText("Ok");
                    blastcheck.setBackground(col);
                    return true;
                }
                //System.out.println(s);
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                //tools.showerror(s);
                if (s.contains("blastx")){
                    blastcheck.setText("Ok");
                    blastcheck.setBackground(col);
                    return true;
                }
            }


        }
        catch (IOException e) {
            //tools.showerror(e.toString());
            //e.printStackTrace();

        }

        return false;
    }
    private boolean checkjava (String path){
         String s = null;

        try {

	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(path+"java -version");

            BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("1.6")){
                    javacheck.setText("Ok");
                    javacheck.setBackground(col);
                    return true;
                }
                //System.out.println(s);
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                //tools.showerror(s);
                if (s.contains("1.6")){
                    javacheck.setText("Ok");
                    javacheck.setBackground(col);
                    return true;
                }
            }


        }
        catch (IOException e) {
            //tools.showerror(e.toString());
            //e.printStackTrace();

        }

        return false;
    }
    private boolean macpath (){
        String path="/usr/local/ncbi/bin";
        boolean e=new File (path+"/blastn").exists();
        if (e){return true;}
        return false;
    }
    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        System.exit(0);
        dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void conbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conbutActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_conbutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JDialog ins_obj;
        try {
            ins_obj = new InstallFirstRun(this, false);
            ins_obj.setLocation(550, 0);
            ins_obj.setVisible(true);
        } catch (MalformedURLException ex) {
            tools.showerror(ex.toString());
        } catch (IOException ex) {
            tools.showerror(ex.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void solvmysqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvmysqlActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Yes","No"};
        String msg="MySQL has not been detected. If you are sure, you have mysql\n";
        msg=msg+" press YES and select the folder containing mysql.\n";
        msg=msg+" Remember you have a complete installation guide when you press the button HELP\n";
        msg=msg+"in the main windows.";
        int n = JOptionPane.showOptionDialog(null,msg,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if (n==0){
            //no answer
            checkcustommysql();
        }
    }//GEN-LAST:event_solvmysqlActionPerformed

    private void solvrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvrActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Yes","No"};
        String msg="R has not been detected. If you are sure, you have R\nbut is it not included in the path";
        msg=msg+" press YES and select the folder containing R.\n";
        msg=msg+" Remember you have a complete installation guide when you press the button HELP\n";
        msg=msg+"in the main windows.";
        int n = JOptionPane.showOptionDialog(null,msg,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if (n==0){
            //no answer
            boolean pack;
            try {
                pack = checkcustomr();
                if (pack){
                rpack=true;
            }
            } catch (InterruptedException ex) {
                Logger.getLogger(InstallChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_solvrActionPerformed

    private void solvjavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvjavaActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Yes","No"};
        String msg="JAVA 1.6 has not been detected. If you are sure, you have this version\nbut is it not included in the path";
        msg=msg+" press YES and select the folder containing java 1.6.\n";
        msg=msg+" Remember you have a complete installation guide when you press the button HELP\n";
        msg=msg+"in the main windows.";
        int n = JOptionPane.showOptionDialog(null,msg,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if (n==0){
            //no answer
            JFileChooser c=new JFileChooser();
            String namedir="";
            c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            tools check=new tools();
            int rVal=c.showOpenDialog(this);
            if (rVal==JFileChooser.APPROVE_OPTION){
                namedir=c.getSelectedFile().toString();
            }
            boolean javatemp =checkjava(namedir+"/");
            if (javatemp){
                java=true;
                solvjava.setEnabled(false);
            }
        }
    }//GEN-LAST:event_solvjavaActionPerformed

    private void solvbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvbActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Yes","No"};
        String msg="blast+ has not been detected. If you are sure, you have this version\n";
        msg=msg+" press YES and select the folder containing blastx.\n";
        msg=msg+"In snow leopard the default location is: /usr/local/ncbi/bin\n";
        msg=msg+" Remember you have a complete installation guide when you press the button HELP\n";
        msg=msg+"in the main windows.";
        int n = JOptionPane.showOptionDialog(null,msg,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if (n==0){
            //no answer
            JFileChooser c=new JFileChooser();
            String namedir="";
            c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            int rVal=c.showOpenDialog(this);
            if (rVal==JFileChooser.APPROVE_OPTION){
                namedir=c.getSelectedFile().toString();
            }
            boolean blasttemp =checkblast(namedir+"/");
            if (blasttemp){
                java=true;
                solvb.setEnabled(false);
            }
        }
    }//GEN-LAST:event_solvbActionPerformed

    private void checkmagainbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkmagainbutActionPerformed
        // TODO add your handling code here:
         solvmysql.setEnabled(false);
         m=checkmyslq();
         if (!m){
           solvmysql.setEnabled(true);
           tools.showcerror("MySQL is not installed. Follow the steps of the guide\n" +
                   "and come back when it is installed. Or press SOLVE if it is \n" +
                   "installed in a specific location");
        }
    }//GEN-LAST:event_checkmagainbutActionPerformed

    private void checkragainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkragainActionPerformed
        // TODO add your handling code here:
        solvr.setEnabled(false);
        try {
           r = checkr();
           rpack=checkrpack();
           if (!r){
           solvr.setEnabled(true);
           tools.showcerror("R is not installed. Follow the steps of the guide\n" +
                   "and come back when it is installed. Or press SOLVE if it is \n" +
                   "installed in a specific location");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(InstallChecker.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }

    }//GEN-LAST:event_checkragainActionPerformed
    private boolean checkcustomr () throws InterruptedException{
        JFileChooser c=new JFileChooser();
        String filename;
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        tools check=new tools();
        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            boolean Epathdb=check.fileexist(filename);
            if (Epathdb){

                check.readparameters("seqbuster.op");
                check.r=filename;
                check.saveparameters("seqbuster.op");
                
                r=checkcustomrpack(filename);

                if (m & r & rpack){
                    conbut.setEnabled(true);
                    //close.setEnabled(false);
                }

            }else{
                JOptionPane.showMessageDialog(null,"No correct folder. Please try again!\n");
            }

        }
        return true;
    }
    private boolean checkcustomrpack(String path) throws InterruptedException{
        String s = null;
        boolean result=false;
        try {


            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(path+"/R CMD BATCH lib/checkrpack.R temp/checkrpack.Rout");
            p.waitFor();
            BufferedReader stdError = new BufferedReader(new
            InputStreamReader(p.getErrorStream()));
            try {
                FileInputStream fstream = new FileInputStream("temp/checkrpack.Rout");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;

                while ((strLine = br.readLine()) != null )   {
                    if (strLine.contains("RMySQL")){
                        result=true;
                        rmysqlcheck.setText("Ok");
                        rmysqlcheck.setBackground(col);
                    }
                    if (strLine.contains("XML")){
                        result=true;
                        rxmlcheck.setText("Ok");
                        rxmlcheck.setBackground(col);
                    }
                }
                rcheck.setText("Ok");
                rcheck.setBackground(col);
                solvr.setEnabled(false);
            } catch (IOException ex) {
                //Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showcerror("R wasn't found in this path "+path);
            }
            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");


            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                //System.out.println(s);
                tools.showcerror("R wasn't found in this path "+path);
            }


        }
        catch (IOException e) {
            tools.showerror(e.toString());
            //e.printStackTrace();

        }

        return result;
    }
    private boolean checkcustommysql(){
        JFileChooser c=new JFileChooser();
        String filename;
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        tools check=new tools();
        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            boolean Epathdb=check.fileexist(filename);
            if (Epathdb){

                check.readparameters("seqbuster.op");
                check.mysql=filename;
                check.saveparameters("seqbuster.op");
                mysqlcheck.setText("Ok");
                mysqlcheck.setBackground(col);
                solvmysql.setEnabled(false);
                m=true;
                if (m & r & rpack){
                    conbut.setEnabled(true);
                    close.setEnabled(false);
                }

            }else{
                JOptionPane.showMessageDialog(null,"No correct folder. Please try again!\n");
            }

        }


        return true;
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blastcheck;
    private javax.swing.JButton checkmagainbut;
    private javax.swing.JButton checkragain;
    private javax.swing.JButton close;
    private javax.swing.JButton conbut;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel javacheck;
    private javax.swing.JLabel mysqlcheck;
    private javax.swing.JLabel rcheck;
    private javax.swing.JLabel rmysqlcheck;
    private javax.swing.JLabel rnafoldcheck;
    private javax.swing.JLabel rxmlcheck;
    private javax.swing.JLabel samcheck;
    private javax.swing.JButton solvb;
    private javax.swing.JButton solvjava;
    private javax.swing.JButton solvmysql;
    private javax.swing.JButton solvr;
    // End of variables declaration//GEN-END:variables

}
