package gui.baseFrame;

import domain.client.PlayerActionController;
import domain.client.UIUpdater;
import domain.server.listeners.LabelChangeListener;
import domain.util.Flags;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SquareLabel extends JLabel implements MouseListener, LabelChangeListener {
    private char size;
    private final double LR_MARGIN = 0.997; // Left right
    private final double UD_MARGIN = 0.993; // up down
    private boolean opaqueChecker;
    private int[] location;
    private String actionToApply = null;

    public SquareLabel(char size, int[] loc) {
        this.opaqueChecker = false;
        location = loc.clone();
        this.size = size;
        //System.out.println(size + " ");
        this.setBackground(new Color(255, 0, 0, 70));
        this.setSize(0, 0);
        this.setOpaque(false);
        this.setVisible(false);
        this.addMouseListener(this);
        UIUpdater.getInstance().addLabelChangeListener(this);

    }

    public void setOpaqueOrNot(boolean state) {
        this.opaqueChecker = state;
    }

    public boolean getOpaqueOrNot() {
        return this.opaqueChecker;
    }

    public void draw(int width, int height) {
        if (location[0] == 0 && (location[1] >= 0 && location[1] <= 13)) {
            this.setBounds(
                    (int) (width * LR_MARGIN - this.getWidth()),
                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * location[1] : (location[1] + 1)) * this.getHeight())),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        } else if (location[0] == 0 && (location[1] >= 14 && location[1] <= 27)) {
            //System.out.println(i + "-------------------------------------------------------------------------------------------------" +size);
            this.setBounds(
                    (int) (width * LR_MARGIN - this.getWidth() - ((size == 'B' ? 2 * (location[1] - 14) : (location[1] - 14 + 1)) * this.getWidth())),
                    (int) (height * UD_MARGIN - this.getHeight()),
                    (int) ((size == 'B' ? 2 : 1) * (width / 17.0)),
                    (int) (2 * (height / 17.0)));
        } else if (location[0] == 0 && (location[1] >= 28 && location[1] <= 41)) {
            this.setBounds(
                    (int) (width * (1 - LR_MARGIN)),
                    (int) (height * UD_MARGIN - this.getHeight() - ((size == 'B' ? 2 * (location[1] - 28) : (location[1] - 28 + 1)) * this.getHeight())),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        } else if (location[0] == 0 && (location[1] >= 42 && location[1] <= 55)) {
            this.setBounds(
                    (int) (width * (1 - LR_MARGIN) + ((size == 'B' ? 2 * (location[1] - 42) : (location[1] - 42 + 1)) * this.getWidth())),
                    (int) (height * (1 - UD_MARGIN)),
                    (int) ((size == 'B' ? 2 : 1) * (width / 17.0)),
                    (int) (2 * (height / 17.0)));
        } else if (location[0] == 1 && (location[1] >= 0 && location[1] <= 9)) {
            this.setBounds(
                    (int) (width * LR_MARGIN - (2 * this.getWidth() + width * (1 - LR_MARGIN))),
                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * location[1] : (location[1] + 2)) * this.getHeight()) + this.getHeight()),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        } else if (location[0] == 1 && (location[1] >= 10 && location[1] <= 19)) {
            this.setBounds(
                    (int) (width * LR_MARGIN - width * (1 - LR_MARGIN) - 2 * this.getWidth() - ((size == 'B' ? 2 * (location[1] - 10) : (location[1] - 10 + 2)) * this.getWidth())),
                    (int) (height * UD_MARGIN - 2 * this.getHeight() - height * (1 - UD_MARGIN)),
                    (int) ((size == 'B' ? 2 : 1) * (width / 17.0)),
                    (int) (2 * (height / 17.0)));
        } else if (location[0] == 1 && (location[1] >= 20 && location[1] <= 29)) {
            this.setBounds(
                    (int) (2 * width * (1 - LR_MARGIN) + this.getWidth()),
                    (int) (height * UD_MARGIN - 2 * this.getHeight() - ((size == 'B' ? 2 * (location[1] - 20) : (location[1] - 20 + 2)) * this.getHeight()) - height * (1 - UD_MARGIN)),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        } else if (location[0] == 1 && (location[1] >= 30 && location[1] <= 39)) {
            this.setBounds(
                    (int) (2 * width * (1 - LR_MARGIN) + this.getWidth() + ((size == 'B' ? 2 * (location[1] - 30) : (location[1] - 30 + 2)) * this.getWidth())),
                    (int) (2 * height * (1 - UD_MARGIN) + this.getHeight()),
                    (int) ((size == 'B' ? 2 : 1) * (width / 17.0)),
                    (int) (2 * (height / 17.0)));
        } else if (location[0] == 2 && (location[1] >= 0 && location[1] <= 5)) {
            this.setBounds(
                    (int) (width * LR_MARGIN - (3 * this.getWidth() + 2 * width * (1 - LR_MARGIN))),
                    (int) (2 * height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * location[1] : (location[1] + 3)) * this.getHeight()) + 2 * this.getHeight()),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        } else if (location[0] == 2 && (location[1] >= 6 && location[1] <= 11)) {
            this.setBounds(
                    (int) (width * LR_MARGIN - 2 * width * (1 - LR_MARGIN) - 3 * this.getWidth() - ((size == 'B' ? 2 * (location[1] - 6) : (location[1] - 6 + 3)) * this.getWidth())),
                    (int) (height * UD_MARGIN - 3 * this.getHeight() - 2 * height * (1 - UD_MARGIN)),
                    (int) ((size == 'B' ? 2 : 1) * (width / 17.0)),
                    (int) (2 * (height / 17.0)));
        } else if (location[0] == 2 && (location[1] >= 12 && location[1] <= 17)) {
            this.setBounds(
                    (int) (3 * width * (1 - LR_MARGIN) + 2 * this.getWidth()),
                    (int) (height * UD_MARGIN - 3 * this.getHeight() - ((size == 'B' ? 2 * (location[1] - 12) : (location[1] - 12 + 3)) * this.getHeight()) - 2 * height * (1 - UD_MARGIN)),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        } else if (location[0] == 2 && (location[1] >= 18 && location[1] <= 23)) {
            this.setBounds(
                    (int) (3 * width * (1 - LR_MARGIN) + 2 * this.getWidth() + ((size == 'B' ? 2 * (location[1] - 18) : (location[1] - 18 + 3)) * this.getWidth())),
                    (int) (2 * height * (1 - UD_MARGIN) + 2 * this.getHeight()),
                    (int) ((size == 'B' ? 2 : 1) * (width / 17.0)),
                    (int) (2 * (height / 17.0)));
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Label Clicked");
//        if(getOpaqueOrNot()){
//            setOpaqueOrNot(false);
//        }else{
//            setOpaqueOrNot(true);
//        }
//        this.setOpaque(getOpaqueOrNot());
        this.setBackground(new Color(255, 0, 16, 40));
        if (getActionType().equals("UP")) {
            PlayerActionController.getInstance().upgradeSquare(this.location);
        } else if(getActionType().equals("DOWN")){
            PlayerActionController.getInstance().downgradeSquare(this.location);
        } else if(getActionType().equals(String.valueOf(Flags.getFlag("Mortgage")))){
            PlayerActionController.getInstance().mortgageSquare(this.location);
        } else if(getActionType().equals(String.valueOf(Flags.getFlag("Unmortgage")))){
            PlayerActionController.getInstance().unmortgageSquare(this.location);

        }
        this.actionToApply = null;
        this.setVisible(false);
        this.setOpaque(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void onLabelChangeEvent(ArrayList<int[]> locationList, String actionType, int i) {

        if(i == 0){
            for (int[] lst : locationList){
                if(lst[0]==location[0] && lst[1]==location[1]){
                    this.setVisible(false);
                    this.setOpaque(false);
                }
            }
        }else {
            setActionType(actionType);
            for (int[] loc : locationList) {
                if (loc[0] == this.location[0] && loc[1] == this.location[1]) {
                    if (getActionType().equals("UP"))
                        this.setBackground(new Color(0, 252, 255, 70));
                    else
                        this.setBackground(new Color(112, 0, 255, 70));
                    this.setVisible(true);
                    this.setOpaque(true);
                }
            }
        }
        repaint();
    }

    private String getActionType() {
        return this.actionToApply;
    }

    private void setActionType(String actionType) {
        this.actionToApply = actionType;
    }
}
