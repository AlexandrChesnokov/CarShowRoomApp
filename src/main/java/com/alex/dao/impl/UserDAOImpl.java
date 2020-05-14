package com.alex.dao.impl;

import com.alex.dao.ConnectionPool;
import com.alex.dao.UserDao;
import com.alex.model.Role;
import com.alex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UserDAOImpl implements UserDao {

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
            log.error("SQLException in getAll", e);
        }
        return users;
    }


    @Override
    public User getOne(String email) {

        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
        } catch (SQLException e) {
            log.error("SQLException in getOne", e);
        }
        return null;
    }
    @Override
    public boolean add(User user) {

        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
            return true;
        } catch (SQLException e) {
            log.error("SQLException in add", e);
           return false;
        }

    }

    @Override
    public User findByEmail(String email) {
        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
                return user;
            }
        } catch (SQLException e) {
            log.error("SQLException in findByEmail", e);
        }
        return null;
    }

    @Override
    public Role getUserRolesById(int id) {

       Role role = new Role();

        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
            log.error("SQLException in getUserRolesById", e);
        }
        return role;
    }

    @Override
    public Role getRoleById(int id) {

        Role role = new Role();
        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from roles where id = ?;");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException e) {
           log.error("SQLException in getRoleById", e);
        }
        return role;
    }

    @Override
    public boolean changeRole(User user) {

        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = conn
                    .prepareStatement("update user_roles set role_id = (select id from roles where name = ?)" +
                            "where user_id = ?");

            ps.setString(1, user.getRole().getName());
            ps.setInt(2, user.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
           log.error("SQLException in changeRole");
            return false;
        }
    }

    @Override
    public User findUserById(int id) {

        try  (Connection conn = ConnectionPool.getInstance().getConnection()) {
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
                return user;
            }
        } catch (SQLException e) {
            log.error("SQLException in findUserById", e);
        }
        return null;
    }
}
