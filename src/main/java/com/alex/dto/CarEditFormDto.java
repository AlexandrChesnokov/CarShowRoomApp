package com.alex.dto;

import lombok.Data;

import java.util.Objects;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

public class CarEditFormDto {

    private String price;
    private String hp;
    private String color;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "CarEditFormDto{" +
                "price='" + price + '\'' +
                ", hp='" + hp + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEditFormDto that = (CarEditFormDto) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(hp, that.hp) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, hp, color);
    }
}
