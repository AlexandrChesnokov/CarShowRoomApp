package com.alex.controller;


import com.alex.dao.CarDao;
import com.alex.dao.UserDao;
import com.alex.model.Car;
import com.alex.model.Parameters;
import com.alex.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/cars")
    public String getCars(Model model) throws SQLException {
        model.addAttribute("cars", carService.showAllCars());
        return "cars";

    }

    @GetMapping("/searchCarsByMaker")
    public String searchCars(Model model, @RequestParam(value = "")String maker) {
        String carName = maker;
        model.addAttribute("cars", carService.findCarByMaker(carName));
        return "cars";
    }

    @GetMapping("/viewCar")
    public String viewCar(Model model, @RequestParam(name = "id") int id) {
        System.out.println(id);
        model.addAttribute("car", carService.findCarById(id));
        return "viewCar";
    }

    @GetMapping("/searchCarsByParams")
    public String searchCarsByParams(@RequestParam(value = "fromYear") String fromYear,
                                     @RequestParam(value = "toYear") String toYear,
                                     @RequestParam(value = "fromPrice") double fromPrice,
                                     @RequestParam(value = "toPrice") double toPrice,
                                     @RequestParam(value = "colorName") String colorName,
                                     @RequestParam(value = "fromHp") int fromHp,
                                     @RequestParam(value = "toHp") int toHp,
                                     Model model) {

        System.out.println("CONTROLLER HERE");
        Parameters prm = new Parameters();
        prm.setFromYearParam(fromYear);
        prm.setToYearParam(toYear);
        prm.setFromPriceParam(fromPrice);
        prm.setToPriceParam(toPrice);
        prm.setColorNameParam(colorName);
        prm.setFromHpParam(fromHp);
        prm.setToHpParam(toHp);

        model.addAttribute("cars", carService.findCarByParams(prm));

        return "cars";

    }

    @GetMapping("/editCar")
    public String editCar(Model model,
                          @RequestParam(value = "id") int id) {

        model.addAttribute("car", carService.findCarById(id));
        return "editCar";
    }


    @PostMapping("/editCar")
    public String editCar(@ModelAttribute Car car,
                          @RequestParam int id) {
      car.setId(id);
        System.out.println("<<<<< CAR ID " + car.getId());
        System.out.println("<<<<< CAR price " + car.getPrice());
        System.out.println("<<<<< CAR hp " + car.getHp());
      carService.editCarByParams(car);
      return "cars";
    }




}
