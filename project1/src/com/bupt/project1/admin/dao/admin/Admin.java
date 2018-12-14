package com.bupt.project1.admin.dao.admin;

import com.bupt.project1.bean.Admin1;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Admin implements AdminDao {

    QueryRunner queryRunner=new QueryRunner(Dbcp.getBasicDataSource());
    @Override
    public int addAmin(Admin1 admin1) {
        int update =0;
        try {
            update = queryRunner.update("insert into admin1 values(null,?,?)", admin1.getUsername(), admin1.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<Admin1> selectAdmin() {
        List<Admin1> query =null;
        try {
            query = queryRunner.query("select * from admin1", new BeanListHandler<Admin1>(Admin1.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int deleteAdmin(int aid) {
        return 0;
    }

    @Override
    public int updateAdmin(Admin1 admin1) {
        int update =0;
        try {
            update = queryRunner.update("update admin1 set password=? where username=?",  admin1.getPassword(),admin1.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public Admin1 checkLogin(Admin1 admin1) {
        Admin1 query =null;
        try {
            query = queryRunner.query("select * from admin1 where username=? and password=?",
                    new BeanHandler<Admin1>(Admin1.class), admin1.getUsername(), admin1.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
