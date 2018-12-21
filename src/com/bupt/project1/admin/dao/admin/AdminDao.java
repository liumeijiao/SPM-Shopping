package com.bupt.project1.admin.dao.admin;

import com.bupt.project1.bean.Admin1;

import java.util.List;

public interface AdminDao {
    int addAmin(Admin1 admin1);
    List<Admin1> selectAdmin();
    int deleteAdmin(int aid);
    int updateAdmin(Admin1 admin1);
    Admin1 checkLogin(Admin1 admin1);
}
