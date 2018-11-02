package gui.baseFrame.buttons.hostJoinButtons;

import domain.ConnectGameHandler;
import gui.Main;
import gui.ServerSetListener;
import gui.baseFrame.BaseFrame;
import network.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HostButton extends JButton implements ActionListener {
    private JTextField IDField;
    private JTextField portField;
    private Server server;
//    private static boolean serverSet;

//    private static ArrayList<ServerSetListener> serverSetListeners;

    public HostButton(String text, JTextField IDField, JTextField portField) {
        super(text);
        this.addActionListener(this);
        this.IDField = IDField;
        this.portField = portField;
//        serverSetListeners = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("HostGame Button Pressed");
        BaseFrame.setStatus("Lobby");
        server = ConnectGameHandler.getInstance().connectHost(IDField.getText(), Integer.parseInt(portField.getText()));
        if(server != null){
            System.out.println("Host button");
            Main.setServer(server);
        }
    }

//    private void setServer(boolean b) {
//        serverSet = b;
//        publishServerSetEvent();
//    }

//    private static void publishServerSetEvent() {
//        System.out.println("Publish event\n" + serverSetListeners.size());
//        for(ServerSetListener ssl : serverSetListeners) {
//            ssl.onServerSetEvent();
//        }
//    }
//
//    public static boolean addServerSetListener(ServerSetListener ssl){
//        return serverSetListeners.add(ssl);
//    }

//    public static Server getServer() {
//        return server;
//    }
}
