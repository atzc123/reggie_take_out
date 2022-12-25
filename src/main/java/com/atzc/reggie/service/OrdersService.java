package com.atzc.reggie.service;

import com.atzc.reggie.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张驰
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-12-21 20:37:25
*/
public interface OrdersService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

}
