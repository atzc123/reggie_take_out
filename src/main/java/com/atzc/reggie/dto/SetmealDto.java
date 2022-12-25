package com.atzc.reggie.dto;

import com.atzc.reggie.entity.Setmeal;
import com.atzc.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
