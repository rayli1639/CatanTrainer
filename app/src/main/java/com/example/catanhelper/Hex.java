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
    Resource getRes() {
        return res;
    }
    int getNum() {
        return num;
    }
    boolean getPortCon() {
        return portCon;
    }
}
