package com.company;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private int id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Active", nullable = false)
    private boolean isActive;
    @Column(name = "Adult")
    private boolean isAdult;
    @Column(name = "Pocket", nullable = false)
    private double pocket;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
}
