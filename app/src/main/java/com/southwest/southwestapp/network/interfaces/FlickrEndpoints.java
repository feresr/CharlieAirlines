package com.southwest.southwestapp.network.interfaces;

import com.southwest.southwestapp.network.models.FlickrSearchPhotoResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FlickrEndpoints {

    String API_KEY = "043651e39a2497fdacf5ca9e653dde2a";

    @GET("services/rest/?method=flickr.photos.search&api_key=" + API_KEY + "&meadia=photos&sort=relevance&format=json&nojsoncallback=1&page=1")
    Call<FlickrSearchPhotoResponse> searchPhotosByKeyword(@Query("text") String query, @Query("per_page") int amount);

    @GET("services/rest/?method=flickr.photos.search&api_key=" + API_KEY + "&meadia=photos&sort=relevance&accuracy=3&content_type=7&radius=32&format=json&nojsoncallback=1&page=1&content_type=6")
    Call<FlickrSearchPhotoResponse> searchPhotosByPlace(@Query("lon") String lon, @Query("lat") String lat, @Query("per_page") int amount);

}
