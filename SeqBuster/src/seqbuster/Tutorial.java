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

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lpantano
 */
public class Tutorial extends javax.swing.JFrame {

    /** Creates new form Tutorial */
    public Tutorial() throws IOException {
        initComponents();
        File file = new File("html/logo.png");
        Image img = ImageIO.read(file);
        this.setIconImage(img);
        this.setTitle("SeqBuster");
        setinfo();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tut = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        news = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seqbuster.SeqBusterApp.class).getContext().getResourceMap(Tutorial.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tut.setBackground(resourceMap.getColor("tut.background")); // NOI18N
        tut.setMinimumSize(new java.awt.Dimension(106, 500));
        tut.setName("tut"); // NOI18N
        tut.setPreferredSize(new java.awt.Dimension(106, 500));
        jScrollPane1.setViewportView(tut);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        news.setMinimumSize(new java.awt.Dimension(106, 500));
        news.setName("news"); // NOI18N
        news.setPreferredSize(new java.awt.Dimension(106, 500));
        jScrollPane2.setViewportView(news);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Tutorial().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void setinfo() throws IOException{
        //URL helpURL = new URL("html/manualApp.html");
        File htmlFile=new File("html/manualApp.html");
        URL manualurl=htmlFile.toURL();
        tut.setPage(manualurl);
        File htmlFile2=new File("html/News.html");
        URL newsurl=htmlFile2.toURL();
        news.setPage(newsurl);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JEditorPane news;
    public static javax.swing.JEditorPane tut;
    // End of variables declaration//GEN-END:variables

}
