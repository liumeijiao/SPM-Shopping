package com.bupt.project1.admin.dao.product;

import com.bupt.project1.bean.Category;
import com.bupt.project1.bean.Product;
import com.bupt.project1.utils.Count;
import com.bupt.project1.utils.Dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class product implements productDao {
    QueryRunner queryRunner= new QueryRunner(Dbcp.getBasicDataSource());
    @Override
    public int addToProduct(Product product) {
        int update =0;
        try {
            update = queryRunner.update("insert into product values(?,?,?,?,?,?,?,?)", product.getPid(), product.getPname(), product.getEstoreprice(),
                    product.getMarkprice(), product.getPnum(), product.getCid(), product.getImgurl(), product.getDescp());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public List<Product> selectAllInDB() {
        List<Product> query =null;
        try {
            query = queryRunner.query("select * from product", new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public int deleteInDB(String pid) {
        int update =0;
        try {
            update = queryRunner.update("delete from product where pid=?", pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public Product selectById(String i) {
        Product query =null;
        try {
            query = queryRunner.query("select * from product where pid=?", new BeanHandler<Product>(Product.class), i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public int updatePro(Product product) {
        int update =0;
        try {
            update = queryRunner.update("update product set cid=?,pnum=?,pname=?,estoreprice=?,markprice=?" +
                            ",descp=? ,imgurl=? where pid=?", product.getCid(), product.getPnum(), product.getPname(), product.getEstoreprice(),
                    product.getMarkprice(),
                    product.getDescp(),product.getImgurl(), product.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public Category selectCategoryName(int cid) {
        Category query =null;
        try {
            query = queryRunner.query("select * from category where cid=?", new BeanHandler<Category>(Category.class),cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int caculateCount() {
        Connection connection = Dbcp.getConnection();
        String sql="select count(*) AS  count1 from product";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt("count1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    @Override
    public List<Product> showAllPro(int recordNumberPerPage, int parseInt) {
        List<Product> query=null;
        try {
            query = queryRunner.query("select * from product limit ? offset ?", new BeanListHandler<Product>(Product.class)
                    , recordNumberPerPage, parseInt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Product> multSelect(String pid, String cid, String pname, String minprice, String maxprice,int every,int offset) {
        String sql = "select * from product where 1=1";
        ArrayList objects = new ArrayList<>();

        if(pid!=""||!pid.isEmpty()){
            sql+=" and pid=?";
            objects.add(pid);
        }
        if(cid!=""||!cid.isEmpty()){
            sql+=" and cid=?";
            int i = Integer.parseInt(cid);
            objects.add(i);
        }
        if(pname!=""||!pname.isEmpty()){
            sql+=" and pname=?";
            objects.add(pname);
        }
        if(minprice!=""||!minprice.isEmpty()){
            sql+=" and estoreprice>=?";
            double v = Double.parseDouble(minprice);
            objects.add(v);
        }
        if(maxprice!=""||!maxprice.isEmpty()){
            sql+=" and estoreprice<=?";
            double v = Double.parseDouble(maxprice);
            objects.add(v);
        }

        sql +="  limit ? offset ?";
        objects.add(every);
        objects.add(offset);
        Object[] toArray = objects.toArray();
        List<Product> query =null;

        try {
           query = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),toArray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int caculateSlect() {

        return 0;
    }

    @Override
    public int multCount(String pid, String cid, String pname, String minprice, String maxprice) {
        int count=0;

        String sql = "select count(*) as cou from product where 1=1";
        ArrayList objects = new ArrayList<>();

        if(pid!=""||!pid.isEmpty()){
            sql+=" and pid=?";
            objects.add(pid);
        }
        if(cid!=""||!cid.isEmpty()){
            sql+=" and cid=?";
            int i = Integer.parseInt(cid);
            objects.add(i);
        }
        if(pname!=""||!pname.isEmpty()){
            sql+=" and pname=?";
            objects.add(pname);
        }
        if(minprice!=""||!minprice.isEmpty()){
            sql+=" and estoreprice>?";
            double v = Double.parseDouble(minprice);
            objects.add(v);
        }
        if(maxprice!=""||!maxprice.isEmpty()){
            sql+=" and estoreprice<?";
            double v = Double.parseDouble(maxprice);
            objects.add(v);
        }
        int cou =0;
        Object[] toArray = objects.toArray();
        try {
            List<Count> query = queryRunner.query(sql, new BeanListHandler<Count>(Count.class), toArray);

            for (Count count1 : query) {
                cou = count1.getCou();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cou;
    }
}
