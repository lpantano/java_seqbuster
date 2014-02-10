
package seqbuster;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;

/////////////////////////////////////////////////////////
//  Bare Bones Browser Launch                          //
//  Version 1.5                                        //
//  December 10, 2005                                  //
//  Supports: Mac OS X, GNU/Linux, Unix, Windows XP    //
//  Example Usage:                                     //
//     String url = "http://www.centerkey.com/";       //
//     BareBonesBrowserLaunch.openURL(url);            //
//  Public Domain Software -- Free to Use as You Like  //
/////////////////////////////////////////////////////////

public class OpenBrowser {



   private static final String errMsg = "Error attempting to launch web browser";

   public static void openURL(String url) {
      String osName = System.getProperty("os.name");
      try {
         if (osName.startsWith("Mac OS")) {
            openUrlInBrowser(url);
            }
         else if (osName.startsWith("Windows"))
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
         else { //assume Unix or Linux
            String[] browsers = {
               "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++)
               if (Runtime.getRuntime().exec(
                     new String[] {"which", browsers[count]}).waitFor() == 0)
                  browser = browsers[count];
            if (browser == null)
               throw new Exception("Could not find web browser");
            else
               Runtime.getRuntime().exec(new String[] {browser, url});
            }
         }
      catch (Exception e) {
         tools.showerror(e.getLocalizedMessage(),e.toString());
         }
      }

private static void openUrlInBrowser(String url)
{
  Runtime runtime = Runtime.getRuntime();
  String[] args = { "osascript", "-e", "open location \"" + url + "\"" };
  try
  {
    Process process = runtime.exec(args);
  }
  catch (IOException e)
  {
      tools.showerror(e.getLocalizedMessage(),e.toString());
    // do what you want with this
  }
}


}
