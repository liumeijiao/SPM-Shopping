package com.bupt.project1.user.service;

import com.bupt.project1.admin.dao.order.OrderDao;
import com.bupt.project1.bean.Order1;
import com.bupt.project1.bean.OrderItem;
import com.bupt.project1.bean.ShoppingItem;
import com.bupt.project1.user.dao.OrderDao1;
import com.bupt.project1.utils.Dbcp;
import com.bupt.project1.utils.Transanction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    OrderDao1 orderDao = new OrderDao1();
    public int addService(Order1 order1, List<ShoppingItem> shoppingItem1,int uid) {
        int i =0;
        Transanction transanction = new Transanction(Dbcp.getConnection());
        Connection connection = transanction.getConnection();
        try {
            //添加事务，要么都执行，要么都不执行

            connection.setAutoCommit(false);
            //添加订单
            i =orderDao.addOrder(connection,order1);
            //根据用户信息查询订单ID
            List<Order1> order1s = orderDao.selectOrder(connection,uid);

            for (ShoppingItem shoppingItem : shoppingItem1) {
                for (Order1 order11 : order1s) {
                    //将该用户的所有订单信息添加到item中
                   OrderItem orderItems= orderDao.selectItem(connection,order11.getOid());
                   if(orderItems==null) {
                        int j = orderDao.addOrderItem(connection, order11.getOid(), order11.getState(), shoppingItem);
                    }
                }
                //删除购物车中的信息
                int d=orderDao.deleteShoppingCarItem(connection,shoppingItem);
                //更新库存
                int k=orderDao.upDatePro(connection,shoppingItem);
            }
            orderDao.deleteShoppingCar(connection,uid);
        } catch (Exception e){
            transanction.rollBack();
        }
        transanction.Transubmit();
        return i;
    }

    public List<Order1> selectOrder(int uid) {
        //
        List<Order1> order1s=orderDao.selectOrder1(uid);
        return order1s;
    }

    public int cancleOrder(int oid, String state,List<ShoppingItem> shoppingItem1) {
        //更新库存
        Transanction transanction = new Transanction(Dbcp.getConnection());
        Connection connection = transanction.getConnection();
        int i =0;
        try {
            connection.setAutoCommit(false);
            for (ShoppingItem shoppingItem : shoppingItem1) {
                int k=orderDao.upDatePro1(connection,shoppingItem);
            }
            //修改订单状态
            i = orderDao.cancleOrder1(connection,oid, state);
            //删除订单item。
          //  int j=orderDao.deleteOrderItem(connection,oid,Integer.parseInt(state));
        } catch (Exception e){
            transanction.rollBack();
        }
        transanction.Transubmit();

        return i;
    }
}
