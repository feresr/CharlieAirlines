package com.southwest.southwestapp.network.interfaces;

import com.southwest.southwestapp.network.models.ParseCheckInList;
import com.southwest.southwestapp.network.models.ParseUser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ParseEndpoints {

    @GET("1/login")
    Call<ParseUser> doLogin(@Query("username") String username, @Query("password") String password);

    @GET("1/classes/CheckIn")
    Call<ParseCheckInList> doRetrieveReservation(@Query(value = "where", encoded = true) String query);
}
