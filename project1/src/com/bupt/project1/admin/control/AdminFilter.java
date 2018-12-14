package com.bupt.project1.admin.control;

import com.bupt.project1.bean.Admin1;
import com.bupt.project1.bean.Category;
import com.bupt.project1.admin.service.CategoryService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AdminFilter",urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Admin1 admin = (Admin1) session.getAttribute("admin");
        if(admin!=null){
            CategoryService categoryService = new CategoryService();
            List<Category> categoryList =categoryService.showAllCate();
            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("categoryName",categoryList);
            chain.doFilter(req, resp);
        }else {
            response.getWriter().println("请登录后查询");
            response.setHeader("refresh","2;url=/project1/index_admin.jsp");
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
