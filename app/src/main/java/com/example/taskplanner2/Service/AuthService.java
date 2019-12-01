package com.example.taskplanner2.Service;


import com.example.taskplanner2.Login.LoginWrapper;
import com.example.taskplanner2.Login.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("taskPlanner/user/login")
    Call<Token> loginUser(@Body LoginWrapper userLogin);
}
