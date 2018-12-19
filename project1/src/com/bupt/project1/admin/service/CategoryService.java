package com.bupt.project1.admin.service;

import com.bupt.project1.bean.Category;
import com.bupt.project1.admin.dao.category.Category_db;
import com.bupt.project1.utils.PageHelper;

import java.util.List;

public class CategoryService {
    Category_db category_db=new Category_db();
    public  int addCategory(String cname) {
        int i =0;
        boolean exit=false;//不存在
        List<Category> categoryList = category_db.showAllCate();
        for (Category category : categoryList) {
            if(category.getCname().equals(cname)){
                exit=true;//表示存在
                break;
            }
        }
        if(exit==false){
            i = category_db.addCategory(cname);
        }
        return i;
    }


    public  int deleteCategory(int cid) {
        int i=category_db.deleteCategory(cid);
        return i;
    }

    public List<Category>showAllCate() {
        List<Category> categoryList = category_db.showAllCate();
        return categoryList;
    }

    public PageHelper showAllCate1(String num) {

        if(num==null||num.isEmpty()){
            num="1";
        }

        int recordNumberPerPage = 2;
        int parseInt = Integer.parseInt(num);
        int totalRecordsNum=category_db.caculateCount();


        PageHelper pageHelper = new PageHelper(recordNumberPerPage,parseInt,totalRecordsNum);

        List<Category> categoryList = category_db.showAllCate1(recordNumberPerPage,(parseInt-1)*(recordNumberPerPage));
        pageHelper.setRecordSum(categoryList);

        return pageHelper;
    }

    public int updateCate(Category category) {
         int i = category_db.alterCategory(category);
        return i;
    }

    public List<Category> showCate() {
        List<Category> categoryList = category_db.showCate();
        return categoryList;
    }

    public Category showCateById(String pid) {
        Category category = category_db.showCateById(pid);
        return category;
    }
}
