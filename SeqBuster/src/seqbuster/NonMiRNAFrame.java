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

package seqbuster;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList.*;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public  class NonMiRNAFrame extends javax.swing.JDialog  {

    int numexp=0;
    public   NonMiRNAFrame(java.awt.Frame parent) throws SQLException {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(CloseButAdapFrame);
        showinfo();
        
    }

    public void showinfo() throws SQLException{

        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
        Statement statment = (Statement) con.createStatement();
        String readsamples="select `name` from `experiments` where `type` like 'miRNA' ";
        ResultSet rs=statment.executeQuery(readsamples);
        while (rs.next()) {
            ExpList.addItem(rs.getString(1));
        }
        rs=statment.executeQuery("select `name` from `experiments` where `type` like 'usRNA' ");
        while (rs.next()) {
            numexp++;
        }
        
        rs=statment.executeQuery("select `specie` from `seqbuster`.`genome` group by `specie`");
        ArrayList<String> spelist = new ArrayList<String>();
        while (rs.next()) {
            spelist.add(rs.getString(1));
        }
        rs=statment.executeQuery("select `specie` from `seqbuster`.`track` group by `specie`");
        while (rs.next()) {
             if (!spelist.contains(rs.getString(1))){
                spelist.add(rs.getString(1));
             }

        }
        for(int i=0; i<spelist.size(); i++) {
            SpeList.addItem(spelist.get(i));
        }
        updatespelist();
        rs=statment.executeQuery("select count(*) from `seqbuster`.`genome`");
        rs.next();
        NumGenText.setText(rs.getString(1));
        //if (rs.getInt(1)==0){
           // doad.setEnabled(false);
        //}
        rs=statment.executeQuery("select count(*) from `seqbuster`.`track`");
        rs.next();
        NumTraText.setText(rs.getString(1));
        //int numtrack=Integer.parseInt(rs.getString(1));
        readsamples="select * from `seqbuster`.`track`";
//        Vector listtrack=new Vector();
        rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        //int p=tralist.getModel().getSize();
        int p=0;
        tralist.removeAll();
        while (rs.next()) {

            model.add(p,rs.getString(1));
            p++;

        }

        tralist.setModel(model);
        tralist.repaint();


        rs.close();
        statment.close();
        con.close();
    }
    Thread nThread2 = null;
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doad = new javax.swing.JButton();
        CloseButAdapFrame = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ExpList = new javax.swing.JComboBox();
        SpeList = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        NumGenText = new javax.swing.JTextField();
        NumTraText = new javax.swing.JTextField();
        LoaGenBut = new javax.swing.JButton();
        LoaTraBut = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        GenList = new javax.swing.JComboBox();
        mistext = new javax.swing.JTextField();
        addtext = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lablefreq = new javax.swing.JLabel();
        freqtext = new javax.swing.JTextField();
        clus2opt = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tralist = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        probar = new javax.swing.JProgressBar();
        CusAlgCheck = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        rempreb = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        addopt = new javax.swing.JCheckBox();
        jButton15 = new javax.swing.JButton();
        ssopt = new javax.swing.JCheckBox();
        jButton16 = new javax.swing.JButton();
        upannfilec = new javax.swing.JCheckBox();
        deltrab = new javax.swing.JButton();
        delgenb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(NonMiRNAFrame.class);
        setTitle(resourceMap.getString("aboutBox.title")); // NOI18N
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doad.setText(resourceMap.getString("doad.text")); // NOI18N
        doad.setName("doad"); // NOI18N
        doad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doadActionPerformed(evt);
            }
        });
        getContentPane().add(doad, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 130, 30));

        CloseButAdapFrame.setText(resourceMap.getString("CloseButAdapFrame.text")); // NOI18N
        CloseButAdapFrame.setName("CloseButAdapFrame"); // NOI18N
        CloseButAdapFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButAdapFrameActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButAdapFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 130, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        ExpList.setName("ExpList"); // NOI18N
        getContentPane().add(ExpList, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 140, 30));

        SpeList.setName("SpeList"); // NOI18N
        SpeList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SpeListMouseReleased(evt);
            }
        });
        SpeList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpeListItemStateChanged(evt);
            }
        });
        getContentPane().add(SpeList, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 130, 30));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 20, 420));

        NumGenText.setBackground(resourceMap.getColor("NumGenText.background")); // NOI18N
        NumGenText.setEditable(false);
        NumGenText.setText(resourceMap.getString("NumGenText.text")); // NOI18N
        NumGenText.setName("NumGenText"); // NOI18N
        getContentPane().add(NumGenText, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 75, 50, -1));

        NumTraText.setBackground(resourceMap.getColor("NumTraText.background")); // NOI18N
        NumTraText.setEditable(false);
        NumTraText.setText(resourceMap.getString("NumTraText.text")); // NOI18N
        NumTraText.setName("NumTraText"); // NOI18N
        getContentPane().add(NumTraText, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 50, -1));

        LoaGenBut.setText(resourceMap.getString("LoaGenBut.text")); // NOI18N
        LoaGenBut.setName("LoaGenBut"); // NOI18N
        LoaGenBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoaGenButActionPerformed(evt);
            }
        });
        getContentPane().add(LoaGenBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        LoaTraBut.setText(resourceMap.getString("LoaTraBut.text")); // NOI18N
        LoaTraBut.setName("LoaTraBut"); // NOI18N
        LoaTraBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoaTraButActionPerformed(evt);
            }
        });
        getContentPane().add(LoaTraBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 150, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        GenList.setName("GenList"); // NOI18N
        getContentPane().add(GenList, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 130, 30));

        mistext.setText(resourceMap.getString("mistext.text")); // NOI18N
        mistext.setName("mistext"); // NOI18N
        getContentPane().add(mistext, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 30, -1));

        addtext.setText(resourceMap.getString("addtext.text")); // NOI18N
        addtext.setName("addtext"); // NOI18N
        getContentPane().add(addtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 30, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, -1));

        lablefreq.setText(resourceMap.getString("lablefreq.text")); // NOI18N
        lablefreq.setName("lablefreq"); // NOI18N
        getContentPane().add(lablefreq, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        freqtext.setText(resourceMap.getString("freqtext.text")); // NOI18N
        freqtext.setName("freqtext"); // NOI18N
        getContentPane().add(freqtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 50, -1));

        clus2opt.setSelected(true);
        clus2opt.setText(resourceMap.getString("clus2opt.text")); // NOI18N
        clus2opt.setName("clus2opt"); // NOI18N
        clus2opt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clus2optMouseClicked(evt);
            }
        });
        getContentPane().add(clus2opt, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, -1, -1));

        jButton4.setBackground(resourceMap.getColor("jButton4.background")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 55, 20, 20));

        jButton5.setBackground(resourceMap.getColor("jButton5.background")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 20, 20));

        jButton6.setBackground(resourceMap.getColor("jButton6.background")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setFocusable(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 20, 20));

        jButton7.setBackground(resourceMap.getColor("jButton7.background")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setFocusable(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 20, 20));

        jButton8.setBackground(resourceMap.getColor("jButton8.background")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setFocusable(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setOpaque(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 20, 20));

        jButton9.setBackground(resourceMap.getColor("jButton9.background")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setOpaque(true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 20, 20));

        jButton10.setBackground(resourceMap.getColor("jButton10.background")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.setFocusable(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 20, 20));

        jButton11.setBackground(resourceMap.getColor("jButton11.background")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.setFocusable(false);
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setOpaque(true);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 20, 20));

        jButton12.setBackground(resourceMap.getColor("jButton12.background")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setBorderPainted(false);
        jButton12.setFocusable(false);
        jButton12.setName("jButton12"); // NOI18N
        jButton12.setOpaque(true);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 20, 20));

        jButton13.setBackground(resourceMap.getColor("jButton13.background")); // NOI18N
        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setBorderPainted(false);
        jButton13.setFocusable(false);
        jButton13.setName("jButton13"); // NOI18N
        jButton13.setOpaque(true);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 20, 20));

        jButton14.setBackground(resourceMap.getColor("jButton14.background")); // NOI18N
        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setBorderPainted(false);
        jButton14.setFocusable(false);
        jButton14.setName("jButton14"); // NOI18N
        jButton14.setOpaque(true);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 228, 20, 20));

        jButton17.setBackground(resourceMap.getColor("jButton17.background")); // NOI18N
        jButton17.setText(resourceMap.getString("jButton17.text")); // NOI18N
        jButton17.setBorderPainted(false);
        jButton17.setFocusable(false);
        jButton17.setName("jButton17"); // NOI18N
        jButton17.setOpaque(true);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 20, 20));

        jLabel8.setBackground(resourceMap.getColor("jLabel8.background")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 180, -1));

        jLabel9.setBackground(resourceMap.getColor("jLabel9.background")); // NOI18N
        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setOpaque(true);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 160, 30));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("jSeparator2"); // NOI18N
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 20, 420));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tralist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        tralist.setName("tralist"); // NOI18N
        jScrollPane1.setViewportView(tralist);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 180, 110));

        jLabel10.setBackground(resourceMap.getColor("jLabel10.background")); // NOI18N
        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 240, 130));

        jLabel11.setBackground(resourceMap.getColor("jLabel11.background")); // NOI18N
        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel11.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 240, 30));

        jLabel12.setBackground(resourceMap.getColor("jLabel12.background")); // NOI18N
        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setOpaque(true);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, 40));

        jLabel13.setBackground(resourceMap.getColor("jLabel13.background")); // NOI18N
        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 30));

        jLabel14.setBackground(resourceMap.getColor("jLabel14.background")); // NOI18N
        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel14.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 240, 40));

        jLabel15.setBackground(resourceMap.getColor("jLabel15.background")); // NOI18N
        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setOpaque(true);
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 240, 40));

        probar.setName("probar"); // NOI18N
        getContentPane().add(probar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 390, -1));

        CusAlgCheck.setText(resourceMap.getString("CusAlgCheck.text")); // NOI18N
        CusAlgCheck.setName("CusAlgCheck"); // NOI18N
        CusAlgCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CusAlgCheckMouseClicked(evt);
            }
        });
        CusAlgCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusAlgCheckActionPerformed(evt);
            }
        });
        getContentPane().add(CusAlgCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, 190, 70));

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, -1, -1));

        rempreb.setText(resourceMap.getString("rempreb.text")); // NOI18N
        rempreb.setName("rempreb"); // NOI18N
        rempreb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remprebActionPerformed(evt);
            }
        });
        getContentPane().add(rempreb, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 130, -1));

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addopt.setText(resourceMap.getString("addopt.text")); // NOI18N
        addopt.setName("addopt"); // NOI18N
        addopt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addoptMouseClicked(evt);
            }
        });
        jPanel1.add(addopt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jButton15.setBackground(resourceMap.getColor("jButton15.background")); // NOI18N
        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setFocusable(false);
        jButton15.setName("jButton15"); // NOI18N
        jButton15.setOpaque(true);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 20, 20));

        ssopt.setText(resourceMap.getString("ssopt.text")); // NOI18N
        ssopt.setName("ssopt"); // NOI18N
        ssopt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ssoptMouseClicked(evt);
            }
        });
        jPanel1.add(ssopt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 170, -1));

        jButton16.setBackground(resourceMap.getColor("jButton16.background")); // NOI18N
        jButton16.setText(resourceMap.getString("jButton16.text")); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setFocusable(false);
        jButton16.setName("jButton16"); // NOI18N
        jButton16.setOpaque(true);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 20, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, 240, 80));

        upannfilec.setText(resourceMap.getString("upannfilec.text")); // NOI18N
        upannfilec.setName("upannfilec"); // NOI18N
        upannfilec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upannfilecMouseClicked(evt);
            }
        });
        getContentPane().add(upannfilec, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, -1));

        deltrab.setText(resourceMap.getString("deltrab.text")); // NOI18N
        deltrab.setName("deltrab"); // NOI18N
        deltrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deltrabActionPerformed(evt);
            }
        });
        getContentPane().add(deltrab, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 150, -1));

        delgenb.setText(resourceMap.getString("delgenb.text")); // NOI18N
        delgenb.setName("delgenb"); // NOI18N
        delgenb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delgenbActionPerformed(evt);
            }
        });
        getContentPane().add(delgenb, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doadActionPerformed
      
        final infoFrame info_obj;
        info_obj = new infoFrame("Starting analysis\n");

        Runnable view = new Runnable() {
        public void run() {
                
                numexp=new nonMiRNA().startana(ssopt,CusAlgCheck,ExpList,GenList,SpeList,upannfilec,clus2opt,freqtext,probar,addopt,tralist,numexp,info_obj);
               
                tools.freememory();
                try {
                    updateinfo();
                } catch (SQLException ex) {
                    //Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                    Logger logger=Logger.getLogger(NonMiRNAFrame.class.getName());
                    tools.writelog(logger, ex);
                }
             }
        };
        nThread2= new Thread(view);
        nThread2.start();
        
    }//GEN-LAST:event_doadActionPerformed

    


    private void CloseButAdapFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButAdapFrameActionPerformed
        // TODO add your handling code here:
        
        if (nThread2!=null){

            if (nThread2.isAlive()){
              Object[] options = {"Yes","No"};

              int n = JOptionPane.showOptionDialog(null,"Do you want to cancel the process?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
              if (n==1){


               }else{
                    nThread2.stop();
                    Runtime.getRuntime().gc();
                    dispose();
               }
              }else{
                Runtime.getRuntime().gc();
                dispose();
              }

        }else{
            Runtime.getRuntime().gc();
             dispose();
        }
    }//GEN-LAST:event_CloseButAdapFrameActionPerformed

    private void LoaGenButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoaGenButActionPerformed
        // TODO add your handling code here:
        //JFrame mainFrame = seqbusterApp.getApplication().getMainFrame();
        loadgenome_obj = new LoaNewGenFrame(this);
        loadgenome_obj.setLocationRelativeTo(this);
        loadgenome_obj.setVisible(true);
        loadgenome_obj.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {
                    try {
                        updateinfo();
                    } catch (SQLException ex) {
                        Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            public void windowActivated(WindowEvent arg0) {
                //System.out.println("Window Activated");
            }
            public void windowClosing(WindowEvent arg0) {
                //System.out.println("Window Closing");
            }
            public void windowDeactivated(WindowEvent arg0) {
                //System.out.println("Window Deactivated");
            }
            public void windowDeiconified(WindowEvent arg0) {
                //System.out.println("Window Deiconified");
            }
            public void windowIconified(WindowEvent arg0) {
                //System.out.println("Window Iconified");
            }
            public void windowOpened(WindowEvent arg0) {
                //System.out.println("Window Opened");
            }
         });
        doad.setEnabled(true);
        
    }//GEN-LAST:event_LoaGenButActionPerformed

    private void LoaTraButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoaTraButActionPerformed
        // TODO add your handling code here:
        loadgenome_obj = new LoaNewTraFrame(this);
        loadgenome_obj.setLocationRelativeTo(this);
        loadgenome_obj.setVisible(true);
        loadgenome_obj.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {
                    try {
                        updateinfo();
                    } catch (SQLException ex) {
                        Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            public void windowActivated(WindowEvent arg0) {
                //System.out.println("Window Activated");
            }
            public void windowClosing(WindowEvent arg0) {
                //System.out.println("Window Closing");
            }
            public void windowDeactivated(WindowEvent arg0) {
                //System.out.println("Window Deactivated");
            }
            public void windowDeiconified(WindowEvent arg0) {
                //System.out.println("Window Deiconified");
            }
            public void windowIconified(WindowEvent arg0) {
                //System.out.println("Window Iconified");
            }
            public void windowOpened(WindowEvent arg0) {
                //System.out.println("Window Opened");
            }
         });
        
    }//GEN-LAST:event_LoaTraButActionPerformed

    private void SpeListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpeListMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_SpeListMouseReleased

    private void SpeListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpeListItemStateChanged
        // TODO add your handling code here:
        updatespelist();


    }//GEN-LAST:event_SpeListItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Show the number of genomes loaded to SeqBuster.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Show the number of tracks features (ncRNA,repeat...) loaded to SeqBuster.");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Load a new genome to the database.");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Load a new track to the database.");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Choose a sample for the analysis.");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Minimum number of counts that sequences should have to be considered in the analysis.\n 5000 sequences ~ 3 hours of time consuming.");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Choose the specie that your data belongs.");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Choose the genome to user for the annotation.");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Number of mismatches allowed in the alignment.");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Number of nucleotides allowed as additions in the alingment.\n 0 will be a perfect mactch at the end of the alignment, \n 1 will be allowing 1 nt at the end not matched with the genome...");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Select the tracks-features to do the analysis.");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Map the usRNAs using the selected tracks-features.");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Do the first clustering to get pre-usRNAs. All sequences with a 100% of identity and 80% overlapping.");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void CusAlgCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CusAlgCheckMouseClicked
        // TODO add your handling code here:
        if (CusAlgCheck.isSelected()){
            GenList.setEnabled(false);
            SpeList.setEnabled(false);
            upannfilec.setSelected(false);
            addtext.setEnabled(false);
            mistext.setEnabled(false);
            clus2opt.setSelected(true);
            doad.setEnabled(true);
        }else{
            GenList.setEnabled(true);
            SpeList.setEnabled(true);
            addtext.setEnabled(true);
            mistext.setEnabled(true);
        }
    }//GEN-LAST:event_CusAlgCheckMouseClicked

    private void remprebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remprebActionPerformed
       String expname = (String)ExpList.getSelectedItem();
       JFileChooser c=new JFileChooser();
        String filename;

        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            try {
                cluster.removepred(expname, filename);
            } catch (SQLException ex) {
                Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showcerror("No correct connection to database");
            } catch (IOException ex) {
                Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showcerror("No file founded, or error format");
            }
        }

    }//GEN-LAST:event_remprebActionPerformed

    private void upannfilecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upannfilecMouseClicked
        // TODO add your handling code here:
        if (upannfilec.isSelected()){
            clus2opt.setSelected(true);
            CusAlgCheck.setSelected(false);
            GenList.setEnabled(false);
            SpeList.setEnabled(false);
            addtext.setEnabled(false);
            mistext.setEnabled(false);
            doad.setEnabled(true);
        }else{
            clus2opt.setSelected(false);
            CusAlgCheck.setSelected(false);
            GenList.setEnabled(true);
            SpeList.setEnabled(true);
            addtext.setEnabled(true);
            mistext.setEnabled(true);
        }
    }//GEN-LAST:event_upannfilecMouseClicked

    private void clus2optMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clus2optMouseClicked
        // TODO add your handling code here:
        if(clus2opt.isSelected()){
            upannfilec.setSelected(false);
            CusAlgCheck.setSelected(false);
            GenList.setEnabled(true);
            SpeList.setEnabled(true);
            addtext.setEnabled(true);
            mistext.setEnabled(true);
        }else{
            upannfilec.setSelected(false);
            CusAlgCheck.setSelected(false);
        }
    }//GEN-LAST:event_clus2optMouseClicked

    private void CusAlgCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusAlgCheckActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CusAlgCheckActionPerformed

    private void addoptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addoptMouseClicked
        // TODO add your handling code here:
       if (addopt.isSelected()){
            SpeList.setEnabled(true);
            doad.setEnabled(true);
        }
    }//GEN-LAST:event_addoptMouseClicked

    private void delgenbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delgenbActionPerformed
        try {
            // TODO add your handling code here:
            RemoveGenomeFrame remgen = new RemoveGenomeFrame();
            remgen.setVisible(true);
            remgen.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {
                    try {
                        updateinfo();
                    } catch (SQLException ex) {
                        Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            public void windowActivated(WindowEvent arg0) {
                //System.out.println("Window Activated");
            }
            public void windowClosing(WindowEvent arg0) {
                //System.out.println("Window Closing");
            }
            public void windowDeactivated(WindowEvent arg0) {
                //System.out.println("Window Deactivated");
            }
            public void windowDeiconified(WindowEvent arg0) {
                //System.out.println("Window Deiconified");
            }
            public void windowIconified(WindowEvent arg0) {
                //System.out.println("Window Iconified");
            }
            public void windowOpened(WindowEvent arg0) {
                //System.out.println("Window Opened");
            }
         });
        } catch (MalformedURLException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_delgenbActionPerformed

    private void deltrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deltrabActionPerformed

        try {
            // TODO add your handling code here:
            RemovetrackFrame remtra = new RemovetrackFrame();
            remtra.setVisible(true);
            remtra.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {
                    try {
                        updateinfo();
                    } catch (SQLException ex) {
                        Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            public void windowActivated(WindowEvent arg0) {
                //System.out.println("Window Activated");
            }
            public void windowClosing(WindowEvent arg0) {
                //System.out.println("Window Closing");
            }
            public void windowDeactivated(WindowEvent arg0) {
                //System.out.println("Window Deactivated");
            }
            public void windowDeiconified(WindowEvent arg0) {
                //System.out.println("Window Deiconified");
            }
            public void windowIconified(WindowEvent arg0) {
                //System.out.println("Window Iconified");
            }
            public void windowOpened(WindowEvent arg0) {
                //System.out.println("Window Opened");
            }
        });
        } catch (MalformedURLException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deltrabActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        tools.showinfo("This module will calculate the secondary structure of the usRNA. You will need RNAfold to run this module.");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void ssoptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ssoptMouseClicked
        if(ssopt.isSelected()){

            GenList.setEnabled(true);
            SpeList.setEnabled(true);
            tools.showinfo("Make sure that the species/genome lists are selected \n"
                    +"with the correct information, otherwise you will get errors \n"
                    + "during the process.");
        }
    }//GEN-LAST:event_ssoptMouseClicked

    private void updatespelist(){
        String pathout=SeqBusterView.pathout.getText()+"/";
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        String expname = (String)ExpList.getSelectedItem();
        String spe = (String)SpeList.getSelectedItem();
        Connection con = null;
        try {
            con =  DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
            Statement statment =con.createStatement();
            //statment.executeUpdate("drop table if exists `"+expname+"clusraw` ");
            String readsamples="select `name` from `seqbuster`.`genome` where `specie` like '"+spe+"' ";
            ResultSet rs=statment.executeQuery(readsamples);

                GenList.removeAllItems();
                 while (rs.next()) {
                    GenList.addItem(rs.getString(1));
                 }
        } catch (SQLException ex) {
            Logger.getLogger(NonMiRNAFrame.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }

    }

    private void updateinfo() throws SQLException{
        String host=SeqBusterView.HosMysText.getText();
        String user=SeqBusterView.UseMysText.getText();
        String pssw=SeqBusterView.PswMysText.getText();
        String namepro=SeqBusterView.loaprotext.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+namepro,user,pssw);
        Statement statment = (Statement) con.createStatement();
        String readsamples="select `name` from `experiments` where `type` like 'miRNA' ";
        ResultSet rs=null;

        rs=statment.executeQuery("select `name` from `experiments` where `type` like 'usRNA' ");
        while (rs.next()) {
            numexp++;
        }
        
        rs=statment.executeQuery("select `specie` from `seqbuster`.`track` group by `specie`");
        while (rs.next()) {
            SpeList.addItem(rs.getString(1));

        }
        updatespelist();
        rs=statment.executeQuery("select count(*) from `seqbuster`.`genome`");
        rs.next();
        NumGenText.setText(rs.getString(1));
        //if (rs.getInt(1)==0){
            //doad.setEnabled(false);
        //}
        rs=statment.executeQuery("select count(*) from `seqbuster`.`track`");
        rs.next();
        NumTraText.setText(rs.getString(1));
        //int numtrack=Integer.parseInt(rs.getString(1));
        readsamples="select * from `seqbuster`.`track`";
//        Vector listtrack=new Vector();
        rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        //int p=tralist.getModel().getSize();
        int p=0;
        tralist.removeAll();
        while (rs.next()) {

            model.add(p,rs.getString(1));
            p++;

        }

        tralist.setModel(model);
        tralist.repaint();


        rs.close();
        statment.close();
        con.close();


    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButAdapFrame;
    private javax.swing.JCheckBox CusAlgCheck;
    public static javax.swing.JComboBox ExpList;
    public static javax.swing.JComboBox GenList;
    private javax.swing.JButton LoaGenBut;
    private javax.swing.JButton LoaTraBut;
    public static javax.swing.JTextField NumGenText;
    public static javax.swing.JTextField NumTraText;
    public static javax.swing.JComboBox SpeList;
    private javax.swing.JCheckBox addopt;
    public static javax.swing.JTextField addtext;
    private javax.swing.JCheckBox clus2opt;
    private javax.swing.JButton delgenb;
    private javax.swing.JButton deltrab;
    private javax.swing.JButton doad;
    private javax.swing.JTextField freqtext;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lablefreq;
    public static javax.swing.JTextField mistext;
    private javax.swing.JProgressBar probar;
    private javax.swing.JButton rempreb;
    private javax.swing.JCheckBox ssopt;
    public static javax.swing.JList tralist;
    private javax.swing.JCheckBox upannfilec;
    // End of variables declaration//GEN-END:variables
    public static JDialog loadgenome_obj;
    public static JDialog loadtrack_obj;
   


public void DoRuntime (String cmd) throws IOException  {

       if (cmd.matches("")) {
         System.err.println("Need command to run");
         System.exit(-1);
           }
       System.out.printf("running %s ",cmd);
       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
  
    }



}