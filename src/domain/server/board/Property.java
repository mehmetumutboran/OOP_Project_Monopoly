package domain.server.board;

import domain.server.building.Building;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.building.Skyscraper;
import domain.util.MessageConverter;

import java.util.ArrayList;

public class Property extends DeedSquare implements Upgradable {
    private String color;
    private ArrayList<Building> buildingList;
    private boolean hasUpgrade;
    //    private int[] rents = new int[10];
    private int currentRent;

    public Property() {
        this("", 0, 0, 0, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, "");
    }

    public Property(String name, int layer, int index, int buyValue, int[] rents, String color) {
        super(name, layer, index, buyValue, null, rents);
        // this.rents = rents.clone();
        this.currentRent = rents[0];
        this.color = color;
        this.buildingList = new ArrayList<>();
        hasUpgrade = false;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUpgraded(boolean hasUpgrade) {
        this.hasUpgrade = hasUpgrade;
    }


    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(ArrayList<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public int[] getRents() {
        return rents;
    }

    public void setRents(int[] rents) {
        this.rents = rents;
    }

    public int getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(int currentRent) {
        this.currentRent = currentRent;
    }


//    public boolean isUpgradable(Property square,String name) {
//        //if(square.getBuildingList().isEmpty()) return false;
//        if(!square.getBuildingList().isEmpty() && square.getBuildingList().get(0) instanceof Skyscraper) return false;
//        boolean checker = false;
//        for (DeedSquare sq : Board.getInstance().getSameColoredSquares(square.getColor())) {
//          //  if(square.getName().equals(sq.getName()))continue;
//            if(sq.getOwner()==null) continue;
//            else if(!sq.getOwner().equals(square.getOwner())) continue;
//            if(!square.getBuildingList().isEmpty()&& !((Property)sq).getBuildingList().isEmpty()&& square.getBuildingList().get(0) instanceof Hotel &&
//                    (((Property)sq).getBuildingList().get(0) instanceof Hotel || ((Property)sq).getBuildingList().get(0) instanceof Skyscraper)) {
//                checker = true;
//            }else if(!((Property)sq).getBuildingList().isEmpty() && square.getBuildingCount()==4 &&
//                    (((Property)sq).getBuildingList().get(0) instanceof Hotel || ((Property)sq).getBuildingCount()==4)){
//                checker = true;
//            } else if (square.getBuildingCount() == 3 &&
//                    (((Property) sq).getBuildingCount() == 3 || ((Property) sq).getBuildingCount() == 4)) {
//                checker = true;
//            }else if (square.getBuildingCount()==2 &&
//                    (((Property)sq).getBuildingCount()==2 || ((Property)sq).getBuildingCount()==3)){
//                checker = true;
//            } else if (square.getBuildingCount() == 1 &&
//                    (((Property) sq).getBuildingCount() == 1 || ((Property) sq).getBuildingCount() == 2)) {
//                checker = true;
//            }else if (square.getBuildingCount()==0 &&
//                    (((Property)sq).getBuildingCount()==0 || ((Property)sq).getBuildingCount()==1)){
//                checker =true;
//            }
//            else {
//                return false;
//            }
//        }
//        return checker;
//    }
//    public boolean isDowngradable(Property square) {
//        boolean checker = false;
//        if (square.getBuildingList().isEmpty()) return false;
//        for (DeedSquare sq : Board.getInstance().getSameColoredSquares(square.getColor())) {
//            if(sq.getOwner()==null)continue;
//            else if (!sq.getOwner().equals(square.getOwner()))continue;
//            if (!((Property)sq).getBuildingList().isEmpty() && square.getBuildingList().get(0) instanceof Skyscraper &&
//                    (((Property) sq).getBuildingList().get(0) instanceof Skyscraper || ((Property) sq).getBuildingList().get(0) instanceof Hotel)) {
//                checker = true;
//            }else if(!((Property)sq).getBuildingList().isEmpty() && square.getBuildingList().get(0) instanceof Hotel &&
//                    (((Property)sq).getBuildingList().get(0) instanceof Hotel
//                            || ((Property)sq).getBuildingCount()==4)){
//                checker=true;
//            }else if(square.getBuildingCount()==4 &&
//                (((Property)sq).getBuildingCount()==4 || ((Property)sq).getBuildingCount()==3)){
//            checker = true;
//
//            }else if (square.getBuildingCount()==3 &&
//                (((Property)sq).getBuildingCount()==3 || ((Property)sq).getBuildingCount()==2)){
//            checker = true;
//            }else if (square.getBuildingCount()==2 &&
//                (((Property)sq).getBuildingCount()==2 || ((Property)sq).getBuildingCount()==1)){
//            checker = true;
//            }else if (square.getBuildingCount()==1 &&
//                    (((Property)sq).getBuildingCount()==1 || ((Property)sq).getBuildingCount()==0)) {
//                checker = true;
//            }
//            else {
//                return false;
//            }
//        }
//        return checker;
//    }
    @Override
    public String generateSaveInfo() {
        return super.generateSaveInfo() + ";" +
                color + ";" +
                MessageConverter.convertListToString(buildingList) + ";" +
                hasUpgrade + "+";
    }

    @Override
    public boolean isUpgraded() {
        return hasUpgrade;
    }

    @Override
    public int getBuildingCount() {
        return this.buildingList.size();
    }

    @Override
    public int getUpgradeLevel() {
        if (buildingList.isEmpty()) return 0;
        if (buildingList.get(0) instanceof Skyscraper)
            return 6;
        else if (buildingList.get(0) instanceof Hotel) {
            return 5;
        }
        return buildingList.size();
    }

    @Override
    public void upgrade() {
        //@requires isUpgradable true
        //@effects buildingList, hasUpgrade, currentRent
        if (getUpgradeLevel() <= 3) buildingList.add(new House(buildingCost));
        else if (getUpgradeLevel() == 4) {
            buildingList.clear();
            buildingList.add(new Hotel(buildingCost));
        } else {
            buildingList.clear();
            buildingList.add(new Skyscraper(buildingCost));
        }
        hasUpgrade = true;
        updateRent();
    }

    @Override
    public void downgrade() {
        //@requires isDowngradable true
        //@effects buildingList, hasUpgrade, currentRent
        if (getUpgradeLevel() >= 1 && getUpgradeLevel() <= 4) {
            buildingList.remove(buildingList.size() - 1);
        } else if (getUpgradeLevel() == 5) {
            buildingList.clear();
            for (int i = 0; i < 4; i++)
                buildingList.add(new House(buildingCost));
        } else if (getUpgradeLevel() == 6) {
            buildingList.remove(0);
            buildingList.add(new Hotel(buildingCost));
        }
        if (buildingList.isEmpty()) hasUpgrade = false;
        updateRent();

    }

    @Override
    public int getBuildingCost() {
        return this.buildingCost;
    }

    @Override
    public boolean isUpgradable() {
        if (!buildingList.isEmpty() && buildingList.get(0) instanceof Skyscraper) return false;
        boolean checker = false;
        for (Property sq : Board.getInstance().getSameColoredSquares(color)) {
            //  if(square.getName().equals(sq.getName()))continue;
            if (sq.getOwner() == null) continue;
            else if (!sq.getOwner().equals(owner)) continue;
            else if (sq.equals(this)) continue;

            int levelDiff = ((Upgradable) sq).getUpgradeLevel() - getUpgradeLevel();

            if (levelDiff == 0 || levelDiff == 1)
                checker = true;

            else
                return false;

//            if (!buildingList.isEmpty() && !sq.getBuildingList().isEmpty() && buildingList.get(0) instanceof Hotel &&
//                    (sq.getBuildingList().get(0) instanceof Hotel || sq.getBuildingList().get(0) instanceof Skyscraper)) {
//                checker = true;
//            } else if (!sq.getBuildingList().isEmpty() && getBuildingCount() == 4 &&
//                    (sq.getBuildingList().get(0) instanceof Hotel || sq.getBuildingCount() == 4)) {
//                checker = true;
//            } else if (getBuildingCount() == 3 &&
//                    (sq.getBuildingCount() == 3 || sq.getBuildingCount() == 4)) {
//                checker = true;
//            } else if (getBuildingCount() == 2 &&
//                    (sq.getBuildingCount() == 2 || sq.getBuildingCount() == 3)) {
//                checker = true;
//            } else if (getBuildingCount() == 1 &&
//                    (sq.getBuildingCount() == 1 || sq.getBuildingCount() == 2)) {
//                checker = true;
//            } else if (getBuildingCount() == 0 &&
//                    (sq.getBuildingCount() == 0 || sq.getBuildingCount() == 1)) {
//                checker = true;
//            } else {
//                return false;
//            }
        }
        return checker;
    }

    @Override
    public boolean isDowngradable() {
        boolean checker = false;
        if (buildingList.isEmpty()) return false;
        for (Property sq : Board.getInstance().getSameColoredSquares(color)) {
            if (sq.getOwner() == null) continue;
            else if (!sq.getOwner().equals(owner)) continue;
            else if (sq.equals(this)) continue;
            int levelDiff = getUpgradeLevel() - ((Upgradable) sq).getUpgradeLevel();
            if (levelDiff == 0 || levelDiff == 1)
                checker = true;
            else
                return false;
        }
//        boolean checker = false;
//        if (square.getBuildingList().isEmpty()) return false;
//        for (DeedSquare sq : Board.getInstance().getSameColoredSquares(square.getColor())) {
//            if (sq.getOwner() == null) continue;
//            else if (!sq.getOwner().equals(square.getOwner())) continue;
//            if (!((Property) sq).getBuildingList().isEmpty() && square.getBuildingList().get(0) instanceof Skyscraper &&
//                    (((Property) sq).getBuildingList().get(0) instanceof Skyscraper || ((Property) sq).getBuildingList().get(0) instanceof Hotel)) {
//                checker = true;
//            } else if (!((Property) sq).getBuildingList().isEmpty() && square.getBuildingList().get(0) instanceof Hotel &&
//                    (((Property) sq).getBuildingList().get(0) instanceof Hotel
//                            || ((Property) sq).getBuildingCount() == 4)) {
//                checker = true;
//            } else if (square.getBuildingCount() == 4 &&
//                    (((Property) sq).getBuildingCount() == 4 || ((Property) sq).getBuildingCount() == 3)) {
//                checker = true;
//
//            } else if (square.getBuildingCount() == 3 &&
//                    (((Property) sq).getBuildingCount() == 3 || ((Property) sq).getBuildingCount() == 2)) {
//                checker = true;
//            } else if (square.getBuildingCount() == 2 &&
//                    (((Property) sq).getBuildingCount() == 2 || ((Property) sq).getBuildingCount() == 1)) {
//                checker = true;
//            } else if (square.getBuildingCount() == 1 &&
//                    (((Property) sq).getBuildingCount() == 1 || ((Property) sq).getBuildingCount() == 0)) {
//                checker = true;
//            } else {
//                return false;
//            }
//        }
//        return checker;
        return checker;
    }

    @Override
    public void updateRent() {
        if (this.buildingList.isEmpty()) {
            setCurrentRent(rents[0]);
        } else if (this.buildingList.get(0).getName().equals("Skyscrapper")) {
            setCurrentRent(rents[6]);
        } else if (this.buildingList.get(0).getName().equals("Hotel")) {
            setCurrentRent(rents[5]);
        } else {
            if (this.buildingList.size() == 4) {
                setCurrentRent(rents[4]);
            } else if (this.buildingList.size() == 3) {
                setCurrentRent(rents[3]);
            } else if (this.buildingList.size() == 2) {
                setCurrentRent(rents[2]);
            } else {
                setCurrentRent(rents[1]);
            }
        }
    }

    public boolean repOK(){
        super.repOK();
        if(this.currentRent<0)return false;
        if(buildingList.size()>4)return false;
        if(!this.color.equals("PINK") && !this.color.equals("LIGHTGREEN") && !this.color.equals("LIGHTYELLOW")
                && !this.color.equals("MEDIUMBLUE") && !this.color.equals("PURPLE") && !this.color.equals("DARKOLIVEGREEN") && !this.color.equals("LIGHTPINK") &&
                !this.color.equals("BROWN") && !this.color.equals("MEDIUMPURPLE") && !this.color.equals("LIGHTBLUE") &&
                !this.color.equals("DEEPPINK") && !this.color.equals("ORANGE") && !this.color.equals("RED")
        && !this.color.equals("YELLOW") && !this.color.equals("GREEN") && !this.color.equals("BLUE") &&
                !this.color.equals("WHITE") && !this.color.equals("BLACK") && !this.color.equals("GRAY") && !this.color.equals("SANDYBROWN"))return false;
        return true;
    }

}
