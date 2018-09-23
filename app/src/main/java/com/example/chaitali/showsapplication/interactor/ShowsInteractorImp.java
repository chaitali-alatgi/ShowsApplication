package com.example.chaitali.showsapplication.interactor;

import com.example.chaitali.showsapplication.IShowsAPI;
import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ShowsInteractorImp implements IShowsInteractor {

    @Inject
    IShowsAPI iShowsAPI;

    @Inject
    public ShowsInteractorImp() {

    }

    @Override
    public Observable<List<Show>> getShows() {
       return iShowsAPI.getShows();
    }
}
