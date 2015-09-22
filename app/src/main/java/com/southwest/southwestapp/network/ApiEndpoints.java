package com.southwest.southwestapp.network;

import com.southwest.southwestapp.network.models.User;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiEndpoints {

    @GET("1/login")
    Call<User> doLogin(@Query("username") String username, @Query("password") String password);
}
