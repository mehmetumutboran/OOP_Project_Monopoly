package gui.baseFrame;

public class TokenFactory {

    private static TokenFactory tf;
    private TokenLabel newTkn;

    private TokenFactory() {

    }

    public static TokenFactory getInstance() {
        if (tf == null) {
            tf = new TokenFactory();
        }
        return tf;
    }

    public TokenLabel getNewToken(String name) {
        newTkn = new TokenLabel(name);
        return newTkn;
    }

}
