package com.alex.controller;


import com.alex.dao.CarDao;
import com.alex.dao.UserDao;
import com.alex.model.Car;
import com.alex.model.Enhance;
import com.alex.model.Parameters;
import com.alex.model.User;
import com.alex.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    @GetMapping("/cars")
    public String getCars(Model model) throws SQLException {
        model.addAttribute("cars", carService.showAllCars());
        return "cars";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    @GetMapping("/searchCarsByMaker")
    public String searchCars(Model model, @RequestParam(value = "")String maker) {
        String carName = maker;
        model.addAttribute("cars", carService.findCarByMaker(carName));
        return "cars";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    @GetMapping("/viewCar")
    public String viewCar(Model model, @RequestParam(name = "id") int id) {
        System.out.println(id);
        model.addAttribute("car", carService.findCarById(id));
        model.addAttribute("enh", new Enhance());
        if (carService.isAvailable(id)) {
            model.addAttribute("status", "is available");
        } else {
            model.addAttribute("status", "sold");
        }
        return "viewCar";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN',  'MANAGER')")
    @GetMapping("/editCar")
    public String editCar(Model model,
                          @RequestParam(value = "id") int id) {

        model.addAttribute("car", carService.findCarById(id));
        return "editCar";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/editCar")
    public String editCar(@ModelAttribute Car car,
                          @RequestParam int id,
                          Model model) {
      car.setId(id);

      if (carService.editCarByParams(car)) {
          model.addAttribute("result", "Parameters changed successfully");
        } else {
          model.addAttribute("result", "Error while changing parameters, possibly incorrect data entered");
        }
      return "welcome";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("deleteCar")
    public String deleteCar(Model model, @RequestParam(value = "id") int id) {
        if (carService.deleteCarById(id)) {
            model.addAttribute("result", "Car successfully deleted");
        } else {
            model.addAttribute("result", "Error while deleting a car, possibly incorrect data entered");
        }
        return "welcome";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    @GetMapping("/searchCars")
    public String searchCars() {
        return "searchCars";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "addCar";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car car, Model model) {
        if (carService.addCar(car)) {
            model.addAttribute("result", "Adding a car was successful");
        } else {
            model.addAttribute("result", "Error adding car, possibly entered incorrect data");
        }
        return "welcome";
    }

    @PostMapping("/orderCar")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    public String orderCar(@RequestParam(value = "carId") int carId,
                           @ModelAttribute Enhance enhance,
                           Model model) {



        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = user.getId();

        if (carService.isAvailable(carId)) {
            carService.orderCar(userId, carId, enhance.getId());
            model.addAttribute("result", "Your order has been completed");
        } else {
            model.addAttribute("result", "The car is already sold");
        }

        return "welcome";


    }


}
