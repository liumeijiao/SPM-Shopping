package com.bupt.project1.admin.control;

import com.bupt.project1.bean.Product;
import com.bupt.project1.admin.service.ProductService;
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
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AddProductServlet",urlPatterns = "/admin/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext context = getServletConfig().getServletContext();
        File attribute = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(attribute);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        String realPath;
        Product product=new Product();
        ProductService productService = new ProductService();
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            Iterator<FileItem> iterator = fileItems.iterator();

            while (iterator.hasNext()){
                FileItem fileItem = iterator.next();
                if(fileItem.isFormField()){
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    if("cid".equals(fieldName)){
                        product.setCid(Integer.parseInt(value));
                    }else if("pid".equals(fieldName)){
                        product.setPid(value);
                    }else if("pnum".equals(fieldName)){
                        product.setPnum(Integer.parseInt(value));
                    }else if("pname".equals(fieldName)){
                        product.setPname(value);
                    }else if("estoreprice".equals(fieldName)){
                        product.setEstoreprice(Double.parseDouble(value));
                    }else if("markprice".equals(fieldName)){
                        product.setMarkprice(Double.parseDouble(value));
                    }else if("description".equals(fieldName)){
                        product.setDescp(value);
                    }
                }else{
                    insertImg(request, product, fileItem);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i=productService.addProduct(product);
        if(i==1){
            response.getWriter().println("插入成功.........");

        }else {
            response.getWriter().println("该类商品已经存在，即将跳转至商品列表页");
            response.setHeader("refresh","2;url=/project1/admin/ProductServlet?op=findAllProduct");
        }

    }

    private void insertImg(HttpServletRequest request, Product product, FileItem fileItem) throws Exception {
        String realPath;
        String itemName = fileItem.getName();
        String[] split = itemName.split("/.");
        String hou = split[split.length - 1];
        String s = UUID.randomUUID().toString();
        String newName=s+"."+hou;
        realPath= request.getServletContext().getRealPath(newName);

        product.setImgurl(newName);
        File file = new File(realPath);
        fileItem.write(file);
    }

//    private void insertImg(Product product, FileItem fileItem) throws Exception {
//
//
//    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doPost(request,response);
    }
}
