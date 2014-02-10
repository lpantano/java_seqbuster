

package seqbuster;

/**
 *
 * @author lpantano
 */
public class byLineLength implements java.util.Comparator{
     public int compare(Object one, Object two) {
        int sdif = ((String)one).length() - ((String)two).length();
        return sdif;
    } 
}
