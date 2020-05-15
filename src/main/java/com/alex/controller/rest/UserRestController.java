package com.alex.controller.rest;

import com.alex.model.Car;
import com.alex.model.Parameters;
import com.alex.model.User;
import com.alex.service.CarService;
import com.alex.service.UserService;
import com.alex.util.RestValidator;
import com.alex.util.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@Slf4j
@RestController
@RequestMapping(value = "api/")
public class UserRestController {

    private final UserService userService;

    private final CarService carService;

    private final RestValidator validator;

    private final UserInfo userInfo;

    public UserRestController(UserService userService, CarService carService, RestValidator validator, UserInfo userInfo) {
        this.userService = userService;
        this.carService = carService;
        this.validator = validator;
        this.userInfo = userInfo;
    }

    @GetMapping("cars")
    public ResponseEntity<List<Car>> getCars(HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to show all cars", userInfo.getJwtUserEmail(req));

        return new ResponseEntity<>(carService.showAllCars(), HttpStatus.OK);
    }

    @GetMapping("cars/{maker}")
    public ResponseEntity<List<Car>> getCarsByMaker(@PathVariable(name = "maker") String maker,
                                                    HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to show car by maker: {}", userInfo.getJwtUserEmail(req), maker);

        List<Car> cars = carService.findCarByMaker(maker);
        if (!cars.isEmpty()) {
            return new ResponseEntity<>(carService.findCarByMaker(maker), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(carService.findCarByMaker(maker), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "cars/adv-search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchCars(@RequestBody Parameters prm,
                                             HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to adv search", userInfo.getJwtUserEmail(req));

        String validateResult = validator.carParamsValidate(prm);
        if (!validateResult.equals("ok")) {
            return new ResponseEntity<>(validateResult, HttpStatus.BAD_REQUEST);
        }

        List<Car> cars = carService.findCarByParams(prm);

        if (cars.size() == 0) {
            return new ResponseEntity<>(cars, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @GetMapping("cars/to-order/{carId}/{enhId}")
    public ResponseEntity<Object> toOrder(@PathVariable(name = "carId") int carId,
                                          @PathVariable(name = "enhId") int enhId,
                                          HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to order car by id: {}", userInfo.getJwtUserEmail(req), carId);

        User user = userService.findByEmail(userInfo.getJwtUserEmail(req));

        if (!carService.isAvailable(carId)) {
           return new ResponseEntity<>("The car is already sold", HttpStatus.NO_CONTENT);
        }

        carService.orderCar(user.getId(), carId, enhId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }



}
