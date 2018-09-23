package com.example.chaitali.showsapplication;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface IShowsAPI {
    @GET("shows")
    Observable<List<Show>> getShows();
}
