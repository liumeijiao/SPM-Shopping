package com.bupt.project1.admin.control;

import com.bupt.project1.bean.Admin1;
import com.bupt.project1.admin.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet",urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String op = request.getParameter("op");
        AdminService adminService = new AdminService();
        switch (op){
            case "addAdmin":{
                addAdmin(request, response, adminService);
                break;
            }

            case "findAllAdmin":{
                allAdmin(request, response, adminService);
                break;
            }

            case "updateAdmin":{
                updateAdmin(request, response, adminService);

                break;
            }

            case "login":{
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Admin1 admin1 = new Admin1();
                admin1.setUsername(username);
                admin1.setPassword(password);
                Admin1 admin11= adminService.checkLogin(admin1);
                if(admin11!=null){
                    HttpSession session = request.getSession();
                    session.setAttribute("admin",admin1);
                    response.getWriter().println("登录成功");
                    response.setHeader("refresh","2;url=/project1/admin/main.jsp");
                }else {
                    response.getWriter().println("登录失败，请重新登录");
                    response.setHeader("refresh","2;url=/project1/index_admin.jsp");
                }

            }
        }

    }

    private void allAdmin(HttpServletRequest request, HttpServletResponse response, AdminService adminService) throws ServletException, IOException {
        List<Admin1> admin1List = adminService.selectAll();
        request.setAttribute("admin1List",admin1List);
        request.getRequestDispatcher("/admin/admin/adminList.jsp").forward(request,response);
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response, AdminService adminService) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if(password.equals(password1)){
            Admin1 admin1 = new Admin1();
            admin1.setPassword(password);
            admin1.setUsername(username);
            int i=adminService.updateAdmin(admin1);
            if(i==1){
                response.getWriter().println("修改成功，即将跳转管理者列表页面");
                response.setHeader("refresh","3,url=/project1/admin/AdminServlet?op=findAllAdmin");
            }
        }
    }


    private void addAdmin(HttpServletRequest request, HttpServletResponse response, AdminService adminService) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if(password.equals(password1)){
            Admin1 admin1 = new Admin1();
            admin1.setPassword(password);
            admin1.setUsername(username);
            int i = adminService.addAdmin(admin1);
            if(i==1) {
                response.getWriter().println("添加成功，即将跳转至管理员列表界面");
                response.setHeader("refresh", "3,url=/project1/admin/AdminServlet?op=findAllAdmin");
            }else {
                response.getWriter().println("该用户已经存在");
                response.setHeader("refresh","2;url=/project1/admin/addAdmin.jsp");
            }
            }else {
            response.getWriter().println("输入密码不一致，请重新注册");
            response.setHeader("refresh","3,url=/project1/admin/admin/addAdmin.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
