package com.company;

public class User {

    private String id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public double getPocket() {
        return pocket;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public void setPocket(double pocket) {
        this.pocket = pocket;
    }
}
