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
        ResponseInterpretable upgradeResponseInterpreter = new UpgradeResponseInterpreter();
        ResponseInterpretable downgradeResponseInterpreter = new DowngradeResponseInterpreter();
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
        ResponseInterpretable resumeResponseInterpreter = new ResumeResponseInterpreter();
        ResponseInterpretable loadResponseInterpreter = new LoadResponseInterpreter();
        ResponseInterpretable removeResponseInterpreter = new RemoveResponseInterpreter();
        ResponseInterpretable fullResponseInterpreter = new FullResponseInterpreter();
        ResponseInterpretable doubleCounterResponseInterpreter = new DoubleCounterResponseInterpreter();
        ResponseInterpretable finishResponseInterpreter = new FinishResponseInterpreter();
        ResponseInterpretable buttonResponseInterpreter = new ButtonResponseInterpreter();
        ResponseInterpretable mortgageResponseInterpreter = new MortgageResponseInterpreter();
        ResponseInterpretable unmortgageResponseInterpreter = new UnmortgageResponseInterpreter();
        ResponseInterpretable dontMortgageResponseInterpreter = new DontMortgageResponseInterpreter();
        ResponseInterpretable dontUnmortgageResponseInterpreter = new DontUnmortgageResponseInterpreter();
        ResponseInterpretable labelLighterResponseInterpreter = new LabelLighterResponseInterpreter();
        ResponseInterpretable ipResponseInterpreter = new IPResponseInterpreter();


        interpreterMap = new HashMap<>();
        interpreterMap.put(Flags.getFlag("Move"), moveResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Money"), moneyChangeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Buy"), buyResponseInterpreter);
        interpreterMap.put(Flags.getFlag("PayRent"), payRentResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontPayRent"), dontPayRentResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Queue"), queueResponseInterpreter);
        interpreterMap.put(Flags.getFlag("InitQueue"), initQueueResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Upgrade"), upgradeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Downgrade"), downgradeResponseInterpreter); //TODO
        interpreterMap.put(Flags.getFlag("Token"), tokenMovementResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Roll"), rollResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Special"), specialSquareResponseInterpreter);
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
        interpreterMap.put(Flags.getFlag("Resume"), resumeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Load"), loadResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Remove"), removeResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Full"), fullResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DoubleCounter"), doubleCounterResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontBuy"), dontBuyResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Finish"), finishResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Button"), buttonResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Mortgage"), mortgageResponseInterpreter);
        interpreterMap.put(Flags.getFlag("Unmortgage"), unmortgageResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontMortgage"), dontMortgageResponseInterpreter);
        interpreterMap.put(Flags.getFlag("DontUnmortgage"), dontUnmortgageResponseInterpreter);
        interpreterMap.put(Flags.getFlag("LabelLighter"), labelLighterResponseInterpreter);
        interpreterMap.put(Flags.getFlag("IP"), ipResponseInterpreter);
    }

    public void interpret(String message) {
        char flag = message.charAt(0);

        if (interpreterMap.keySet().contains(flag))
            interpreterMap.get(flag).interpret(message.split("[|]"));
    }

}
