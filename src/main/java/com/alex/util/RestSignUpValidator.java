package com.alex.util;

import com.alex.dto.SignUpRequestDto;
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
public class RestSignUpValidator {

    @Autowired
    UserService userService;

    private static final String EMAIL_REGEXP = "^[-a-z0-9!#$%&'*+/=?^_`{|}~ ]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
            ")*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|" +
            "asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum" +
            "|name|net|org|pro|tel|travel|[a-z][a-z])$";

    private static final String PHONE_REGEXP = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    private static final String NAME_REGEXP = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";

    private static final String STATUS_OK = "ok";

    public String validate(SignUpRequestDto requestDto) {

        Pattern emailPattern = Pattern.compile(EMAIL_REGEXP);
        Matcher emailMatcher = emailPattern.matcher(requestDto.getEmail());

        if (!emailMatcher.matches()) {
            return "invalid email format";
        }

        Pattern phonePattern = Pattern.compile(PHONE_REGEXP);
        Matcher phoneMatcher = phonePattern.matcher(requestDto.getPhone_number());

        if (!phoneMatcher.matches()) {
            return "invalid phone format";
        }

        Pattern fnamePattern = Pattern.compile(NAME_REGEXP);
        Matcher fnameMatcher = fnamePattern.matcher(requestDto.getFirstname());

        if (!fnameMatcher.matches()) {
            return "firstname invalid format";
        }

        Pattern lnamePattern = Pattern.compile(NAME_REGEXP);
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
}
