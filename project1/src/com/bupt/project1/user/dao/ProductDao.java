package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Product;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao implements ProductInterface{
    QueryRunner queryRunner= new QueryRunner(Dbcp.getBasicDataSource());
    public List<Product> findProByC(int cid1) {
        List<Product> query =null;
        try {
            query = queryRunner.query("select * from product where cid=?",
                    new BeanListHandler<Product>(Product.class), cid1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Product> findProByname(String pname) {
        List<Product> query=null;
        try {
            query = queryRunner.query("select * from product where pname like ?",
                    new BeanListHandler<Product>(Product.class), "%"+pname+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    @Override
    public Product findProById(String pid) {
        Product query =null;
        try {
            query = queryRunner.query("select * from product where pid=?",
                    new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
