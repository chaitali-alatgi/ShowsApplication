package com.example.chaitali.showsapplication.interactor;

import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.repository.IShowsRepositorySource;
import com.example.chaitali.showsapplication.repository.ShowsRepositorySourceImp;

import java.util.List;

import javax.inject.Inject;


public class ShowsInteractorImp implements IShowsInteractor {

    IShowsRepositorySource iShowsRepositorySource;

    @Inject
    public ShowsInteractorImp(ShowsRepositorySourceImp iShowsRepositorySource) {
        this.iShowsRepositorySource = iShowsRepositorySource;
    }

    @Override
    public void getShows(final IShowsCallBack callBack) {
        iShowsRepositorySource.getShows(new IShowsRepositorySource.IShowsCallBack() {
            @Override
            public void onSuccess(List<Show> showList) {
                callBack.onSuccess(showList);
            }

            @Override
            public void onError(String message) {
                callBack.onError(message);
            }
        });
    }
}
