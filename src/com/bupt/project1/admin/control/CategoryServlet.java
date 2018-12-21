package com.bupt.project1.admin.control;

import com.bupt.project1.bean.Category;
import com.bupt.project1.admin.service.CategoryService;
import com.bupt.project1.utils.PageHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/admin/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String op = request.getParameter("op");
        CategoryService categoryService = new CategoryService();
        switch (op){
            case "addCategory":{
                addCate(request, response, categoryService);
                break;
            }

            case "findAllCategory" :{
                findAll(request, response, categoryService);
                break;
            }
            case "delete":{
                delete(request, response, categoryService);
                break;
            }

            case "findCategory":{
//                List<Category> categoryList =categoryService.showAllCate();
//                ServletContext servletContext = request.getServletContext();
//
//                servletContext.setAttribute("categoryName",categoryList);
                request.getRequestDispatcher("/admin/product/addProduct.jsp").forward(request,response);
            }
            case "updateCategory":{
                updateCate(request, response, categoryService);
                break;
            }

            case "findCategoryByUpdate":{
                String pid = request.getParameter("pid");
                List<Category> categoryList = new ArrayList<Category>();
                Category category = categoryService.showCateById(pid);
                //放入第一个.

                categoryList.add(category);
                //其他依次放入
                List<Category> categoryOtherList = categoryService.showCate();
                for (Category category1 : categoryOtherList) {
                    if(!(category1.getCname().equals(category.getCname()))){
                        categoryList.add(category1);
                    }
                }
                request.setAttribute("categoryName",categoryList);
                request.getRequestDispatcher("/admin/ProductServlet?op=updateProduct1").forward(request,response);
            }

        }
    }

    private void updateCate(HttpServletRequest request, HttpServletResponse response, CategoryService categoryService) throws IOException {
        String cid = request.getParameter("cid");
        System.out.println(cid);
        String cname = request.getParameter("cname");
        Category category = new Category(Integer.parseInt(cid), cname);
        int i = categoryService.updateCate(category);
        if(i==1){
            response.getWriter().println("修改成功，即将返回分类界面");
            response.setHeader("refresh","2;url=/project1/admin/CategoryServlet?op=findAllCategory");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, CategoryService categoryService) throws IOException {
        String cid = request.getParameter("cid");
        int i = categoryService.deleteCategory(Integer.parseInt(cid));
        if(i==1){
          response.getWriter().println("删除成功，即将跳转至分类列表界面");
          response.setHeader("refresh","2;url=/project1/admin/CategoryServlet?op=findAllCategory");
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response, CategoryService categoryService) throws ServletException, IOException {
        String num = request.getParameter("num");
        PageHelper categoryList = categoryService.showAllCate1(num);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request,response);
    }

    private void addCate(HttpServletRequest request, HttpServletResponse response, CategoryService categoryService) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String cname = request.getParameter("cname");
        int i = categoryService.addCategory(cname);
        if(i==1){
            response.getWriter().println("添加成功........");
        }else {
            response.getWriter().println("该类别已经存在");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  response.setContentType("text/html;charset=utf-8");
        doPost(request,response);
    }
}
