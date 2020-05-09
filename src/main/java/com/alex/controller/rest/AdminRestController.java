package com.alex.controller.rest;

import com.alex.dto.UserDto;
import com.alex.model.User;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") int id) {

        User user = userService.findUserById(id);

        System.out.println(user == null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws SQLException {

        List<UserDto> list = UserDto.fromUserList(userService.showAll());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
