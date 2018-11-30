package domain.server.board;

/**
 * Abstract parent class for all the squares on the board.
 * It will hold the common elements for all the squares.
 */
public abstract class Square {
    protected String name;

    protected int[] location;


    public Square() {
        this("", 0, 0);
    }

    /**
     * Constructor
     *
     * @param name  Name of the Square
     * @param layer Integer between [0,2] (inclusive). Indicates the layer on the game board.
     * @param index Integer between [0,55] (inclusive). Indicates the index of the square on that layer.
     */
    public Square(String name, int layer, int index) {
        this.name = name;
        this.location = new int[]{layer, index};
    }

    /**
     * Gets Name
     *
     * @return Name of the Square
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name Name of the Square
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets location of the square.
     *
     * @return Position of the Square on the Game Board as an array. <br>
     * First index holds on which layer the square is. <br>
     * Second index holds on which index on that layer the square is.
     */
    public int[] getLocation() {
        return location;
    }

    /**
     * Sets location of the Square
     *
     * @param location Array of length 2 which shows the location.
     */
    public void setLocation(int[] location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return name;
    }
}
