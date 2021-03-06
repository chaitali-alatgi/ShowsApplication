package di;

import com.example.chaitali.showsapplication.interactor.IShowsInteractor;
import com.example.chaitali.showsapplication.interactor.ShowsInteractorImp;
import com.example.chaitali.showsapplication.presenter.IShowsPresenter;
import com.example.chaitali.showsapplication.presenter.ShowsPresenterImp;
import com.example.chaitali.showsapplication.repository.IShowsRepositorySource;
import com.example.chaitali.showsapplication.repository.ShowsRepositorySourceImp;
import com.example.chaitali.showsapplication.repository.database.IDataBaseSource;
import com.example.chaitali.showsapplication.repository.database.ShowsDataBaseImp;
import com.example.chaitali.showsapplication.view.IShowsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ShowsModule {
    IShowsView showsView;

    public ShowsModule(IShowsView view) {
        showsView = view;
    }

    @Provides
    @Singleton
    IShowsView provideShowsView() {
        return showsView;
    }

    @Provides
    @Singleton
    IShowsPresenter providePresenter(ShowsPresenterImp showsPresenterImp) {
        return showsPresenterImp;
    }

    @Provides
    @Singleton
    IShowsInteractor provideInteractor(ShowsInteractorImp showsInteractorImp) {
        return showsInteractorImp;
    }

    @Provides
    @Singleton
    IDataBaseSource provideDataBaseSource(ShowsDataBaseImp iDataBaseSource) {
        return iDataBaseSource;
    }

    @Provides
    @Singleton
    IShowsRepositorySource provideShowsRepositorySource(ShowsRepositorySourceImp showsRepositorySourceImp) {
        return showsRepositorySourceImp;
    }


}
