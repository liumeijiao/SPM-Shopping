package com.bupt.project1.utils;

import java.util.List;

public class PageHelper {
    int totalRecordsNum;
    int currentPageNum;
    int totalPageNum;
    int prevPageNum;
    int nextPageNum;
    int recordNumberPerPage;

    List recordSum;


    public PageHelper(int recordNumberPerPage, int parseInt, int totalRecordsNum) {
        this.totalPageNum=totalRecordsNum;
        this.recordNumberPerPage=recordNumberPerPage;
        this.setTotalPageNum((totalPageNum%recordNumberPerPage==0?
                (totalPageNum/recordNumberPerPage):(totalPageNum/recordNumberPerPage)+1));
        this.currentPageNum=parseInt;
        this.totalRecordsNum=totalRecordsNum;
        this.setPrevPageNum((currentPageNum-1)==0?currentPageNum:currentPageNum-1);
        this.setNextPageNum((currentPageNum)>=totalPageNum?totalPageNum:currentPageNum+1);
    }

    public int getRecordNumberPerPage() {
        return recordNumberPerPage;
    }


    public void setRecordNumberPerPage(int recordNumberPerPage) {
        this.recordNumberPerPage = recordNumberPerPage;
    }

    public int getTotalRecordsNum() {
        return totalRecordsNum;
    }

    public void setTotalRecordsNum(int totalRecordsNum) {
        this.totalRecordsNum = totalRecordsNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPrevPageNum() {
        return prevPageNum;
    }

    public void setPrevPageNum(int prevPageNum) {
        this.prevPageNum = prevPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public List getRecordSum() {
        return recordSum;
    }

    public void setRecordSum(List recordSum) {
        this.recordSum = recordSum;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "totalRecordsNum=" + totalRecordsNum +
                ", currentPageNum=" + currentPageNum +
                ", totalPageNum=" + totalPageNum +
                ", prevPageNum=" + prevPageNum +
                ", nextPageNum=" + nextPageNum +
                ", recordNumberPerPage=" + recordNumberPerPage +
                ", recordSum=" + recordSum +
                '}';
    }
}
