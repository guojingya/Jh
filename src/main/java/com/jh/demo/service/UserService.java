package com.jh.demo.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.jh.demo.mapper.usermapper;
import com.jh.demo.pojo.Product;
import com.jh.demo.pojo.ProductPojo;
import com.jh.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class UserService {
    @Autowired
    private usermapper userMapper;

    public String queryUser(String username, String password) {
        // 查询
        User user = this.userMapper.selectOne(username,password);
        // 校验用户名
        if (user == null) {
            return null;
        }
        // 校验密码
        if (!user.getPassword().equals(password)) {
            return null;
        }
        // 用户名密码都正确
        return user.getAccount();
    }
    public List<Product> getProduct(int oid){
       return this.userMapper.getProduct(oid);
    }


    public void export(){
        List<ProductPojo> productAll = userMapper.getProductAll();
        Consumer<ExcelWriter> consumer = writer -> {
            writer.write(productAll, EasyExcel.writerSheet("产品信息")
                    .head(ProductPojo.class)
                    .build());
        };
        export1("D:/产品报表.xlsx", consumer);

    }

    public static void export1(String fileName, Consumer<ExcelWriter> writerConsumer) {
        ExcelWriter writer = EasyExcel.write(fileName)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .build();
        writerConsumer.accept(writer);
        writer.finish();
    }

}
