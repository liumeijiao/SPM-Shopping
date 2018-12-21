package com.bupt.project1.admin.service;

import com.bupt.project1.bean.Order1;
import com.bupt.project1.admin.dao.order.orderDB;
import com.bupt.project1.bean.OrderItem;
import com.bupt.project1.bean.User;

import java.util.List;

public class OrderService {
    orderDB order = new orderDB();
    public List<Order1> selectAllService() {
        List<Order1> order1List = order.selectAll();
        for (Order1 order1 : order1List) {
          User user = order.selectUser(order1.getUid());
          order1.setUser(user);
        }
        return order1List;
    }

    public int deletteOrder(int i) {
        int j1= order.deleteItem(i);
        int j=0;
        if(j1==1) {
            j = order.deleteOrder(i);
        }
        return j;
    }

    public List<OrderItem> selectAllServiceByOid(int i) {
        List<OrderItem> orderItems= order.selectByOid(i);
        return orderItems;
    }
}
