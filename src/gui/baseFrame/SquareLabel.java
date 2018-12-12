package gui.baseFrame;

import domain.client.PlayerActionController;
import domain.client.UIUpdater;
import domain.server.listeners.LabelChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SquareLabel extends JLabel implements MouseListener, LabelChangeListener {
    private char size;
    private final double LR_MARGIN = 0.99142857; //Left right
    private final double UD_MARGIN = 0.993; // up down
    private boolean opaqueChecker;
    private int[] location;

    public SquareLabel(char size, int [] loc) {
        this.opaqueChecker=false;
        location=loc.clone();
        this.size = size;
        //System.out.println(size + " ");
        this.setBackground(new Color(255, 0, 0, 70));
        this.setSize(0,0);
        this.setOpaque(false);
        this.setVisible(true);
        this.addMouseListener(this);
        UIUpdater.getInstance().addLabelChangeListener(this);

    }
    public void setOpaqueOrNot (boolean state){
        this.opaqueChecker = state;
    }
    public boolean getOpaqueOrNot (){
        return this.opaqueChecker;
    }

    public void draw(int width, int height) {
        if (location[0]==0 && (location[1]>=0 && location[1]<=13)) {
            this.setBounds(
                    (int) (width * LR_MARGIN - this.getWidth()),
                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * location[1] : (location[1]+1)) * this.getHeight())),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==0 && (location[1]>=14 && location[1]<=27)){
            //System.out.println(i + "-------------------------------------------------------------------------------------------------" +size);
            this.setBounds(
                    (int) (width * LR_MARGIN - this.getWidth()-((size=='B' ? 2 * (location[1]-14) : (location[1]-14+1)) * this.getWidth())),
                    (int) (height * UD_MARGIN - this.getHeight()),
                    (int) ((size == 'B' ? 2 : 1) * (width/17.0)),
                    (int) (2 * (height / 17.0)));
        }else if(location[0]==0 && (location[1]>=28 && location[1]<=41)){
            this.setBounds(
                    (int) (width * (1-LR_MARGIN)),
                    (int) (height *  UD_MARGIN -this.getHeight()-((size == 'B' ? 2 * (location[1]-28) : (location[1]-28+1)) * this.getHeight())),
                    (int) (2 * (width / 17.0)),
                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==0 && (location[1]>=42 && location[1]<=55)){
            this.setBounds(
                    (int) (width * (1-LR_MARGIN) + ((size=='B' ? 2 * (location[1]-42) : (location[1]-42+1)) * this.getWidth())),
                    (int) (height * (1 - UD_MARGIN)),
                    (int) ((size == 'B' ? 2 : 1) * (width/17.0)),
                    (int) (2 * (height / 17.0)));
        }else if(location[0]==1 && (location[1]>=0 && location[1]<10)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==1 && (location[1]>=10 && location[1]<20)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==1 && (location[1]>=20 && location[1]<30)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==1 && (location[1]>=30 && location[1]<40)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==2 && (location[1]>=0 && location[1]<6)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==2 && (location[1]>=6 && location[1]<12)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==2 && (location[1]>=12 && location[1]<18)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
        }else if(location[0]==2 && (location[1]>=18 && location[1]<24)){
//            this.setBounds(
//                    (int) (width * LR_MARGIN - this.getWidth()),
//                    (int) (height * (1 - UD_MARGIN) + ((size == 'B' ? 2 * i : i) * this.getHeight())),
//                    (int) (2 * (width / 17.0)),
//                    (int) ((size == 'B' ? 2 : 1) * height / 17.0));
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
        PlayerActionController.getInstance().upgradeLabel(this.location);
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
    public void onLabelChangeEvent(ArrayList<int[]> locationList) {
        for (int[] loc : locationList){
            if(loc[0]==this.location[0] && loc[1]==this.location[1]){
                this.setBackground(new Color(0, 252, 255, 70));
                this.setOpaque(true);
            }else {
                continue;
            }
        }
    }
}
