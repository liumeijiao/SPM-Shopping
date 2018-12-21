package com.bupt.project1.admin.dao.user;

import com.bupt.project1.bean.User;

import java.util.List;

public interface UserDao {
    int addUser(User user);
    List<User> selectAll();
}
