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

    @Autowired
    private UserDao userDAO;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> showAll() throws SQLException {
        return userDAO.getAll();
    }

    @Override
    public void save(User user) throws SQLException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       Set<Role> roles = new HashSet<>();
       roles.add(roleDao.getOne(3));
       user.setRoles(roles);
       userDAO.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
