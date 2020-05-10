package com.alex.controller;

import com.alex.model.User;
import com.alex.service.UserService;
import com.alex.util.RestValidator;
import com.alex.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    RestValidator validator;



    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    @GetMapping("/")
    public String getWelcomePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("firstname", user.getFirstname());
        return "welcome";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getUsers(Model model) throws SQLException {
        model.addAttribute("users", userService.showAll());
        return "users";

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/changeRole")
    public String changeRole(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("user", userService.findUserById(id));
            return "changeRole";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/changeRole")
    public String changeRole(@RequestParam(value = "id") int id,
                             @ModelAttribute User user,
                             Model model) {

        if (!validator.roleNameValidate(user.getRole().getName())) {
            model.addAttribute("result", "Roles do not exist");
            return "welcome";
        }

        user.setId(id);
       if (userService.changeRole(user)) {
           model.addAttribute("result", "Role successfully changed");
       } else {
           model.addAttribute("result", "An error occurred while changing the role; possibly incorrect data was entered");
       }
        return "welcome";
    }




}
