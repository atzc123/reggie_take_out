package com.atzc.reggie.mapper;

import com.atzc.reggie.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张驰
 * @description 针对表【orders(订单表)】的数据库操作Mapper
 * @createDate 2022-12-21 20:37:25
 * @Entity com.atzc.reggie.entity.Orders
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




