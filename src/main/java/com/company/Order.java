package com.company;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDERkk")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    private int amount;
    private double price;

    @ManyToOne(targetEntity=User.class, fetch = FetchType.LAZY)
//    @JoinColumn(name="userId")//Optional
    private User user;

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
