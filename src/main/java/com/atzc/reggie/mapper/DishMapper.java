package com.atzc.reggie.mapper;

import com.atzc.reggie.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 张驰
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-12-11 21:32:20
* @Entity com.atzc.reggie.entity.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




