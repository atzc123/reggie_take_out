package com.atzc.reggie.mapper;

import com.atzc.reggie.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 张驰
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-12-11 16:40:18
* @Entity com.atzc.reggie.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




