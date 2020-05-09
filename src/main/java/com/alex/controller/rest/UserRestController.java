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

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") int id) {

        User user = userService.findUserById(id);

        System.out.println(user == null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
