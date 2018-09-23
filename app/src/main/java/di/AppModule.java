package di;

import android.content.Context;

import com.example.chaitali.showsapplication.ShowsApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    ShowsApplication showsApplication;

    public AppModule(ShowsApplication application) {
        showsApplication = application;
    }

    @Provides
    ShowsApplication provideApplication() {
        return showsApplication;
    }

    @Provides
    Context providesContext() {
        return showsApplication.getApplicationContext();
    }

}
