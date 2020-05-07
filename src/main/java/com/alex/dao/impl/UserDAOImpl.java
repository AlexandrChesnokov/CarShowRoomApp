package com.alex.dao.impl;

import com.alex.dao.UserDao;
import com.alex.model.Role;
import com.alex.model.User;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

@Component
public class UserDAOImpl implements UserDao {

    private static Connection conn;



    static {
        String url = null;
        String username = null;
        String password = null;

        // load db properties
        try (InputStream in = UserDao.class
                .getClassLoader().getResourceAsStream("persistence.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // acquire db connection
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setPhone_number(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setManager_id(rs.getInt(6));
                user.setPassword(rs.getString(7));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User getOne(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where email = ?"
            );
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setEmail(rs.getString(5));
                return user;
            }
        } catch (SQLException ignored) {
        }
        return null;
    }
    @Override
    public void add(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users " +
                    "(firstname, lastname, phone_number, email, manager_id, password)" +
                    " values( ?, ?, ?, ?, ?, ?)");

            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getPhone_number());
            ps.setString(4, user.getEmail());
            ps.setInt(5, 0);
            ps.setString(6, user.getPassword());

            PreparedStatement ps2 = conn.prepareStatement("insert into user_roles values " +
                    "((select id from users where email = ?), 3)");

            ps2.setString(1, user.getEmail());


            ps.executeUpdate();
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where email = ?"
            );
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(7));
                Set<Role> roleSet = getUserRolesById(user.getId());
                user.setRoles(roleSet);
                return user;
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Set<Role> getUserRolesById(int id) {
        Set<Role> roles = new HashSet<>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from user_roles where user_id = ?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                roles.add(getRoleById(rs.getInt(2)));
            }


        } catch (SQLException e) {
            //ss
        }

        return roles;
    }

    @Override
    public Role getRoleById(int id) {

        Role role = new Role();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from roles where id = ?;");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
            }


        } catch (SQLException e) {
            //ss
        }

        return role;
    }
}
