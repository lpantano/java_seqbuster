/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoaNewGenFrame.java
 *
 * Created on Apr 27, 2010, 6:30:17 PM
 */

package seqbuster;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author lpantano
 */
public class CusAlgFrame extends javax.swing.JDialog {

    /** Creates new form LoaNewGenFrame */
    public CusAlgFrame() throws MalformedURLException, IOException {
        //super(parent);
        initComponents();
        bargen.setValue(0);
        bargen.setStringPainted(true);
        Point  p= SeqBusterApp.getApplication().getMainFrame().getLocation();
        this.setLocation(p.x+500, p.y+100);
        File htmlFile=new File("html/cusalgimg.html");
        URL manualurl=htmlFile.toURL();
        
        editpanel.setPage(manualurl);
        seefile("test/custom.ann");
        //editpanel.setText("http://hgdownload.cse.ucsc.edu/goldenPath/hg19/chromosomes/");
    }

   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadbut = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filtext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chrtext = new javax.swing.JTextField();
        sttext = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        bargen = new javax.swing.JProgressBar();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        starttext = new javax.swing.JTextField();
        endtext = new javax.swing.JTextField();
        seqtext = new javax.swing.JTextField();
        mmtext = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        gaptext = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        editpanel = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spet = new javax.swing.JTextField();
        gent = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(CusAlgFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadbut.setText(resourceMap.getString("loadbut.text")); // NOI18N
        loadbut.setName("loadbut"); // NOI18N
        loadbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadbutActionPerformed(evt);
            }
        });
        getContentPane().add(loadbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 100, -1));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 100, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        filtext.setText(resourceMap.getString("filtext.text")); // NOI18N
        filtext.setName("filtext"); // NOI18N
        filtext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtextMouseClicked(evt);
            }
        });
        getContentPane().add(filtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 220, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        chrtext.setText(resourceMap.getString("chrtext.text")); // NOI18N
        chrtext.setName("chrtext"); // NOI18N
        getContentPane().add(chrtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 40, -1));

        sttext.setText(resourceMap.getString("sttext.text")); // NOI18N
        sttext.setName("sttext"); // NOI18N
        getContentPane().add(sttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 40, -1));

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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 20, 20));

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
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 20, 20));

        bargen.setName("bargen"); // NOI18N
        getContentPane().add(bargen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 210, -1));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 20, 20));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        starttext.setText(resourceMap.getString("starttext.text")); // NOI18N
        starttext.setName("starttext"); // NOI18N
        getContentPane().add(starttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 40, -1));

        endtext.setText(resourceMap.getString("endtext.text")); // NOI18N
        endtext.setName("endtext"); // NOI18N
        getContentPane().add(endtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 40, -1));

        seqtext.setText(resourceMap.getString("seqtext.text")); // NOI18N
        seqtext.setName("seqtext"); // NOI18N
        getContentPane().add(seqtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 40, -1));

        mmtext.setText(resourceMap.getString("mmtext.text")); // NOI18N
        mmtext.setName("mmtext"); // NOI18N
        getContentPane().add(mmtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 40, -1));

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));

        gaptext.setText(resourceMap.getString("gaptext.text")); // NOI18N
        gaptext.setName("gaptext"); // NOI18N
        getContentPane().add(gaptext, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 40, -1));

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
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 20, 20));

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
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 20, 20));

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
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 20, 20));

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
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 20, 20));

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
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 20, 20));

        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 320, 10));

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
        table.setName("table"); // NOI18N
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 570, 80));

        jLabel13.setBackground(resourceMap.getColor("jLabel13.background")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 570, -1));

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        editpanel.setName("editpanel"); // NOI18N
        jScrollPane2.setViewportView(editpanel);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 260, 200));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setEnabled(false);
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 70, -1));

        spet.setText(resourceMap.getString("spet.text")); // NOI18N
        spet.setName("spet"); // NOI18N
        getContentPane().add(spet, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 110, -1));

        gent.setText(resourceMap.getString("gent.text")); // NOI18N
        gent.setEnabled(false);
        gent.setName("gent"); // NOI18N
        getContentPane().add(gent, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 110, -1));

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 130, 40));

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 130, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadbutActionPerformed
        // TODO add your handling code here:
        
        Runnable view = new Runnable() {

        public void run() {
        bargen.setValue(50);

        
        
            //parsecusalg(String file,String expname,String gen,String spe);
            String file=filtext.getText();
            String expname = (String)NonMiRNAFrame.ExpList.getSelectedItem();
            String db = gent.getText();
            String spe = spet.getText();
            cluster clu= new cluster();
                try {
                    int num=clu.parsecusalg(file, expname, db, spe,Integer.parseInt(chrtext.getText()),Integer.parseInt(starttext.getText()),Integer.parseInt(endtext.getText()),Integer.parseInt(sttext.getText()),Integer.parseInt(seqtext.getText()),Integer.parseInt(mmtext.getText()),Integer.parseInt(gaptext.getText()));
                    //String file,String expname,String gen,String spe,int chrin, int startin,int endin,int stin,int seqin, int idin, int mmin, int gapin,int startseqin, int endseqin
                    HelpDesk.addinfo("Number of pre-usRNAs mapped: "+num+"\n");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CusAlgFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                } catch (IOException ex) {
                    Logger.getLogger(CusAlgFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                } catch (SQLException ex) {
                    Logger.getLogger(CusAlgFrame.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
            
            clu=null;
            bargen.setValue(100);
            dispose();
        }
        };
        Thread nThread = new Thread(view);
        nThread.start();

    }//GEN-LAST:event_loadbutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the chromosome information.");
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the start information.");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the end information.");

    }//GEN-LAST:event_jButton7ActionPerformed

    private void filtextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtextMouseClicked
        // TODO add your handling code here:
        JFileChooser c=new JFileChooser(filtext.getText());
        String filename;

        int rVal=c.showOpenDialog(this);
        if (rVal==JFileChooser.APPROVE_OPTION){
            filename=c.getSelectedFile().toString();
            filtext.setText(filename);

            seefile(filename);

            JOptionPane.showMessageDialog(null,"You can see a line of your file in the above table to select \nthe corresponding columns in the below parameters. ");
        }
}//GEN-LAST:event_filtextMouseClicked
    private void seefile(String filename){
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

    }


    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the strand information.");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the small RNA sequence(target sequence) information.");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the mismatch information.");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the column with the gap information.");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Select the file that contains the mapping information (should be tab delimited file.)");
    }//GEN-LAST:event_jButton13ActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bargen;
    private javax.swing.JTextField chrtext;
    private javax.swing.JEditorPane editpanel;
    private javax.swing.JTextField endtext;
    private javax.swing.JTextField filtext;
    private javax.swing.JTextField gaptext;
    private javax.swing.JTextField gent;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loadbut;
    private javax.swing.JTextField mmtext;
    private javax.swing.JTextField seqtext;
    private javax.swing.JTextField spet;
    private javax.swing.JTextField starttext;
    private javax.swing.JTextField sttext;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
