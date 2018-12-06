package com.cskaoyan.project1.user.controler;

import com.cskaoyan.project1.bean.Category;
import com.cskaoyan.project1.bean.Product;
import com.cskaoyan.project1.user.service.MainService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet",urlPatterns = "/MainServlet")
public class MainServlet extends HttpServlet {


    //用于初始化界面

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        MainService mainService = new MainService();
        List<Category> categoryList= mainService.findAllCate();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("categories",categoryList);
        // productTop
        List<Product> productTop = mainService.findTopPro();
        servletContext.setAttribute("productTop",productTop);

        //hotProducts
        List<Product> hotProducts = mainService.findHotPro();
        servletContext.setAttribute("hotProducts",hotProducts);
//        response.sendRedirect("/project1/index.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
