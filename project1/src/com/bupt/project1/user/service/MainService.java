package com.bupt.project1.user.service;

import com.bupt.project1.bean.Category;
import com.bupt.project1.bean.Product;
import com.bupt.project1.user.dao.MainDao;

import java.util.List;

public class MainService {
    MainDao mainDao = new MainDao();
    public List<Category> findAllCate() {
        List<Category> categoryList=mainDao.findAllCategory();
        return categoryList;
    }

    public List<Product> findTopPro() {
        List<Product> products=mainDao.findTopPro();
        return products;
    }

    public List<Product> findHotPro() {
        List<Product> products=mainDao.findHotPro();
        return products;
    }
}
