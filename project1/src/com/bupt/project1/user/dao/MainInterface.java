package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Category;
import com.bupt.project1.bean.Product;

import java.util.List;

public interface MainInterface {
    List<Category> findAllCategory();

    List<Product> findTopPro();

    List<Product> findHotPro();
}
