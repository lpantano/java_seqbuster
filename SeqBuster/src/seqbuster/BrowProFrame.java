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
 * seqbusterAboutBox.java
 */

package seqbuster;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



import javax.swing.text.Document;
import seqbuster.UpTriRea.*;

public class BrowProFrame  extends javax.swing.JDialog {

    int idnum=0;

    public BrowProFrame(String idu) throws SQLException, MalformedURLException, IOException {
//        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        idnum=Integer.parseInt(idu);
      
        showinfo();
    }

    
    public void showinfo () throws SQLException, MalformedURLException, IOException{

                if (new File("temp/profile.jpg").exists()){
                new File("temp/profile.jpg").delete();
                new File("temp/profile.info").delete();
                new File("temp/profile.html").delete();
            }
            PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter( "temp/profile.html")));
            PrintWriter outinfo = new PrintWriter(new BufferedWriter(new FileWriter( "temp/profile.info")));
            System.out.println(idnum);
            outinfo.write(idnum+"\n");
            outinfo.close();
            int indcol=0;
            
            //run R file
            
            Runtime runtime = Runtime.getRuntime();
            Process process = null;

            
                    tools check=new tools();
                    check.readparameters("seqbuster.op");
                    String r=check.r;
                    if (!r.matches("")){
                        r=r+"/";
                    }
                    String cmd=r+"R CMD BATCH --vanilla Rscripts/R/profileinfo.R"+ " temp/profileinfo.Rout";
        //
                //String cmd="Rscript  Rscripts/R/timeserieclus.R ";
                //System.out.println(cmd);
                try {
                    process = runtime.exec(cmd);
                    try {

                        process.waitFor();

                    } catch (InterruptedException ex) {

                        tools.showerror(ex.getLocalizedMessage(),ex.toString());
                    }

                } catch (IOException ex) {
                    Logger.getLogger(NonMiRNAanaTSFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());

                }

                    outfile.write("<img  src=\"profile.jpg\" />");
                    outfile.close();
                    Document doc = infopanel.getDocument();
                    doc.putProperty(Document.StreamDescriptionProperty, null);
                    File htmlFile=new File("temp/profile.html");
                    URL manualurl=htmlFile.toURL();
                    infopanel.setPage(manualurl);
                    infopanel.repaint();
            if (!(new File("temp/profile.jpg").exists())){
                outfile.close();
                new File("temp/clus.html").delete();
                tools.showinfo("This cluster does not exist in this sample.");
            }

            
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButAdapFrame = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infopanel = new javax.swing.JEditorPane();
        clusvisb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(BrowProFrame.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        infopanel.setBackground(resourceMap.getColor("infopanel.background")); // NOI18N
        infopanel.setEditable(false);
        infopanel.setName("infopanel"); // NOI18N
        jScrollPane1.setViewportView(infopanel);

        clusvisb.setText(resourceMap.getString("clusvisb.text")); // NOI18N
        clusvisb.setName("clusvisb"); // NOI18N
        clusvisb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clusvisbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clusvisb, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                        .addComponent(CloseButAdapFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CloseButAdapFrame)
                    .addComponent(clusvisb))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void clusvisbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clusvisbActionPerformed
        tools.showinfo("The image is inside seqbuster folder: temp/profile.jpg");
    }//GEN-LAST:event_clusvisbActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JButton clusvisb;
    private javax.swing.JEditorPane infopanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
