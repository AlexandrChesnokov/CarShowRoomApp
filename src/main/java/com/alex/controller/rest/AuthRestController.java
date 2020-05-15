package com.alex.controller.rest;

import com.alex.dto.AuthRequestDto;
import com.alex.model.User;
import com.alex.security.jwt.JwtTokenProvider;
import com.alex.service.UserService;
import com.alex.util.RestValidator;
import com.alex.util.UserInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("api/")
public class AuthRestController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RestValidator validator;

    private final UserInfo userInfo;

    public AuthRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, RestValidator validator, UserInfo userInfo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.validator = validator;
        this.userInfo = userInfo;
    }


    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequestDto requestDto) {

        log.info("IN REST: Received a request from user {} to login", requestDto.getEmail());

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

            log.debug("IN REST: User {} passed validation and received a token", requestDto.getEmail());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.debug("IN REST: User {} didn't pass validation", requestDto.getEmail());
            throw new BadCredentialsException("invalid username or password");
        }
    }

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> signup(@RequestBody User user) throws SQLException {

        String validateResult = "";

        log.info("IN REST: Registration request received, validation begins");

        validateResult = validator.registerFormValidate(user);

        if (!validateResult.equals("ok")) {
            log.debug("IN REST: Validation failed. Result: {}", validateResult);
            return new ResponseEntity<>(validateResult, HttpStatus.BAD_REQUEST);
        }

        log.debug("IN REST: Validation Passed");


        userService.save(user);

        return ResponseEntity.ok("success");
    }
}
