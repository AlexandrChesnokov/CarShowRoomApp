package com.alex.controller;

import com.alex.dao.UserDao;
import com.alex.model.User;
import com.alex.service.UserService;
import com.alex.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class MainController {



    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;



    @GetMapping("/")
    public String getWelcomePage(Model model) {
        return "welcome";
    }

    @GetMapping("/users")
    public String getUsers(Model model) throws SQLException {
        model.addAttribute("users", userService.showAll());
        return "users";

    }




}
