package com.bupt.project1.user.service;

import com.bupt.project1.bean.Product;
import com.bupt.project1.bean.ShoppingItem;
import com.bupt.project1.bean.Shoppingcar;
import com.bupt.project1.bean.User;
import com.bupt.project1.user.dao.shoppingDao;

import java.util.List;

public class shoppingService {
    shoppingDao shoppingDao = new shoppingDao();
    public int  addCart(int uid,String pid,int snum) {
       //先查询购物车中是否存在该用户的购物车
       User users= shoppingDao.selectUser(uid);
        if(users==null){
           // 如果不存在，则需要将该用户信息加入至购物车
            int i = shoppingDao.addShopping(uid);
        }
       //通过用户信息获取购物车的ID
       Shoppingcar shoppingcar= shoppingDao.selectSid(uid);
        //通过用户信息及商品信息查询购物车item中是否存在该商品的信息
       ShoppingItem shoppingItem = shoppingDao.selectBySidPid(uid,pid,snum);
        int j=0;
        if(shoppingItem==null){
            //如果不存在，就直接将此条信息添加
            j=shoppingDao.addShoppingItem(pid,shoppingcar.getSid(),snum);
        }else{
            //如果存在，则只需要更改数量即可。
            j=shoppingDao.insertSnum(pid,shoppingcar.getSid(),snum);
        }

       if(1==j){
           return j;
       }
       return j;
    }

    public List<ShoppingItem> findCart(int uid) {
       //需要给item提供一个product的对象。
        List<ShoppingItem> shoppingItem=shoppingDao.findCart(uid);
        for (ShoppingItem item : shoppingItem) {
            Product product = shoppingDao.findProByPid(item.getPid());
//            int i=shoppingDao.count(item.getPid());
            item.setProduct(product);
//            item.setSnum(i);
        }



        return shoppingItem;
    }

    public int remove(int uid, int itemid) {
       int i= shoppingDao.removeItem(itemid);
       int j= shoppingDao.remove(uid);
       if(i==j){
           return 1;
       }
       return 0;
    }
}
