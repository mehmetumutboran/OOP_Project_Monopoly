package gui.baseFrame;

import gui.baseFrame.buttons.initialScreenButons.SinglePlayerButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BaseFrame extends JFrame implements Runnable {
    private final int FRAME_WIDTH = 1080;
    private final int FRAME_HEIGHT = 720;

    private HashMap<String, JPanel> panelMap;
    private static boolean isChanged = false;

    public static String status = "Init";

    private InitialScreenPanel initialScreenPanel;
    private MultiPlayerPanel multiPlayerPanel;
    private SinglePlayerPanel singlePlayerPanel;
    private LobbyPanel lobbyPanel;


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
        lobbyPanel = new LobbyPanel();
        //TODO add others

        panelMap.put("Init", initialScreenPanel);
        panelMap.put("Multi", multiPlayerPanel);
        panelMap.put("Single", singlePlayerPanel);
        panelMap.put("Lobby", lobbyPanel);
    }


    public synchronized static String getStatus() {
        return status;
    }

    public synchronized static boolean isIsChanged() {
        return isChanged;
    }

    public synchronized static void setIsChanged(boolean isChanged) {
        BaseFrame.isChanged = isChanged;
    }

    public static void setStatus(String status) {
        BaseFrame.status = status;
        System.out.println(status);
        isChanged = true;

    }

    public static void main(String[] args) {
        new BaseFrame();

    }

    @Override
    public void run() {
        while (true) {
            if (isIsChanged()) {
                synchronized (this) {
                    this.getContentPane().removeAll();
                    this.getContentPane().add(panelMap.get(getStatus()));
                    this.revalidate();
                    this.repaint();
                    setIsChanged(false);
                }
            }
        }
    }
}
