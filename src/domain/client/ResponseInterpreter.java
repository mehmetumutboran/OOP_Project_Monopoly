package domain.client;

import domain.client.interpreter.*;
import domain.util.Flags;

import java.util.HashMap;

/**
 * There should be only UI Updater inside it.
 *
 */
public class ResponseInterpreter {
    private static ResponseInterpreter ourInstance;

    public static ResponseInterpreter getInstance() {
        if(ourInstance == null)
            ourInstance = new ResponseInterpreter();
        return ourInstance;
    }

    private HashMap<Character, ResponseInterpretable> interpreterMap;

    private ResponseInterpreter() {
        ResponseInterpretable moveResponseInterpreter = new MoveResponseInterpreter();
        ResponseInterpretable moneyChangeResponseInterpreter = new MoneyChangeResponseInterpreter();
        ResponseInterpretable buyResponseInterpreter = new BuyResponseInterpreter();
        ResponseInterpretable payRentResponseInterpreter = new PayRentResponseInterpreter();
        ResponseInterpretable queueResponseInterpreter = new QueueResponseInterpreter();
        ResponseInterpretable upDownResponseInterpreter = new UpDownResponseInterpreter();
        ResponseInterpretable tokenMovementResponseInterpreter = new TokenMovementResponseInterpreter();
        ResponseInterpretable rollResponseInterpreter = new RollResponseInterpreter();
        ResponseInterpretable specialSquareResponseInterpreter = new SpecialSquareResponseInterpreter();
        ResponseInterpretable jailResponseInterpreter = new JailResponseInterpreter();
        ResponseInterpretable startResponseInterpreter = new StartResponseInterpreter();
        ResponseInterpretable kickResponseInterpreter = new KickResponseInterpreter();
        ResponseInterpretable closeResponseInterpreter = new CloseResponseInterpreter();

        interpreterMap = new HashMap<>();
        interpreterMap.put(Flags.getFlag("Start"), moveResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), moneyChangeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), buyResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), payRentResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), queueResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), upDownResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), upDownResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), tokenMovementResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), rollResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), specialSquareResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), jailResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), jailResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), startResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Kick"), kickResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Close"), closeResponseInterpreter);
    }

    public void interpret(String message){
        char flag = message.charAt(0);

        if (interpreterMap.keySet().contains(flag))
            interpreterMap.get(flag).interpret(message.split("[|]"));
    }

}
