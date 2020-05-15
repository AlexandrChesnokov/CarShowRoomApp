package com.alex.model;


import java.util.Objects;

public class Car {

    private int id;
    private String name;
    private String maker_name;
    private double price;
    private String color;
    private String year;
    private int hp;

    public Car() {
    }

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

    public String getMaker_name() {
        return maker_name;
    }

    public void setMaker_name(String maker_name) {
        this.maker_name = maker_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker_name='" + maker_name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                ", hp=" + hp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Double.compare(car.price, price) == 0 &&
                hp == car.hp &&
                Objects.equals(name, car.name) &&
                Objects.equals(maker_name, car.maker_name) &&
                Objects.equals(color, car.color) &&
                Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maker_name, price, color, year, hp);
    }
}
