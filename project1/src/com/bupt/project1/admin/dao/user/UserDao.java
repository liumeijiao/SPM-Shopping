package com.cskaoyan.project1.admin.dao.user;

import com.cskaoyan.project1.bean.User;

import java.util.List;

public interface UserDao {
    int addUser(User user);
    List<User> selectAll();
}
