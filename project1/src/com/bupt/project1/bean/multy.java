package com.bupt.project1.bean;

public class multy {
    String pid ;
    String cid ;
    String pname ;
    String minprice ;
    String maxprice;

    public multy() {
    }

    @Override
    public String toString() {
        return "multy{" +
                "pid='" + pid + '\'' +
                ", cid='" + cid + '\'' +
                ", pname='" + pname + '\'' +
                ", minprice='" + minprice + '\'' +
                ", maxprice='" + maxprice + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMinprice() {
        return minprice;
    }

    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }
}
