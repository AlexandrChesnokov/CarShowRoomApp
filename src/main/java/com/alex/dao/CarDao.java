package com.alex.dao;

import com.alex.model.Car;
import com.alex.model.Parameters;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {

    List<Car> showAllCars() throws SQLException;

    List<Car> findCarsByParams(Parameters parameters) throws SQLException;

    List<Car> findCarByMaker(String maker) throws SQLException;

    Car findCarById(int id) throws SQLException;

    void editCarByParams(Car car);

    void deleteCarById(int id);

    void addCar(Car car);

    void addCarMaker(String name);

    boolean makerIsPresent(String name) throws SQLException;

    int findMakerId(String name);

    void orderCar(int userId, int carId, int enhanceId);

    boolean isAvailable(int carId);




}
