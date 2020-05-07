package com.alex.service.impl;


import com.alex.dao.CarDao;
import com.alex.model.Car;
import com.alex.model.Parameters;
import com.alex.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarDao carDao;


    @Override
    public List<Car> showAllCars() {
        try {
            return carDao.showAllCars();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findCarByMaker(String maker) {
        try {
            return carDao.findCarByMaker(maker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car findCarById(int id) {
        try {
            return carDao.findCarById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findCarByParams(Parameters parameters) {
        try {
            return carDao.findCarsByParams(parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editCarByParams(Car car) {
        carDao.editCarByParams(car);
    }
}
