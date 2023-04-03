package com.jh.demo.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.jh.demo.pojo.Product;
import com.jh.demo.service.StockService;
import com.jh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("Jh")
public class JhController {
    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private StockService stockService;



    @RequestMapping("doLogin")
    public SaResult doLogin(String username, String password) {
        //判断用户名和密码是否正确（密码须要md5加密，为了测试暂时不写）
        String account = userService.queryUser(username, password);
        if(account!=null) {
            //根据账号进行登录
            StpUtil.login(account);
            SaResult.ok()
                    .set("id", StpUtil.getLoginId())
                    .set("name", username);
        }

        // 获取 Token 相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 返回给前端
        return SaResult.data(tokenInfo);
    }


    @RequestMapping("test")
    public String test() {
        Object o = redisTemplate.opsForValue().get("1");
        System.out.println(o);
        return "测试";
    }


   //查询某个订单下的所有产品以及购买的数量(简单实现一个连表查询)
    @RequestMapping("getProduct")
    public List<Product> getProduct(int oid){
        List<Product> productList = this.userService.getProduct(oid);
        return productList;
    }
   //将库存放到缓存，利用redis的特性来扣减库存，解决了超扣和性能问题
    @RequestMapping("stock")
    public Boolean stock(int skuId,Integer num){
        Boolean stock = stockService.stock(skuId, num);
        return stock;
    }

    //订单产品导出excel,使用easyexcel
    @RequestMapping("export")
    public void export(){
        userService.export();
    }

    //Java中的并发集合是什么，测试类进行测试演示
    @RequestMapping("listTest")
        public void listTest(){
        List<String> list = new ArrayList<String>();
            for (int i = 0; i < 30; i++) {
                new Thread(() -> {
                    list.add(UUID.randomUUID().toString().substring(0, 8)); //写操作
                    System.out.println(list.toString()); //读操作
                }, String.valueOf(i)).start();
            }
        }

        //Java中的并发集合是什么，测试类进行测试演示
    @RequestMapping("listTest2")
    public void listTest2(){
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8)); //写操作
                System.out.println(list.toString()); //读操作
            }, String.valueOf(i)).start();
        }
    }

}

