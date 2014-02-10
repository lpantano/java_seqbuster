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
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
//import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class BrowFrame  extends javax.swing.JDialog {

    String exp=null;
    String optslq="";
    String orderopt="";
    int col=0;
    public BrowFrame(javax.swing.JDialog parent,String expname) throws SQLException, IOException {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        exp=expname;
        BufferedImage  image;
        //Panel panel = new PanelImg("html/logo.jpg");
        //panel.setBackground(Color.red);
        //getContentPane().add(panel,new AbsoluteConstraints(320,400,300,70));
        loaddata(exp,"");
        setVisible(true);
    }
public void  loaddata (String expname,String opt) throws SQLException {
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con = tools.docon(namepro);
        Statement statment =  con.createStatement();
        String readsamples="describe `"+expname+"`";
        ResultSet rs=statment.executeQuery(readsamples);
        //MetaData model = rs.getMetaData();
        //table.setAutoCreateColumnsFromModel(false);
        
        if (optslq.matches("")){
         optslq=" where `DB` not like 'na'";
        }
        opt=optslq+opt+orderopt;
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        col=0;

        while (rs.next()){
            model.addColumn(rs.getString(1));
            TableColumn colm = table.getColumnModel().getColumn(col);
            colm.setWidth(50);
            col++;
            orderl.addItem(rs.getString(1));
//            System.out.println(rs.getString(1));
        }
        if (!ptext.getText().equals("")){
            opt=opt+" limit "+ptext.getText()+","+ntext.getText();
        }else{
            opt=opt+" limit 0,50";
        }
        readsamples="select * from `"+expname+"` "+opt;
//            System.out.println(opt);

//        System.out.println(readsamples);
        opttext.setText(readsamples);
        rs=statment.executeQuery(readsamples);
        while (rs.next()){
            Object[] row=new Object [col];
            for (int i=0;i<col;i++){
                row[i]=rs.getString(i+1);
            }
            model.addRow(row);
        }
        //table.setModel(model);
        table = autoResizeColWidth(table, model);
        table.repaint();
        rs.close();
        statment.close();
        con.close();

    }

