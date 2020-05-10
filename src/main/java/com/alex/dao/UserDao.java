package com.alex.dao;

import com.alex.model.Role;
import com.alex.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface UserDao {

    List<User> getAll() throws SQLException;

    User getOne(String email) throws SQLException;

    boolean add(User user) throws SQLException;

    User findByEmail(String email);

    Role getUserRolesById(int id);

    Role getRoleById(int id);

    User findUserById(int id);

    boolean changeRole(User user);
}
