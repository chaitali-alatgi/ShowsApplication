package com.example.chaitali.showsapplication.presenter;

import com.example.chaitali.showsapplication.interactor.IShowsInteractor;
import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.view.IShowsView;

import java.util.List;

import javax.inject.Inject;

public class ShowsPresenterImp implements IShowsPresenter {

    IShowsInteractor interactor;
    IShowsView view;

    @Inject
    public ShowsPresenterImp(IShowsView view, IShowsInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void getShows() {
        interactor.getShows(new IShowsInteractor.IShowsCallBack() {
            @Override
            public void onSuccess(List<Show> showList) {
                view.showShows(showList);
            }

            @Override
            public void onError(String message) {
                view.onError(message);
            }
        });

    }

}
