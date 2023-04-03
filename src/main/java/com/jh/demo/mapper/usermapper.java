package com.jh.demo.mapper;

import com.jh.demo.pojo.Product;
import com.jh.demo.pojo.ProductPojo;
import com.jh.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface usermapper {
    //根据id查询
    User selectOne(String username, String password);
    List<Product> getProduct(int oid);
    Product getProductByPid(int pid);
    List<ProductPojo> getProductAll();

}
