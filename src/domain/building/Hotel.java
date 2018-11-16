package domain.building;

public class Hotel extends Building {
    public Hotel(int cost) {
        super(cost);
    }

    @Override
    public String getName() {
        return "Hotel";
    }
}
