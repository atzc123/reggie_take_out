package com.atzc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atzc.reggie.entity.OrderDetail;
import com.atzc.reggie.service.OrderDetailService;
import com.atzc.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author 张驰
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-12-21 20:37:25
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




