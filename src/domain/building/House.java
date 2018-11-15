package domain.building;

public class House extends Building{
    public House(int cost) {
        super(cost);
    }

    @Override

    public String getName() {
        return "House";
    }
}
