package com.atzc.reggie.service.impl;

import com.atzc.reggie.common.CustomException;
import com.atzc.reggie.entity.Category;
import com.atzc.reggie.entity.Dish;
import com.atzc.reggie.entity.Setmeal;
import com.atzc.reggie.mapper.CategoryMapper;
import com.atzc.reggie.service.CategoryService;
import com.atzc.reggie.service.DishService;
import com.atzc.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张驰
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
 * @createDate 2022-12-11 16:40:18
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Autowired
    private DishService dishService;


    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        if (count1 > 0) {
            //抛异常
            throw new CustomException("当前分类项关联了菜品，不能删除");
        }
        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            throw new CustomException("当前分类项关联了套餐，不能删除");
        }
        //正常删除分类
        super.removeById(id);

    }


}




