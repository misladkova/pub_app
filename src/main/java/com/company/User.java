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

    private String username;
    private String password;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public void setName(String username) {
        this.username = username;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
