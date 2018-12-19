package com.bupt.project1.admin.dao.category;

import com.bupt.project1.bean.Category;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Category_db implements CategoryDao{
    QueryRunner queryRunner = new QueryRunner(Dbcp.getBasicDataSource());
    public  int addCategory(String cname) {
        int update =0;
        try {
            update = queryRunner.update("insert into category values(null,?)",cname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(update);
        return  update;
    }

    @Override
    public int deleteCategory(int cid) {
        int update =0;
        try {
            update = queryRunner.update("delete from category where cid=?", cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public List<Category> showAllCate() {
        List<Category> query =null;
        try {
            query = queryRunner.query("select * from category", new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int alterCategory(Category category) {
        int update =0;
        try {
            update = queryRunner.update("update category set cname=? where cid=?",category.getCname(),category.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return update;
    }

    public List<Category> showCate() {
        List<Category> select_cname_from_category=null;
        try {
            select_cname_from_category = queryRunner.query("SELECT * from category", new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  select_cname_from_category;
    }

    public Category showCateById(String pid) {
        Category query =null;
        try {
            query = queryRunner.query("SELECT category.cid,category.cname FROM category INNER JOIN " +
                    "(SELECT product.cid AS cid,product.pid FROM product where pid=?) AS pro ON\n" +
                    "pro.cid=category.cid;", new BeanHandler<Category>(Category.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public int caculateCount() {
        Connection connection = Dbcp.getConnection();
        String sql="select count(*) AS  count1 from category";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt("count1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    public List<Category> showAllCate1(int recordNumberPerPage, int parseInt) {
        List<Category> query=null;
        try {
            query = queryRunner.query("select * from category limit ? offset ?", new BeanListHandler<Category>(Category.class)
                    , recordNumberPerPage, parseInt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
