package com.alex.controller.rest;

import com.alex.dto.UserDto;
import com.alex.model.Role;
import com.alex.model.User;
import com.alex.service.UserService;
import com.alex.util.RestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    RestValidator validator;


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

    @PutMapping("/change-role/{id}/{role}")
    public ResponseEntity<Object> changeRole(@PathVariable(name = "id")int id,
                                             @PathVariable(name = "role")String role) {

        if (!validator.roleNameValidate(role))
            return new ResponseEntity<>("role does not exist", HttpStatus.BAD_REQUEST);

        User user = new User();
        Role userRole = new Role();
        userRole.setName(role);
        user.setId(id);
        user.setRole(userRole);

        userService.changeRole(user);

        return new ResponseEntity<>("role changed successfully", HttpStatus.OK);
    }




}
