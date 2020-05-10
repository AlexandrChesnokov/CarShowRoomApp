package com.alex.dao.impl;

import com.alex.dao.CarDao;
import com.alex.dao.ConnectionPool;
import com.alex.dao.UserDao;
import com.alex.model.Car;
import com.alex.model.Parameters;
import com.alex.model.Role;
import com.alex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
            e.printStackTrace();         //TODO: sdelat' logger i zakritie connection v finally
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
            parameters.setFromYearParam("" + parameters.getFromYearParam() + "-01-01");
        if (parameters.getToYearParam().equals(""))
            parameters.setToYearParam("2045-01-01");
        else
            parameters.setToYearParam("" + parameters.getToYearParam() + "-01-01");
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
            e.printStackTrace();            //TODO: sdelat' logger i zakritie connection v finally
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
        } catch (SQLException ignored) {              //TODO: sdelat' logger i zakritie connection v finally
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
                conn.close();
                return car;
            }
        } catch (SQLException ignored) {          //TODO: sdelat' logger i zakritie connection v finally
        }
        conn.close();
        return null;
    }

    @Override
    public boolean editCarByParams(Car car) {
        Connection conn = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "update models set price = ?, color = ?, hp = ?" +
                            "where id = ?;"
            );

            ps.setDouble(1, car.getPrice());
            ps.setString(2, car.getColor());
            ps.setInt(3, car.getHp());
            ps.setInt(4, car.getId());
            ps.executeUpdate();

            conn.close();
            return true;

        } catch (SQLException ignored) {
            try {
                conn.close();
            } catch (SQLException e) {                   //TODO: sdelat' logger i zakritie connection v finally
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean deleteCarById(int id) {

        Connection conn = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "delete from models where id = ?;"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

            conn.close();
            return true;

        } catch (SQLException ignored) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }                                //TODO: sdelat' logger i zakritie connection v finally
            return false;
        }

    }

    @Override
    public boolean addCar(Car car) {
        Connection conn = ConnectionPool.getInstance().getConnection();
        car.setYear(car.getYear() + "-01-01");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yearIssue = LocalDate.parse(car.getYear(), formatter);

        boolean isPresent = makerIsPresent(car.getMaker_name());

        System.out.println("1 -" + car.toString());

        try {

            if (!isPresent)
                addCarMaker(car.getMaker_name());

            int id = findMakerId(car.getMaker_name());
            PreparedStatement ps =
                    conn.prepareStatement("insert into models (name, maker_id, price, color, yearissue, hp) " +
                            "values (?, ?, ?, ?, ?, ?)");

            System.out.println("4 -" + car.toString());

            ps.setString(1, car.getName());
            ps.setInt(2, id);
            ps.setDouble(3, car.getPrice());
            ps.setString(4, car.getColor());
            ps.setDate(5, java.sql.Date.valueOf(yearIssue));
            ps.setInt(6, car.getHp());
            ps.executeUpdate();
            conn.close();
            return true;



        } catch (SQLException ignored) {
            return false;
        }                                   //TODO: sdelat' logger i zakritie connection v finally

    }

    @Override
    public boolean makerIsPresent(String name) {

        Connection conn = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement psCheckMaker = conn.prepareStatement(
                    "select case when exists(select name from makers\n" +
                            "    where name = ?) then 'present' else 'missing' end;"
            );

            psCheckMaker.setString(1, name);

            ResultSet rs = psCheckMaker.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }

            return result.equals("present");

        } catch (SQLException e) {            //TODO: sdelat' logger i zakritie connection v finally
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addCarMaker(String name) {

        Connection conn = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement psAddMaker =
                    conn.prepareStatement("insert into makers (name) values (?);");
            psAddMaker.setString(1, name);
            psAddMaker.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }

                    //TODO: sdelat' logger i zakritie connection v finally

    }

    @Override
    public int findMakerId(String name) {
        Connection conn3 = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement psFindMakerId = conn3.prepareStatement("select id from makers where name = ?");

            psFindMakerId.setString(1, name);

            ResultSet rsMakerId = psFindMakerId.executeQuery();
            int id = 0;
            if (rsMakerId.next()) {
                id = rsMakerId.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }                                  //TODO: sdelat' logger i zakritie connection v finally

 return 0;
    }

    @Override
    public boolean orderCar(int userId, int carId, int enhanceId) {

        Connection conn = ConnectionPool.getInstance().getConnection();

        double price = sumPrice(carId, enhanceId);

        System.out.println("Параметры пришли в дао - " +" Юзер ид = " +userId +" Кар ид = " +carId + " Енх ид = " + enhanceId);
        try {

            PreparedStatement ps = conn
                    .prepareStatement("insert into orders (user_id, model_id, enhance_id, sum_price) " +
                            "values (?, ?, ?, ?)");

            ps.setInt(1, userId);
            ps.setInt(2, carId);
            ps.setInt(3, enhanceId);
            ps.setDouble(4, price);

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException ex) {           //TODO: sdelat' logger i zakritie connection v finally
                ex.printStackTrace();
            }
            return false;
        }

    }

    public double sumPrice(int carId, int enhanceId) {

        Connection conn = ConnectionPool.getInstance().getConnection();
        double price = 0;
        try {

            PreparedStatement ps = conn.prepareStatement("select car.price + enh.price from models car, enhance enh where car.id = ? and enh.id = ?");

            ps.setInt(1, carId);
            ps.setInt(2, enhanceId);

            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                price = rs.getDouble(1);
            }
            return price;
        } catch (SQLException e) {              //TODO: sdelat' logger i zakritie connection v finally
            //s
        }

        System.out.println("Сум прайс вернул = " + price);
        return price;

    }

    @Override
    public boolean isAvailable(int carId) {
        Connection conn = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("select case when exists(select model_id from orders where model_id = ?)\n" +
                    "then 'true' else 'false' end;");

            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }
            if (result.equals("true")) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            //TODO: sdelat' logger i zakritie connection v finally
        }

        return false;
    }
}

