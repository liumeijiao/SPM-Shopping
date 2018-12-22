package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Category;
import com.bupt.project1.bean.Product;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MainDao implements MainInterface{
    QueryRunner queryRunner= new QueryRunner(Dbcp.getBasicDataSource());

    public List<Category> findAllCategory() {
        List<Category> query =null;
        try {
           query = queryRunner.query("select * from category limit 7 offset 0", new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Product> findTopPro() {
        List<Product> query =null;
        try {
            query = queryRunner.query("SELECT product.cid,product.pname,product.imgurl,product.estoreprice,product.pid,product.estoreprice,product.markprice FROM product INNER JOIN (SELECT cid,cname FROM category WHERE cname='top') AS pro\n" +
                    "                    ON product.cid=pro.cid ", new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Product> findHotPro() {
        List<Product> query =null;
        try {
            query = queryRunner.query("select * from product limit 9 offset 2", new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;

    }
}
