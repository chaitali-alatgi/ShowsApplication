package di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ServiceModule.class})
public interface IAppComponent {
    IShowsComponent plus(ShowsModule module);
}
