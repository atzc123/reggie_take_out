package com.atzc.reggie.service;

import com.atzc.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 张驰
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service
 * @createDate 2022-12-11 16:40:18
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);


}
