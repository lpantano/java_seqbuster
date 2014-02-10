    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class xmlReader  {
    String namepro=SeqBusterView.loaprotext.getText();
    String host=SeqBusterView.HosMysText.getText();
    String user = SeqBusterView.UseMysText.getText();
    String pssw = SeqBusterView.PswMysText.getText();
    static String namescript=null;

    public void readfile(String namefile,int numgroups){
  try {
            File file = new File("Rscripts/GUIR/"+namefile+".xml");
            namescript=namefile;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
//            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            JDialog frame = new JDialog();
            frame.setTitle("miRNA Analysis");
            frame.setModal(true);
            
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //frame.setSize(620, 400);
            AbsoluteLayout grid=new AbsoluteLayout ();
            frame.setLayout(grid);
//            frame.setResizable ( false );
            frame.repaint();
            JPanel myPane = new JPanel(new AbsoluteLayout());
            
            //show samples
            JPanel ss=getGroups(1);
            myPane.add(ss,new AbsoluteConstraints(0,0,160,ss.getHeight()));
            if (numgroups==2){
                JPanel ss2=getGroups(2);
                myPane.add(ss2,new AbsoluteConstraints(160,0,160,ss2.getHeight()));
            }
//            System.out.println(ss.getHeight());
            JPanel tp=getFieldText(doc);
            myPane.add(tp,new AbsoluteConstraints(0,ss.getHeight()+10,300,tp.getHeight()));
//            System.out.println(tp.getHeight());
            JPanel sp=getFieldSel(doc);
            myPane.add(sp,new AbsoluteConstraints(310,ss.getHeight()+10,340,sp.getHeight()));
            JPanel cp=getFieldCheck(doc);
            myPane.add(cp,new AbsoluteConstraints(310,ss.getHeight()+sp.getHeight()+5,300,cp.getHeight()));
            int maxh=Math.max(ss.getHeight()+sp.getHeight()+cp.getHeight(),ss.getHeight()+tp.getHeight());
            myPane.add(getButtonPanel(frame,myPane),new AbsoluteConstraints(0,maxh+10,350,50));
            frame.setSize(690,maxh+120 );
            ///////////myPane.setSize( );
            frame.add(myPane,new AbsoluteConstraints(30,0,690,maxh+120));
            Point  p= SeqBusterApp.getApplication().getMainFrame().getLocation();
            frame.setLocation(p.x+500, p.y+100);
            frame.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public JPanel getGroups(int ns) throws SQLException {
        JPanel p = new JPanel(new AbsoluteLayout());
        
//        .setName("jScrollPane1");
        JList list=new JList();
        list.setName("group"+ns);
        list.setSize(100,70);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        String readsamples="select `name` from `"+namepro+"`.`experiments` where `type` like 'miRNA'";
//        Vector listtrack=new Vector();
        Connection con =DriverManager.getConnection("jdbc:mysql://" + host + "/seqbuster", user, pssw);
        Statement statment = con.createStatement();
        ResultSet rs=statment.executeQuery(readsamples);
        DefaultListModel model = new DefaultListModel();
        //int p=tralist.getModel().getSize();
        int index=0;
        list.removeAll();

        while (rs.next()) {
//            System.out.println(rs.getString(1));
            model.add(index,rs.getString(1));
            index++;

        }

        list.setModel(model);
        //list.repaint();
        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setSize(100,70);
        jScrollPane.setViewportView(list);
        
////        JButton butdo = new JButton("Done");
////        p.add(butdo,new AbsoluteConstraints(0,0,60,30));
        p.add(new JLabel("Group "+ns),new AbsoluteConstraints(0,0,60,30));
        p.add(jScrollPane,new AbsoluteConstraints(10,30,100,120));
        rs.close();
        statment.close();
        con.close();
        p.setSize(160,150);
        return p;
    }

    private static JPanel getButtonPanel(final JDialog d,final JPanel pa)  {
      JPanel p = new JPanel(new AbsoluteLayout());
      JButton butdo = new JButton("Run");
      butdo.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                
                GetParam getparameters = new GetParam ();
                try {
                    boolean done=getparameters.get(pa);
                    if (done){
                        getparameters.runpackage(namescript,d);
                    }else{
                        JOptionPane.showMessageDialog(null, "you have to select some sample","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(xmlReader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(xmlReader.class.getName()).log(Level.SEVERE, null, ex);
                }
//                System.out.println("in doaction\n");
            }
        });
      p.add(butdo,new AbsoluteConstraints(0,0,100,30));
      JButton but = new JButton("Close");
//      but.setBounds(0,0,20,50);
      but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //coolTable.setFrozenColumns(2);
                d.dispose();
            }
        });
      p.add(but,new AbsoluteConstraints(120,0,100,30));
      JButton butsave = new JButton("Save last result");
      butsave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                GetParam getparameters = new GetParam ();
                try {
                    getparameters.savelast();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(xmlReader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(xmlReader.class.getName()).log(Level.SEVERE, null, ex);
                }
                getparameters=null;
                
//                System.out.println("in saveaction\n");
            }
        });
      p.add(butsave,new AbsoluteConstraints(240,0,100,30));
