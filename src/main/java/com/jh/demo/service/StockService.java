package com.jh.demo.service;

import com.jh.demo.mapper.usermapper;
import com.jh.demo.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    /**
     *  库存操做对外接口
     *
     */
    @Service
    public class StockService {

        @Autowired
        private StockComponent stockComponent;

        @Autowired
        private usermapper usermapper;


        private static final String REDIS_STOCK_KEY="redis_key:stock:";

        /**
         * 扣减库存
         * @param skuId
         * @param num
         * @return
         */
        public Boolean stock(int skuId,Integer num) {
            // 库存ID
            String redisKey = REDIS_STOCK_KEY + skuId;
            long stock = stockComponent.stock(redisKey, 60 * 60, num, () -> Integer.parseInt(initStock(skuId)));
            if(stock < 0){//异常,库存不足
                System.out.println("库存不足");
                //log.info("库存不足........");
                Product productSku = usermapper.getProductByPid(skuId);
               // throw new MallException(MsRespCode.STOCK_NUMBER_ERROR,new Object[]{productMapper.selectById(productSku.getProductId()).getTitle()});
            }
            return stock >= 0 ;
        }



        /**
         * 添加redis - sku库存数量
         * @param skuId
         * @param num
         * @return
         */
        public Long addStock(String skuId ,Integer num) {
            // 库存ID
            String redisKey = REDIS_STOCK_KEY + skuId;
            long l = stockComponent.addStock(redisKey, num);
            return l;
        }


        /**
         * 获取初始的库存
         *
         * @return
         */
        private String initStock(int pid) {
            //初始化库存
            Product productSku = usermapper.getProductByPid(pid);
            return productSku.getNums()+"";
        }

        /**
         * 获取sku库存
         * @param skuId
         * @return
         */
        public Integer getStock(String skuId) {

            // 库存ID
            String redisKey = REDIS_STOCK_KEY + skuId;

            return stockComponent.getStock(redisKey);
        }
    }

