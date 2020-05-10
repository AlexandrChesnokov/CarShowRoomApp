package com.alex.util;

import com.alex.dto.ParametersDto;
import com.alex.dto.SignUpRequestDto;
import com.alex.model.Parameters;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@Component
public class RestValidator {

    @Autowired
    UserService userService;

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

    public String registerFormValidate(SignUpRequestDto requestDto) {

        Pattern emailPattern = Pattern.compile(EMAIL_REGEXP);
        Matcher emailMatcher = emailPattern.matcher(requestDto.getEmail());

        if (!emailMatcher.matches()) {
            return "invalid email format";
        }

        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Matcher phoneMatcher = phonePattern.matcher(requestDto.getPhone_number());

        if (!phoneMatcher.matches()) {
            return "invalid phone format";
        }

        Pattern fnamePattern = Pattern.compile(NAME_REGEX);
        Matcher fnameMatcher = fnamePattern.matcher(requestDto.getFirstname());

        if (!fnameMatcher.matches()) {
            return "firstname invalid format";
        }

        Pattern lnamePattern = Pattern.compile(NAME_REGEX);
        Matcher lnameMatcher = lnamePattern.matcher(requestDto.getLastname());

        if (!lnameMatcher.matches()) {
            return "lastname invalid format";
        }

        if (requestDto.getPassword().length() < 8 || requestDto.getPassword().length() > 255) {
            return "password must be between 8 and 255 characters long";
        }

        if (userService.findByEmail(requestDto.getEmail()) != null) {
            return "this email is already in use";
        }

        return STATUS_OK;

    }


    public boolean roleNameValidate(String name) {
        return name.equals("MANAGER") || name.equals("ADMIN") || name.equals("USER");
    }

    public String carParamsValidate(ParametersDto prm) {

        System.out.println(prm.getFromYearParam() + " " + prm.getToYearParam());

        if (!IsCorrectValue(prm.getFromYearParam(), CAR_YEAR_REGEX) ||
                !IsCorrectValue(prm.getToYearParam(), CAR_YEAR_REGEX)) {
            return "incorrect car year value";
        }

        if (!IsCorrectValue(prm.getToPriceParam(), PRICE_REGEX) ||
                !IsCorrectValue(prm.getFromPriceParam(), PRICE_REGEX)) {
            return "incorrect price value";
        }

        if (!IsColorPresent(prm.getColorNameParam())) {
            return "incorrect color value";
        }

        if (!IsCorrectValue(prm.getFromHpParam(), HORSEPOWER_REGEX) ||
                !IsCorrectValue(prm.getToHpParam(), HORSEPOWER_REGEX)) {
            return "incorrect hp value";
        }

        return STATUS_OK;


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
