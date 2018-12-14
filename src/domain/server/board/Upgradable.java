package domain.server.board;

public interface Upgradable {
    boolean isUpgradable();

    boolean isDowngradable();

    void updateRent();

    boolean isUpgraded();

    int getBuildingCount();

    int getUpgradeLevel(); // Can be 0, 1, 2, 3 indicating house, hotel, skyscraper, depot, respectively

    void upgrade();

    void downgrade();

    int getBuildingCost();

    boolean hasUpgradedSquareInGroup();

    //TODO!!! Add this methods

}
