package nz.co.liuming.cityfriends.common.rest;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import nz.co.liuming.cityfriends.CityFreindsApplication;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuming on 15/06/16.
 */
public class RestModule {

    private static final String BASE_URL = "https://mojo-backend-mingliu.c9users.io/v2/";
    private static RestApis apis = null;

    static {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build();

        apis = retrofit.create(RestApis.class);

    }

    public static RestApis getApis() {
        return apis;
    }

    private static OkHttpClient okHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .cache(providesCache())
                .addInterceptor(logging)
                .addNetworkInterceptor(logging)
                .addInterceptor(requestInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    private static Cache providesCache() {
        File cacheFile = new File(CityFreindsApplication.get().getCacheDir(), "okhttp");
        return new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
    }

    private static Interceptor requestInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();


                Request.Builder requestBuilder = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json");

                if (CityFreindsApplication.get().getUserDelegate().isLogin()) {
                    StringBuilder tokenBuilder = new StringBuilder("Bearer");
                    tokenBuilder.append(" ").append(CityFreindsApplication.get().getUserDelegate().getId());
                    tokenBuilder.append(" ").append(CityFreindsApplication.get().getUserDelegate().getToken());
                    requestBuilder.header("Authorization", tokenBuilder.toString());
                }


                Request request = requestBuilder.method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
    }
}
