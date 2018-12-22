package com.bupt.project1.admin.service;

import com.bupt.project1.bean.Category;
import com.bupt.project1.bean.Product;
import com.bupt.project1.admin.dao.product.product;
import com.bupt.project1.user.dao.ProductDao;
import com.bupt.project1.utils.PageHelper;

import java.util.List;

public class ProductService {
    product product1 = new product();
    public int addProduct(Product product) {
        int i =0;
        boolean exit = false;
        List<Product> productList = product1.selectAllInDB();
        for (Product product2 : productList) {
            if(product2.getPid().equals(product.getPid())){
                exit=true;
                break;
            }
        }

        if(exit==false){
            i= product1.addToProduct(product);
        }

        return i;
    }

    public PageHelper showAllPro(String num) {

        if(num==null||num.isEmpty()){
            num="1";
        }

        int recordNumberPerPage = 5;
        int parseInt = Integer.parseInt(num);
        int totalRecordsNum=product1.caculateCount();


        PageHelper pageHelper = new PageHelper(recordNumberPerPage,parseInt,totalRecordsNum);

        List<Product> productList = product1.showAllPro(recordNumberPerPage,(parseInt-1)*(recordNumberPerPage));

        for (Product product : productList) {
            int cid = product.getCid();
            Category category = product1.selectCategoryName(cid);
            product.setCategory(category);
        }


        pageHelper.setRecordSum(productList);

        return pageHelper;
    }

    public int deleteProduct(String pid) {
        int i=product1.deleteInDB(pid);
        return i;
    }

    public Product selectById(String i) {
        Product product = product1.selectById(i);
        return product;
    }

    public int updateProduct(Product product) {
        int i=product1.updatePro(product);
        return i;
    }

    public PageHelper multSelect(String pid, String cid, String pname, String minprice, String maxprice,String num) {
        if(num==null||num.isEmpty()){
            num="1";
        }
        int recordNumberPerPage = 5;
        int parseInt = Integer.parseInt(num);


        List<Product> productList = product1.multSelect(pid,cid,pname,minprice,maxprice,recordNumberPerPage,(parseInt-1)*(recordNumberPerPage));

        int totalRecordsNum = product1.multCount(pid,cid,pname,minprice,maxprice);
        for (Product product : productList) {
            int cid1 = product.getCid();
            Category category = product1.selectCategoryName(cid1);
            product.setCategory(category);
        }

        PageHelper pageHelper = new PageHelper(recordNumberPerPage,parseInt,totalRecordsNum);
        pageHelper.setRecordSum(productList);

        return pageHelper;

    }


}
