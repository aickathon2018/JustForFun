package com.example.shopranger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Style {

    @SerializedName("styleName")
    @Expose
    private String styleName;
    @SerializedName("confidence")
    @Expose
    private Double confidence;

    /**
     * No args constructor for use in serialization
     *
     */
    public Style() {
    }

    /**
     *
     * @param styleName
     * @param confidence
     */
    public Style(String styleName, Double confidence) {
        super();
        this.styleName = styleName;
        this.confidence = confidence;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}
