package com.alex.dao.impl;

import com.alex.dao.ConnectionPool;
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

    @Override
    public List<User> getAll() {
        Connection conn = ConnectionPool.getInstance().getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select u.*, r.id, r.name \n" +
                    "from users u join user_roles ur on u.id = ur.user_id join roles r on ur.role_id = r.id;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                Role role = new Role();
                user.setId(rs.getInt(1));
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setPhone_number(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setManager_id(rs.getInt(6));
                user.setPassword(rs.getString(7));
                role.setId(rs.getInt(8));
                role.setName(rs.getString(9));
                user.setRole(role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User getOne(String email) {
        Connection conn = ConnectionPool.getInstance().getConnection();
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
                conn.close();
                return user;
            }
        } catch (SQLException ignored) {
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void add(User user) {
        Connection conn = ConnectionPool.getInstance().getConnection();
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
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmail(String email) {
        Connection conn = ConnectionPool.getInstance().getConnection();
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
                Role role = getUserRolesById(user.getId());
                user.setRole(role);
                conn.close();
                return user;
            }
        } catch (SQLException ignored) {
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role getUserRolesById(int id) {
        Connection conn = ConnectionPool.getInstance().getConnection();
       Role role = new Role();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from user_roles where user_id = ?"
            );


            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = getRoleById(rs.getInt(2));
            }



            return role;
        } catch (SQLException e) {
            //ss
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public Role getRoleById(int id) {
        Connection conn = ConnectionPool.getInstance().getConnection();

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
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public void changeRole(User user) {

        Connection conn = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = conn
                    .prepareStatement("update user_roles set role_id = (select id from roles where name = ?)" +
                            "where user_id = ?");

            ps.setString(1, user.getRole().getName());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            //ss
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(int id) {

        Connection conn = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("select u.*, r.id, r.name \n" +
                    "from users u join user_roles ur on u.id = ur.user_id join roles r on ur.role_id = r.id where u.id = ?;");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            User user = new User();
            if (rs.next()) {
                Role role = new Role();
                user.setId(rs.getInt(1));
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setPhone_number(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setManager_id(rs.getInt(6));
                user.setPassword(rs.getString(7));
                role.setId(rs.getInt(8));
                role.setName(rs.getString(9));
                user.setRole(role);
            }

            return user;





        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
