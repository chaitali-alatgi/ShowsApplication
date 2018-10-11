package com.example.chaitali.showsapplication.presenter;

import android.util.Log;

import com.example.chaitali.showsapplication.IShowsAPI;
import com.example.chaitali.showsapplication.database.RoomDatabaseManager;
import com.example.chaitali.showsapplication.interactor.IShowsInteractor;
import com.example.chaitali.showsapplication.interactor.ShowsInteractorImp;
import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.view.IShowsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

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
