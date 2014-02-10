/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author lpantano
 */
public class PanelImg extends Panel{

 private static final long serialVersionUID = 1L;

  private BufferedImage image;

  public PanelImg(String filename) {
    try {
      image = ImageIO.read(new File(filename));
    } catch (IOException ie) {
      ie.printStackTrace();
    }
  }

  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, null);
  }


}
