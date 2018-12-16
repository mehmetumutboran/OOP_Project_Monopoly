package domain.server.board;

public interface Upgradable {
    /**
     * Checks other same colored squares or railroad to determine whether the square is upgradable
     * @return whether the square is upgradable
     */
    boolean isUpgradable();

    /**
     * Checks other same colored squares or railroad to determine whether the square is downgradable
     * @return whether the square is downgradable
     */
    boolean isDowngradable();

    void updateRent();

    boolean isUpgraded();

    int getBuildingCount();

    int getUpgradeLevel(); // Can be 0, 1, 2, 3 indicating house, hotel, skyscraper, depot, respectively

    /**
     * Builds the appropriate building to the square
     */
    void upgrade();

    /**
     * Demolishes the appropriate building to the square
     */
    void downgrade();

    int getBuildingCost();

    //TODO!!! Add this methods

}
