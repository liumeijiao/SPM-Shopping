package com.bupt.project1.user.controler;

import com.bupt.project1.user.service.ProductService;
import com.bupt.project1.bean.Product;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductsServlet",urlPatterns = "/ProductsServlet")
public class ProductsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ProductService productService = new ProductService();
        String op = request.getParameter("op");
        switch (op){
            case "byCid":{
                String cid = request.getParameter("cid");
                int cid1 = Integer.parseInt(cid);
                List<Product> products = productService.findProByCid(cid1);

                request.setAttribute("products",products);
                request.getRequestDispatcher("/products.jsp").forward(request,response);
                break;
            }

            case "findProByName":{
                String pname = request.getParameter("pname");
                List<Product> products =productService.findProByName(pname);
                request.setAttribute("products",products);
                request.getRequestDispatcher("/products.jsp").forward(request,response);
            }

            case "findProductById":{
                String pid = request.getParameter("pid");
                Product product = productService.finProById(pid);
                request.setAttribute("product",product);
                request.getRequestDispatcher("/productdetail.jsp").forward(request,response);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
