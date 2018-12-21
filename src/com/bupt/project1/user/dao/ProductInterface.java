package com.bupt.project1.user.dao;

import com.bupt.project1.bean.Product;

import java.util.List;

public interface ProductInterface {
    List<Product> findProByC(int cid1);

    List<Product> findProByname(String pname);

    Product findProById(String pid);
}
