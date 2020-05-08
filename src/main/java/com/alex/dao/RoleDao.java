package com.alex.dao;

import com.alex.model.Role;

import java.sql.SQLException;

public interface RoleDao {

    public Role getOne(int id) throws SQLException;
}
