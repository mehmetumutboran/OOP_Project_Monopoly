package domain.util;

import domain.server.Savable;
import domain.server.controller.ServerCommunicationHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LoadGameHandler {
    private static LoadGameHandler ourInstance;
    private String loadFile;

    public static LoadGameHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new LoadGameHandler();
        }
        return ourInstance;
    }

    private LoadGameHandler() {
    }

    public String[] getSaveFiles() {
        File dir = new File("save");
        return dir.list();
    }


    public void setLoadFile(String loadFile) {
        this.loadFile = loadFile;
        System.out.println("\n\nSetLOADFile:\n" + loadFile);
    }

    public boolean isNewGame() {
        return loadFile.equals("New Game");
    }

    public void sendLoad() {
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Load"), readFile());
    }

    private String readFile() {
        String load = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("save" + System.getProperty("file.separator") + loadFile))) {
            load = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(load);
        return load;
    }

    public void loadGame(String load) {
        String[] loadElements = load.split("[*]");
        loadPlayers(loadElements);
        GameInfo.getInstance().setPlayerQueue(
                MessageConverter.convertStringToDeque(
                        loadElements[loadElements.length - 1]));
        System.out.println(GameInfo.getInstance().getPlayerList());
        System.out.println(GameInfo.getInstance().getPlayerQueue());
    }

    private void loadPlayers(String[] loadElements) {
        GameInfo.getInstance().reset();
        for (int i = 0; i < loadElements.length - 1; i++) {
            loadPlayer(loadElements[i]);
        }
    }

    private void loadPlayer(String loadElement) {
        String[] player = loadElement.split("[,]");
        String name = null, color = null, readiness = null;
        int layer = 0 , location = 0, balance = 0, doubleCounter = 0;
        boolean isInJail = false, isStarted = true;
        ArrayList<? extends Savable> propertyList = new ArrayList<>();
        ArrayList<? extends Savable> utilityList = new ArrayList<>();
        ArrayList<? extends Savable> railroadList = new ArrayList<>();
        ArrayList<? extends Savable> mortgagedSquares = new ArrayList<>();
        for (int i = 0; i < player.length; i++) {
            name = player[0];
            layer = Integer.parseInt(player[1]);
            location = Integer.parseInt(player[2]);
            color = player[3];
            balance = Integer.parseInt(player[4]);
            propertyList = MessageConverter.convertStringToList(player[5], 5);
            utilityList = MessageConverter.convertStringToList(player[6], 6);
            railroadList = MessageConverter.convertStringToList(player[7], 7);
            mortgagedSquares = MessageConverter.convertStringToList(player[8], 8);
            readiness = player[9];
            isStarted = Boolean.parseBoolean(player[10]);
            doubleCounter = Integer.parseInt(player[11]);
            isInJail = Boolean.parseBoolean(player[12]);
        }
        GameInfo.getInstance().loadPlayer(name, layer, location, color,
                balance, propertyList, utilityList, railroadList, mortgagedSquares, readiness, isStarted, doubleCounter, isInJail);
    }
}
