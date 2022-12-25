package com.atzc.reggie.service.impl;

import com.atzc.reggie.common.CustomException;
import com.atzc.reggie.dto.DishDto;
import com.atzc.reggie.entity.Dish;
import com.atzc.reggie.entity.DishFlavor;
import com.atzc.reggie.entity.Setmeal;
import com.atzc.reggie.entity.SetmealDish;
import com.atzc.reggie.mapper.DishMapper;
import com.atzc.reggie.service.DishFlavorService;
import com.atzc.reggie.service.DishService;
import com.atzc.reggie.service.SetmealDishService;
import com.atzc.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张驰
 * @description 针对表【dish(菜品管理)】的数据库操作Service实现
 * @createDate 2022-12-11 21:32:20
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
        implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private SetmealService setmealService;


    /**
     * 新增菜品，同时保存对应的口味数据
     *
     * @param dishDto
     */
    @Transactional//事务
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表到dish
        this.save(dishDto);
        Long dishId = dishDto.getId();//菜品id
        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品的口味到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);


    }

    /**
     * 根据id查询菜品的信息和口味信息
     *
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品的基本信息,从dish表中查询
        DishDto dishDto = new DishDto();
        Dish dish = this.getById(id);
        BeanUtils.copyProperties(dish, dishDto);
        //查询当前菜品的口味信息，从dish_flavor中查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavor = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavor);
        return dishDto;
    }

    /**
     * 修改菜品
     * @param dishDto
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新菜品基本的信息,dish表
        this.updateById(dishDto);
        //更新口味表中的信息,但是更新之前删除所有的口味
        //1.清理当前菜品对应的口味数据 dish_flavor表的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //2.添加当前提交过来的口味数据  dish_flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据传过来的id批量或者是单个的删除菜品，并判断是否是启售的
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(List<Long> ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //先查询该菜品是否在售卖，如果是则抛出业务异常
        queryWrapper.in(ids != null, Dish::getId, ids);
        List<Dish> list = this.list(queryWrapper);
        for (Dish dish : list) {
            Integer status = dish.getSort();
            if (status == 0) {
                this.removeById(dish.getId());
            }
            throw new CustomException("删除菜品中有正在售卖菜品,无法全部删除");
        }
    }

    /**
     * 菜品批量删除和单个删除，删除时用到deleteByIds方法删除菜品
     *
     * @param ids
     * @return
     */
    @Transactional
    public boolean deleteInSetmeal(List<Long> ids) {
        boolean flag = true;
        //1.根据菜品id在stemeal_dish表中查出哪些套餐包含该菜品
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getDishId, ids);
        List<SetmealDish> SetmealDishList = setmealDishService.list(setmealDishLambdaQueryWrapper);
        //2.如果菜品没有关联套餐，直接删除就行  其实下面这个逻辑可以抽离出来，这里我就不抽离了
        if (SetmealDishList.size() == 0) {
            //这个deleteByIds中已经做了菜品启售不能删除的判断力
            this.deleteByIds(ids);
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(DishFlavor::getDishId, ids);
            dishFlavorService.remove(queryWrapper);
            return flag;
        }

        //3.如果菜品有关联套餐，并且该套餐正在售卖，那么不能删除
        //3.1得到与删除菜品关联的套餐id
        ArrayList<Long> Setmeal_idList = new ArrayList<>();
        for (SetmealDish setmealDish : SetmealDishList) {
            Long setmealId = setmealDish.getSetmealId();
            Setmeal_idList.add(setmealId);
        }
        //3.2查询出与删除菜品相关联的套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId, Setmeal_idList);
        List<Setmeal> setmealList = setmealService.list(setmealLambdaQueryWrapper);
        //3.3对拿到的所有套餐进行遍历，然后拿到套餐的售卖状态，如果有套餐正在售卖那么删除失败
        for (Setmeal setmeal : setmealList) {
            Integer status = setmeal.getStatus();
            if (status == 1) {
                flag = false;
            }
        }
        //3.4要删除的菜品关联的套餐没有在售，可以删除
        //3.5这下面的代码并不一定会执行,因为如果前面的for循环中出现status == 1,那么下面的代码就不会再执行
        this.deleteByIds(ids);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DishFlavor::getDishId, ids);
        dishFlavorService.remove(queryWrapper);
        return flag;
    }
}




