package com.alex.util;

import com.alex.dao.UserDao;
import com.alex.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.SQLException;

@Component
public class UserValidator implements Validator {

    private final UserDao userDAO;

    public UserValidator(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        try {
            if (userDAO.getOne(user.getEmail()) != null) {
                errors.rejectValue("email", "", "This email is already in use");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
