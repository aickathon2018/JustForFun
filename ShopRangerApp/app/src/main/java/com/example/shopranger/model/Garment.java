package com.example.shopranger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Garment {

    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("boundingBox")
    @Expose
    private BoundingBox_ boundingBox;
    @SerializedName("confidence")
    @Expose
    private Double confidence;

    /**
     * No args constructor for use in serialization
     *
     */
    public Garment() {
    }

    /**
     *
     * @param typeName
     * @param boundingBox
     * @param confidence
     */
    public Garment(String typeName, BoundingBox_ boundingBox, Double confidence) {
        super();
        this.typeName = typeName;
        this.boundingBox = boundingBox;
        this.confidence = confidence;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BoundingBox_ getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox_ boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}
