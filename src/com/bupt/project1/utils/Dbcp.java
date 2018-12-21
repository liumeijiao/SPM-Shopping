package com.bupt.project1.utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Dbcp {
    static BasicDataSource basicDataSource;
    static {
       basicDataSource =new BasicDataSource();
       basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       basicDataSource.setUrl("jdbc:mysql:///project1?serverTimezone=GMT");
       basicDataSource.setPassword("123456");
       basicDataSource.setUsername("root");
    }

    public static BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public static Connection getConnection(){
        Connection connection =null;
        try {
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
