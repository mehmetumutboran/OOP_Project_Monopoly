package gui.baseFrame;

import domain.client.UIUpdater;
import domain.server.listeners.CloseButtonListener;
import domain.server.listeners.PlayerKickedListener;
import gui.baseFrame.panels.*;
import gui.controlDisplay.ControlFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BaseFrame extends JFrame implements Runnable, CloseButtonListener, PlayerKickedListener {
    private final int FRAME_WIDTH = 1080;
    private final int FRAME_HEIGHT = 720;
    private final String CURRENT_VERSION = "v2.2.0";

    private HashMap<String, JPanel> panelMap;
    private boolean changed = false;

    private static BaseFrame baseFrame;

    private String status = "Init";

    private InitialScreenPanel initialScreenPanel;
    private MultiPlayerPanel multiPlayerPanel;
    private SinglePlayerPanel singlePlayerPanel;
    private LobbyPanel lobbyPanel;
    private HostPanel hostPanel;
    private JoinPanel joinPanel;
    private GamePanel gamePanel;
    private CreditsPanel creditsPanel;
    private ControlFrame controlDisplay;
    private String title = null;


    private BaseFrame() throws HeadlessException {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelMap = new HashMap<>();
        initializeFrame();
        UIUpdater.getInstance().addCloseButtonListener(this);
        (new Thread(this)).start();
        this.add(initialScreenPanel);
        this.setVisible(true);
    }

    public static BaseFrame getInstance() {
        if (baseFrame == null) {
            baseFrame = new BaseFrame();
        }
        return baseFrame;
    }

    public void setFrameTitle(String newTitle) {
        setChanged(true);
        title = newTitle;
    }

    private void initializeFrame() {
        initialScreenPanel = new InitialScreenPanel(FRAME_WIDTH, FRAME_HEIGHT);
        multiPlayerPanel = new MultiPlayerPanel(FRAME_WIDTH, FRAME_HEIGHT);
        singlePlayerPanel = new SinglePlayerPanel(FRAME_WIDTH, FRAME_HEIGHT);
        lobbyPanel = new LobbyPanel(FRAME_WIDTH, FRAME_HEIGHT);
        hostPanel = new HostPanel(FRAME_WIDTH, FRAME_HEIGHT);
        joinPanel = new JoinPanel(FRAME_WIDTH, FRAME_HEIGHT);
        creditsPanel = new CreditsPanel(FRAME_WIDTH, FRAME_HEIGHT, CURRENT_VERSION);
        gamePanel = new GamePanel(1400, 1000);
        controlDisplay = new ControlFrame(this);

        panelMap.put("Init", initialScreenPanel);
        panelMap.put("Multi", multiPlayerPanel);
        panelMap.put("Single", singlePlayerPanel);
        panelMap.put("Lobby", lobbyPanel);
        panelMap.put("Host", hostPanel);
        panelMap.put("Join", joinPanel);
        panelMap.put("Game", gamePanel);
        panelMap.put("Credits", creditsPanel);
    }


    public synchronized String getStatus() {
        return status;
    }

    public synchronized boolean isChanged() {
        return changed;
    }

    public synchronized void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void setStatus(String status) {
        if (this.status.equals(status)) return;
        this.status = status;
        System.out.println(status);
        changed = true;
    }

    public void setHost() {
        lobbyPanel.setHost(true);
        this.status = "Lobby";
        lobbyPanel.validate();
        lobbyPanel.repaint();
        lobbyPanel.refresh();
        this.changed = true;
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
                } else if (getStatus().equals("Host")) {
                    lobbyPanel.setHost(true);
                }
                //else if (getStatus().equals("Init")) lobbyPanel.setHost(false);
                else if (getStatus().equals("Game")) {
                    this.setSize(1415, 1040);
                    this.controlDisplay.setVisible(true);
                }
                if (title != null) {
                    this.setTitle(title);
                    this.controlDisplay.setTitle(title);
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
        JOptionPane.showMessageDialog(null, "You are kicked!!",
                "Connection terminated", JOptionPane.WARNING_MESSAGE);
    }

    public void setSaveButton(boolean b){
        controlDisplay.setHost(b);
    }
}
