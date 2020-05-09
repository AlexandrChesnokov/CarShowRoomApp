package com.alex.controller.rest;

import com.alex.dto.AuthRequestDto;
import com.alex.dto.SignUpRequestDto;
import com.alex.dto.UserDto;
import com.alex.model.User;
import com.alex.security.jwt.JwtTokenProvider;
import com.alex.service.UserService;
import com.alex.util.RestSignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */
@RestController
@RequestMapping("/api")
public class AuthRestController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    RestSignUpValidator validator;


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequestDto requestDto) {

        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email  " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("invalid username or password");
        }
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> signup(@RequestBody SignUpRequestDto requestDto) throws SQLException {

        String validateResult = "";

        validateResult = validator.validate(requestDto);

        if (!validateResult.equals("ok")) {
            return new ResponseEntity<>(validateResult, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setPhone_number(requestDto.getPhone_number());
        user.setFirstname(requestDto.getFirstname());
        user.setLastname(requestDto.getLastname());

        userService.save(user);

        return ResponseEntity.ok("success");
    }
}
