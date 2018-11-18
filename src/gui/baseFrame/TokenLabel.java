package gui.baseFrame;


import gui.ColorConverter;

import javax.swing.*;

public class TokenLabel extends JLabel{

    private String owner;
    private String color;


    public TokenLabel(String owner,String color){
       super();
       this.owner = owner;
       this.color = color;
       this.setBackground(ColorConverter.getInstance().getColor(this.color));
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
