package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Product;
import com.bupt.project1.bean.ShoppingItem;
import com.bupt.project1.bean.Shoppingcar;
import com.bupt.project1.bean.User;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class shoppingDao implements shoppingcar {
    QueryRunner queryRunner = new QueryRunner(Dbcp.getBasicDataSource());
    @Override
    public int addShopping(int uid) {
        int update =0;
        try {
           update = queryRunner.update("insert into shoppingcar values(null,?) ",uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<ShoppingItem> findCart(int uid) {
        List<ShoppingItem> shoppingItems =null;
        try {
            shoppingItems = queryRunner.query("SELECT shoppingitem.itemid,shoppingitem.pid,shoppingitem.sid,shoppingitem.snum \n" +
                    "FROM shoppingitem INNER JOIN (SELECT uid ,sid FROM shoppingcar WHERE uid=?)AS " +
                    "car ON car.sid = shoppingitem.sid; \n", new BeanListHandler<ShoppingItem>(ShoppingItem.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingItems;
    }

    @Override
    public Product findProByPid(String pid) {
        Product query =null;
        try {
             query = queryRunner.query("select * from product where pid =?", new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public Shoppingcar selectSid(int uid) {
        Shoppingcar query =null;
        try {
            query = queryRunner.query("select * from shoppingcar where uid=?",
                    new BeanHandler<Shoppingcar>(Shoppingcar.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int addShoppingItem(String pid,int sid,int snum) {
        int update =0;
        try {
            update = queryRunner.update("insert into shoppingitem values(null,?,?,?)", sid, pid,snum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int count(String pid) {
        Connection connection = Dbcp.getConnection();
        String sql = "select count(*) as cou from shoppingitem where pid=?";
        int cou =0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cou = resultSet.getInt("cou");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cou;
    }

    @Override
    public int removeItem(int itemid) {
        int update =0;
        try {
           update=  queryRunner.update("delete from shoppingitem where itemid=?", itemid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int remove(int uid) {
        int update =0;
        try {
            update = queryRunner.update("delete from shoppingcar where uid = ?", uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public User selectUser(int uid) {
        User users=null;
        try {
            users= queryRunner.query("select * from shoppingcar where uid =?",new BeanHandler<User>(User.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public ShoppingItem selectBySidPid(int uid, String pid, int snum) {
        ShoppingItem query =null;
        try {
            query = queryRunner.query("SELECT shoppingitem.itemid,shoppingitem.pid,shoppingitem.sid FROM shoppingitem INNER JOIN \n" +
                     "(SELECT shoppingcar.sid FROM shoppingcar WHERE uid=?)AS shop ON shop.sid=shoppingitem.sid WHERE " +
                     "pid=?", new BeanHandler<ShoppingItem>(ShoppingItem.class), uid, pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int insertSnum(String pid, int sid, int snum) {
        int update =0;
        try {
             update = queryRunner.update("update shoppingitem set snum=snum+? " +
                     " where pid=? and sid=?", snum, pid,sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
