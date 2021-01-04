package com.company;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DRINK")
public class Drink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private int id;
    @Column(name = "Product_name")
    private String productName;
    @Column(name = "Price")
    private double price;
    @Column(name = "For_adults")
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
