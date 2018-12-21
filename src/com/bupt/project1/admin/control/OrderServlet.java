package com.bupt.project1.admin.control;

import com.bupt.project1.admin.service.OrderService;
import com.bupt.project1.bean.Order1;
import com.bupt.project1.bean.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet",urlPatterns = "/admin/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String op = request.getParameter("op");
        OrderService orderService = new OrderService();
        switch (op){
            case "findAllOrder":{
               List<Order1> order1List = orderService.selectAllService();
               request.setAttribute("order1List",order1List);
               request.getRequestDispatcher("/admin/order/orderList.jsp").forward(request,response);
            }
            case "delOrder":{

                String oid = request.getParameter("oid");
                int i= orderService.deletteOrder(Integer.parseInt(oid));
                if(i==1){
                    request.getRequestDispatcher("/admin/OrderServlet?op=findAllOrder").forward(request,response);
                }
            }
            case "orderDetail":{
                //"/admin/OrderServlet?op=orderDetail&oid=${order.oid}">查看</a>
                String oid = request.getParameter("oid");
                List<OrderItem> orderItems = orderService.selectAllServiceByOid(Integer.parseInt(oid));
                request.setAttribute("orderItems",orderItems);
                request.getRequestDispatcher("/admin/order/orderDetails.jsp").forward(request,response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
