package com.southwest.southwestapp.network.interfaces;

import com.southwest.southwestapp.network.models.ParseCheckInList;
import com.southwest.southwestapp.network.models.ParseUser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ParseEndpoints {

    @GET("1/login")
    Call<ParseUser> doLogin(@Query("username") String username, @Query("password") String password);

    @GET("1/classes/CheckIn?where%3D%7B%22number_confirmation%22%3A%224FA3H%22%2C%22first_name%22%3A%22John%22%2C%20%22last_name%22%3A%22Smith%22%7D")
    Call<ParseCheckInList> doRetrieveReservation();
}
