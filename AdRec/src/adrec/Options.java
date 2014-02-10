/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adrec;
import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lpantano
 */
public class Options {
  @Parameter
  public List<String> parameters = new ArrayList<String>();
 
  @Parameter(names = "-v", description = "version")
  public boolean version = false;
  
  @Parameter(names = "-a", description = "adapter", required = true)
  public String adapt;
  
  @Parameter(names = "-l", description = "min adapter length", required = true)
  public Integer len;
  
  @Parameter(names = "-c", description = "complexity", required = true)
  public Double com;
  
  @Parameter(names = "-m", description = "nt allowed for errors", required = true)
  public Integer mut;
  
  @Parameter(names = "-s", description = "start ", required = true)
  public Integer start;
  
  @Parameter(names = "-e", description = "end", required = true)
  public Integer end;
  
  @Parameter(names = "-i", description = "input", required = true)
  public String input;
  
  @Parameter(names = "-o", description = "output", required = true)
  public String output;
  
  
  @Parameter(names = "-h", description = "help mode",help=true)
  public boolean help;

}
