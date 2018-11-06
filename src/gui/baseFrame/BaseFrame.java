package gui.baseFrame;

import gui.baseFrame.panels.*;
import gui.controlDisplay.ControlFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BaseFrame extends JFrame implements Runnable {
    private final int FRAME_WIDTH = 1080;
    private final int FRAME_HEIGHT = 720;

    private HashMap<String, JPanel> panelMap;
    private static boolean changed = false;

    public static String status = "Init";

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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelMap = new HashMap<>();

        initializeFrame();
        (new Thread(this)).start();
        this.add(initialScreenPanel);
        this.setVisible(true);
    }

    private void initializeFrame() {
        initialScreenPanel = new InitialScreenPanel(FRAME_WIDTH, FRAME_HEIGHT);
        multiPlayerPanel = new MultiPlayerPanel(FRAME_WIDTH, FRAME_HEIGHT);
        singlePlayerPanel = new SinglePlayerPanel(FRAME_WIDTH, FRAME_HEIGHT);
        lobbyPanel = new LobbyPanel(FRAME_WIDTH, FRAME_HEIGHT);
        hostPanel = new HostPanel(FRAME_WIDTH, FRAME_HEIGHT);
        joinPanel = new JoinPanel(FRAME_WIDTH, FRAME_HEIGHT);
        gamePanel = new GamePanel(1400, 1000);

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
                if (getStatus().equals("Join")) lobbyPanel.setHost(false);
                else if (getStatus().equals("Host")) lobbyPanel.setHost(true);
                else if (getStatus().equals("Game")) {
                    controlDisplay = new ControlFrame(this);
                    this.setSize(1415,1040);
                }
                this.revalidate();
                this.repaint();
                setChanged(false);
            }
        }
    }
}
