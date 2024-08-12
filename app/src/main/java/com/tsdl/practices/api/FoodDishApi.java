package com.tsdl.practices.api;

import com.tsdl.practices.entity.menu.DishListResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FoodDishApi {

    @GET("/menu/dish/list")
    Call<DishListResult> getDishListPage(@Header("Authorization") String token, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

}
