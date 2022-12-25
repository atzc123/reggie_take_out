package com.atzc.reggie.service;

import com.atzc.reggie.dto.DishDto;
import com.atzc.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 张驰
 * @description 针对表【dish(菜品管理)】的数据库操作Service
 * @createDate 2022-12-11 21:32:20
 */
public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表，dish和dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品对应的信息和口味
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);

    //根据传过来的id批量或者是单个的删除菜品，并判断是否是启售的
    public void deleteByIds(List<Long> ids);

    //菜品批量删除和单个删除，删除时用到deleteByIds方法删除菜品
    public boolean deleteInSetmeal(List<Long> ids);


}
