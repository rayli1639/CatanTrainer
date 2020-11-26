package com.example.catanhelper;

public class Hex {
    private Resource res;
    private int num;
    private boolean portCon;
    private float center;

    Hex(boolean portCon, float center) {
        this(Resource.DESERT, 0, portCon, center);
    }

    /**
     * Constructs a hex with given resource and portCon data.
     */
    Hex(Resource res, int num, boolean portCon, float center) {
        this.res = res;
        this.num = num;
        this.portCon = portCon;
        this.center = center;
    }
    public Resource getRes() {
        return res;
    }
    public int getNum() {
        return num;
    }
    public boolean getPortCon() {
        return portCon;
    }
    @Override
    public String toString() {
        return String.format("[[%s : %d]]", res, num);
    }
}
