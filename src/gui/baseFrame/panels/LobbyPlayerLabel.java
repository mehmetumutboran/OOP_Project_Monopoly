package gui.baseFrame.panels;

import domain.server.controller.ConnectGameHandler;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LobbyPlayerLabel extends JLabel implements MouseListener {
    private String name;

    public LobbyPlayerLabel(String name, Color bg) {
        super(name);
        this.name = name;
        this.setBackground(bg);
        this.setOpaque(true);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(GameInfo.getInstance().isMyselfHost() && !name.equals(ClientFacade.getInstance().getUsername())) {
            int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                    "Do you really want to kick " + name + " ?", //Object message,
                    "Are you sure?", //String title
                    JOptionPane.YES_NO_OPTION, //int optionType
                    JOptionPane.WARNING_MESSAGE, //int messageType
                    null, //Icon icon,
                    new String[]{"Yes", "No"}, //Object[] options,
                    "Yes");//Object initialValue
            if (choice == 0) {
                System.out.println("Player " + name + " is kicked!!!!");
                ConnectGameHandler.getInstance().kickPlayer(name);
            }
        }else{
            System.out.println("A client clicked on label");
        }

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
    public String getName() {
        return name;
    }
}
