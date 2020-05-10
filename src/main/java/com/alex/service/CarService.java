package com.alex.service;

import com.alex.dao.CarDao;
import com.alex.model.Car;
import com.alex.model.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CarService {

    List<Car> showAllCars();

    List<Car> findCarByMaker(String maker);

    Car findCarById(int id);

    List<Car> findCarByParams(Parameters parameters);

    boolean editCarByParams(Car car);

    boolean deleteCarById(int id);

    boolean addCar(Car car);

    boolean orderCar(int userId, int carId, int enhanceId);

    boolean isAvailable(int id);


}
