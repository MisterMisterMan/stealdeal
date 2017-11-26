package com.example.stealdeal.stealdeal;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by Glenn on 26.11.2017.
 */

public class Offer implements Serializable {

    private int id;
    private String name;
    private Location location;
    private String createdBy;

    public Offer(int id, String name, Location location, String createdBy){
        this.id = id;
        this.name = name;
        this.location = location;
        this.createdBy = createdBy;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
