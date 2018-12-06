package com.cskaoyan.project1.user.controler;

import com.cskaoyan.project1.bean.ShoppingItem;
import com.cskaoyan.project1.bean.User;
import com.cskaoyan.project1.user.service.SendJMail;
import com.cskaoyan.project1.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "/UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        UserService userService = new UserService();
        String op = request.getParameter("op");
        switch (op){
            case "login":{
                login(request, response, userService);
                break;
            }

            case "regist":{
                register(request, response, userService);
                break;
            }

            case "checkIsExit":{

                String username = request.getParameter("username");

                User user = userService.checkIsExit(username);

                if(user!=null){
                    response.getWriter().print("no");
                }else{
                    response.getWriter().print("ok");
                }
                break;
            }
            case "lgout":{
                 HttpSession session = request.getSession();
                 session.removeAttribute("user");
                 //session.setAttribute("shoppingCar",shoppingItem);
                 session.removeAttribute("shoppingCar");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response, UserService userService) throws IOException {
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthday(birthday);
        int i = userService.insertUser(user);

        SendJMail sendJMail = new SendJMail();
        String s = UUID.randomUUID().toString();
        boolean b = sendJMail.sendMail(email, "<a href='/project1/user/activeUser?activecode=" + s + "'>" +
                "点我激活</a>");
        if(i==1&&b){
            response.getWriter().println("注册成功，请登录");
            response.setHeader("refresh","2;/project1/user/login.jsp");
        }else {
            response.getWriter().println("注册失败，请重新注册");
            response.setHeader("refresh","2;/project1/regist.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response, UserService userService) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.checkIsRight(username,password);
        String remember_me = request.getParameter("remember_me");

        String verifyCode = request.getParameter("verifyCode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
        if(user!=null&&checkcode_session.equals(verifyCode)){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            if(remember_me!=""||!remember_me.isEmpty()) {
                HttpSession session1 = request.getSession();
                String id = session1.getId();
                Cookie cookie2 = new Cookie("JSESSIONID", id);
                cookie2.setMaxAge(60*30);
                cookie2.setPath(request.getContextPath());
                response.addCookie(cookie2);
            }
            response.sendRedirect("/project1/index.jsp");
        }else {
            response.getWriter().print("输入有误，请重新输入");
            response.setHeader("refresh","3;url=/project1/user/login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
