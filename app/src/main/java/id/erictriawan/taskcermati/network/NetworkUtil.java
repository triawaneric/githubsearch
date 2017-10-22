package id.erictriawan.taskcermati.network;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import id.erictriawan.taskcermati.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


/**
 * Created by erictriawan on 10/20/17.
 */

public class NetworkUtil {
    public static RestAPI getRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.readTimeout(Constants.getServicePeriod(), TimeUnit.MILLISECONDS);
        httpClient.connectTimeout(Constants.getServicePeriod(), TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization","token 2717ca2dc4bea2c9b2e7af09f6e37542de61c58a")
                        .header("Retry-After","30")
                        .header("User-Agent","Demo-App")
                        .method(original.method(), original.body());



                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        });

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestAPI.class);
    }
}
