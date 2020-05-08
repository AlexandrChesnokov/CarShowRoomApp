package com.alex.controller;


import com.alex.model.User;
import com.alex.service.SecurityService;
import com.alex.service.UserService;
import com.alex.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class AuthController {

    @Autowired
    UserValidator userValidator;



    @Autowired
    UserService userService;


    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@Valid @ModelAttribute("user")User user, BindingResult result) throws SQLException {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "sign_up";
        }
        userService.save(user);

        return "redirect:/welcome";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false)Boolean error,
                        Model model) {

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }


        return "sign_in";
    }
}
