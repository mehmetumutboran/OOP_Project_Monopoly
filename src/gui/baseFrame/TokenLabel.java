package gui.baseFrame;

import domain.listeners.GameStartedListener;
import domain.listeners.TokenMovementListener;
import gui.ColorConverter;

import javax.swing.*;

public class TokenLabel extends JLabel implements TokenMovementListener{

    private String owner;
    private String color;

    public TokenLabel(String owner,String color){
       super();
       this.owner = owner;
       this.color = color;
       this.setBackground(ColorConverter.getInstance().getColor(this.color));
    }

    @Override
    public void onTokenMovement() {

    }
}
