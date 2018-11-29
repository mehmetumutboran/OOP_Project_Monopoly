package domain.util;

import domain.server.player.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm_ddMMyyyy"));
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

        assert printWriter != null;
        printWriter.println(saveInfo);
        printWriter.close();
    }


    /** {@link domain.server.player.Player}
     *  {@link GameInfo}
     *  {@link domain.server.board.Board}
     */

    // player : name ,[ location[], color], balance, [] , ,
    // queue :
    public String createSave() {
        // TODO add card logic
        String queue = GameInfo.getInstance().generateSaveInfo();
        String playerList =
                GameInfo.getInstance().getPlayerList()
                        .stream().map(Player::generateSaveInfo).collect(Collectors.joining());
        return "Players:\n" +playerList + "Queue:\n" + queue;
    }
}
