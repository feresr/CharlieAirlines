package com.southwest.southwestapp.network;

import com.southwest.southwestapp.network.interfaces.FlickrEndpoints;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by fernando.raviola on 18/09/2015.
 * <p/>
 * Documentation:
 * https://www.flickr.com/services/api/flickr.photos.search.html
 * https://www.flickr.com/services/api/misc.urls.html
 */
public class FlickrApi {

    private FlickrEndpoints service;

    private static final String BASE_URL = "https://api.flickr.com";

    public FlickrApi() {
        super();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrEndpoints.class);
    }

    public FlickrEndpoints getInterface() {
        return service;
    }

}
