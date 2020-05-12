package com.alex.dto;

import com.alex.model.Car;
import com.alex.model.Parameters;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */
public class CarDto {

    private String name;
    private String maker_name;
    private String price;
    private String color;
    private String year;
    private String hp;


    public Car toCar() {
        Car car = new Car();
        car.setName(name);
        car.setMaker_name(maker_name);
        car.setPrice(Double.parseDouble(price));
        car.setColor(color);
        car.setYear(year);
        car.setHp(Integer.parseInt(hp));

        return car;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "name='" + name + '\'' +
                ", maker_name='" + maker_name + '\'' +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                ", hp='" + hp + '\'' +
                '}';
    }
}
