package com.bupt.project1.user.dao;

import com.bupt.project1.bean.User;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao implements UserInterface{
    QueryRunner queryRunner = new QueryRunner(Dbcp.getBasicDataSource());
    public int insertUser(User user) {
        int update =0;
        try {
           update = queryRunner.update("insert into user1 values(null,?,?,?,?,?,now())", user.getUsername(),
                   user.getNickname(),user.getPassword(),user.getEmail(),user.getBirthday());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public User checkIsExit(String username) {
        User query =null;
        try {
            query = queryRunner.query("select * from user1 where username=?",
                    new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public User checkIsRight(String username, String password) {
        User query =null;
        try {
            query = queryRunner.query("select * from user1 where username=? and password=?", new BeanHandler<User>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
