package com.alex.model;

import java.util.Objects;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

public class Enhance {

    private int id;
    private String name;
    private double price;
    private int increase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    @Override
    public String toString() {
        return "Enhance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", increase=" + increase +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enhance enhance = (Enhance) o;
        return id == enhance.id &&
                Double.compare(enhance.price, price) == 0 &&
                increase == enhance.increase &&
                Objects.equals(name, enhance.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, increase);
    }
}
