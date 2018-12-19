package com.bupt.project1.admin.test;

import com.bupt.project1.bean.Category;
import com.bupt.project1.admin.dao.category.Category_db;
import org.junit.Test;

import java.util.List;

public class CategoryTest {
    @Test
    public void testAdd(){
        Category_db category_db = new Category_db();
        String name ="zs";
        int i = category_db.addCategory(name);
    }

    @Test
    public void testDelete(){
        Category_db category_db = new Category_db();
        int jj=5;
        int i = category_db.deleteCategory(jj);
    }

    @Test
    public void testShow(){
        Category_db category_db = new Category_db();

        List<Category> categoryList = category_db.showAllCate();
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

    @Test
    public void testupdate(){
        Category_db category_db = new Category_db();

        Category hello = new Category(1, "hello");
        int i = category_db.alterCategory(hello);
        System.out.println(i);

    }

    @Test
    public void testupdate1(){
        Category_db category_db = new Category_db();

        Category category = category_db.showCateById("1");
        System.out.println(category);

    }

    @Test
    public void testupdate2(){
        Category_db category_db = new Category_db();

        int count = category_db.caculateCount();
        System.out.println(count);

    }

    @Test
    public void testupdate3(){
        Category_db category_db = new Category_db();

        List<Category> categoryList = category_db.showAllCate1(2, 1);
        for (Category category : categoryList) {
            System.out.println(category);
        }

    }

    @Test
    public void testupdate4(){
        Category_db category_db = new Category_db();

        List<Category> categoryList = category_db.showCate();
        for (Category category : categoryList) {
            System.out.println(category);
        }

    }

}
