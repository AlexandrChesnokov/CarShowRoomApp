package com.alex.dao.impl;


import com.alex.dao.ConnectionPool;
import com.alex.dao.RoleDao;
import com.alex.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
