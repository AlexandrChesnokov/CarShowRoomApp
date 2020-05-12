package com.alex.controller.rest;

import com.alex.dto.UserDto;
import com.alex.model.Role;
import com.alex.model.User;
import com.alex.service.UserService;
import com.alex.util.RestValidator;
import com.alex.util.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    private final UserService userService;

    private final RestValidator validator;

    private final UserInfo userInfo;

    public AdminRestController(UserService userService, RestValidator validator, UserInfo userInfo) {
        this.userService = userService;
        this.validator = validator;
        this.userInfo = userInfo;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") int id,
                                               HttpServletRequest req) {

        User user = userService.findUserById(id);

        log.info("IN REST: Received a request from user {} to show user by id {}", userInfo.getJwtUserEmail(req), id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers(HttpServletRequest req) throws SQLException {

        log.info("IN REST: Received a request from user {} to show all users", userInfo.getJwtUserEmail(req));

        List<UserDto> list = UserDto.fromUserList(userService.showAll());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/change-role/{id}/{role}")
    public ResponseEntity<Object> changeRole(@PathVariable(name = "id")int id,
                                             @PathVariable(name = "role")String role,
                                             HttpServletRequest req) {

        log.info("IN REST: Received a request from user {} to change user role id {}", userInfo.getJwtUserEmail(req), id);

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
