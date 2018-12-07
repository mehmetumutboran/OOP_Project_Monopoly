package domain.client;

import domain.client.interpreter.*;
import domain.util.Flags;

import java.util.HashMap;

/**
 * There should be only UI Updater inside it.
 */
public class ResponseInterpreter {
    private static ResponseInterpreter ourInstance;

    public static ResponseInterpreter getInstance() {
        if (ourInstance == null)
            ourInstance = new ResponseInterpreter();
        return ourInstance;
    }

    private HashMap<Character, ResponseInterpretable> interpreterMap;

    private ResponseInterpreter() {
        ResponseInterpretable moveResponseInterpreter = new MoveResponseInterpreter();
        ResponseInterpretable moneyChangeResponseInterpreter = new MoneyChangeResponseInterpreter();
        ResponseInterpretable buyResponseInterpreter = new BuyResponseInterpreter();
        ResponseInterpretable dontBuyResponseInterpreter = new DontBuyResponseInterpreter();
        ResponseInterpretable payRentResponseInterpreter = new PayRentResponseInterpreter();
        ResponseInterpretable dontPayRentResponseInterpreter = new DontPayRentResponseInterpreter();
        ResponseInterpretable queueResponseInterpreter = new QueueResponseInterpreter();
        ResponseInterpretable initQueueResponseInterpreter = new InitQueueResponseInterpreter();
        ResponseInterpretable upDownResponseInterpreter = new UpDownResponseInterpreter();
        ResponseInterpretable tokenMovementResponseInterpreter = new TokenMovementResponseInterpreter();
        ResponseInterpretable rollResponseInterpreter = new RollResponseInterpreter();
        ResponseInterpretable specialSquareResponseInterpreter = new SpecialSquareResponseInterpreter();
        ResponseInterpretable jailResponseInterpreter = new JailResponseInterpreter();
        ResponseInterpretable startResponseInterpreter = new StartResponseInterpreter();
        ResponseInterpretable dontStartResponseInterpreter = new DontStartResponseInterpreter();
        ResponseInterpretable kickResponseInterpreter = new KickResponseInterpreter();
        ResponseInterpretable closeResponseInterpreter = new CloseResponseInterpreter();
        ResponseInterpretable addPlayerResponseInterpreter = new AddPlayerResponseInterpreter();
        ResponseInterpretable addPlayerListResponseInterpreter = new AddPlayerListResponseInterpreter();
        ResponseInterpretable addBotResponseInterpreter = new AddBotResponseInterpreter();
        ResponseInterpretable colorChangeResponseInterpreter = new ColorChangeResponseInterpreter();
        ResponseInterpretable dontchangecolorResponseInterpreter = new DontChangeColorResponseInterpreter();
        ResponseInterpretable readinessResponseInterpreter = new ReadinessResponseInterpreter();
        ResponseInterpretable saveResponseInterpreter = new SaveResponseInterpreter();
        ResponseInterpretable pauseResponseInterpreter = new PauseResponseInterpreter();
        ResponseInterpretable loadResponseInterpreter = new LoadResponseInterpreter();
        ResponseInterpretable removeResponseInterpreter = new RemoveResponseInterpreter();
        ResponseInterpretable fullResponseInterpreter = new FullResponseInterpreter();
        ResponseInterpretable doubleCounterResponseInterpreter = new DoubleCounterResponseInterpreter();

        interpreterMap = new HashMap<>();
        interpreterMap.put(Flags.getFlag("Move"), moveResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), moneyChangeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Buy"), buyResponseInterpreter);
        interpreterMap.put(Flags.getFlag("PayRent"), payRentResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontPayRent"), dontPayRentResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), queueResponseInterpreter);
        interpreterMap.put(Flags.getFlag("InitQueue"), initQueueResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), upDownResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), upDownResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), tokenMovementResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Roll"), rollResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), specialSquareResponseInterpreter);
        interpreterMap.put(Flags.getFlag("GoToJail"), jailResponseInterpreter);
        interpreterMap.put(Flags.getFlag("GoOutOfJail"), jailResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Start"), startResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontStart"), dontStartResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Kick"), kickResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Close"), closeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("AddPlayer"), addPlayerResponseInterpreter);
        interpreterMap.put(Flags.getFlag("AddPlayerList"), addPlayerListResponseInterpreter);
        interpreterMap.put(Flags.getFlag("AddBot"), addBotResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Color"), colorChangeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontChangeColor"), dontchangecolorResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Readiness"), readinessResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Save"), saveResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Pause"), pauseResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Load"), loadResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Remove"), removeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Full"), fullResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DoubleCounter"), doubleCounterResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontBuy"), dontBuyResponseInterpreter);
    }

    public void interpret(String message) {
        char flag = message.charAt(0);

        if (interpreterMap.keySet().contains(flag))
            interpreterMap.get(flag).interpret(message.split("[|]"));
    }

}
