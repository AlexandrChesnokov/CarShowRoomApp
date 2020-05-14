package com.alex.dao.impl;

import com.alex.dao.CarDao;
import com.alex.dao.ConnectionPool;
import com.alex.model.Car;
import com.alex.model.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CarDaoImpl implements CarDao {


    @Override
    public List<Car> showAllCars() {


        List<Car> cars = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {

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
            log.error("SQLException in showAllCars", e);
        }


        return cars;
    }

    @Override
    public List<Car> findCarsByParams(Parameters parameters) {
        if (parameters.getFromYearParam().equals(""))
            parameters.setFromYearParam("1945-01-22");
        else
            parameters.setFromYearParam("" + parameters.getFromYearParam() + "-01-01");
        if (parameters.getToYearParam().equals(""))
            parameters.setToYearParam("2045-01-01");
        else
            parameters.setToYearParam("" + parameters.getToYearParam() + "-01-01");    //FIXME formatted i prisvaivanie v otdelniy method
        if (parameters.getColorNameParam().equals(""))
            parameters.setColorNameParam("%");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromY = LocalDate.parse(parameters.getFromYearParam(), formatter);
        LocalDate toY = LocalDate.parse(parameters.getToYearParam(), formatter);

        List<Car> cars = new ArrayList<>();

        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
            log.error("SQLException in findCarsByParams", e);
        }
        return cars;
    }

    @Override
    public List<Car> findCarByMaker(String maker) {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
        } catch (SQLException e) {
            log.error("SQLException in findCarByMaker", e);
        }

        return cars;
    }

    @Override
    public Car findCarById(int id) throws SQLException {

        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
        } catch (SQLException e) {
            log.error("SQLException in findCarById", e);
        }

        return null;
    }

    @Override
    public boolean editCarByParams(Car car) {
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "update models set price = ?, color = ?, hp = ?" +
                            "where id = ?;"
            );

            ps.setDouble(1, car.getPrice());
            ps.setString(2, car.getColor());
            ps.setInt(3, car.getHp());
            ps.setInt(4, car.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            log.error("SQLException in editCarByParams", e);
            return false;
        }
    }

    @Override
    public boolean deleteCarById(int id) {


        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "delete from models where id = ?;"
            );
            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            log.error("SQLException in deleteCarById", e);
            return false;
        }

    }

    @Override
    public boolean addCar(Car car) {

        car.setYear(car.getYear() + "-01-01");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");    //FIXME formatted i prisvaivanie v otdelniy method
        LocalDate yearIssue = LocalDate.parse(car.getYear(), formatter);

        boolean isPresent = makerIsPresent(car.getMaker_name());

        System.out.println("1 -" + car.toString());

        try (Connection conn = ConnectionPool.getInstance().getConnection()) {

            if (!isPresent)
                addCarMaker(car.getMaker_name());

            int id = findMakerId(car.getMaker_name());
            PreparedStatement ps =
                    conn.prepareStatement("insert into models (name, maker_id, price, color, yearissue, hp) " +
                            "values (?, ?, ?, ?, ?, ?)");

            ps.setString(1, car.getName());
            ps.setInt(2, id);
            ps.setDouble(3, car.getPrice());
            ps.setString(4, car.getColor());
            ps.setDate(5, java.sql.Date.valueOf(yearIssue));
            ps.setInt(6, car.getHp());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            log.error("SQLException in addCar", e);
            return false;
        }

    }

    @Override
    public boolean makerIsPresent(String name) {


        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
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

        } catch (SQLException e) {
            log.error("SQLException in makerIsPresent", e);
        }
        return false;
    }

    @Override
    public boolean addCarMaker(String name) {

        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement psAddMaker =
                    conn.prepareStatement("insert into makers (name) values (?);");
            psAddMaker.setString(1, name);
            psAddMaker.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.error("SQLException in addCarMaker", e);
            return false;
        }
    }

    @Override
    public int findMakerId(String name) {
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement psFindMakerId = conn.prepareStatement("select id from makers where name = ?");

            psFindMakerId.setString(1, name);

            ResultSet rsMakerId = psFindMakerId.executeQuery();
            int id = 0;
            if (rsMakerId.next()) {
                id = rsMakerId.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            log.error("SQLException in findMakerById");
        }

        return 0;
    }

    @Override
    public boolean orderCar(int userId, int carId, int enhanceId) {

        double price = sumPrice(carId, enhanceId);
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement ps = conn
                    .prepareStatement("insert into orders (user_id, model_id, enhance_id, sum_price) " +
                            "values (?, ?, ?, ?)");

            ps.setInt(1, userId);
            ps.setInt(2, carId);
            ps.setInt(3, enhanceId);
            ps.setDouble(4, price);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            log.error("SQLException in orderCar", e);
            return false;
        }

    }

    public double sumPrice(int carId, int enhanceId) {

        double price = 0;
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement ps = conn.prepareStatement("select car.price + enh.price from models car, enhance enh where car.id = ? and enh.id = ?");

            ps.setInt(1, carId);
            ps.setInt(2, enhanceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                price = rs.getDouble(1);
            }
            return price;
        } catch (SQLException e) {
            log.error("SQLException in sumPrice", e);
        }

        System.out.println("Сум прайс вернул = " + price);
        return price;

    }

    @Override
    public boolean isAvailable(int carId) {

        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select case when exists(select model_id from orders where model_id = ?)\n" +
                    "then 'true' else 'false' end;");

            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            String result = "";
            if (rs.next()) {
                result = rs.getString(1);
            }
            return !result.equals("true");

        } catch (SQLException e) {
            log.error("SQLException in isAvailable", e);
        }

        return false;
    }
}

