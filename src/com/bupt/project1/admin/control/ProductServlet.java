package com.bupt.project1.admin.control;

import com.bupt.project1.bean.Product;
import com.bupt.project1.bean.multy;
import com.bupt.project1.admin.service.ProductService;
import com.bupt.project1.utils.PageHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ProductServlet",urlPatterns = "/admin/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=utf-8");
         String op = request.getParameter("op");
         ProductService productService = new ProductService();
         switch (op){
             case "findAllProduct":{
                 findAll(request, response, productService);
                 break;
             }
             case "deleteOne":{
                 deleteOne(request, response, productService);
                 break;
             }

             case "updateProduct1":{
                 updatePro(request, response, productService);
                 break;
             }

             case "updateProduct":{
                 updateData(request, response, productService);
                 break;
             }

             case "mult":{
                 HttpSession session = request.getSession(false);
                 multy temp = (multy) session.getAttribute("temp");
                 if(temp!=null){
                     session.setAttribute("temp",null);
                }
//           response.sendRedirect("/project1/admin/ProductServlet?op=multSelect");
             request.getRequestDispatcher("/admin/ProductServlet?op=multSelect").forward(request,response);
            }
             case "multSelect" :{
                 String pid = request.getParameter("pid");
                 String cid = request.getParameter("cid");
                 String pname = request.getParameter("pname");
                 String minprice = request.getParameter("minprice");
                 String maxprice = request.getParameter("maxprice");
                 String num = request.getParameter("num");

                 multy multy = new multy();
                 multy.setCid(cid);
                 multy.setPid(pid);
                 multy.setPname(pname);
                 multy.setMaxprice(maxprice);
                 multy.setMinprice(minprice);
                 HttpSession session = request.getSession();
                 multy temp1 = (com.bupt.project1.bean.multy)session.getAttribute("temp");
                 if(temp1==null) {
                     session.setAttribute("temp", multy);
                 }

                 multy temp = (com.bupt.project1.bean.multy) session.getAttribute("temp");

                 PageHelper pageHelper = productService.multSelect(temp.getPid(), temp.getCid(), temp.getPname(),
                         temp.getMinprice(), temp.getMaxprice(), num);
                  HttpSession session1 = request.getSession();


                 request.setAttribute("mult",pageHelper);
                 request.getRequestDispatcher("/admin/product/MultSele.jsp").forward(request,response);
                 break;
             }
         }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response, ProductService productService) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String num = request.getParameter("num");
        PageHelper productList = productService.showAllPro(num);
        request.setAttribute("productList",productList);
        request.getRequestDispatcher("/admin/product/productList.jsp").forward(request,response);
    }

    private void deleteOne(HttpServletRequest request, HttpServletResponse response, ProductService productService) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String pid = request.getParameter("pid");
        int i=productService.deleteProduct(pid);
        if(i==1){
             response.getWriter().println("删除成功，即将跳转至首页");
             response.setHeader("refresh","2;url=/project1/admin/ProductServlet?op=findAllProduct");
        }
    }

    private void updatePro(HttpServletRequest request, HttpServletResponse response, ProductService productService) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String pid = request.getParameter("pid");
        Product product = productService.selectById(pid);
        request.setAttribute("product",product);

        request.getRequestDispatcher("/admin/product/updateProduct.jsp").forward(request,response);
        return;
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response, ProductService productService) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String imgurl1 = request.getParameter("imgurl");
        String path=getServletContext().getRealPath(imgurl1);
        File file = new File(path);

        if(file.exists()) {
            boolean delete = file.delete();
        }


            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext context = getServletConfig().getServletContext();
            File attribute = (File) context.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(attribute);
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            String realPath;
            Product product = new Product();
            try {
                List<FileItem> fileItems = fileUpload.parseRequest(request);
                Iterator<FileItem> iterator = fileItems.iterator();

                while (iterator.hasNext()) {
                    FileItem fileItem = iterator.next();
                    if (fileItem.isFormField()) {
                        String fieldName = fileItem.getFieldName();
                        String value = fileItem.getString("utf-8");
                        if ("cid".equals(fieldName)) {
                            product.setCid(Integer.parseInt(value));
                        } else if ("pid".equals(fieldName)) {
                            product.setPid(value);
                        } else if ("pnum".equals(fieldName)) {
                            product.setPnum(Integer.parseInt(value));
                        } else if ("pname".equals(fieldName)) {
                            product.setPname(value);
                        } else if ("estoreprice".equals(fieldName)) {
                            product.setEstoreprice(Double.parseDouble(value));
                        } else if ("markprice".equals(fieldName)) {
                            product.setMarkprice(Double.parseDouble(value));
                        } else if ("description".equals(fieldName)) {
                            product.setDescp(value);
                        }
                    } else {
                        String realPath1;
                        String itemName = fileItem.getName();
                        String[] split = itemName.split("/.");
                        String hou = split[split.length - 1];
                        String s = UUID.randomUUID().toString();
                        String newName1=s+"."+hou;
                        realPath1= request.getServletContext().getRealPath(newName1);
                        System.out.println(realPath1+"新图片位置");
                        product.setImgurl(newName1);
                        System.out.println(product);
                        File file1 = new File(realPath1);
                        fileItem.write(file1);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            int i = productService.updateProduct(product);
            if (i == 1) {
                response.getWriter().println("修改成功");
                response.setHeader("refresh", "2;url=/project1/admin/ProductServlet?op=findAllProduct");
            }
        }





    private void insertImg(HttpServletRequest request, Product product, FileItem fileItem) throws Exception {


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        doPost(request,response);
    }
}
