package com.southwest.southwestapp.network.interfaces;

import com.southwest.southwestapp.network.models.SwaUser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SwaEndpoints {

    @GET("1/login")
    Call<SwaUser> doLogin(@Query("username") String username, @Query("password") String password);
}
