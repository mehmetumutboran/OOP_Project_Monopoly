package domain.building;

public class TrainDepot extends Building {

    public TrainDepot(int cost) {
        super(cost);
    }

    @Override
    public String getName() {
        return "TrainDepot";
    }
}
