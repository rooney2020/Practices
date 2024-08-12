package com.tsdl.practices.entity.menu;

import com.tsdl.practices.entity.Dish;
import com.tsdl.practices.entity.BaseResult;

import java.util.List;

public class DishListResult extends BaseResult {

    private List<Dish> rows;

    private int total;

    public List<Dish> getRows() {
        return rows;
    }

    public void setRows(List<Dish> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
