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

        public TokenLabel getNewToken(String name, String color){
            newTkn = new TokenLabel(name, color);
            return newTkn;
        }

    }
