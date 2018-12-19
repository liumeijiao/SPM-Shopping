package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Order1;
import com.bupt.project1.bean.OrderItem;
import com.bupt.project1.bean.ShoppingItem;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    int addOrder(Connection connection, Order1 order1);

    int addOrderItem(Connection connection, int oid, int state, ShoppingItem shoppingItem);

    int deleteShoppingCarItem(Connection connection, ShoppingItem shoppingItem);

    int upDatePro(Connection connection, ShoppingItem shoppingItem);

    List<Order1> selectOrder(Connection connection, int uid);

//    int cancleOrder(int oid, String state);
     int cancleOrder(Connection connection, int oid, String state);

    int deleteOrderItem(Connection connection, int oid, int state);
    List<Order1> selectOrder1(int uid);

    int upDatePro1(Connection connection, ShoppingItem shoppingItem);

    void deleteShoppingCar(Connection connection, int uid);
    int cancleOrder1(Connection connection, int oid, String state);

    OrderItem selectItem(Connection connection, int oid);
}
