package com.company.order;

public class OrderSumDTO {

    private String productName;
    private int amount;
    private double unitPrice;
    private double price;

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
