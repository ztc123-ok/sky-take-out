package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @return
     */

    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     *
     * 批量插入套餐菜品
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
