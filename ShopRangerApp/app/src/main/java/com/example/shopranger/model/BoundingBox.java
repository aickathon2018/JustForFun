package com.example.shopranger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoundingBox {

    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Integer y;
    @SerializedName("w")
    @Expose
    private Integer w;
    @SerializedName("h")
    @Expose
    private Integer h;

    /**
     * No args constructor for use in serialization
     *
     */
    public BoundingBox() {
    }

    /**
     *
     * @param w
     * @param h
     * @param y
     * @param x
     */
    public BoundingBox(Integer x, Integer y, Integer w, Integer h) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

}