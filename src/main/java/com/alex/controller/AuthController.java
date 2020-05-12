package com.alex.controller;


import com.alex.model.User;
import com.alex.service.UserService;
import com.alex.util.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Slf4j
@Controller
public class AuthController {



    private final UserValidator userValidator;

    private final UserService userService;

    public AuthController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@Valid @ModelAttribute("user")User user, BindingResult result) throws SQLException {
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            log.info("user failed validation");
            return "sign_up";
        }
        log.info("user validated");
        userService.save(user);

        return "welcome";
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
