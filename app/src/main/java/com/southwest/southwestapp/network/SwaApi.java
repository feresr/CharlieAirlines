package com.southwest.southwestapp.network;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.network.interfaces.SwaEndpoints;
import com.southwest.southwestapp.network.utils.ToStringConverter;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class SwaApi {

    private static boolean USE_CACHE = false;

    private static final String BASE_URL = "https://api.parse.com/";

    private static final String X_Parse_Application_Id = "Qx2SQ0Bv4Y7WpNmU6CKBgQqO9HRSOZyIrGaQOymh";
    private static final String X_Parse_REST_API_Key = "o5sUgO75LU6s1JxDWWEJvmbVyEu3Xm54NxHX9HM4";
    private static final String X_Parse_Revocable_Session = "1";

    private static final String CACHE_NAME = "rest_client_cache";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    private SwaEndpoints service;

    public SwaApi() {

        OkHttpClient okClient = new OkHttpClient();

        // Set Cache
        if (USE_CACHE) {
            File cacheDirectory = new File(AppHelper.getInstance().getCacheDir(), CACHE_NAME);
            Cache cache = new Cache(cacheDirectory, CACHE_SIZE);
            if (cache != null) {
                okClient.setCache(cache);
            }
        }

        // Set timeout
        okClient.setConnectTimeout(10, TimeUnit.SECONDS);
        okClient.setWriteTimeout(10, TimeUnit.SECONDS);
        okClient.setReadTimeout(30, TimeUnit.SECONDS);

        // Set headers
        okClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request().newBuilder()
                        .addHeader("X-Parse-Application-Id", X_Parse_Application_Id)
                        .addHeader("X-Parse-REST-API-Key", X_Parse_REST_API_Key)
                        .addHeader("X-Parse-Revocable-Session", X_Parse_Revocable_Session)
                        .build();

                return chain.proceed(request);
            }
        });

        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverter(String.class, new ToStringConverter())
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = client.create(SwaEndpoints.class);
    }

    public SwaEndpoints getInterface() {
        return service;
    }
}
