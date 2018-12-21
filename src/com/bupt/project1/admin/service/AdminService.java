package com.bupt.project1.admin.service;

import com.bupt.project1.bean.Admin1;
import com.bupt.project1.admin.dao.admin.Admin;

import java.util.List;

public class AdminService {
    Admin admin=new Admin();

    public int addAdmin(Admin1 admin1) {
        int i = 0;
        boolean exit=false;//表示不存在

        List<Admin1> admin1List = admin.selectAdmin();
        for (Admin1 admin11 : admin1List) {
            if(admin11.getUsername().equals(admin1)){
                exit=true;
                break;
            }
        }
        if (exit == false) {
            i = admin.addAmin(admin1);
        }
        return i;
    }

    public List<Admin1> selectAll() {
        List<Admin1> admin1List = admin.selectAdmin();
        return  admin1List;
    }

    public int updateAdmin(Admin1 admin1) {
        int i = admin.updateAdmin(admin1);
        return i;
    }

    public Admin1 checkLogin(Admin1 admin1) {
        Admin1 admin11 = admin.checkLogin(admin1);
        return admin11;
    }
}
