package com.bupt.project1.admin.control;

public class Shoppingcar {
  int  sid;
  int  uid;
  ShoppingItem shoppingItems;

    public Shoppingcar() {
    }

    public Shoppingcar(int sid, int uid) {
        this.sid = sid;
        this.uid = uid;
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItems;
    }

    public void setShoppingItem(ShoppingItem shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "shoppingcar{" +
                "sid=" + sid +
                ", uid=" + uid +
                '}';
    }
}
