package domain.client.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveGameHandler {
    private static SaveGameHandler ourInstance;

    public static SaveGameHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new SaveGameHandler();
        }
        return ourInstance;
    }

    private SaveGameHandler() {
    }

    public void saveGame(String saveInfo) {
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHm_ddMMyyyy"));
        PrintWriter printWriter = null;
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                printWriter = new PrintWriter("save\\MonopolySave_" + fileName + ".txt",
                        StandardCharsets.UTF_8);
            } else {
                printWriter = new PrintWriter("save/MonopolySave_" + fileName + ".txt",
                        StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert printWriter!=null;
        printWriter.println(saveInfo);
        printWriter.close();
    }
}
