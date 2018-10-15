package di;

import android.util.Log;

import com.example.chaitali.showsapplication.view.ShowsApplication;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    ShowsApplication showsApplication;
    public static final String BASE_URL = "http://api.tvmaze.com/";

    public NetworkModule(ShowsApplication application) {
        showsApplication = application;
    }

    @Provides
    @Singleton
    Cache provideCache() {
        int cacheSize = 15 * 1024 * 1024;   // 15 MB
        return new Cache(showsApplication.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.readTimeout(2, TimeUnit.MINUTES);
        builder.connectTimeout(2, TimeUnit.MINUTES);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e("","request= "+request);
                Response response = chain.proceed(request);
                Log.e(""," response= "+response);
                return response;
            }
        });
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, Cache cache, OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(okHttpClient);
        return builder.build();
    }
}
