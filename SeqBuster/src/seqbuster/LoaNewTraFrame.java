/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoaNewTraFrame.java
 *
 * Created on Apr 27, 2010, 6:30:36 PM
 */

package seqbuster;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JDialog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class LoaNewTraFrame extends javax.swing.JDialog {

    /** Creates new form LoaNewTraFrame */
    public LoaNewTraFrame(javax.swing.JDialog parent) {
        super(parent);
        initComponents();
        bartrack.setValue(0);
        bartrack.setStringPainted(true);
        //editpanel.setText("http://estivill_lab.crg.es/seqbuster/javaapp/repeatchr11.txt");
        DefaultTableModel model = new DefaultTableModel();
        
        int col=0;
        TableColumn column = null;

        //String [] header = {"1529","NM_009878","chr9","+","123836859","123839607","123837119","123839098","2","123836859,123838738,","123837260,123839607,","197948","Cdkn2d"};
        String [] header = {"1133","NM_016724","chr11","+","71900601","71907366","7190321","71907221","6","71900601,71901619,71903209,71906314,71906655,71906940,","71900803,71901685,71903385,71906503,71906791,71907366,","0","FOLR1","cmpl","cmpl","-1,-1,0,0,0,1,"};
        for (int i=0;i<header.length;i++){
            int num=i+1;
            model.addColumn("col"+num);
                   
            col++;
//            System.out.println(rs.getString(1));
        }


            Object[] row=new Object [col];
            for (int i=0;i<header.length;i++){
                row[i]=header[i];
            }
            model.addRow(row);
            table.setModel(model);
            for (int i=0;i<header.length;i++){
                TableColumn colm = table.getColumnModel().getColumn(i);
                colm.setPreferredWidth(100);
                
            }
            table.repaint();

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        filtext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        namtext = new javax.swing.JTextField();
        spetext = new javax.swing.JTextField();
        LoaBut = new javax.swing.JButton();
        CloBut = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        chrtext = new javax.swing.JTextField();
        skicheck = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        starttext = new javax.swing.JTextField();
        sttext = new javax.swing.JTextField();
        endtext = new javax.swing.JTextField();
        nametext = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        bartrack = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        genetrackcheck = new javax.swing.JCheckBox();
        jButton14 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(LoaNewTraFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        filtext.setText(resourceMap.getString("filtext.text")); // NOI18N
        filtext.setName("filtext"); // NOI18N
        filtext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtextMouseClicked(evt);
            }
        });
        getContentPane().add(filtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 180, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 295, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 335, -1, -1));

        namtext.setText(resourceMap.getString("namtext.text")); // NOI18N
        namtext.setName("namtext"); // NOI18N
        namtext.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                namtextFocusLost(evt);
            }
        });
        getContentPane().add(namtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 80, -1));

        spetext.setText(resourceMap.getString("spetext.text")); // NOI18N
        spetext.setName("spetext"); // NOI18N
        getContentPane().add(spetext, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 60, -1));

        LoaBut.setText(resourceMap.getString("LoaBut.text")); // NOI18N
        LoaBut.setName("LoaBut"); // NOI18N
        LoaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoaButActionPerformed(evt);
            }
        });
        getContentPane().add(LoaBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 130, -1));

        CloBut.setText(resourceMap.getString("CloBut.text")); // NOI18N
        CloBut.setName("CloBut"); // NOI18N
        CloBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloButActionPerformed(evt);
            }
        });
        getContentPane().add(CloBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 120, -1));

        jLabel4.setBackground(resourceMap.getColor("jLabel4.background")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 350, -1));

        chrtext.setText(resourceMap.getString("chrtext.text")); // NOI18N
        chrtext.setName("chrtext"); // NOI18N
        getContentPane().add(chrtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 30, 25));

        skicheck.setText(resourceMap.getString("skicheck.text")); // NOI18N
        skicheck.setName("skicheck"); // NOI18N
        getContentPane().add(skicheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, -1, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, -1, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, -1, -1));

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        starttext.setText(resourceMap.getString("starttext.text")); // NOI18N
        starttext.setName("starttext"); // NOI18N
        getContentPane().add(starttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 30, 25));

        sttext.setText(resourceMap.getString("sttext.text")); // NOI18N
        sttext.setName("sttext"); // NOI18N
        sttext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sttextActionPerformed(evt);
            }
        });
        getContentPane().add(sttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 30, 25));

        endtext.setText(resourceMap.getString("endtext.text")); // NOI18N
        endtext.setName("endtext"); // NOI18N
        getContentPane().add(endtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 30, 25));

        nametext.setText(resourceMap.getString("nametext.text")); // NOI18N
        nametext.setName("nametext"); // NOI18N
        nametext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nametextActionPerformed(evt);
            }
        });
        getContentPane().add(nametext, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 30, 25));

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
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 20, 20));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 295, 20, 20));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 335, 20, 20));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 20, 20));

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
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 20, 20));

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
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 20, 20));

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
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 20, 20));

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
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, 20, 20));

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
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 20, 20));

        bartrack.setName("bartrack"); // NOI18N
        getContentPane().add(bartrack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jLabel11.setBackground(resourceMap.getColor("jLabel11.background")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setOpaque(true);
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, -1));

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
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setName("table"); // NOI18N
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 530, 80));

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 430, -1));

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 140, 50));

        jLabel14.setBackground(resourceMap.getColor("jLabel14.background")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 350, 140));

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 460, 20));

        genetrackcheck.setText(resourceMap.getString("genetrackcheck.text")); // NOI18N
        genetrackcheck.setName("genetrackcheck"); // NOI18N
        genetrackcheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genetrackcheckMouseClicked(evt);
            }
        });
        getContentPane().add(genetrackcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 220, -1));

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
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 20, 20));

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 160, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtextMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(filtext.getText());
        String filename;

        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            filtext.setText(filename);

            DefaultTableModel model = new DefaultTableModel();

            int col = 0;
            try {
                BufferedReader in = new BufferedReader(new FileReader(filename));
                try {

                    String l = in.readLine();
//                    l = in.readLine();
                    String [] header=l.split("\t");
                    for (int i = 0; i < header.length; i++) {
                        int num = i + 1;
                        model.addColumn("col" + num);

                        col++;
//            System.out.println(rs.getString(1));
                    }


                    Object[] row = new Object[col];
                    for (int i = 0; i < header.length; i++) {
                        row[i] = header[i];
                    }
                    model.addRow(row);
                    table.setModel(model);
                    for (int i = 0; i < header.length; i++) {
                        TableColumn colm = table.getColumnModel().getColumn(i);
                        colm.setPreferredWidth(100);

                    }
                    l = in.readLine();
                    for (int i = 0; i < header.length; i++) {
                        TableColumn colm = table.getColumnModel().getColumn(i);
                        colm.setPreferredWidth(100);

                    }
                    table.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(LoaNewTraFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"Something wrong with your file, you need a table separated by TAB delimeter!");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoaNewTraFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Your file is not there!!");
            }
            
            JOptionPane.showMessageDialog(null,"You can see a line of your file in the above table to select \nthe corresponding columns in the below parameters. ");
        }
    }//GEN-LAST:event_filtextMouseClicked
    Thread nThread=null;
    private void LoaButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoaButActionPerformed
        // TODO add your handling code here:
        Runnable view = new Runnable() {
        public void run() {
            bartrack.setValue(50);

        LoadData loaddata_obj=new LoadData();
        int skip=0;
        if (skicheck.isSelected()){
            skip=1;
        }
        try {
            bartrack.setValue(50);
            JOptionPane.showMessageDialog(null,"Depend on the file this step takes several minutes. \n5000000 lines (rmsk file) take 20 minutes.\n You can check the state in the progess bar situated in the current windows.","warning",JOptionPane.WARNING_MESSAGE);
            if (genetrackcheck.isSelected()){
                loaddata_obj.loadtrackgene( namtext.getText(),filtext.getText(), spetext.getText());
            }else{
                loaddata_obj.loadtrack( namtext.getText(),filtext.getText(), chrtext.getText(), spetext.getText(), skip);
            }
            bartrack.setValue(100);
//            setVisible(false);
            
         

        } catch (IOException ex) {
            Logger.getLogger(LoaNewTraFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoaNewTraFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error: copy this error and send to lorena.pantano@crg.es: "+ex);
        }


        }
        };
        nThread = new Thread(view);
        nThread.start();

            
        
    }//GEN-LAST:event_LoaButActionPerformed

    private void CloButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloButActionPerformed
        // TODO add your handling code here:
        if (nThread!=null){

            if (nThread.isAlive()){
              Object[] options = {"Yes","No"};

              int n = JOptionPane.showOptionDialog(null,"Do you want to cancel the process?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
              if (n==1){


               }else{
                    nThread.stop();
                    dispose();
               }
              }else{
                dispose();
              }
        
        }else{
             dispose();
        }
        
    }//GEN-LAST:event_CloButActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select file containing the data of the track(should be file with columns separated by TAB character");
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give a name to the track.");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give the specie ID of the data. human=>hsa");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give the number of column that contain the 'chromosome' information.");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give the number of column that contain the 'end' information.");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give the number of column that contain the 'start' information.");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give the number of column that contain the 'strand' information.");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Give the number of column that contain the 'name/subclass' information.");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"skip the first line if the file has a header line.");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void namtextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namtextFocusLost
        // TODO add your handling code here:
        if (namtext.getText().length()>15){
            JOptionPane.showMessageDialog(null,"The name is longer than 15 characters, please reduce the size.");
            namtext.setText("");
        }
        Pattern p = Pattern.compile("\\W");
        Matcher m = p.matcher(namtext.getText());

        if (m.find() ){
            JOptionPane.showMessageDialog(null,"The name contains non alpha-numerical characters. /nOnly letters and numbers are allowed.");
            namtext.setText("");
        }
    }//GEN-LAST:event_namtextFocusLost

    private void nametextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nametextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nametextActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void genetrackcheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genetrackcheckMouseClicked
        // TODO add your handling code here:
        if (genetrackcheck.isSelected()){
            chrtext.setEnabled(false);
            endtext.setEnabled(false);
            sttext.setEnabled(false);
            starttext.setEnabled(false);
            nametext.setEnabled(false);
        }else{
            chrtext.setEnabled(true);
            endtext.setEnabled(true);
            sttext.setEnabled(true);
            starttext.setEnabled(true);
            nametext.setEnabled(true);
        }
    }//GEN-LAST:event_genetrackcheckMouseClicked

    private void sttextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sttextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sttextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloBut;
    private javax.swing.JButton LoaBut;
    private javax.swing.JProgressBar bartrack;
    public static javax.swing.JTextField chrtext;
    public static javax.swing.JTextField endtext;
    private javax.swing.JTextField filtext;
    private javax.swing.JCheckBox genetrackcheck;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField nametext;
    private javax.swing.JTextField namtext;
    private javax.swing.JCheckBox skicheck;
    private javax.swing.JTextField spetext;
    public static javax.swing.JTextField starttext;
    public static javax.swing.JTextField sttext;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
