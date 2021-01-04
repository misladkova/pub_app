package com.company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(targetEntity=Order.class,cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;

    public int getId() {
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

    public void setId(int id) {
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
