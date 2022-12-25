package com.atzc.reggie.service;

import com.atzc.reggie.dto.SetmealDto;
import com.atzc.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 张驰
 * @description 针对表【setmeal(套餐)】的数据库操作Service
 * @createDate 2022-12-11 21:32:20
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时删除套餐和菜品的关联数据
     *
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    /**
     * 修改套餐
     */
    public void updateWithDish(SetmealDto setmealDto);


    /**
     * 根据套餐id查询套餐中的菜品
     */
    public SetmealDto getByIdWithDish(long id);

}
