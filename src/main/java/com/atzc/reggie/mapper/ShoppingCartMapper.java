package com.atzc.reggie.mapper;

import com.atzc.reggie.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 张驰
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-12-20 18:48:42
* @Entity com.atzc.reggie.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




