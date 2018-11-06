package gui;

import javax.swing.*;
import java.awt.*;

public class ColorBoxRenderer extends JLabel implements ListCellRenderer {

    public ColorBoxRenderer(){
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        if(value==null) return this;
        setText(value.toString());
        setBackground(ColorConverter.getInstance().getColor(value.toString()));
        return this;
    }
}
