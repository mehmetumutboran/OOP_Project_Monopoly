package domain.board;

public abstract class Square {
    private String name;

    private int[] index;

    public Square(String name , int layer , int index) {
        this.name = name; this.index[0] = layer; this.index[1] = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
