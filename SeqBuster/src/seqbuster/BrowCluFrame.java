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

public class BrowCluFrame  extends javax.swing.JDialog {

    String exp="";
    int idnum=0;
    int idunum=0;
    int idflag=0;
    String expname="0";
    public BrowCluFrame(String idu,String id,String expn) throws SQLException, MalformedURLException, IOException {
//        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        exp=idu;
        expname=expn;
        idunum=Integer.parseInt(idu);
        idnum=Integer.parseInt(id);
        if (idu.matches("0")){
            exp=id;
            idflag=1;
            
        }
            String namepro=SeqBusterView.loaprotext.getText();
            String host=SeqBusterView.HosMysText.getText();
            String user = SeqBusterView.UseMysText.getText();
            String pssw = SeqBusterView.PswMysText.getText();
            String port=SeqBusterView.PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}
            Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + "/"+namepro, user, pssw);
            Statement statment = con.createStatement();
            String readsamples = "select `name` from `experiments` where `description` like 'usRNA'";
            ResultSet rs = statment.executeQuery(readsamples);
            while (rs.next()) {
                listexp.addItem(rs.getString(1).replace("clusmap",""));

            }
            rs.close();
            statment.close();
            con.close();
           if (!expname.matches("")){
            showinfo();
           }
    }

    
    public void showinfo () throws SQLException, MalformedURLException, IOException{

            String namepro=SeqBusterView.loaprotext.getText();
            String host=SeqBusterView.HosMysText.getText();
            String user = SeqBusterView.UseMysText.getText();
            String pssw = SeqBusterView.PswMysText.getText();
            String port=SeqBusterView.PorMysText.getText();
            if (!port.matches("")){host=host+":"+port;}
            
            Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + "/"+namepro, user, pssw);
            Statement statment =  con.createStatement();
            Statement statmentcol =  con.createStatement();
            String readsamples = "select * from `"+expname+"clusmap` where `idu` ="+exp+" group by `ncl`";
            if (idflag==1){
                readsamples = "select * from `"+expname+"clusmap` where `id` ="+exp+" group by `ncl`";
            }
            ResultSet rs = statment.executeQuery(readsamples);
            String readcol="describe `"+expname+"clusmap`";
            ResultSet rscol=statmentcol.executeQuery(readcol);
            String [] colname = new  String [40];
            if (new File("temp/cluster.jpg").exists()){
                new File("temp/cluster.jpg").delete();
                new File("temp/cluster.info").delete();
                new File("temp/clus.html").delete();
            }
            PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter( "temp/clus.html")));
            int indcol=0;
            while (rscol.next()){
                indcol++;
                colname[indcol]=rscol.getString(1);
            }
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            //System.out.println(readsamples+"\n");
            outfile.write("Showing cluster number: "+exp+" in sample: "+expname+"\n<br>");
            int first=0;
            int id=0;
            while (rs.next()){
                //System.out.println("enter\n");
                if (first==0){
                    id=rs.getInt(1);
                    outfile.write("number of sequences: "+rs.getInt(4)+"\n<br>number of loci: "+rs.getInt(5)+"\n<br>frequency:"+rs.getInt(6)+"\n<br>length: "+rs.getInt(7)+"\n\n<br><br>");
                    
                }
                first=1;
                outfile.write("chromosome: "+rs.getString(9)+"\n<br>start: "+rs.getInt(10)+"\n<br>end: "+rs.getInt(11)+"\n<br>strand: "+rs.getString(12)+"\n<br>Annotation:\n<br>");
                if (numCols>15){
                    for(int i=16;i<=numCols;i++){
                        outfile.write(colname[i]+"\t"+rs.getString(i)+"\n<br>");
                    }

                }
                outfile.write("\n\n<br><br>");
                
            }
            rs=statment.executeQuery("select `id`,`ncl`,`nc` from `"+namepro+"`.`"+expname+"clusmap` where `id`="+id);
            rs.next();
            int nl=rs.getInt(2);
            Statement statment2 =  con.createStatement();
            rs=statment.executeQuery("select `nc`,`chr`,`start`,`end` from `"+namepro+"`.`"+expname+"clusmap` where `id`="+id+" and `ncl`="+nl);
               while (rs.next()){
                   int nclistadd=rs.getInt(1);
                   
                    ResultSet rsseqs=statment2.executeQuery("select `seq`,`freq`,`type` from `"+namepro+"`.`"+expname+"clusraw` where `freq`>0 and `nc`="+nclistadd );
                    while(rsseqs.next()){
                        
                        //System.out.println(pos+"\t"+rsseqs.getInt(2)+"\t"+rsseqs.getString(1)+"\n");
                        outfile.write(rsseqs.getInt(2)+": "+rsseqs.getString(1)+"\n<br>");
                    }
                    rsseqs.close();

                }
            //run R file
            
            Runtime runtime = Runtime.getRuntime();
            Process process = null;
            int done=cluster.doclusinfo(expname,idnum,idunum,idflag);
            if (done==1){
                    tools check=new tools();
                    check.readparameters("seqbuster.op");
                    String r=check.r;
                    if (!r.matches("")){
                        r=r+"/";
                    }
                    String cmd=r+"R CMD BATCH --vanilla Rscripts/R/clusterinfo.R"+ " temp/clusterinfo.Rout";
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

                    outfile.write("<img src=\"cluster.jpg\" />");
                    outfile.close();
                    Document doc = infopanel.getDocument();
                    doc.putProperty(Document.StreamDescriptionProperty, null);
                    File htmlFile=new File("temp/clus.html");
                    URL manualurl=htmlFile.toURL();
                    infopanel.setPage(manualurl);
                    infopanel.repaint();
            }else{
                outfile.close();
                new File("temp/clus.html").delete();
                tools.showinfo("This cluster does not exist in this sample.");
            }


            rscol.close();
            rs.close();
            statment.close();
            statment2.close();
            con.close();
            
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButAdapFrame = new javax.swing.JButton();
        searchbut = new javax.swing.JButton();
        listexp = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        infopanel = new javax.swing.JEditorPane();
        clusvisb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(BrowCluFrame.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });

        searchbut.setText(resourceMap.getString("searchbut.text")); // NOI18N
        searchbut.setName("searchbut"); // NOI18N
        searchbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbutActionPerformed(evt);
            }
        });

        listexp.setName("listexp"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        infopanel.setBackground(resourceMap.getColor("infopanel.background")); // NOI18N
        infopanel.setEditable(false);
        infopanel.setName("infopanel"); // NOI18N
        jScrollPane1.setViewportView(infopanel);

        clusvisb.setText(resourceMap.getString("clusvisb.text")); // NOI18N
        clusvisb.setName("clusvisb"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(listexp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchbut, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(clusvisb, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                                .addComponent(CloseButAdapFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listexp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchbut))
                .addGap(22, 22, 22)
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
        setVisible(false);
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void searchbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbutActionPerformed
        if (idunum!=0){
                try {
                    try {
                         expname = (String)listexp.getSelectedItem();
                         showinfo();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(BrowCluFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BrowCluFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BrowCluFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            tools.showinfo("this cluster is not presented in other samples");
        }
    }//GEN-LAST:event_searchbutActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JButton clusvisb;
    private javax.swing.JEditorPane infopanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox listexp;
    private javax.swing.JButton searchbut;
    // End of variables declaration//GEN-END:variables
    
}
