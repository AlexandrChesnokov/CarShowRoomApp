package com.alex.controller;

import com.alex.dao.UserDao;
import com.alex.model.Role;
import com.alex.model.User;
import com.alex.service.UserService;
import com.alex.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class MainController {



    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;


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
                             @ModelAttribute User user) {
        System.out.println(user.getId() + " <<<< ID");
        System.out.println(user.getRole().getName() + " <<<<< ROLE");
        user.setId(id);
        userService.changeRole(user);
        return "welcome";
    }




}
