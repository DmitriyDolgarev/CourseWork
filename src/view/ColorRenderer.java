package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColorRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        /*
        switch (row)
        {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

         */

        if (value.toString() == "Отсутствует")
        {
            c.setBackground(new Color(131, 131, 131));
        }
        else {
            String spredStr = value.toString().substring(0, value.toString().length() - 1);
            double spred = Double.parseDouble(spredStr);

            if (spred > 5) {
                c.setBackground(Color.GREEN);
            } else if (spred > 2) {
                c.setBackground(Color.YELLOW);
            } else {
                c.setBackground(new Color(255, 78, 78));
            }
        }

        return c;
    }
}
