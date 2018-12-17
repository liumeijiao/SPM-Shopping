package com.bupt.project1.admin.dao.category;

import com.bupt.project1.bean.Category;

import java.util.List;

public interface CategoryDao {
    int addCategory(String cname);
    int deleteCategory(int cid);
    List<Category> showAllCate();
    int alterCategory(Category category);
    List<Category> showCate();
    Category showCateById(String pid);
    int caculateCount();
    List<Category> showAllCate1(int recordNumberPerPage, int parseInt);

}
