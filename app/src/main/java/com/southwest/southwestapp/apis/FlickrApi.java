package com.southwest.southwestapp.apis;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by fernando.raviola on 18/09/2015.
 */
public class FlickrApi {

    FlickrInterface service;

    public FlickrApi() {
        super();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrInterface.class);

    }

    public FlickrInterface getInterface() {
        return service;
    }

    /**
     * Created by fernando.raviola on 18/09/2015.
     */

    public interface FlickrInterface {
        @GET("services/rest/?method=flickr.photos.search&api_key=cdfae5144dd6b74611e56c68a0adc600&sort=relevance&content_type=7&geo_context=2&format=json&nojsoncallback=1&per_page=5&page=1")
        Call<SearchPhotoResponse> searchPhotos(@Query("text") String query);
    }

    public class SearchPhotoResponse {
        private String stat;
        private Photos photos;
    }

    public class Photos {
        int page;
        int pages;
        int perpage;
        int total;
        private ArrayList<Photo> photo;
    }

    public class Photo {
        String id;
        String owner;
        String secret;
        String server;
        int farm;
        String title;
        int ispublic;
        int isfriend;
        int isfamily;
    }


}
