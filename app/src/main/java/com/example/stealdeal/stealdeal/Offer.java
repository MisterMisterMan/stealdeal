package com.example.stealdeal.stealdeal;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by Glenn on 26.11.2017.
 */

public class Offer implements Serializable {

    private int id;
    private String name;
    private int quantity;
    private Long expiryDate;
    private double longitudeMin;
    private double longitudeMax;
    private double latitudeMin;
    private double latitudeMax;
    private double price;
    private String userName;

    public Offer(int id, String name, int quantity, Long date, double longitudeMin, double longitudeMax, double latitudeMin, double latitudeMax, double price, String userName) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = date;
        this.longitudeMin = longitudeMin;
        this.longitudeMax = longitudeMax;
        this.latitudeMin = latitudeMin;
        this.latitudeMax = latitudeMax;
        this.price = price;
        this.userName = userName;
    }

    public Offer(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getLongitudeMin() {
        return longitudeMin;
    }

    public void setLongitudeMin(double longitudeMin) {
        this.longitudeMin = longitudeMin;
    }

    public double getLongitudeMax() {
        return longitudeMax;
    }

    public void setLongitudeMax(double longitudeMax) {
        this.longitudeMax = longitudeMax;
    }

    public double getLatitudeMin() {
        return latitudeMin;
    }

    public void setLatitudeMin(double latitudeMin) {
        this.latitudeMin = latitudeMin;
    }

    public double getLatitudeMax() {
        return latitudeMax;
    }

    public void setLatitudeMax(double latitudeMax) {
        this.latitudeMax = latitudeMax;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
