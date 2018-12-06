package com.cskaoyan.project1.admin.service;

import com.cskaoyan.project1.bean.User;
import com.cskaoyan.project1.admin.dao.user.UserInDb;

import java.util.List;

public class UserService {
    UserInDb userInDb = new UserInDb();
    public int addUser(User user) {
        int i = 0;
        boolean exit =false;//表示不存在
        List<User> userList = userInDb.selectAll();
        for (User user1 : userList) {
            if(user1.getUsername().equals(user.getUsername())){
                exit=true;
                break;
            }
        }
        if(exit==false){
            i = userInDb.addUser(user);
        }
        return i;
    }

    public List<User> selectAll() {
        List<User> userList = userInDb.selectAll();
        return userList;
    }
}
