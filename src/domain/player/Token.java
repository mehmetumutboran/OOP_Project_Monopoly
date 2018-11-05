package domain.player;

public class Token {
    private int location;
    private String color;

    public Token() {
        this(0, "");
    }

    public Token(String color) {
        this(0, color);
    }

    public Token(int location, String color) {
        this.location = location;
        this.color = color;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
