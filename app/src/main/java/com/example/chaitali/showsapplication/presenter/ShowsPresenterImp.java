package com.example.chaitali.showsapplication.presenter;

import android.util.Log;

import com.example.chaitali.showsapplication.IShowsAPI;
import com.example.chaitali.showsapplication.interactor.IShowsInteractor;
import com.example.chaitali.showsapplication.interactor.ShowsInteractorImp;
import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.view.IShowsView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        Observable<List<Show>> shows =  interactor.getShows();
        shows.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Show>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("",""+e);
            }

            @Override
            public void onNext(List<Show> shows) {
                view.showShows(shows);
            }
        });

    }
}
