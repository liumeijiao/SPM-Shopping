package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Order1;
import com.bupt.project1.bean.OrderItem;
import com.bupt.project1.bean.ShoppingItem;
import com.bupt.project1.utils.Dbcp;
import com.bupt.project1.utils.Transanction;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao1 implements OrderDao {
    QueryRunner queryRunner = new QueryRunner();
    @Override
    public int addOrder(Connection connection,Order1 order1) {
        int update =0;
        try {
            update = queryRunner.update( connection,"insert into order1 values(null,?,?,?,?,1,now(),?)",
                    order1.getMoney(),order1.getRecipients(),order1.getTel(),order1.getAddress(),order1.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int addOrderItem(Connection connection,int oid,int state, ShoppingItem shoppingItem) {
        int update =0;
        try {
            update = queryRunner.update( connection,"insert into " +
                            "orderitem values(null,?,?,?)",
                    oid, shoppingItem.getPid(),shoppingItem.getSnum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int deleteShoppingCarItem(Connection connection,ShoppingItem shoppingItem) {
        int update =0;
        try {
            update = queryRunner.update(connection,"delete from shoppingitem where sid=?", shoppingItem.getSid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int upDatePro(Connection connection,ShoppingItem shoppingItem) {
        int update =0;
        try {
            update = queryRunner.update( connection,"update product set pnum=pnum-?  where pid=?", shoppingItem.getSnum(), shoppingItem.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<Order1> selectOrder(Connection connection,int uid) {
        List<Order1> query =null;
        try {
            query = queryRunner.query( connection,"select * from order1 where uid=?", new BeanListHandler<Order1>(Order1.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Order1> selectOrder1(int uid) {
        List<Order1> query =null;
        try {
            query = queryRunner.query( Dbcp.getConnection(),"select * from order1 where uid=?", new BeanListHandler<Order1>(Order1.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int cancleOrder1(Connection connection,int oid, String state) {
        int update =0;
        try {
            update = queryRunner.update(connection,"update order1 set state=? WHERE oid=?;",Integer.parseInt(state),oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int cancleOrder(Connection connection,int oid, String state) {
        int update =0;
        try {
           update = queryRunner.update(Dbcp.getConnection(),"update order1 set state=? WHERE oid=?;",Integer.parseInt(state),oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return update;
    }

    @Override
    public int upDatePro1(Connection connection,ShoppingItem shoppingItem) {
        int update =0;
        try {
            update = queryRunner.update(connection,"update product set pnum=pnum+?  where pid=?",
                    shoppingItem.getSnum(), shoppingItem.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int deleteOrderItem(Connection connection,int oid, int state) {
        int update =0;
        try {
            update = queryRunner.update(connection, "delete from orderitem where oid=?", oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public void deleteShoppingCar(Connection connection,int uid) {
        try {
            int update = queryRunner.update(connection, "" +
                    "delete from shoppingcar where uid=?", uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem selectItem(Connection connection ,int oid) {
        OrderItem query =null;
        try {
            query = queryRunner.query(connection, "select * from orderitem where oid =?",
                     new BeanHandler<OrderItem>(OrderItem.class), oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
