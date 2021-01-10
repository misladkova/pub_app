package com.company;

import java.util.List;

public class DrinkDTO {

    private String productName;
    private double price;
    private boolean isForAdult;

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isForAdult() {
        return isForAdult;
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
