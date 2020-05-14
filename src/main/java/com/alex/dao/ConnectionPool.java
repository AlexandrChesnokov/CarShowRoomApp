package com.alex.dao;

import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Slf4j
public class ConnectionPool {

    private ConnectionPool() {
        //private constructor
    }


    public static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/myDbDS");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            log.error("Datasource problem");
        }
        return c;
    }

}


