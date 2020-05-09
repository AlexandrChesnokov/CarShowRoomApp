package com.alex.dto;

import com.alex.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */

@JsonIgnoreProperties
public class UserDto {

    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String phone_number;
    private String password;
    private int manager_id;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone_number(phone_number);
        user.setPassword(password);
        user.setManager_id(manager_id);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setPhone_number(user.getPhone_number());
        userDto.setPassword(user.getPassword());
        userDto.setManager_id(user.getManager_id());
        return userDto;
    }

    public static List<UserDto> fromUserList(List<User> users) {

        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
           list.add(UserDto.fromUser(user));
        }
        return list;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
