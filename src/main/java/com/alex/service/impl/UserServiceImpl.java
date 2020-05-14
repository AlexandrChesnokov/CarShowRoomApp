package com.alex.service.impl;


import com.alex.dao.RoleDao;
import com.alex.dao.UserDao;
import com.alex.model.User;
import com.alex.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
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
    public List<User> showAll()  {
        try {
            return userDAO.getAll();
        } catch (SQLException e) {
            log.error("SQLException in showAll", e);
        }

        return null;
    }

    @Override
    public boolean save(User user)  {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            user.setRole(roleDao.getOne(3));
            return   userDAO.add(user);
        } catch (SQLException e) {
            log.error("SQLException in save", e);
        }
       return false;
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