public void  showdata (String expname,String opt) throws SQLException {

        String namepro=SeqBusterView.loaprotext.getText();
        Connection con = tools.docon(namepro);
        Statement statment =  con.createStatement();
        String readsamples="describe `"+expname+"`";
        ResultSet rs=statment.executeQuery(readsamples);
        //MetaData model = rs.getMetaData();
        //table.setAutoCreateColumnsFromModel(false);
        opt=optslq+opt+orderopt;
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        col=0;
       
        while (rs.next()){
            model.addColumn(rs.getString(1));
            TableColumn colm = table.getColumnModel().getColumn(col);
            colm.setWidth(50);
            col++;
//            System.out.println(rs.getString(1));
        }
        if (!ptext.getText().equals("")){
            opt=opt+" limit "+ptext.getText()+","+ntext.getText();
        }else{
            ptext.setText("0");
            opt=opt+" limit 0,50";
        }
        readsamples="select * from `"+expname+"` "+opt;
//            System.out.println(opt);
        
//        System.out.println(readsamples);
        opttext.setText(readsamples);
        rs=statment.executeQuery(readsamples);
        while (rs.next()){
            Object[] row=new Object [col];
            for (int i=0;i<col;i++){
                row[i]=rs.getString(i+1);
            }
            model.addRow(row);
        }
        //table.setModel(model);
        table = autoResizeColWidth(table, model);
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

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(
            SwingConstants.LEFT);

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        seqtext = new javax.swing.JTextField();
        searchbut = new javax.swing.JButton();
        locustext = new javax.swing.JTextField();
        f1text = new javax.swing.JTextField();
        f2text = new javax.swing.JTextField();
        l1text = new javax.swing.JTextField();
        l2text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        exportbut = new javax.swing.JButton();
        ptext = new javax.swing.JTextField();
        ntext = new javax.swing.JTextField();
        next = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        opttext = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        mutext = new javax.swing.JTextField();
        tr5text = new javax.swing.JTextField();
        tr3text = new javax.swing.JTextField();
        ad3text = new javax.swing.JTextField();
        orderl = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        resetb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(BrowFrame.class);
        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 210, -1));

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
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setColumnSelectionAllowed(true);
        table.setName("table"); // NOI18N
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 890, 240));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        seqtext.setText(resourceMap.getString("seqtext.text")); // NOI18N
        seqtext.setName("seqtext"); // NOI18N
        getContentPane().add(seqtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 140, -1));

        searchbut.setText(resourceMap.getString("searchbut.text")); // NOI18N
        searchbut.setName("searchbut"); // NOI18N
        searchbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbutActionPerformed(evt);
            }
        });
        getContentPane().add(searchbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 90, -1));

        locustext.setText(resourceMap.getString("locustext.text")); // NOI18N
        locustext.setName("locustext"); // NOI18N
        getContentPane().add(locustext, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 160, -1));

        f1text.setText(resourceMap.getString("f1text.text")); // NOI18N
        f1text.setName("f1text"); // NOI18N
        getContentPane().add(f1text, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 40, 30));

        f2text.setText(resourceMap.getString("f2text.text")); // NOI18N
        f2text.setName("f2text"); // NOI18N
        getContentPane().add(f2text, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 50, 30));

        l1text.setText(resourceMap.getString("l1text.text")); // NOI18N
        l1text.setName("l1text"); // NOI18N
        getContentPane().add(l1text, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 40, 30));

        l2text.setText(resourceMap.getString("l2text.text")); // NOI18N
        l2text.setName("l2text"); // NOI18N
        getContentPane().add(l2text, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 50, 30));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        exportbut.setText(resourceMap.getString("exportbut.text")); // NOI18N
        exportbut.setName("exportbut"); // NOI18N
        exportbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportbutActionPerformed(evt);
            }
        });
        getContentPane().add(exportbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 190, -1));

        ptext.setText(resourceMap.getString("ptext.text")); // NOI18N
        ptext.setName("ptext"); // NOI18N
        getContentPane().add(ptext, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 465, 50, 30));

        ntext.setText(resourceMap.getString("ntext.text")); // NOI18N
        ntext.setName("ntext"); // NOI18N
        getContentPane().add(ntext, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 465, 60, 30));

        next.setText(resourceMap.getString("next.text")); // NOI18N
        next.setName("next"); // NOI18N
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        getContentPane().add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 100, -1));

        prev.setText(resourceMap.getString("prev.text")); // NOI18N
        prev.setName("prev"); // NOI18N
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });
        getContentPane().add(prev, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 100, -1));

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, -1, -1));

        opttext.setText(resourceMap.getString("opttext.text")); // NOI18N
        opttext.setName("opttext"); // NOI18N
        getContentPane().add(opttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 560, -1));

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, -1, -1));

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, -1));

        mutext.setText(resourceMap.getString("muttext.text")); // NOI18N
        mutext.setName("muttext"); // NOI18N
        getContentPane().add(mutext, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 65, 60, -1));

        tr5text.setText(resourceMap.getString("tr5text.text")); // NOI18N
        tr5text.setName("tr5text"); // NOI18N
        getContentPane().add(tr5text, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 25, 60, -1));

        tr3text.setText(resourceMap.getString("tri3text.text")); // NOI18N
        tr3text.setName("tri3text"); // NOI18N
        getContentPane().add(tr3text, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 60, -1));

        ad3text.setText(resourceMap.getString("ad3text.text")); // NOI18N
        ad3text.setName("ad3text"); // NOI18N
        getContentPane().add(ad3text, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 26, 60, -1));

        orderl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose" }));
        orderl.setName("orderl"); // NOI18N
        orderl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                orderlMouseReleased(evt);
            }
        });
        orderl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderlItemStateChanged(evt);
            }
        });
        getContentPane().add(orderl, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 160, -1));

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        resetb.setText(resourceMap.getString("resetb.text")); // NOI18N
        resetb.setName("resetb"); // NOI18N
        resetb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbActionPerformed(evt);
            }
        });
        getContentPane().add(resetb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 190, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        tools.freememory();
        dispose();
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void searchbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbutActionPerformed
        try {
            // TODO add your handling code here:
            readparam();
            
            ptext.setText("");
            showdata(exp, "");
        } catch (SQLException ex) {
            Logger.getLogger(BrowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_searchbutActionPerformed

    private void readparam(){
            if (!f1text.getText().matches("")){
                 optslq=" where `DB` not like 'na' and `freq` >= "+f1text.getText()+"";
//                 f1text.setText("");
            }else{
                optslq=" where `DB` not like 'na' and `freq` >= 0";
            }
            if (!seqtext.getText().matches("")){
                optslq=optslq+" and  `seq` like '%"+seqtext.getText()+"%'";
//                seqtext.setText("");
            }
            if (!locustext.getText().matches("")){
                optslq=optslq+" and  `chr` like '%"+locustext.getText()+"%'";
//                locustext.setText("");
            }
            if (!f2text.getText().matches("")){
                optslq=optslq+" and  `freq` <="+f2text.getText();
//                f2text.setText("");
            }
            if (!l1text.getText().matches("")){
                optslq=optslq+" and  `len` >="+l1text.getText();
//                l1text.setText("");
            }
            if (!l2text.getText().matches("")){
                optslq=optslq+" and  `len` <="+l2text.getText();
//                l2text.setText("");
            }
            if (!tr5text.getText().matches("")){
                optslq=optslq+" and  `trimmed5` like '%"+tr5text.getText()+"%'";
//                tr5text.setText("");
            }
            if (!tr3text.getText().matches("")){
                optslq=optslq+" and  `trimmed3` like '%"+tr3text.getText()+"%'";
//                tr3text.setText("");
            }
            if (!ad3text.getText().matches("")){
                optslq=optslq+" and  `addition3` like '%"+ad3text.getText()+"%'";
//                ad3text.setText("");
            }
            if (!tr5text.getText().matches("")){
                optslq=optslq+" and  `mut` like '%"+mutext.getText()+"%'";
//                tr5text.setText("");
            }
    }
    private void exportbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportbutActionPerformed
        // TODO add your handling code here:
        String pathout=SeqBusterView.pathout.getText()+"/";
        String readsamples="select * from `"+exp+"` "+optslq;
        String namepro=SeqBusterView.loaprotext.getText();
        Connection con;
        try {
            con = tools.docon(namepro);
            Statement statment =  con.createStatement();
            ResultSet rs=statment.executeQuery(readsamples);
             try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathout + namepro + "/" + "table.txt")));
                while (rs.next()){
                    for (int i=1;i<=col;i++){
    //                System.out.println(model.getValueAt(r, c));
                        
                        out.write(rs.getString(i)+"\t");
                    }
                    out.write("\n");
                }
                    


                 out.close();
                 JOptionPane.showMessageDialog(null,"Your data is in:"+pathout + namepro + "/" + "table.txt");
            } catch (IOException ex) {
                Logger.getLogger(BrowDEnmFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
            }
            rs.close();
            statment.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BrowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_exportbutActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        // TODO add your handling code here:
        int value1=Integer.parseInt(ptext.getText())-50;
        if (value1<0){
           value1=0;
           
        }
        ptext.setText(""+value1);
        int value2=Integer.parseInt(ntext.getText())-50;
        if (value2<50){
            value2=50;
            
        }
        ntext.setText(""+value2);
        try {
            showdata(exp, "");
        } catch (SQLException ex) {
            Logger.getLogger(BrowFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.getSQLState());
        }
    }//GEN-LAST:event_prevActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        int value1=Integer.parseInt(ptext.getText())+50;
        if (value1<0){
           value1=0;
           
        }
        ptext.setText(""+value1);
        int value2=Integer.parseInt(ntext.getText())+50;
        if (value2<50){
            value2=50;
            
        }
        ntext.setText(""+value2);
        try {
            showdata(exp, "");
        } catch (SQLException ex) {
            Logger.getLogger(BrowFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.getSQLState());
        }
    }//GEN-LAST:event_nextActionPerformed

    private void orderlItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderlItemStateChanged
        // TODO add your handling code here:
        String ordername=(String)orderl.getSelectedItem();
        try {
            orderopt=" order by " + ordername + " ASC";
            showdata(exp, "");
        } catch (SQLException ex) {
            Logger.getLogger(BrowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_orderlItemStateChanged

    private void orderlMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderlMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_orderlMouseReleased

    private void resetbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbActionPerformed
        // TODO add your handling code here:
        optslq="";
    }//GEN-LAST:event_resetbActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JTextField ad3text;
    private javax.swing.JButton exportbut;
    private javax.swing.JTextField f1text;
    private javax.swing.JTextField f2text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField l1text;
    private javax.swing.JTextField l2text;
    private javax.swing.JTextField locustext;
    private javax.swing.JTextField mutext;
    private javax.swing.JButton next;
    private javax.swing.JTextField ntext;
    private javax.swing.JLabel opttext;
    private javax.swing.JComboBox orderl;
    private javax.swing.JButton prev;
    private javax.swing.JTextField ptext;
    private javax.swing.JButton resetb;
    private javax.swing.JButton searchbut;
    private javax.swing.JTextField seqtext;
    private javax.swing.JTable table;
    private javax.swing.JTextField tr3text;
    private javax.swing.JTextField tr5text;
    // End of variables declaration//GEN-END:variables
    
}
