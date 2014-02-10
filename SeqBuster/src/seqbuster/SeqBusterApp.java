/*
 * seqbusterApp.java
 */

package seqbuster;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import javax.swing.*;
import java.lang.Thread.*;
import java.io.*;
import seqbuster.tools.*;
/**
 * The main class of the application.
 */
public class SeqBusterApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    
    @Override protected void startup() {
        try {
           
                try {
                try {

                    SeqBusterView seqbuster=new SeqBusterView(this);
                    show(seqbuster);
                    final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    
                    //SeqBusterView changeprepath=new SeqBusterView ();
                    tools check=new tools ();
                    String fileoptions="seqbuster.op";
                    String fileinstalled="installed";
                    boolean Eoptions=check.fileexist(fileoptions);
                    boolean Einstalled=check.fileexist(fileinstalled);
                   
                    if (!Eoptions | !Einstalled) {
                        try {
                             JDialog check_obj = new InstallChecker();
                            check_obj.setVisible(true);
                            if (!Eoptions){
                                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileoptions)));
                            }
                            JDialog InitFrame_obj = new FirstrunFrame();
                            InitFrame_obj.setLocation(dim.width/2-350, dim.height/2-250);
                            InitFrame_obj.setVisible(true);

                        } catch (IOException ie) {
                            tools.showcerror("Cannot create option file!!!\nYou will have to configure the preference options every time you load SeqBuster\n");

                        }

                    }
                    

                    
                } catch (MalformedURLException ex) {
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                } catch (InterruptedException ex) {
                    Logger.getLogger(SeqBusterApp.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
                } catch (IOException ex) {
                    Logger.getLogger(SeqBusterApp.class.getName()).log(Level.SEVERE, null, ex);
                    tools.showerror(ex.getLocalizedMessage(),ex.toString());
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeqBusterApp.class.getName()).log(Level.SEVERE, null, ex);
            tools.showerror(ex.getLocalizedMessage(),ex.toString());
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of seqbusterApp
     */
    public static SeqBusterApp getApplication() {
        return Application.getInstance(SeqBusterApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(SeqBusterApp.class, args);
    }
}
