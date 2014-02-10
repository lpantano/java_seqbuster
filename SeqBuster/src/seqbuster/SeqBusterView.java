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


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

/**
 * The application's main frame.
 */
public class SeqBusterView extends FrameView {

    int loadpro=0;


    public SeqBusterView(SingleFrameApplication app) throws SQLException, MalformedURLException, IOException, InterruptedException {
        super(app);

        initComponents();
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        //mainPanel.setSize(500,300);
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        
        

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    
                    
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    
                }
            }
        });

        
        File file = new File("html/logo.png");
        Image img = ImageIO.read(file);
        getFrame().setIconImage(img);
        getFrame().setTitle("SeqBuster");
        
        String fileoptions="seqbuster.op";
        tools check=new tools ();
        boolean Eoptions=check.fileexist(fileoptions);
        boolean Einstalled=check.fileexist("installed");
//        JFrame mainFrame = SeqBuster20App.getApplication().getMainFrame();
        JFrame help_obj=new HelpDesk();
        help_obj.setLocation(dim.width/2-350, 50);
        help_obj.setVisible(true);
//        JEditorPane editorPane = new JEditorPane();
//        Tutorial.tut.setEditable(false);
        
        Runnable view = new Runnable() {

            public void run() {
            JFrame tut_obj;
                try {
                    
                    tut_obj = new Tutorial();
                    tut_obj.setLocation(dim.width/2+200, 0);
                    tut_obj.setVisible(true);
                    
                } catch (IOException ex) {
                    Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }

            }
        };

        Thread nThread = new Thread(view);
        nThread.start();
        
        if (Eoptions & Einstalled){
            
            check.readparameters(fileoptions);
//            pathdb.setText(check.dbpre);
            pathblast.setText(check.blastpre);
            pathfold.setText(check.foldpre);
            pathout.setText(check.outpre);
            ps2pdfText.setText(check.ps2pdfpre);
            UseMysText.setText(check.user);
            HosMysText.setText(check.host);
            PswMysText.setText(check.passw);
            PorMysText.setText(check.port);
            String host=SeqBusterView.HosMysText.getText();
            String user = SeqBusterView.UseMysText.getText();
            String pssw = SeqBusterView.PswMysText.getText();
            Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
            Statement statment =  con.createStatement();
            String readsamples = "select `name` from `project`";
            ResultSet rs = statment.executeQuery(readsamples);
            while (rs.next()) {
                nameproject.addItem(rs.getString(1));

            }
            rs.close();
            statment.close();
            con.close();
        }

         
       
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
            aboutBox = new SeqBusterAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        SeqBusterApp.getApplication().show(aboutBox);
    }


    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        pannel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        LoaProBut = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        upRawBut = new javax.swing.JButton();
        mirAnnBut = new javax.swing.JButton();
        CusAnnBut = new javax.swing.JButton();
        AnaBut = new javax.swing.JButton();
        CreProBut = new javax.swing.JButton();
        loaprotext = new javax.swing.JTextField();
        upTriBut = new javax.swing.JButton();
        nonMirRNABut = new javax.swing.JButton();
        usRNAAnaBut = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        ManBut = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        nameproject = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CloseApp = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        pathblast = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        pathout = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        pathfold = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ps2pdfText = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        CheMysBut = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        UseMysText = new javax.swing.JTextField();
        HosMysText = new javax.swing.JTextField();
        PswMysText = new javax.swing.JPasswordField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        PorMysText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cheDB = new javax.swing.JButton();
        cheR = new javax.swing.JButton();
        probar = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        reinstall = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(622, 515));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pannel.setMaximumSize(null);
        pannel.setName("pannel"); // NOI18N
        pannel.setPreferredSize(new java.awt.Dimension(584, 510));
        pannel.setRequestFocusEnabled(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(680, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(680, 450));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 450));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(SeqBusterView.class);
        LoaProBut.setText(resourceMap.getString("LoaProBut.text")); // NOI18N
        LoaProBut.setName("LoaProBut"); // NOI18N
        LoaProBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoaProButActionPerformed(evt);
            }
        });
        jPanel1.add(LoaProBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 100, -1));

        jSeparator1.setName("jSeparator1"); // NOI18N
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 20));

        upRawBut.setForeground(resourceMap.getColor("upRawBut.foreground")); // NOI18N
        upRawBut.setText(resourceMap.getString("upRawBut.text")); // NOI18N
        upRawBut.setEnabled(false);
        upRawBut.setName("upRawBut"); // NOI18N
        upRawBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upRawButActionPerformed(evt);
            }
        });
        jPanel1.add(upRawBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 120, 50));

        mirAnnBut.setForeground(resourceMap.getColor("mirAnnBut.foreground")); // NOI18N
        mirAnnBut.setText(resourceMap.getString("mirAnnBut.text")); // NOI18N
        mirAnnBut.setEnabled(false);
        mirAnnBut.setName("mirAnnBut"); // NOI18N
        mirAnnBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mirAnnButActionPerformed(evt);
            }
        });
        jPanel1.add(mirAnnBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 140, 50));

        CusAnnBut.setForeground(resourceMap.getColor("CusAnnBut.foreground")); // NOI18N
        CusAnnBut.setText(resourceMap.getString("CusAnnBut.text")); // NOI18N
        CusAnnBut.setEnabled(false);
        CusAnnBut.setName("CusAnnBut"); // NOI18N
        CusAnnBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusAnnButActionPerformed(evt);
            }
        });
        jPanel1.add(CusAnnBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 140, 50));

        AnaBut.setForeground(resourceMap.getColor("AnaBut.foreground")); // NOI18N
        AnaBut.setText(resourceMap.getString("AnaBut.text")); // NOI18N
        AnaBut.setEnabled(false);
        AnaBut.setName("AnaBut"); // NOI18N
        AnaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnaButActionPerformed(evt);
            }
        });
        jPanel1.add(AnaBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 150, 50));

        CreProBut.setText(resourceMap.getString("CreProBut.text")); // NOI18N
        CreProBut.setName("CreProBut"); // NOI18N
        CreProBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreProButActionPerformed(evt);
            }
        });
        jPanel1.add(CreProBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 110, -1));

        loaprotext.setBackground(resourceMap.getColor("loaprotext.background")); // NOI18N
        loaprotext.setEditable(false);
        loaprotext.setText(resourceMap.getString("loaprotext.text")); // NOI18N
        loaprotext.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        loaprotext.setDisabledTextColor(resourceMap.getColor("loaprotext.disabledTextColor")); // NOI18N
        loaprotext.setName("loaprotext"); // NOI18N
        jPanel1.add(loaprotext, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, -1));

        upTriBut.setForeground(resourceMap.getColor("UpTriReaBut.foreground")); // NOI18N
        upTriBut.setText(resourceMap.getString("UpTriReaBut.text")); // NOI18N
        upTriBut.setEnabled(false);
        upTriBut.setName("UpTriReaBut"); // NOI18N
        upTriBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upTriButActionPerformed(evt);
            }
        });
        jPanel1.add(upTriBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 120, 50));

        nonMirRNABut.setForeground(resourceMap.getColor("nonMirRNABut.foreground")); // NOI18N
        nonMirRNABut.setText(resourceMap.getString("nonMirRNABut.text")); // NOI18N
        nonMirRNABut.setEnabled(false);
        nonMirRNABut.setName("nonMirRNABut"); // NOI18N
        nonMirRNABut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonMirRNAButActionPerformed(evt);
            }
        });
        jPanel1.add(nonMirRNABut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 140, 50));

        usRNAAnaBut.setForeground(resourceMap.getColor("usRNAAnaBut.foreground")); // NOI18N
        usRNAAnaBut.setText(resourceMap.getString("usRNAAnaBut.text")); // NOI18N
        usRNAAnaBut.setEnabled(false);
        usRNAAnaBut.setName("usRNAAnaBut"); // NOI18N
        usRNAAnaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usRNAAnaButActionPerformed(evt);
            }
        });
        jPanel1.add(usRNAAnaBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 150, 50));

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
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 20, 20));

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
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 20, 20));

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
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 20, 20));

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
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 20, 20));

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
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 20, 20));

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
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 20, 20));

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
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 20, 20));

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
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 20, 20));

        ManBut.setForeground(resourceMap.getColor("ManBut.foreground")); // NOI18N
        ManBut.setText(resourceMap.getString("ManBut.text")); // NOI18N
        ManBut.setName("ManBut"); // NOI18N
        ManBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManButActionPerformed(evt);
            }
        });
        jPanel1.add(ManBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 120, 50));

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
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 20, 20));

        nameproject.setName("nameproject"); // NOI18N
        jPanel1.add(nameproject, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 30));

        jLabel6.setBackground(resourceMap.getColor("jLabel6.background")); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 140, 50));

        jLabel7.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 150, 50));

        jLabel8.setBackground(resourceMap.getColor("jLabel8.background")); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 140, 50));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getActionMap(SeqBusterView.class, this);
        CloseApp.setAction(actionMap.get("quit")); // NOI18N
        CloseApp.setText(resourceMap.getString("CloseApp.text")); // NOI18N
        CloseApp.setName("CloseApp"); // NOI18N
        jPanel1.add(CloseApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 350, -1));

        jLabel13.setBackground(resourceMap.getColor("jLabel13.background")); // NOI18N
        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 380, 60));

        pannel.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setMaximumSize(new java.awt.Dimension(592, 450));
        jPanel2.setMinimumSize(new java.awt.Dimension(592, 450));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(592, 450));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 33, 375, 50));

        jLabel37.setText(resourceMap.getString("jLabel37.text")); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 107, -1, -1));

        pathblast.setText(resourceMap.getString("pathblast.text")); // NOI18N
        pathblast.setName("pathblast"); // NOI18N
        pathblast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pathblastMouseClicked(evt);
            }
        });
        jPanel2.add(pathblast, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 101, 228, -1));

        jLabel45.setText(resourceMap.getString("jLabel45.text")); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        jPanel2.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 375, 50));

        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 289, -1, -1));

        pathout.setText(resourceMap.getString("pathout.text")); // NOI18N
        pathout.setName("pathout"); // NOI18N
        pathout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pathoutMouseClicked(evt);
            }
        });
        jPanel2.add(pathout, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 283, 228, -1));

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
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 106, 20, 20));

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
        jPanel2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 288, 20, 20));

        jLabel9.setBackground(resourceMap.getColor("jLabel9.background")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setOpaque(true);
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 345, 428, -1));

        pathfold.setText(resourceMap.getString("pathfold.text")); // NOI18N
        pathfold.setName("pathfold"); // NOI18N
        pathfold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pathfoldMouseClicked(evt);
            }
        });
        jPanel2.add(pathfold, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 165, 228, -1));

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
        jPanel2.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 170, 20, 20));

        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 171, -1, -1));

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 217, 87, -1));

        ps2pdfText.setText(resourceMap.getString("ps2pdfText.text")); // NOI18N
        ps2pdfText.setName("ps2pdfText"); // NOI18N
        ps2pdfText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ps2pdfTextMouseClicked(evt);
            }
        });
        ps2pdfText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ps2pdfTextActionPerformed(evt);
            }
        });
        jPanel2.add(ps2pdfText, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 211, 228, -1));

        pannel.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel4.setMaximumSize(new java.awt.Dimension(592, 450));
        jPanel4.setMinimumSize(new java.awt.Dimension(592, 450));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(592, 450));

        CheMysBut.setText(resourceMap.getString("CheMysBut.text")); // NOI18N
        CheMysBut.setName("CheMysBut"); // NOI18N
        CheMysBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheMysButActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        UseMysText.setText(resourceMap.getString("UseMysText.text")); // NOI18N
        UseMysText.setName("UseMysText"); // NOI18N

        HosMysText.setText(resourceMap.getString("HosMysText.text")); // NOI18N
        HosMysText.setName("HosMysText"); // NOI18N

        PswMysText.setText(resourceMap.getString("PswMysText.text")); // NOI18N
        PswMysText.setName("PswMysText"); // NOI18N

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

        jButton18.setBackground(resourceMap.getColor("jButton18.background")); // NOI18N
        jButton18.setText(resourceMap.getString("jButton18.text")); // NOI18N
        jButton18.setBorderPainted(false);
        jButton18.setFocusable(false);
        jButton18.setName("jButton18"); // NOI18N
        jButton18.setOpaque(true);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(resourceMap.getColor("jButton19.background")); // NOI18N
        jButton19.setText(resourceMap.getString("jButton19.text")); // NOI18N
        jButton19.setBorderPainted(false);
        jButton19.setFocusable(false);
        jButton19.setName("jButton19"); // NOI18N
        jButton19.setOpaque(true);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        PorMysText.setText(resourceMap.getString("PorMysText.text")); // NOI18N
        PorMysText.setName("PorMysText"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jButton20.setBackground(resourceMap.getColor("jButton20.background")); // NOI18N
        jButton20.setText(resourceMap.getString("jButton20.text")); // NOI18N
        jButton20.setBorderPainted(false);
        jButton20.setFocusable(false);
        jButton20.setName("jButton20"); // NOI18N
        jButton20.setOpaque(true);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PorMysText, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UseMysText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(PswMysText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(HosMysText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(CheMysBut))
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UseMysText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(PswMysText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheMysBut))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(HosMysText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PorMysText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        pannel.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel3.setMaximumSize(new java.awt.Dimension(592, 450));
        jPanel3.setMinimumSize(new java.awt.Dimension(592, 450));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(592, 450));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        cheDB.setText(resourceMap.getString("cheDB.text")); // NOI18N
        cheDB.setEnabled(false);
        cheDB.setName("cheDB"); // NOI18N
        cheDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheDBActionPerformed(evt);
            }
        });

        cheR.setText(resourceMap.getString("cheR.text")); // NOI18N
        cheR.setEnabled(false);
        cheR.setName("cheR"); // NOI18N
        cheR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheRActionPerformed(evt);
            }
        });

        probar.setName("probar"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        reinstall.setText(resourceMap.getString("reinstall.text")); // NOI18N
        reinstall.setName("reinstall"); // NOI18N
        reinstall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reinstallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(probar, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reinstall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cheR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cheDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cheDB))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(cheR))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(reinstall))
                .addGap(97, 97, 97)
                .addComponent(probar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pannel.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        mainPanel.add(pannel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 460));

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void pathblastMouseClicked(MouseEvent evt) {//GEN-FIRST:event_pathblastMouseClicked
        JFileChooser c=new JFileChooser(pathblast.getText());
        String filename;
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        tools check=new tools();
        int rVal=c.showOpenDialog(mainPanel);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            boolean Epathdb=check.fileexist(filename);
            if (Epathdb){
                pathblast.setText(filename);
                check.readparameters("seqbuster.op");
                check.blastpre=filename;
                check.saveparameters("seqbuster.op");
                
            }else{
                JOptionPane.showMessageDialog(null,"No correct folder. Please try again!\n");
            }

        }
}//GEN-LAST:event_pathblastMouseClicked

    private void pathoutMouseClicked(MouseEvent evt) {//GEN-FIRST:event_pathoutMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(pathout.getText());
        String filename;
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        tools check=new tools();
        int rVal=c.showOpenDialog(mainPanel);
         
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            boolean Epathdb=check.fileexist(filename);
            if (Epathdb){
                boolean success = (new File(filename+"/testpro")).mkdir();
                (new File(filename+"/testpro"+"/miRNAscripts")).mkdir();
                if (!success) {
                    tools.showcerror("error creating testpro folder, or maybe it exists already.\n You can not run the tutorial.");
                }else{
                    tools.showinfo("The test project 'testpro' to run the tutorial has been created.");
                }
                pathout.setText(filename);
                check.readparameters("seqbuster.op");
                check.outpre=filename;
                check.saveparameters("seqbuster.op");
            }else{
                tools.showcerror("No correct folder. Please try again!\n");
                
            }
        }
}//GEN-LAST:event_pathoutMouseClicked

    private void upRawButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_upRawButActionPerformed
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        AdapRecFrame_obj = new AdapRecFrame(mainFrame);
        AdapRecFrame_obj.setLocationRelativeTo(mainFrame);
        AdapRecFrame_obj.setVisible(true);
        //SeqBuster20App.getApplication().show(AdapRecFrame_obj);
    }//GEN-LAST:event_upRawButActionPerformed

    private void CusAnnButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_CusAnnButActionPerformed
        // TODO add your handling code here:
        
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            CusAnnFrame_obj = new CusAnnFrame(mainFrame);
            CusAnnFrame_obj.setLocationRelativeTo(mainFrame);
            CusAnnFrame_obj.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_CusAnnButActionPerformed

    private void AnaButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_AnaButActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null,"Open 'R' and run this command:' source("+System.getProperty("user.dir")+"/Rscripts/SeqBuster.R) '");
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        JDialog miRNApackage_obj;
        try {
            miRNApackage_obj = new miRNAPackages(mainFrame);
            miRNApackage_obj.setLocationRelativeTo(mainFrame);
            miRNApackage_obj.setVisible(true);
            //BrowDEmFrame viewresult = new BrowDEmFrame();
            //viewresult.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_AnaButActionPerformed

    private void CreProButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_CreProButActionPerformed
        // TODO add your handling code here:
        
        String namevar = JOptionPane.showInputDialog("Write a name for the new project: (only alphanumeric characters. max 15)" );
        //namevar="";
        String pathoutvar=pathout.getText();
        //String namevar=nameproject.getText();
        //System.out.println("namevar: "+namevar);
        //check if exist dir nameva in direcotry working
        if (namevar!=null){
        tools check=new tools();
        Pattern p = Pattern.compile("\\W");
        Matcher m = p.matcher(namevar);

        boolean newcheck=check.fileexist(pathoutvar+"/"+namevar);
        if (newcheck){
            //exist project, change name
             tools.showcerror("This name is already in used. Try with other name.");

        }else if(namevar.length()>15) {

            tools.showcerror("The name is longer than 15 characters, please reduce the size.");

        }else if (m.find()){
            tools.showcerror("The name contains non alpha-numerical characters. /nOnly letters and numbers are allowed.");
        }else{
            //create folder and unable text file
            //write name project in label
            boolean success = (new File(pathoutvar+"/"+namevar)).mkdir();
            (new File(pathoutvar+"/"+namevar+"/miRNAscripts")).mkdir();
            if (success) {
               
               Connection con  = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                    Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    //create db mysql

                    String host=HosMysText.getText();
                    String db="seqbuster";
                    String user=UseMysText.getText();
                    String pssw=PswMysText.getText();
                    String port=PorMysText.getText();
                    if (!port.matches("")){host=host+":"+port;}
                    con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/"+db,user,pssw);
                    Statement s = (Statement) con.createStatement();
                    s.executeUpdate ("CREATE DATABASE IF NOT EXISTS "+namevar);
                    s.executeUpdate ("use "+namevar);
                    //create ann and read table
                    //String anntable = "create table IF NOT EXISTS `ann` (`id` BIGINT UNSIGNED NOT NULL ,`code`VARCHAR( 50 ) NOT NULL PRIMARY KEY ,`chr` VARCHAR( 20 ) NOT NULL ,`start` INT UNSIGNED NOT NULL ,`end` INT UNSIGNED NOT NULL ,`strand` CHAR( 1 ) NOT NULL ,`sp` VARCHAR( 10 ) NOT NULL ,`mut` VARCHAR( 10 ) NOT NULL ,`addition3` VARCHAR( 10 ) NOT NULL,`DB` VARCHAR( 20 ) NOT NULL) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
                    //s.executeUpdate(anntable);
                    //String readtable="create table IF NOT EXISTS `reads` (`id` BIGINT UNSIGNED AUTO_INCREMENT  ,`seq` VARCHAR( 50 ) NOT NULL UNIQUE,PRIMARY KEY (id)) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci";
                    //s.executeUpdate(readtable);
                    //create experiment and results table
                    String exptable="CREATE TABLE IF NOT EXISTS `experiments` (`name` varchar(50) collate utf8_unicode_ci NOT NULL, `project` varchar(20) collate utf8_unicode_ci NOT NULL,`description` text collate utf8_unicode_ci NOT NULL,  `type` varchar(50) collate utf8_unicode_ci NOT NULL,  UNIQUE KEY `name` (`name`),  KEY `project` (`project`,`type`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
                    s.executeUpdate(exptable);
                    String restable="CREATE TABLE IF NOT EXISTS `results` (  `name` varchar(50) collate utf8_unicode_ci NOT NULL,  `project` varchar(20) collate utf8_unicode_ci NOT NULL,  `type` enum('exp','diff') collate utf8_unicode_ci NOT NULL,  `date` datetime default NULL,  UNIQUE KEY `name` (`name`),  KEY `project` (`project`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
                    s.executeUpdate(restable);
                    String insertpro="insert into `seqbuster`.`project` values ('"+namevar+"',NOW())";
                    s.executeUpdate(insertpro);
                    s.close();
                    con.close();
                    nameproject.addItem(namevar);
                    nameproject.repaint();

                    JOptionPane.showMessageDialog(null,"Now you can upload to the project! If you have raw data (adapter not trimmed),\n go to 'Upload Raw Data' button, but if you have the sequences \n without adapter go directly to 'Upload trimmed reads.' button");
                } catch (SQLException ex) {
                    Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);

                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                    boolean successdel = (new File(pathoutvar+"/"+namevar)).delete();
                }


            }else{
               tools.showcerror("There is a problem creating directory: " + pathoutvar+"/"+namevar+".\nPlease check permission or maybe exists already.");
               
            }

            }
        }
    }//GEN-LAST:event_CreProButActionPerformed

    private void LoaProButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_LoaProButActionPerformed
        String pathoutvar=pathout.getText();
        String namevar=(String)nameproject.getSelectedItem();
        tools check=new tools();
        boolean newcheck=check.fileexist(pathoutvar+"/"+namevar);
        if (newcheck){

             loaprotext.setText(namevar);
//             nameproject.setText("");
             loaprotext.setBackground(new Color(141,182,205));
             loadpro=1;
             AnaBut.setEnabled(true);
             AnaBut.setForeground(Color.black);
             //CusAnnBut.setEnabled(true);
            //CusAnnBut.setForeground(Color.black);
             mirAnnBut.setEnabled(true);
             mirAnnBut.setForeground(Color.black);
             nonMirRNABut.setEnabled(true);
             nonMirRNABut.setForeground(Color.black);
             usRNAAnaBut.setEnabled(true);
             usRNAAnaBut.setForeground(Color.black);
             upRawBut.setEnabled(true);
             upRawBut.setForeground(Color.black);
             upTriBut.setEnabled(true);
             upTriBut.setForeground(Color.black);
             ManBut.setEnabled(true);
             ManBut.setForeground(Color.black);
         }else{
            loadpro=0;
            AnaBut.setEnabled(false);
             AnaBut.setForeground(Color.GRAY);
//             CusAnnBut.setEnabled(false);
//             CusAnnBut.setForeground(Color.GRAY);
             mirAnnBut.setEnabled(false);
             mirAnnBut.setForeground(Color.GRAY);
             nonMirRNABut.setEnabled(false);
             nonMirRNABut.setForeground(Color.GRAY);
             usRNAAnaBut.setEnabled(false);
             usRNAAnaBut.setForeground(Color.GRAY);
             upRawBut.setEnabled(false);
             upRawBut.setForeground(Color.GRAY);
             upTriBut.setEnabled(false);
             upTriBut.setForeground(Color.GRAY);
             ManBut.setEnabled(false);
             ManBut.setForeground(Color.GRAY);
            JOptionPane.showMessageDialog(null,"this project doesn't exist. Check preference parameters or check the name project.");
            loaprotext.setBackground(new Color(204,51,51));
         }

    }//GEN-LAST:event_LoaProButActionPerformed

    private void CheMysButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_CheMysButActionPerformed
   
        try {
            tools check = new tools();
            String host=HosMysText.getText();
            //String db=ProMysText.getText();
            String user=UseMysText.getText();
            String pssw=PswMysText.getText();
            String port=PorMysText.getText();

            if (!port.matches("")){host=host+":"+port;}
            Connection conexion = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/mysql",user,pssw);
            conexion.close();
            JOptionPane.showMessageDialog(null,"You can connect to MySQL. TEST OK. \nNow,  go to 'Preferences' to finish the configuration.");
            pannel.setSelectedIndex(1);
            check.readparameters("seqbuster.op");
            check.user=user;
            check.host=host;
            check.passw=pssw;
            check.port=port;
            check.saveparameters("seqbuster.op");

        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        }
    }//GEN-LAST:event_CheMysButActionPerformed

    private void upTriButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_upTriButActionPerformed
        // TODO add your handling code here:
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        UpTriReaFrame_obj = new UpTriReaFrame(mainFrame);
        UpTriReaFrame_obj.setLocationRelativeTo(mainFrame);
        UpTriReaFrame_obj.setVisible(true);
    }//GEN-LAST:event_upTriButActionPerformed

    private void mirAnnButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_mirAnnButActionPerformed
        // TODO add your handling code here:
        //int ann=map.readseq("temp/input.fa","DB/mbhairpinRNA.db","DB/miRNA.str");
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            miRannFrame_obj = new chooseDB();
            miRannFrame_obj.setLocationRelativeTo(mainFrame);
            miRannFrame_obj.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        }
    }//GEN-LAST:event_mirAnnButActionPerformed

    private void nonMirRNAButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_nonMirRNAButActionPerformed
           

        
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            detRNAFrame_obj = new NonMiRNAFrame(mainFrame);
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        }
        detRNAFrame_obj.setLocation(100,400);
        detRNAFrame_obj.setVisible(true);
    }//GEN-LAST:event_nonMirRNAButActionPerformed

    private void jButton4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"First, You have to create a project\n If you have a project, write the name in the text field \n and click in the load button. \n Now you can upload a new sample or to perform any analysis.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Add new samples here if you have sequences with the adapter!");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Add new samples here if you have sequences without the adapter!");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Detect miRNAs sequences in your samples.");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Detect other smallRNAs in the same way that miRNAs analisys does, but with custom small RNA database");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"detect other small RNA as described in (Lorena et al)");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Do all miRNAs analyses publish in (Lorena et al,2010)");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Do all non-miRNAs analyses publish in (Lorena et al,2011)");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Remove, reset or browser any sample in the project.");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void ManButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ManButActionPerformed
        // TODO add your handling code here:
        ManDatFrame data;
        JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            data = new ManDatFrame(mainFrame);
            data.setLocationRelativeTo(mainFrame);
            data.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ManDatFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        }
    }//GEN-LAST:event_ManButActionPerformed

    private void usRNAAnaButActionPerformed(ActionEvent evt) {//GEN-FIRST:event_usRNAAnaButActionPerformed
        // TODO add your handling code here:
            JFrame mainFrame = SeqBusterApp.getApplication().getMainFrame();
        try {
            anaRNAFrame_obj = new NonMiRNAanaFrame(mainFrame);
            anaRNAFrame_obj.setLocationRelativeTo(mainFrame);
            anaRNAFrame_obj.setVisible(true);
          
                
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        }

    }//GEN-LAST:event_usRNAAnaButActionPerformed

    private void updatedb() throws MalformedURLException, IOException{

        
    probar.setValue(0);
    probar.setStringPainted(true);
            URL url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/DB/mbhairpinRNA.db");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("DB/mbhairpinRNA.db");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){

                    fw.write(l+"\n");
                }
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }

            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/DB/miRNA.str");
            urlConnection = url.openConnection();
            urlConnection.connect();
            input = url.openStream();

            try {
                FileWriter fw = new FileWriter("DB/miRNA.str");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){

                    fw.write(l+"\n");
                }
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }

    }

    private void updater () throws SQLException{

        URL url;
        String host=HosMysText.getText();
        String user=UseMysText.getText();
        String pssw=PswMysText.getText();
        String port=SeqBusterView.PorMysText.getText();
        if (!port.matches("")){host=host+":"+port;}
        Connection con = (Connection) DriverManager.getConnection ("jdbc:mysql://"+host+"/seqbuster",user,pssw);
        Statement statment = (Statement) con.createStatement();

        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/scriptGUI.sql");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("Rscripts/script.sql");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){
                    statment.executeUpdate(l);
                    fw.write(l+"\n");
                }
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }



        } catch (Exception ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);


        }
        //seqbuster.R
        probar.setValue(75);
        probar.repaint();
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/SeqBuster.R");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("Rscripts/SeqBuster.R");
                FileWriter fw2 = new FileWriter("Rscripts/R/db.R");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                fw.write("localhost<-\""+host+"\"\n");
                fw.write("user<-\""+user+"\"\n");
                fw.write("db<-\"seqbuster\"\n");
                fw.write("psswd<-\""+pssw+"\"\n");
                fw2.write("localhost<-\""+host+"\"\n");
                fw2.write("username<-\""+user+"\"\n");
                fw2.write("dbname<-\"seqbuster\"\n");
                fw2.write("psswd<-\""+pssw+"\"\n");
                while ((l=bufferedReader.readLine())!=null){
                    //statment.executeUpdate(l);
                    fw.write(l+"\n");
                }
                fw.close();
                fw2.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }



        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
        }
        //functions scripts
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/R/list");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {

                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){

                    FileWriter fw = new FileWriter("Rscripts/R/"+l);
                    URL url2 = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/R/"+l);
                    URLConnection urlConnection2 = url2.openConnection();
                    urlConnection2.connect();
                    InputStream input2 = url2.openStream();
                    Reader reader2 = new InputStreamReader(input2);
                    BufferedReader bufferedReader2 = new BufferedReader(reader2);
                    String l2=null;
                    while ((l2=bufferedReader2.readLine())!=null){
                        fw.write(l2+"\n");

                    }
                    fw.close();
                }


            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }




        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
        }

        //guir scripts
        //functions scripts
        probar.setValue(90);
        probar.repaint();
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/GUIR/list");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {

                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
                while ((l=bufferedReader.readLine())!=null){

                    FileWriter fw = new FileWriter("Rscripts/GUIR/"+l);
                    URL url2 = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/GUIR/"+l);
                    URLConnection urlConnection2 = url2.openConnection();
                    urlConnection2.connect();
                    InputStream input2 = url2.openStream();
                    Reader reader2 = new InputStreamReader(input2);
                    BufferedReader bufferedReader2 = new BufferedReader(reader2);
                    String l2=null;
                    while ((l2=bufferedReader2.readLine())!=null){
                        fw.write(l2+"\n");

                    }
                    fw.close();
                }


            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }

            //guir scripts


        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
        }

        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/javaapp/date");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {
                FileWriter fw = new FileWriter("Rscripts/date");
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;

                while ((l=bufferedReader.readLine())!=null){
                    //statment.executeUpdate(l);
                    fw.write(l+"\n");
                }
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
            }



        } catch (Exception ex) {
            Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es : "+ex);
        }
        probar.setValue(100);
        statment.close();
        con.close();

    }

    private void cheDBActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cheDBActionPerformed
        // TODO add your handling code here:
        File file = new File("DB/date");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String current_date=null;
        try {
            fis = new FileInputStream(file);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            // dis.available() returns 0 if the file does not have more lines.
            while (dis.available() != 0) {

                // this statement reads the line from the file and print it to
                // the console.
                current_date=dis.readLine();
            }

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();

        } catch (FileNotFoundException ex) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        } catch (IOException ex) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
        }

        URL url = null;
        try {
            url = new URL("http://estivill_lab.crg.es/seqbuster/DB/date");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream input = url.openStream();

            try {

                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String l=null;
//                while ((l=bufferedReader.readLine())!=null){
                    //statment.executeUpdate(l);
                    l=bufferedReader.readLine();
                    if (!l.matches(current_date)){
                        Object[] options = {"Yes","No"};
                        int n = JOptionPane.showOptionDialog(null,"Do you want to update to version "+l,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                        if (n==0){
                            HelpDesk.helptext.setText("Updating database to version: "+l+"\n");
                            updatedb();
                            HelpDesk.helptext.append("finished at: "+new Date()+"\n");
                            Runtime.getRuntime().gc();
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"There is not update available.");
                    }
//                }


            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);
            }



        } catch (Exception ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Error!!!!.Copy this message and send to lorena.pantano@crg.es :"+ex);


        }



    }//GEN-LAST:event_cheDBActionPerformed

    private void cheRActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cheRActionPerformed
        Connection con;
        try {
            con = tools.docon("seqbuster");

        Statement statment = con.createStatement();
        statment.executeUpdate("truncate table `seqbuster`.`scriptsGUI`");
        
        BufferedReader in;
            try {
                in = new BufferedReader(new FileReader("Rscripts/script.sql"));

            try {

                String l="";
                while ((l=in.readLine())!=null){
                    statment.executeUpdate(l);

                }

                tools.showinfo("Updated!!!");
            } catch (IOException ex) {
                Logger.getLogger(FirstrunFrame.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
                tools.showerror(ex.getLocalizedMessage(),ex.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterView.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
    }//GEN-LAST:event_cheRActionPerformed

    private void jButton14ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the folder where Blast have been installed.\nOnly select the main folder: /usr/local/blast/bin");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the folder where all output will be created.\n For instance: /home/seqbuster");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton17ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Put the user to connect mysql. \nIf you have not created any, write: root.");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Put the passw to connect mysql. \nIf you have not created any,leave empty.");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the host where mysql is installed. \nIf you have no idea what is it, put: localhost.");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the port where mysql is installed. \nIf you have no idea what is it, leave empty.");
    }//GEN-LAST:event_jButton20ActionPerformed

    private void reinstallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reinstallActionPerformed
        
        updates.reinstall(probar);
    }//GEN-LAST:event_reinstallActionPerformed

    private void pathfoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathfoldMouseClicked
         
        JFileChooser c=new JFileChooser(pathblast.getText());
        String filename;
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        tools check=new tools();
        int rVal=c.showOpenDialog(mainPanel);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            boolean Epathdb=check.fileexist(filename);
            if (Epathdb){
                pathfold.setText(filename);
                check.readparameters("seqbuster.op");
                check.foldpre=filename;
                check.saveparameters("seqbuster.op");
                
            }else{
                JOptionPane.showMessageDialog(null,"No correct folder. Please try again!\n");
            }

        }
    }//GEN-LAST:event_pathfoldMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        tools.showinfo("Select folder where RNAfold binaries are installed.");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void ps2pdfTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ps2pdfTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ps2pdfTextActionPerformed

    private void ps2pdfTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ps2pdfTextMouseClicked
        JFileChooser c=new JFileChooser(pathblast.getText());
        String filename;
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        tools check=new tools();
        int rVal=c.showOpenDialog(mainPanel);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            boolean Epathdb=check.fileexist(filename);
            if (Epathdb){
                ps2pdfText.setText(filename);
                check.readparameters("seqbuster.op");
                check.ps2pdfpre=filename;
                check.saveparameters("seqbuster.op");

            }else{
                tools.showcerror("No correct folder. Please try again!\n");
            }

        }
    }//GEN-LAST:event_ps2pdfTextMouseClicked
    public void DoRuntime (String cmd) throws IOException {

       if (cmd.matches("")) {

         System.exit(-1);
           }

       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       process=null;
       runtime=null;


    }
  
    public static String project="none";
    public static String option="none";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnaBut;
    private javax.swing.JButton CheMysBut;
    private javax.swing.JButton CloseApp;
    private javax.swing.JButton CreProBut;
    private javax.swing.JButton CusAnnBut;
    public static javax.swing.JTextField HosMysText;
    private javax.swing.JButton LoaProBut;
    private javax.swing.JButton ManBut;
    public static javax.swing.JTextField PorMysText;
    public static javax.swing.JPasswordField PswMysText;
    public static javax.swing.JTextField UseMysText;
    private javax.swing.JButton cheDB;
    private javax.swing.JButton cheR;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextField loaprotext;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton mirAnnBut;
    public static javax.swing.JComboBox nameproject;
    private javax.swing.JButton nonMirRNABut;
    public static javax.swing.JTabbedPane pannel;
    public static javax.swing.JTextField pathblast;
    public static javax.swing.JTextField pathfold;
    public static javax.swing.JTextField pathout;
    private javax.swing.JProgressBar probar;
    public static javax.swing.JTextField ps2pdfText;
    private javax.swing.JButton reinstall;
    private javax.swing.JButton upRawBut;
    private javax.swing.JButton upTriBut;
    private javax.swing.JButton usRNAAnaBut;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private  int busyIconIndex = 0;
    public static JDialog AdapRecFrame_obj;
    public static JDialog UpTriReaFrame_obj;
    public static JDialog CusAnnFrame_obj;
    public static JFrame InitFrame_obj;
    public static JDialog miRannFrame_obj;
    public static JDialog  detRNAFrame_obj;
    public static JDialog  info_obj;
    public static JDialog anaRNAFrame_obj;
    public static JFrame ins_obj;
    public static JDialog check_obj;
    private JDialog aboutBox;
}
