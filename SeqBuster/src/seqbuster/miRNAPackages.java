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



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import javax.swing.table.TableModel;
import seqbuster.UpTriRea.*;

public class miRNAPackages  extends javax.swing.JDialog {

    String host=SeqBusterView.HosMysText.getText();
    String user = SeqBusterView.UseMysText.getText();
    String pssw = SeqBusterView.PswMysText.getText();
    
    public miRNAPackages(javax.swing.JFrame parent) throws SQLException {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
        Statement statment =  con.createStatement();
        ResultSet rs=null;
            String readsamples = "select `type` from `scriptsGUI` group by `type`";

            rs = statment.executeQuery(readsamples);
//            TypeList.removeAllItems();
            while (rs.next()) {
                TypeList.addItem(rs.getString(1));
                //System.out.println(rs.getString(1));

            }
        
        
        
        TypeList.setSelectedItem("General");
        showdata("General");
        rs.close();
        statment.close();
        con.close();

    }
     class MyDefaultTableModel extends DefaultTableModel {
         public MyDefaultTableModel() {
           super();
         }
         public boolean isCellEditable(int row, int col) {
           return false;
         }
        }
    public void  showdata (String opt) throws SQLException {

        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
         Statement statment = (Statement) con.createStatement();
         ResultSet rs=null;
        MyDefaultTableModel model = new MyDefaultTableModel();
       
        table.setModel(model);
        int col=0;
       
            model.addColumn("Name");
            TableColumn colm = table.getColumnModel().getColumn(col);
            colm.setPreferredWidth(100);
            col++;
            model.addColumn("Description");
            colm = table.getColumnModel().getColumn(col);
            colm.setPreferredWidth(300);
            col++;
//            System.out.println(rs.getString(1));
        
        String readsamples="select `name`,`description` from `scriptsGUI` where `type` like '"+opt+"'";

//        System.out.println(readsamples);
        rs=statment.executeQuery(readsamples);
        while (rs.next()){
            Object[] row=new Object [col];
            for (int i=0;i<col;i++){
                row[i]=rs.getString(i+1);
            }
            model.addRow(row);
        }
        table.repaint();
        rs.close();
        statment.close();
        con.close();

    }

    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButAdapFrame = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        keysearch = new javax.swing.JTextField();
        searchbut = new javax.swing.JButton();
        TypeList = new javax.swing.JComboBox();
        InfoText = new javax.swing.JLabel();
        dobutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(miRNAPackages.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        keysearch.setText(resourceMap.getString("keysearch.text")); // NOI18N
        keysearch.setName("keysearch"); // NOI18N

        searchbut.setText(resourceMap.getString("searchbut.text")); // NOI18N
        searchbut.setName("searchbut"); // NOI18N
        searchbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbutActionPerformed(evt);
            }
        });

        TypeList.setName("TypeList"); // NOI18N
        TypeList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TypeListItemStateChanged(evt);
            }
        });
        TypeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TypeListActionPerformed(evt);
            }
        });

        InfoText.setBackground(resourceMap.getColor("InfoText.background")); // NOI18N
        InfoText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InfoText.setText(resourceMap.getString("InfoText.text")); // NOI18N
        InfoText.setName("InfoText"); // NOI18N
        InfoText.setOpaque(true);

        dobutton.setText(resourceMap.getString("dobutton.text")); // NOI18N
        dobutton.setName("dobutton"); // NOI18N
        dobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(keysearch, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchbut, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(TypeList, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(InfoText, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(CloseButAdapFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(dobutton, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchbut)
                    .addComponent(TypeList, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keysearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InfoText, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CloseButAdapFrame)
                    .addComponent(dobutton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        Runtime.getRuntime().gc();
        this.dispose();
        

        
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void searchbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbutActionPerformed

        Connection con;
        try {
            con =  DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
            Statement statment = con.createStatement();
            ResultSet rs=null;
            String readsamples="select `name`,`description` from `scriptsGUI` where `description` like '%"+keysearch.getText()+"%'";
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);
            int col=0;

            model.addColumn("Name");
            TableColumn colm = table.getColumnModel().getColumn(col);
            colm.setPreferredWidth(100);
            col++;
            model.addColumn("Description");
            colm = table.getColumnModel().getColumn(col);
            colm.setPreferredWidth(300);
            col++;
            rs=statment.executeQuery(readsamples);
            while (rs.next()) {
            Object[] row=new Object [col];
                for (int i = 0; i < col; i++) {
                    row[i] = rs.getString(i + 1);
                }
                model.addRow(row);
            }
            table.repaint();

            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(miRNAPackages.class.getName()).log(Level.SEVERE, null, ex);
        }




    }//GEN-LAST:event_searchbutActionPerformed

    private void TypeListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TypeListItemStateChanged
        
    }//GEN-LAST:event_TypeListItemStateChanged

    private void TypeListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TypeListActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            showdata(TypeList.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(miRNAPackages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TypeListActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        // TODO add your handling code here:
        Object idu=0;
        int[] rowIndex = table.getSelectedRows();
        idu=table.getModel().getValueAt(rowIndex[0],0);
//        idu.toString();
        String readsamples="select `name`,`description` from `scriptsGUI` where `name` like '"+idu.toString()+"'";
        try {
            Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
            Statement statment = con.createStatement();
            ResultSet rs=null;
            rs = statment.executeQuery(readsamples);
            rs.next();
            InfoText.setText("<html>"+rs.getString(2)+"</html>");
            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(miRNAPackages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void dobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobuttonActionPerformed
        // TODO add your handling code here:
        runpackage();
        
    }//GEN-LAST:event_dobuttonActionPerformed
     int dClkRes = 400;    // double-click speed in ms
    long timeMouseDown=0; // last mouse down time
    int lastX=0,lastY=0;  //  last x and y

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:

         long currentTime = evt.getWhen();
         //System.out.println("click " + currentTime);
         //System.out.println("posituion" + evt.getX());
        if ((lastX==evt.getX()) && (lastY==evt.getY()) && ((evt.getWhen() -timeMouseDown) < dClkRes)) {
            //System.out.println("double click " + currentTime);
            runpackage();
        }
         lastX=evt.getX();
         lastY=evt.getY();
         timeMouseDown=currentTime;
    }//GEN-LAST:event_tableMouseClicked

    private void runpackage(){
        int[] rowIndex = table.getSelectedRows();
        Object idu=0;
        //System.out.println(rowIndex+" "+rowIndex[0]);
        if (rowIndex.length>0){
            idu=table.getModel().getValueAt(rowIndex[0],0);
            String readsamples = "select `name`,`script`,`groups` from `scriptsGUI` where `name` like '" + idu.toString() + "'";
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
                Statement statment = con.createStatement();
                ResultSet rs = null;
                rs = statment.executeQuery(readsamples);
                rs.next();

                xmlReader xmlread = new xmlReader();
                //System.out.println("script name " + rs.getString("script"));
                xmlread.readfile(rs.getString("script"),  rs.getInt("groups"));
                rs.close();
                statment.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(miRNAPackages.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error: copy this error and send to lorena.pantano@crg.es: " + ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "You have to select one package!","warning",JOptionPane.WARNING_MESSAGE);
        }


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JLabel InfoText;
    private javax.swing.JComboBox TypeList;
    private javax.swing.JButton dobutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keysearch;
    private javax.swing.JButton searchbut;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    
}
