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
 */
public class FlickrApi {

    FlickrEndpointsInterface service;
    private static final String API_KEY = "043651e39a2497fdacf5ca9e653dde2a";
    public FlickrApi() {
        super();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrEndpointsInterface.class);
    }

    public FlickrEndpointsInterface getInterface() {
        return service;
    }

    /**
     * Created by fernando.raviola on 18/09/2015.
     */
    public interface FlickrEndpointsInterface {
        @GET("services/rest/?method=flickr.photos.search&api_key=" + API_KEY + "&sort=relevance&content_type=7&geo_context=2&format=json&nojsoncallback=1&per_page=5&page=1")
        Call<SearchPhotoResponse> searchPhotos(@Query("text") String query);
    }

    public class SearchPhotoResponse {
        private String stat;
        public Photos photos;
    }

    public class Photos {
        private int page;
        private int pages;
        private int perpage;
        private int total;
        public ArrayList<Photo> photo;
    }

    public class Photo {
        public String id;
        public String owner;
        public String secret;
        public String server;
        public String farm;
        public String title;
    }

}
