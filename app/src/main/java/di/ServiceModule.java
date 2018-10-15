package di;

import com.example.chaitali.showsapplication.repository.IShowsAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    public ServiceModule() {

    }

    @Provides
    @Singleton
    IShowsAPI provideApi(Retrofit retrofit) {
        return retrofit.create(IShowsAPI.class);
    }
}

