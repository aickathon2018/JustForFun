package com.example.shopranger.model;

public class Reservation {

    private String refNumber, licenseNumber, date, time, mall, floor;

    public Reservation(String refNumber, String licenseNumber, String date,String time, String mall, String floor){
        this.refNumber = refNumber;
        this.licenseNumber = licenseNumber;
        this.date = date;
        this.time = time;
        this.mall = mall;
        this.floor = floor;
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }
}
