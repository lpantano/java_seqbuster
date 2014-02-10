/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seqbuster;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lpantano
 */
public class CustomRendererDE extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        int nc=table.getModel().getColumnCount();
        String s2 =  table.getModel().getValueAt(row, nc-1 ).toString();
        if (Double.parseDouble(s2)<0.05 & column==nc-1 ){
            c.setForeground(Color.red);
//            System.out.println(s);
            }else{
               c.setForeground(Color.black);
            }
        return c;
    }
}
