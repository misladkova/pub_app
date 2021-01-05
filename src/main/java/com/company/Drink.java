package com.company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Drink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(targetEntity=Order.class,cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "drink")
    private List<Order> orders = new ArrayList<>();

    private String productName;
    private double price;
    private boolean isForAdult;

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
