package depress_analizator.model;

public class Pixel {
    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setS(double s) {
        this.s = s;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getS() {
        return s;
    }

    public double getL() {
        return l;
    }

    private double h;
    private double s;
    private double l;
}
