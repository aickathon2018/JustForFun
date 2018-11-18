package com.example.shopranger.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person_ {

    @SerializedName("boundingBox")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("colors")
    @Expose
    private List<Color> colors = null;
    @SerializedName("styles")
    @Expose
    private List<Style> styles = null;
    @SerializedName("garments")
    @Expose
    private List<Garment> garments = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Person_() {
    }

    /**
     *
     * @param boundingBox
     * @param colors
     * @param styles
     * @param garments
     */
    public Person_(BoundingBox boundingBox, List<Color> colors, List<Style> styles, List<Garment> garments) {
        super();
        this.boundingBox = boundingBox;
        this.colors = colors;
        this.styles = styles;
        this.garments = garments;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<Garment> getGarments() {
        return garments;
    }

    public void setGarments(List<Garment> garments) {
        this.garments = garments;
    }

}
