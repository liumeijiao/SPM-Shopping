package com.cskaoyan.project1.admin.test;

import com.cskaoyan.project1.bean.User;
import com.cskaoyan.project1.admin.dao.user.UserInDb;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserTest {
    @Test
    public void addTest(){
        User user = new User();
        user.setUsername("zs");
        user.setNickname("hello");
        user.setPassword("123");
        user.setEmail("123");
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date =null;
        try {
            date = format1.parse("2018-6-3");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday("2019-3-3");
        UserInDb userInDb = new UserInDb();
        int i = userInDb.addUser(user);
        System.out.println(i);
    }

    @Test
    public void selectTest(){
        UserInDb userInDb = new UserInDb();
        List<User> userList = userInDb.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}

