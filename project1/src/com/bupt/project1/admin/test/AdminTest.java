package com.bupt.project1.admin.test;

import com.bupt.project1.bean.Admin1;
import com.bupt.project1.admin.dao.admin.Admin;
import org.junit.Test;

import java.util.List;

public class AdminTest {
    @Test
    public void addTest(){
        Admin1 admin1 = new Admin1();
        admin1.setUsername("zs");
        admin1.setPassword("23");
        Admin admin = new Admin();
        admin.addAmin(admin1);
    }

    @Test
    public void selectTest(){
        Admin admin = new Admin();
        List<Admin1> admin1List = admin.selectAdmin();
        for (Admin1 admin1 : admin1List) {
            System.out.println(admin1);
        }
    }

    @Test
    public void updateTest(){
        Admin admin = new Admin();
        Admin1 admin11 = new Admin1();
        admin11.setAid(1);
        admin11.setUsername("nihao");
        admin11.setPassword("he");
        int i = admin.updateAdmin(admin11);
        System.out.println(i);
    }
}

