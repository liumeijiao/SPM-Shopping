package com.cskaoyan.project1.admin.dao.user;

import com.cskaoyan.project1.bean.User;
import com.cskaoyan.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserInDb implements UserDao {
    QueryRunner queryRunner= new QueryRunner(Dbcp.getBasicDataSource());
    @Override
   public int addUser(com.cskaoyan.project1.bean.User user) {

        int update =0;
        try {
            update = queryRunner.update("insert into user1 values(null,?,?,?,?,?,null)", user.getUsername(), user.getNickname(), user.getPassword(), user.getEmail(), user.getBirthday());
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return update;
    }
// java.sql.Date转为java.util.Date
//java.sql.Date date=new java.sql.Date();
//java.util.Date d=new java.util.Date (date.getTime());
//java.util.Date转为java.sql.Date
//java.util.Date utilDate=new Date();
//java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
//java.util.Date utilDate=new Date();
//java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
//java.sql.Time sTime=new java.sql.Time(utilDate.getTime());
//java.sql.Timestamp stp=new java.sql.Timestamp(utilDate.getTime());
    @Override
    public List<User> selectAll() {
         QueryRunner queryRunner1 = new QueryRunner(Dbcp.getBasicDataSource());
        List<User> query =null;
        try {
            query = queryRunner1.query("select * from user1", new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
