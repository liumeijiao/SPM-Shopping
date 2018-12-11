package com.bupt.project1.user.service;

import com.bupt.project1.bean.Product;
import com.bupt.project1.user.dao.ProductDao;

import java.util.List;

public class ProductService {
    ProductDao productDao = new ProductDao();
    public List<Product> findProByCid(int cid1) {
        List<Product> products= productDao.findProByC(cid1);
        return products;
    }

    public List<Product> findProByName(String pname) {
        List<Product> products=productDao.findProByname(pname);
        return products;
    }

    public Product finProById(String pid) {
        Product product=productDao.findProById(pid);
        return product;
    }
}
