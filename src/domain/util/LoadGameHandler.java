package domain.util;

import domain.server.controller.ServerCommunicationHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            load  = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(load);
        return load;
    }
}
