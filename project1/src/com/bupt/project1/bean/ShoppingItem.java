package com.bupt.project1.bean;

public class ShoppingItem {
    int itemid;
    int sid;
    String pid;
    Product product;
    int snum;

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "itemid=" + itemid +
                ", sid=" + sid +
                ", pid='" + pid + '\'' +
                '}';
    }

    public ShoppingItem() {
    }
    public ShoppingItem(int itemid, int sid, String pid) {
        this.itemid = itemid;
        this.sid = sid;
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
