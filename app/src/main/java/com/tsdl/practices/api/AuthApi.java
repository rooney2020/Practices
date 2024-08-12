package com.tsdl.practices.api;

import com.tsdl.practices.entity.auth.CodeResult;
import com.tsdl.practices.entity.auth.LoginForm;
import com.tsdl.practices.entity.auth.LoginResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApi {

    @GET("/code")
    Call<CodeResult> getCode();

    @POST("/auth/login")
    Call<LoginResult> login(@Body LoginForm loginForm);

}
