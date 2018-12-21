package com.bupt.project1.admin.dao.order;

import com.bupt.project1.utils.Dbcp;
import com.bupt.project1.bean.Order1;
import com.bupt.project1.bean.OrderItem;
import com.bupt.project1.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class orderDB implements OrderDao{
    QueryRunner queryRunner = new QueryRunner(Dbcp.getBasicDataSource());
    @Override
    public List<Order1> selectAll() {
        List<Order1> order1List =null;
        try {
            order1List = queryRunner.query("select * from order1", new BeanListHandler<Order1>(Order1.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order1List;
    }

    @Override
    public User selectUser(int uid) {
        User query =null;
        try {
             query = queryRunner.query("select * from user1 where uid=?", new BeanHandler<User>(User.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int deleteOrder(int i) {
        int update =0;
        try {
            update = queryRunner.update("delete from order1 where oid=?", i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int deleteItem(int i) {
        int update =0;
        try {
            update = queryRunner.update("delete from orderitem where oid=?", i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<OrderItem> selectByOid(int i) {
        List<OrderItem> query =null;
        try {
            query = queryRunner.query("select * from orderitem where oid=?",
                    new BeanListHandler<OrderItem>(OrderItem.class), i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
