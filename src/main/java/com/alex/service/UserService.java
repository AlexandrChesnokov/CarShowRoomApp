package com.alex.service;


import com.alex.model.User;

import java.sql.SQLException;
import java.util.List;


public interface UserService {

    List<User> showAll() throws SQLException;

    boolean save(User user) throws SQLException;

    User findByEmail(String email);

    User findUserById(int id);

    boolean changeRole(User user);

}
