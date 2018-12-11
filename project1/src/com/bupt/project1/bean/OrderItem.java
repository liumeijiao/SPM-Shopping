package com.bupt.project1.bean;

public class OrderItem {
    int itemid;
    int oid;
    String pid;
    int buynum;

    public OrderItem() {
    }

    public OrderItem(int itemid, int oid, String pid, int buynum) {
        this.itemid = itemid;
        this.oid = oid;
        this.pid = pid;
        this.buynum = buynum;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemid=" + itemid +
                ", oid='" + oid + '\'' +
                ", pid='" + pid + '\'' +
                ", buynum=" + buynum +
                '}';
    }
}
