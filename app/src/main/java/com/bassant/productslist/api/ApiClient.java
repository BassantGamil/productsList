package com.bassant.productslist.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;
import java.util.TimeZone;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://fakestoreapi.com/";
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static ApiService instance;

    public static ApiService getInstance(Context context) {
        context= context ;
        if (instance == null) {
            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(offlineInterceptor)
                    .addNetworkInterceptor(onlineInterceptor)
                    .cache(cache)
                    .build();

            instance = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(ApiService.class);
        }
        return instance;
    }

    private static final Interceptor onlineInterceptor = chain -> {
        okhttp3.Response response = chain.proceed(chain.request());
        int maxAge = 10; // read from cache for 60 seconds even if there is internet connection
        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + maxAge)
                .removeHeader("Pragma")
                .build();
    };

    private static final Interceptor offlineInterceptor = chain -> {
        Request request = chain.request();
        return chain.proceed(request);
    };

    public static HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("locale", Resources.getSystem().getConfiguration().locale.getLanguage()); // en: English, ar: Arabic
        headers.put("time-zone", TimeZone.getDefault().getID());

        return headers;
    }
}
