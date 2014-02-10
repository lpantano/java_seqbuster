/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author lpantano
 */
public class clustertype {
    int ncname;
    int consflag=0;
    TreeMap<Integer,ArrayList<Integer>> nc = new TreeMap<Integer,ArrayList<Integer>>();
    TreeMap<Integer,ArrayList<Integer>> nl = new TreeMap<Integer,ArrayList<Integer>>();
    TreeMap<String,Integer[]> ncnlp = new TreeMap<String,Integer[]>();
    TreeMap<Integer,ArrayList<Integer>> nccons = new TreeMap<Integer,ArrayList<Integer>>();
    TreeMap<Integer,ArrayList<Integer>> nlcons = new TreeMap<Integer,ArrayList<Integer>>();
    TreeMap<Integer,Integer> cycles = new TreeMap<Integer,Integer>();
    
}
