package domain.die;

import java.util.ArrayList;

public abstract class Die {
    private String faceValue;
    private ArrayList<String> valueList;
    private String[] faces;

    public Die() {
        this.valueList = new ArrayList<>(); //TODO Lazy init
        this.faces = new String[6];
    }

    public String getFaceValue() {
        return faceValue;
    }

    public ArrayList<String> getValueList() {
        return valueList;
    }

    protected void setFaceValue(String faceValue) { //TODO protected?
        this.faceValue = faceValue;
    }

    protected void setFaces(String[] faces) {
        this.faces = faces;
    }
}
