package com.company.user;

import com.company.order.OrderDTO;

import java.util.List;

public class UserDTO {

    private String username;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
    private List<OrderDTO> orders;

    public void setPocket(double pocket) {
        this.pocket = pocket;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPocket() {
        return pocket;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
