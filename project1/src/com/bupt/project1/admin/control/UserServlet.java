package com.cskaoyan.project1.admin.control;

import com.cskaoyan.project1.bean.User;
import com.cskaoyan.project1.admin.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/admin/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String op = request.getParameter("op");
        UserService userService = new UserService();
        switch (op){
            case "adduser":{
                String username = request.getParameter("username");
                String nickname = request.getParameter("nickname");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String birthday = request.getParameter("birthday");
                User user = new User();
                user.setUsername(username);
                user.setNickname(nickname);
                user.setPassword(password);
                user.setEmail(email);
                user.setBirthday(birthday);
                int i = userService.addUser(user);

                if(i==1){
                    response.getWriter().println("添加成功，即将转入用户列表界面");
                }else {
                    response.getWriter().println("该用户已经存在，请重新登录");
                    response.setHeader("refresh","2,url=/project/admin/addUser.jsp");
                }
                break;
            }
            case "findAllUser":{
                List<User> userList=userService.selectAll();
                request.setAttribute("userList",userList);
                request.getRequestDispatcher("/admin/user/userList.jsp").forward(request,response);
                break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
