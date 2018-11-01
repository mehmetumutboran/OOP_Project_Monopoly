package domain.die;

public abstract class Die {
    private int faceValue;
    private int[] faces;

    public Die() {
        this.faces = new int[6];
    }

    public int getFaceValue() {
        return faceValue;
    }

    protected void setFaceValue(int faceValue) { //TODO protected?
        this.faceValue = faceValue;
    }

    protected void setFaces(int[] faces) {
        this.faces = faces;
    }

    protected int[] getFaces() {
        return faces;
    }

    public abstract void roll();

}
