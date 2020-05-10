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

    boolean editCarByParams(Car car);

    boolean deleteCarById(int id);

    boolean addCar(Car car);

    boolean addCarMaker(String name);

    boolean makerIsPresent(String name) throws SQLException;

    int findMakerId(String name);

    boolean orderCar(int userId, int carId, int enhanceId);

    boolean isAvailable(int carId);




}
