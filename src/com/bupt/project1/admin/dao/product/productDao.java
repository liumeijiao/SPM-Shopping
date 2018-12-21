package com.bupt.project1.admin.dao.product;

import com.bupt.project1.bean.Category;
import com.bupt.project1.bean.Product;

import java.util.List;

public interface productDao {
    int addToProduct(Product product);
    List<Product> selectAllInDB();
    int deleteInDB(String pid);
    Product selectById(String i);
    int updatePro(Product product);
    Category selectCategoryName(int cid);
    int caculateCount();
    List<Product> showAllPro(int recordNumberPerPage, int parseInt);

    List<Product> multSelect(String pid, String cid, String pname, String minprice, String maxprice, int every, int offset);

    int caculateSlect();

    int multCount(String pid, String cid, String pname, String minprice, String maxprice);
}
