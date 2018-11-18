package gui.baseFrame;

import domain.UIUpdater;
import domain.controller.ConnectGameHandler;
import domain.controller.MonopolyGameController;
import domain.listeners.CloseButtonListener;
import domain.listeners.PlayerKickedListener;
import gui.baseFrame.panels.*;
import gui.controlDisplay.ControlFrame;
import network.client.clientFacade.ClientFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class BaseFrame extends JFrame implements Runnable, CloseButtonListener, PlayerKickedListener {
    private final int FRAME_WIDTH = 1080;
    private final int FRAME_HEIGHT = 720;
    private final String CURRENT_VERSION = "v1.3.0";

    private HashMap<String, JPanel> panelMap;
    private static boolean changed = false;

    private static String status = "Init";

    private InitialScreenPanel initialScreenPanel;
    private MultiPlayerPanel multiPlayerPanel;
    private SinglePlayerPanel singlePlayerPanel;
    private LobbyPanel lobbyPanel;
    private HostPanel hostPanel;
    private JoinPanel joinPanel;
    private GamePanel gamePanel;
    private ControlFrame controlDisplay;


    public BaseFrame() throws HeadlessException {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ConnectGameHandler.getInstance().addPlayerKickedListener(this);
        this.panelMap = new HashMap<>();
        initializeFrame();
        MonopolyGameController.getInstance().addCloseButtonListener(this);
        UIUpdater.getInstance().addCloseButtonListener(this);
        (new Thread(this)).start();
        this.add(initialScreenPanel);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                MonopolyGameController.getInstance().informClosed();
            }
        });

        this.setVisible(true);
    }

    private void initializeFrame() {
        initialScreenPanel = new InitialScreenPanel(CURRENT_VERSION, FRAME_WIDTH, FRAME_HEIGHT);
        multiPlayerPanel = new MultiPlayerPanel(FRAME_WIDTH, FRAME_HEIGHT);
        singlePlayerPanel = new SinglePlayerPanel(FRAME_WIDTH, FRAME_HEIGHT);
        lobbyPanel = new LobbyPanel(FRAME_WIDTH, FRAME_HEIGHT);
        hostPanel = new HostPanel(FRAME_WIDTH, FRAME_HEIGHT);
        joinPanel = new JoinPanel(FRAME_WIDTH, FRAME_HEIGHT);
        gamePanel = new GamePanel(1400, 1000);
        controlDisplay = new ControlFrame(this);

        panelMap.put("Init", initialScreenPanel);
        panelMap.put("Multi", multiPlayerPanel);
        panelMap.put("Single", singlePlayerPanel);
        panelMap.put("Lobby", lobbyPanel);
        panelMap.put("Host", hostPanel);
        panelMap.put("Join", joinPanel);
        panelMap.put("Game", gamePanel);
    }


    public synchronized static String getStatus() {
        return status;
    }

    public synchronized static boolean isChanged() {
        return changed;
    }

    public synchronized static void setChanged(boolean changed) {
        BaseFrame.changed = changed;
    }

    public static void setStatus(String status) {
        BaseFrame.status = status;
        System.out.println(status);
        changed = true;

    }

    @Override
    public void run() {
        while (true) {
            if (isChanged()) {
                this.getContentPane().removeAll();
                this.getContentPane().add(panelMap.get(getStatus()));
                if (getStatus().equals("Join")) {
                    lobbyPanel.setHost(false);
                    lobbyPanel.validate();
                    lobbyPanel.repaint();
                } else if (getStatus().equals("Host")) lobbyPanel.setHost(true);
                    //else if (getStatus().equals("Init")) lobbyPanel.setHost(false);
                else if (getStatus().equals("Game")) {
                    this.setSize(1415, 1040);
                    this.controlDisplay.setVisible(true);
                }
                this.revalidate();
                this.repaint();
                setChanged(false);
            }
        }
    }

    @Override
    public void onCloseClickedEvent() {
        System.exit(0);
    }

    @Override
    public void onPlayerKickedEvent() {
        setStatus("Join");
        //TODO Reenter unready
        revalidate();
        repaint();
        JOptionPane.showMessageDialog(null, "You are kicked!!" ,
                "Connection terminated", JOptionPane.WARNING_MESSAGE);
    }
}
