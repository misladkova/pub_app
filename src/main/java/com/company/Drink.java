package com.company;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DRINK")
public class Drink {

    private String id;
    private String productName;
    private double price;
    private boolean isForAdult;

    public String getId() {
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

    public void setId(String id) {
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
