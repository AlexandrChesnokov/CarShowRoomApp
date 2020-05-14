package com.alex.service.impl;


import com.alex.dao.RoleDao;
import com.alex.dao.UserDao;
import com.alex.model.Role;
import com.alex.model.User;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDAO;

    private final RoleDao roleDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDAO, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> showAll() throws SQLException {
        return userDAO.getAll();
    }

    @Override
    public boolean save(User user) throws SQLException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRole(roleDao.getOne(3));
     return   userDAO.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findUserById(int id) {
      return userDAO.findUserById(id);
    }

    @Override
    public boolean changeRole(User user) {
       return userDAO.changeRole(user);
    }
}
