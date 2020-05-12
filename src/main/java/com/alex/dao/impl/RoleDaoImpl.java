package com.alex.dao.impl;


import com.alex.dao.ConnectionPool;
import com.alex.dao.RoleDao;
import com.alex.dao.UserDao;
import com.alex.model.Role;
import com.alex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Slf4j
@Component
public class RoleDaoImpl implements RoleDao {

    public Role getOne(int id) {

        try  (Connection conn = ConnectionPool.getInstance().getConnection()){
            PreparedStatement ps = conn.prepareStatement(
                    "select * from roles where id = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               Role role = new Role();
               role.setId(id);
                role.setName(rs.getString(2));
                ;
                return role;
            }
        } catch (SQLException e) {
            log.error("SQLException in getOne", e);
        }

        return null;
    }
}
