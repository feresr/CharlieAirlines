package com.southwest.southwestapp.apis;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by fernando.raviola on 18/09/2015.
 * <p/>
 * Documentation:
 * https://www.flickr.com/services/api/flickr.photos.search.html
 * https://www.flickr.com/services/api/misc.urls.html
 */
public class FlickrApi {

    private FlickrEndpointsInterface service;
    private static final String API_KEY = "043651e39a2497fdacf5ca9e653dde2a";
    private static final String BASE_URL = "https://api.flickr.com";

    public FlickrApi() {
        super();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrEndpointsInterface.class);
    }

    public FlickrEndpointsInterface getInterface() {
        return service;
    }


    public interface FlickrEndpointsInterface {
        @GET("services/rest/?method=flickr.photos.search&api_key=" + API_KEY + "&meadia=photos&sort=relevance&format=json&nojsoncallback=1&page=1")
        Call<SearchPhotoResponse> searchPhotosByKeyword(@Query("text") String query, @Query("per_page") int amount);

        @GET("services/rest/?method=flickr.photos.search&api_key=" + API_KEY + "&meadia=photos&sort=relevance&accuracy=3&content_type=7&radius=32&format=json&nojsoncallback=1&page=1&content_type=6")
        Call<SearchPhotoResponse> searchPhotosByPlace(@Query("lon") String lon, @Query("lat") String lat, @Query("per_page") int amount);
    }

    //FLICKR MODELS

    public class SearchPhotoResponse {
        private String stat;
        public Photos photos;
    }

    public class Photos {
        public ArrayList<FlickrPhoto> photo;
    }

    public class FlickrPhoto {
        private String id;
        private String secret;
        private String server;
        private String farm;

        /*
        Size Suffixes
        The letter suffixes are as follows:

        s	small square 75x75
        q	large square 150x150
        t	thumbnail, 100 on longest side
        m	small, 240 on longest side
        n	small, 320 on longest side
        -	medium, 500 on longest side
        z	medium 640, 640 on longest side
        c	medium 800, 800 on longest side†
        b	large, 1024 on longest side*
        h	large 1600, 1600 on longest side†
        k	large 2048, 2048 on longest side† */

        public String getUrl(String size) {
            return "https://farm" + this.farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_" + size + ".jpg";
        }
    }

}
