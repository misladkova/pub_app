package com.company;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonBackReference
    private User user;
//    public void setUser(User user){this.user=user;}

    public User getUser() {
        return user;
    }

    @ManyToOne(targetEntity=Drink.class, fetch = FetchType.LAZY)
    private Drink drink;
//    public void setDrink(Drink drink){this.drink=drink;}

//    public Drink getDrink() {
//        return drink;
//    }

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
