package com.alex.util;

import com.alex.dto.CarEditFormDto;
import com.alex.model.Car;
import com.alex.model.Parameters;
import com.alex.model.User;
import com.alex.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@Slf4j
@Component
public class RestValidator {

    private final UserService userService;

    private static final String EMAIL_REGEXP = "^[-a-z0-9!#$%&'*+/=?^_`{|}~ ]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
            ")*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|" +
            "asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum" +
            "|name|net|org|pro|tel|travel|[a-z][a-z])$";

    private static final String PHONE_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    private static final String NAME_REGEX = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";

    private static final String CAR_YEAR_REGEX = "^\\d{4}$";

    private static final String PRICE_REGEX = "^[0-9]+$";

    private static final String HORSEPOWER_REGEX = "^[0-9]+$";

    private static final String[] colors = new String[] {"white", "bronze", "yellow", "green",
    "goldenrod", "gold", "emerald", "brown", "red", "lemon", "orange", "purple", "pink", "silver",
    "violet", "black", "blue", "gray"};

    private static final String STATUS_OK = "ok";

    public RestValidator(UserService userService) {
        this.userService = userService;
    }

    public String registerFormValidate(User user) {

        if (!IsCorrectValue(user.getEmail(), EMAIL_REGEXP)) {
            return "invalid email format";
        }

        if (!IsCorrectValue(user.getPhone_number(), PHONE_REGEX)) {
            return "invalid phone format";
        }

        if (!IsCorrectValue(user.getFirstname(), NAME_REGEX)) {
            return "firstname invalid format";
        }

        if (!IsCorrectValue(user.getLastname(), NAME_REGEX)) {
            return "lastname invalid format";
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 255) {
            return "password must be between 8 and 255 characters long";
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return "this email is already in use";
        }

        return STATUS_OK;

    }


    public boolean roleNameValidate(String name) {
        return name.equals("MANAGER") || name.equals("ADMIN") || name.equals("USER");
    }

    public String carParamsValidate(Parameters prm) {


        if (!IsCorrectValue(prm.getFromYearParam(), CAR_YEAR_REGEX) ||
                !IsCorrectValue(prm.getToYearParam(), CAR_YEAR_REGEX)) {
            return "incorrect car year value";
        }

        if (!IsCorrectValue(String.valueOf((int)prm.getToPriceParam()), PRICE_REGEX) ||
                !IsCorrectValue(String.valueOf((int)prm.getFromPriceParam()), PRICE_REGEX)) {
            return "incorrect price value";
        }

        if (!IsColorPresent(prm.getColorNameParam())) {
            return "incorrect color value";
        }

        if (!IsCorrectValue(String.valueOf((int)prm.getFromHpParam()), HORSEPOWER_REGEX) ||
                !IsCorrectValue(String.valueOf((int)prm.getToHpParam()), HORSEPOWER_REGEX)) {
            return "incorrect hp value";
        }
        return STATUS_OK;
    }


    public boolean newCarValidate(Car car) {

        if (!IsCorrectValue(String.valueOf((int)car.getPrice()), PRICE_REGEX)) {

            log.debug("incorrect price format");
            return false;
        }

        if (!IsCorrectValue(String.valueOf(car.getHp()), HORSEPOWER_REGEX)) {
            log.debug("incorrect hp format");
            return false;
        }

        if (!IsColorPresent(car.getColor())) {
            log.debug("incorrect color format");
            return false;
        }

        if (!IsCorrectValue(car.getYear(), CAR_YEAR_REGEX)) {
            log.debug("incorrect year format");
            return false;
        }

        return true;
    }

    public boolean editFormValidate(CarEditFormDto carEditFormDto) {

        if (!IsColorPresent(carEditFormDto.getColor())) {
            return false;
        }

        if (!IsCorrectValue(carEditFormDto.getPrice(), PRICE_REGEX)) {
            return false;
        }

        if (!IsCorrectValue(carEditFormDto.getHp(), HORSEPOWER_REGEX)) {
            return false;
        }

        return true;

    }


    private boolean IsCorrectValue(String value, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    private boolean IsColorPresent(String color) {
        for (String clr : colors) {
            if (color.equals(clr)) {
                return true;
            }
        }

        return false;
    }



}
