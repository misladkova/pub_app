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

    @OneToMany(targetEntity=Order.class , fetch = FetchType.LAZY, mappedBy = "user")

    private List<Order> orders = new ArrayList<>();
    private String username;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public boolean getIsActive() {
        return isActive;
    }
    public boolean getIsAdult() {
        return isAdult;
    }
    public double getPocket() {
        return pocket;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
