/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miraligner;
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
 
  @Parameter(names = "-trim", description = "nt allowed for trimming processes", required = true)
  public String trim;
  
  @Parameter(names = "-add", description = "nt allowed for addition processes", required = true)
  public String add;
  
  @Parameter(names = "-sub", description = "nt allowed for substitution processes", required = true)
  public String sub;
  
  @Parameter(names = "-s", description = "three letter code for species", required = true)
  public String species;
  
  @Parameter(names = "-db", description = "database", required = true)
  public String db;
  
  @Parameter(names = "-i", description = "input", required = true)
  public String input;
  
  @Parameter(names = "-o", description = "output", required = true)
  public String output;
  
  @Parameter(names = "-freq", description = "add freq information")
  public boolean freq;
  
  @Parameter(names = "-pre", description = "add reads mapping to precursor")
  public boolean pre;
  
  @Parameter(names = "-h", description = "help mode",help=true)
  public boolean help;
  
  @Parameter(names = "-fo", description = "format input")
  public String format = "none";

}
