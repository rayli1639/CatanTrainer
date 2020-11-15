package com.example.catanhelper;

public class Hex {
    private Resource res;
    private int num;
    private boolean portCon;
    Hex(boolean portCon) {
        this(Resource.DESERT, 0, portCon);
    }

    /**
     * Constructs a hex with given resource and portCon data.
     */
    Hex(Resource res, int num, boolean portCon) {
        this.res = res;
        this.num = num;
        this.portCon = portCon;
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
