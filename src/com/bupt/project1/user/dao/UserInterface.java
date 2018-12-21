package com.bupt.project1.user.dao;

import com.bupt.project1.bean.User;

public interface UserInterface {
    User checkIsRight(String username, String password);
    int insertUser(User user);
    User checkIsExit(String username);

}
