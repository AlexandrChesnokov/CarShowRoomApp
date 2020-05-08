package com.alex.service;


import com.alex.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


public interface UserService {

    List<User> showAll() throws SQLException;

    void save(User user) throws SQLException;

    User findByEmail(String email);

    User findUserById(int id);

    void changeRole(User user);

}
