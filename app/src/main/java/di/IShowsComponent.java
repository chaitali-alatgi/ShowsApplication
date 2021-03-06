package di;

import com.example.chaitali.showsapplication.view.MainActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {ShowsModule.class})
public interface IShowsComponent {
    void inject(MainActivity mainActivity);
}
