package com.example.shopranger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color {

    @SerializedName("hex")
    @Expose
    private String hex;
    @SerializedName("colorName")
    @Expose
    private String colorName;
    @SerializedName("colorGeneralCategory")
    @Expose
    private String colorGeneralCategory;
    @SerializedName("ratio")
    @Expose
    private Double ratio;

    /**
     * No args constructor for use in serialization
     *
     */
    public Color() {
    }

    /**
     *
     * @param colorName
     * @param hex
     * @param ratio
     * @param colorGeneralCategory
     */
    public Color(String hex, String colorName, String colorGeneralCategory, Double ratio) {
        super();
        this.hex = hex;
        this.colorName = colorName;
        this.colorGeneralCategory = colorGeneralCategory;
        this.ratio = ratio;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorGeneralCategory() {
        return colorGeneralCategory;
    }

    public void setColorGeneralCategory(String colorGeneralCategory) {
        this.colorGeneralCategory = colorGeneralCategory;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

}
