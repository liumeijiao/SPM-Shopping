package com.bupt.project1.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Transanction {
    static ThreadLocal<Connection>  threadLocal = new ThreadLocal<Connection>();
//    static Connection connection;
    public Transanction(Connection connection) {
//        this.connection = connection;

        threadLocal.set(connection);
    }
//    static {
//        threadLocal.set(connection);
//    }



    public  Connection getConnection(){
        Connection connection = threadLocal.get();
        return connection;
    }

    public  void rollBack(){
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void Transubmit(){

        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
