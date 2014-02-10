/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package seqbuster;

//import java.awt.Container;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class HelpDesk extends javax.swing.JFrame {

    /** Creates new form HelpDesk */
    public HelpDesk() throws IOException {
        initComponents();
        File file = new File("lib/logoseqbuster.gif");
        Image img = ImageIO.read(file);
        this.setIconImage(img);
        this.setTitle("SeqBuster");
        this.setSize(585,390);
        //UIManager.put("ProgressBar.selectionBackground", Color.red);
        bar.setStringPainted(true);
        reload();
    }
    public static void addinfo (String msg){

        helptext.append(msg);
        helptext.setCaretPosition(helptext.getText().length() - 1);
        
        
    }
    public static void reload (){
        helptext.setText("");
        Runnable view = new Runnable() {

        public void run() {
            while (true){
                
                freememory.setText("Free memory: "+ Runtime.getRuntime().freeMemory()/1028/1028 +" Mb");
                totalmemory.setText("Total memory: "+ Runtime.getRuntime().totalMemory()/1028/1028 +" Mb");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HelpDesk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        };
        Thread nThread2 = new Thread(view);
        nThread2.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        helptext = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        bar = new javax.swing.JProgressBar();
        freememory = new javax.swing.JTextField();
        totalmemory = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(HelpDesk.class);
        setTitle(resourceMap.getString("Summary.title")); // NOI18N
        setName("Summary"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        helptext.setColumns(20);
        helptext.setLineWrap(true);
        helptext.setRows(5);
        helptext.setWrapStyleWord(true);
        helptext.setName("helptext"); // NOI18N
        jScrollPane1.setViewportView(helptext);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 180));

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 560, -1));

        bar.setBackground(resourceMap.getColor("bar.background")); // NOI18N
        bar.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("bar.border.highlightColor"), null)); // NOI18N
        bar.setName("bar"); // NOI18N
        getContentPane().add(bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 560, -1));

        freememory.setText(resourceMap.getString("freememory.text")); // NOI18N
        freememory.setName("freememory"); // NOI18N
        getContentPane().add(freememory, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 170, -1));

        totalmemory.setText(resourceMap.getString("totalmemory.text")); // NOI18N
        totalmemory.setName("totalmemory"); // NOI18N
        getContentPane().add(totalmemory, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 180, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HelpDesk().setVisible(true);
              
                } catch (IOException ex) {
                    Logger.getLogger(HelpDesk.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar bar;
    public static javax.swing.JTextField freememory;
    public static javax.swing.JTextArea helptext;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField totalmemory;
    // End of variables declaration//GEN-END:variables

}
