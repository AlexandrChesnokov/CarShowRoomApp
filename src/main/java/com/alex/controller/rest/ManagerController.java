package com.alex.controller.rest;

import com.alex.dto.CarDto;
import com.alex.dto.CarEditFormDto;
import com.alex.model.Car;
import com.alex.service.CarService;
import com.alex.service.UserService;
import com.alex.util.RestValidator;
import com.alex.util.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */
@Slf4j
@RestController
@RequestMapping("/api/operation")
public class ManagerController {

    private final UserService userService;

    private final CarService carService;

    private final RestValidator validator;

    private final UserInfo userInfo;

    public ManagerController(UserService userService, CarService carService, RestValidator validator, UserInfo userInfo) {
        this.userService = userService;
        this.carService = carService;
        this.validator = validator;
        this.userInfo = userInfo;
    }

    @PutMapping("/cars/new")
    public ResponseEntity<Object> addCar(@RequestBody CarDto carDto,
                                         HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to add new car", userInfo.getJwtUserEmail(req));

        if (!validator.newCarValidate(carDto)) {
            log.debug("IN REST: Invalid values");
            return new ResponseEntity<>("invalid values", HttpStatus.BAD_REQUEST);
        }

        carService.addCar(carDto.toCar());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PutMapping("/cars/edit/{id}")
    public ResponseEntity<Object> editCar(@RequestBody CarEditFormDto editFormDto,
                                          @PathVariable(name = "id") int id,
                                          HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to edit car by id: {}", userInfo.getJwtUserEmail(req), id);

        if (!validator.editFormValidate(editFormDto)) {
            log.debug("IN REST: Invalid values");
            return new ResponseEntity<>("invalid values", HttpStatus.BAD_REQUEST);
        }

        Car car = carService.findCarById(id);
        car.setHp(Integer.parseInt(editFormDto.getHp()));
        car.setPrice(Double.parseDouble(editFormDto.getPrice()));
        car.setColor(editFormDto.getColor());

        carService.editCarByParams(car);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("cars/delete/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(name = "id") int id,
                                            HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to delete car by id: {}", userInfo.getJwtUserEmail(req), id);

        if (carService.deleteCarById(id)) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
