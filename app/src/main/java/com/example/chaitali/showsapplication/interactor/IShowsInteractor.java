package com.example.chaitali.showsapplication.interactor;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import rx.Observable;

public interface IShowsInteractor {
    Observable<List<Show>> getShows();
}
