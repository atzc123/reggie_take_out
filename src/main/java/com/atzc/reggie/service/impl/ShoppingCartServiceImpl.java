package com.atzc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atzc.reggie.entity.ShoppingCart;
import com.atzc.reggie.service.ShoppingCartService;
import com.atzc.reggie.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author 张驰
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-12-20 18:48:42
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