//      p.pack();
//      p.setPreferredSize(new Dimension(200,100));
      return p;
   }

    private static JPanel getFieldSel(Document doc){
            NodeList nodeLst = doc.getElementsByTagName("select");
//            System.out.println("Information of all select");
            JPanel p = new JPanel(new AbsoluteLayout());
            int posy=0;
            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node Node = nodeLst.item(s);

                if (Node.getNodeType() == Node.ELEMENT_NODE) {

                    Element tElmnt = (Element) Node;
                    String desc= tElmnt.getAttribute("description");
                    String name= tElmnt.getAttribute("name");
//                    System.out.println(desc+" : " + name);
                    JLabel lab = new JLabel("<html>"+desc+"</html>");
                    p.add(lab,new AbsoluteConstraints(0,posy,160,50));
                    ///////////////////////////////////////////////////////////////////
                    Element valElmnt = (Element) Node;
                    NodeList valNmElmntLst = valElmnt.getElementsByTagName("option");
                    JComboBox list=new JComboBox();
                    list.setName(name);
                    list.setSize(100,30);
                    for (int ns=0;ns<valNmElmntLst.getLength();ns++){

                        Node NodeS = valNmElmntLst.item(ns);
                        if (NodeS.getNodeType() == NodeS.ELEMENT_NODE) {
                            Element valOpt = (Element) NodeS;
//                            NodeList valOptLst = valOpt.getElementsByTagName("value");
//                            Element valOptLstElem = (Element) valOptLst.item(0);
                            NodeList valNm = valOpt.getChildNodes();
//                            System.out.println("ind option "+ns+" : "+((Node) valNm.item(0)).getNodeValue());
                            
                            list.addItem(((Node) valNm.item(0)).getNodeValue());
                            
                        }

                    }
                                                            
                    p.add(list,new AbsoluteConstraints(160,posy,150,40));

                }
                posy+=55;
            }
            p.setSize(200,posy+60);
            return p;

    }
    private static JPanel getFieldText(Document doc){
            NodeList nodeLst = doc.getElementsByTagName("text");
//            System.out.println("Information of all text");
            JPanel p = new JPanel(new AbsoluteLayout());
            int posy=0;
            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node Node = nodeLst.item(s);

                if (Node.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element tElmnt = (Element) Node;
                    String desc= tElmnt.getAttribute("description");
                    String value= tElmnt.getAttribute("value");
//                    System.out.println(desc+" : " + value);

                    /////////////////////////////////////////////////////////////
                  
                    JLabel lab = new JLabel("<html>"+desc+"</html>");
                    p.add(lab,new AbsoluteConstraints(0,posy,130,30));
                    /////////////////////////////////////////////////////////////
                    Element valElmnt = (Element) Node;
                    NodeList valNmElmntLst = valElmnt.getElementsByTagName("name");
                    Element valNmElmnt = (Element) valNmElmntLst.item(0);
                    NodeList valNm = valNmElmnt.getChildNodes();
//                    System.out.println("First value : " + ((Node) valNm.item(0)).getNodeValue());
                    JTextField text = new JTextField();
                    text.setName(((Node) valNm.item(0)).getNodeValue());
                    text.setText(value);
//                    text.setPreferredSize(new Dimension (60,10));
                    p.add(text,new AbsoluteConstraints(140,posy,100,30));

                }
                posy+=40;
            }
            
            p.setSize(200,posy+50);
            return p;

    }

    private static JPanel getFieldCheck(Document doc){
            NodeList nodeLst = doc.getElementsByTagName("checkbox");
//            System.out.println("Information of all check");
            JPanel p = new JPanel(new AbsoluteLayout());
            int posy=0;
            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node Node = nodeLst.item(s);

                if (Node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tElmnt = (Element) Node;
                    String desc= tElmnt.getAttribute("description");
                    /////////////////////////////////////////////////////////////
                    Element valElmnt = (Element) Node;
                    NodeList valNmElmntLst = valElmnt.getElementsByTagName("name");
                    Element valNmElmnt = (Element) valNmElmntLst.item(0);
                    NodeList valNm = valNmElmnt.getChildNodes();
//                    System.out.println("First value : " + ((Node) valNm.item(0)).getNodeValue());
                    
                    /////////////////////////////////////////////////////////////
                    
                    JCheckBox check = new JCheckBox();
                    check.setName(((Node) valNm.item(0)).getNodeValue());
//                    check.set
                    check.setText(desc);
                    
//                    text.setPreferredSize(new Dimension (60,10));
                    p.add(check,new AbsoluteConstraints(0,posy,150,30));

                }
                posy+=40;
            }

            p.setSize(200,posy+10);
            return p;

    }
}
