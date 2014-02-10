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



import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;



import javax.swing.text.Document;
import seqbuster.UpTriRea.*;

public class BrowPromFrame  extends javax.swing.JDialog {

    String idnum="";
    String typeid="";
    public BrowPromFrame(String idu,String type) throws SQLException, MalformedURLException, IOException {
//        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        idnum=idu;
        typeid=type;
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
            //System.out.println(idnum);
            outinfo.write(idnum+"\n"+typeid+"\n");
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
                    String cmd=r+"R CMD BATCH --vanilla Rscripts/R/profileinfom.R"+ " temp/profileinfom.Rout";
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
                    DefaultTableModel model = new DefaultTableModel();

                    int col=0;
                    TableColumn column = null;
                    BufferedReader in = new BufferedReader(new FileReader("temp/group1table"));
                    String l = in.readLine();
                    String [] header = l.split("\t");
                    for (int i=0;i<header.length;i++){
                       
                       model.addColumn(header[i]);

                       col++;
                     }
//
                    l = in.readLine();
                    header = l.split("\t");
                    Object[] row=new Object [col];
                    for (int i=0;i<header.length;i++){
                        row[i]=header[i];
                    }
                    model.addRow(row);
                    g1table.setModel(model);
                   
                    g1table = autoResizeColWidth(g1table, model);
                    g1table.setDefaultRenderer(Object.class, new CustomRenderer());
                    g1table.repaint();
//
                    in.close();
//
                    col=0;
                    column = null;
                    DefaultTableModel model2 = new DefaultTableModel();
                    in = new BufferedReader(new FileReader("temp/group1table"));
                    l = in.readLine();
                    header = l.split("\t");
                    for (int i=0;i<header.length;i++){

                       model2.addColumn(header[i]);

                       col++;
                     }
                    //System.out.println(col);
                    l = in.readLine();
                    header = l.split("\t");
                    row=new Object [col];
                    for (int i=0;i<header.length;i++){
                        row[i]=header[i];
                    }
                    model2.addRow(row);
                    g2table.setModel(model2);
                    for (int i=0;i<header.length;i++){
                         TableColumn colm = g2table.getColumnModel().getColumn(i);
                         colm.setPreferredWidth(100);

                     }
                    g2table.setModel(model);

                    g2table = autoResizeColWidth(g2table, model);
                    g2table.setDefaultRenderer(Object.class, new CustomRenderer());
                    g2table.repaint();


                     if (!(new File("temp/profile.jpg").exists())){
                         outfile.close();
                        new File("temp/clus.html").delete();
                        tools.showinfo("This miRNA does not exist in this sample.");
                     }

            
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButAdapFrame = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infopanel = new javax.swing.JEditorPane();
        clusvisb = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        g1table = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        g2table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(BrowPromFrame.class);
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

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        g1table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        g1table.setName("g1table"); // NOI18N
        jScrollPane2.setViewportView(g1table);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        g2table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        g2table.setName("g2table"); // NOI18N
        jScrollPane3.setViewportView(g2table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(clusvisb, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(CloseButAdapFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
    public JTable autoResizeColWidth(JTable table, DefaultTableModel model) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(model);

        int margin = 5;

        for (int i = 0; i < table.getColumnCount(); i++) {
            int                     vColIndex = i;
            DefaultTableColumnModel colModel  = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn             col       = colModel.getColumn(vColIndex);
            int                     width     = 0;

            // Get width of column header
            TableCellRenderer renderer = col.getHeaderRenderer();

            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }

            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);

            width = comp.getPreferredSize().width;

            // Get maximum width of column data
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, vColIndex);
                comp     = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false,
                        r, vColIndex);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            // Add margin
            width += 2 * margin;

            // Set the width
            col.setPreferredWidth(width);
        }

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.LEFT);

        // table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JButton clusvisb;
    private javax.swing.JTable g1table;
    private javax.swing.JTable g2table;
    private javax.swing.JEditorPane infopanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
    
}
