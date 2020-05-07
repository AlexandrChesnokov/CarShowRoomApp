package com.alex.dao.impl;


import com.alex.dao.RoleDao;
import com.alex.dao.UserDao;
import com.alex.model.Role;
import com.alex.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Component
public class RoleDaoImpl implements RoleDao {

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

    public Role getOne(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from roles where id = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               Role role = new Role();
               role.setId(id);
                role.setName(rs.getString(2));

                return role;
            }
        } catch (SQLException ignored) {
        }
        return null;
    }
}
