/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * infoFrame.java
 *
 * Created on May 5, 2010, 12:37:17 PM
 */

package seqbuster;

import java.awt.Point;


public class infoFrame extends javax.swing.JDialog {

    /** Creates new form infoFrame */
    public infoFrame(String msg) {
        super(SeqBusterApp.getApplication().getMainFrame());
        initComponents();
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Point  p= SeqBusterApp.getApplication().getMainFrame().getLocation();
        this.setLocation(p.x+500, p.y+100);
        this.setVisible(true);
        //this.setModal(true);
        infText.setText(msg);
    }


    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        infText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(infoFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFocusCycleRoot(false);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        infText.setColumns(20);
        infText.setEditable(false);
        infText.setLineWrap(true);
        infText.setRows(5);
        infText.setName("infText"); // NOI18N
        jScrollPane1.setViewportView(infText);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 440, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   public static void changemsg (String msg){
       infText.append(msg);
       infText.setCaretPosition(infoFrame.infText.getText().length() - 1);

    }
   public static void clearmsg (){
       infText.append("");
       infText.setCaretPosition(infoFrame.infText.getText().length() - 1);

    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea infText;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
