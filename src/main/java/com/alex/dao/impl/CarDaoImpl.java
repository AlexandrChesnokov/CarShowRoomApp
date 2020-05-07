package com.alex.dao.impl;

import com.alex.dao.CarDao;
import com.alex.dao.ConnectionPool;
import com.alex.dao.UserDao;
import com.alex.model.Car;
import com.alex.model.Parameters;
import com.alex.model.Role;
import com.alex.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Component
public class CarDaoImpl implements CarDao {





    @Override
    public List<Car> showAllCars() throws SQLException {
        Connection conn = ConnectionPool.getInstance().getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select m1.*, m2.name\n" +
                    "from models m1 JOIN makers m2 on m1.maker_id = m2.id;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt(1));
                car.setName(rs.getString(2));
                car.setPrice(rs.getDouble(4));
                car.setColor(rs.getString(5));
                car.setYear(rs.getString(6));
                car.setHp(rs.getInt(7));
                car.setMaker_name(rs.getString(8));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn.close();
        return cars;
    }

    @Override
    public List<Car> findCarsByParams(Parameters parameters) throws SQLException {
        Connection conn = ConnectionPool.getInstance().getConnection();

        if (parameters.getFromYearParam().equals(""))
            parameters.setFromYearParam("1945-01-22");
        else
            parameters.setFromYearParam(""+parameters.getFromYearParam()+"-01-01");
        if (parameters.getToYearParam().equals(""))
            parameters.setToYearParam("2045-01-01");
        else
            parameters.setToYearParam(""+parameters.getToYearParam()+"-01-01");
        if (parameters.getColorNameParam().equals(""))
            parameters.setColorNameParam("%");





        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromY = LocalDate.parse(parameters.getFromYearParam(), formatter);
        LocalDate toY = LocalDate.parse(parameters.getToYearParam(), formatter);



        System.out.println(fromY + " " + toY);



        List<Car> cars = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select model.*, maker.name from models model join makers maker on model.maker_id = maker.id where yearIssue between ? and ?" +
                            "and price between ? and ? " +
                            "and hp between ? and ?" +
                            "and color like ?"
            );
            ps.setDate(1, java.sql.Date.valueOf(fromY));
            ps.setDate(2, java.sql.Date.valueOf(toY));
            ps.setDouble(3, parameters.getFromPriceParam());
            ps.setDouble(4, parameters.getToPriceParam());
            ps.setDouble(5, parameters.getFromHpParam());
            ps.setDouble(6, parameters.getToHpParam());
            ps.setString(7, parameters.getColorNameParam());



            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt(1));
                car.setName(rs.getString(2));
                car.setPrice(rs.getDouble(4));
                car.setColor(rs.getString(5));
                car.setYear(rs.getString(6));
                car.setHp(rs.getInt(7));
                car.setMaker_name(rs.getString(8));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn.close();
        return cars;




    }

    @Override
    public List<Car> findCarByMaker(String maker) throws SQLException {
        Connection conn = ConnectionPool.getInstance().getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select models.*,  makers.name\n" +
                            "from makers  join models on makers.id = models.maker_id\n" +
                            "where makers.name = ?;"
            );
            ps.setString(1, maker);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt(1));
                car.setName(rs.getString(2));
                car.setPrice(rs.getDouble(4));
                car.setColor(rs.getString(5));
                car.setYear(rs.getString(6));
                car.setHp(rs.getInt(7));
                car.setMaker_name(rs.getString(8));
                System.out.println(car.getName());
                cars.add(car);
            }
        } catch (SQLException ignored) {
        }
        conn.close();
        return cars;
    }

    @Override
    public Car findCarById(int id) throws SQLException {
        Connection conn = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select model.*, maker.name\n" +
                            "from models model join makers maker on model.maker_id = maker.id\n" +
                            "where model.id = ?;"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt(1));
                car.setName(rs.getString(2));
                car.setPrice(rs.getDouble(4));
                car.setColor(rs.getString(5));
                car.setYear(rs.getString(6));
                car.setHp(rs.getInt(7));
                car.setMaker_name(rs.getString(8));
                return car;
            }
        } catch (SQLException ignored) {
        }
        conn.close();
        return null;
    }

    @Override
    public void editCarByParams(Car car) {
        Connection conn = ConnectionPool.getInstance().getConnection();






        try {
            PreparedStatement ps = conn.prepareStatement(
                    "update models set price = ?, color = ?, hp = ?" +
                            "where id = ?;"
            );

            System.out.println("CARDAO START");
            System.out.println(car.getPrice() + " " + car.getColor() + " " + car.getHp());
            ps.setDouble(1, car.getPrice());
            ps.setString(2, car.getColor());
            ps.setInt(3, car.getHp());
            ps.setInt(4, car.getId());
            ps.executeUpdate();

            conn.close();

        } catch (SQLException ignored) {
        }

    }
}

