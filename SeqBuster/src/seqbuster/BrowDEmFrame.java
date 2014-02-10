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


//import java.awt.Color;
//import java.awt.Component;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import seqbuster.UpTriRea.*;

public class BrowDEmFrame  extends javax.swing.JDialog {

    String exp=null;
    public BrowDEmFrame(javax.swing.JDialog parent) throws SQLException {
        //super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        showdata("");

    }



    public void  showdata (String opt) throws SQLException {

        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con = tools.docon(namepro);
        Statement statment =  con.createStatement();
        String readsamples="describe `difexp`";
        ResultSet rs=statment.executeQuery(readsamples);
        //MetaData model = rs.getMetaData();


        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        int col=0;
        while (rs.next()){
            model.addColumn(rs.getString(1));
            TableColumn colm = table.getColumnModel().getColumn(col);
            colm.setPreferredWidth(100);
            col++;
//            System.out.println(rs.getString(1));
        }
        readsamples="select * from `difexp`  ";
        if (!opt.matches("")){
            readsamples="select * from `difexp` "+opt;
//            System.out.println(opt);
        }
//        System.out.println(readsamples);
        rs=statment.executeQuery(readsamples);
        int rowind=0;
        while (rs.next()){
            Object[] row=new Object [col];
            for (int i=0;i<col;i++){
                row[i]=rs.getString(i+1);
                rowind++;
            }

            model.addRow(row);
        }
        table = autoResizeColWidth(table, model);
        table.setDefaultRenderer(Object.class, new CustomRenderer());
        table.repaint();
         
        rs.close();
        statment.close();
        con.close();

    }

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
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButAdapFrame = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idtext = new javax.swing.JTextField();
        searchbut = new javax.swing.JButton();
        r1text = new javax.swing.JTextField();
        r2text = new javax.swing.JTextField();
        q1text = new javax.swing.JTextField();
        q2text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        expBut = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(BrowDEmFrame.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        table.setBackground(resourceMap.getColor("table.background")); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.setName("table"); // NOI18N
        jScrollPane1.setViewportView(table);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        idtext.setText(resourceMap.getString("idtext.text")); // NOI18N
        idtext.setName("idtext"); // NOI18N

        searchbut.setText(resourceMap.getString("searchbut.text")); // NOI18N
        searchbut.setFocusCycleRoot(true);
        searchbut.setName("searchbut"); // NOI18N
        searchbut.setSelected(true);
        searchbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbutActionPerformed(evt);
            }
        });

        r1text.setText(resourceMap.getString("r1text.text")); // NOI18N
        r1text.setName("r1text"); // NOI18N

        r2text.setText(resourceMap.getString("r2text.text")); // NOI18N
        r2text.setName("r2text"); // NOI18N

        q1text.setText(resourceMap.getString("q1text.text")); // NOI18N
        q1text.setName("q1text"); // NOI18N

        q2text.setText(resourceMap.getString("q2text.text")); // NOI18N
        q2text.setName("q2text"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        expBut.setText(resourceMap.getString("expBut.text")); // NOI18N
        expBut.setName("expBut"); // NOI18N
        expBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expButActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idtext, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r1text, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r2text, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(q1text, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(q2text, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchbut, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expBut, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(CloseButAdapFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(q1text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(q2text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(expBut))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(r1text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1)
                        .addComponent(idtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(r2text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchbut)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(CloseButAdapFrame)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void searchbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbutActionPerformed
        try {
            // TODO add your handling code here:
            String optslq=null;
            if (!r1text.getText().matches("")){
                optslq=" where `ratio` >= "+r1text.getText()+"";
            }else{
                optslq=" where `ratio` >= 0";
            }
            if (!idtext.getText().matches("")){
                optslq=optslq+" and  `chr` like '%"+idtext.getText()+"%'";
            }

            if (!r2text.getText().matches("")){
                optslq=optslq+" and  `ratio` <="+r2text.getText();
            }
            if (!q1text.getText().matches("")){
                optslq=optslq+" and  `qvalue` >="+q1text.getText();
            }
            if (!q2text.getText().matches("")){
                optslq=optslq+" and  `qvalue` <="+q2text.getText();
            }
            showdata( optslq);
        } catch (SQLException ex) {
            Logger.getLogger(BrowDEmFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
        }

    }//GEN-LAST:event_searchbutActionPerformed

    private void expButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expButActionPerformed
        // TODO add your handling code here:

        String pathout=SeqBusterView.pathout.getText()+"/";

        String namepro=SeqBusterView.loaprotext.getText();
        DefaultTableModel model = new DefaultTableModel();
        model= (DefaultTableModel) table.getModel();
        int coln=model.getColumnCount();
        int rown=model.getRowCount();
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + "table.txt")));
            for (int r=0;r<rown;r++){
            for (int c=0;c<coln;c++){
//                System.out.println(model.getValueAt(r, c));
                out.write(model.getValueAt(r, c)+"\t");
            }
                out.write("\n");

            }
             out.close();
             JOptionPane.showMessageDialog(null,"Your data is in:"+pathout + namepro + "/" + "table.txt");
        } catch (IOException ex) {
            Logger.getLogger(BrowDEmFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
        }

        
    }//GEN-LAST:event_expButActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Object idu=0;
        int[] rowIndex = table.getSelectedRows();
        idu=table.getModel().getValueAt(rowIndex[0],0);
        Object type=table.getModel().getValueAt(rowIndex[0],1);
        JDialog brow_obj;
        try {
            try {
                brow_obj = new BrowPromFrame(idu.toString(),type.toString());
                brow_obj.setLocation(1100, 0);
                brow_obj.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(BrowDEnmFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BrowDEnmFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BrowDEnmFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JButton expBut;
    private javax.swing.JTextField idtext;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField q1text;
    private javax.swing.JTextField q2text;
    private javax.swing.JTextField r1text;
    private javax.swing.JTextField r2text;
    private javax.swing.JButton searchbut;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    
}
