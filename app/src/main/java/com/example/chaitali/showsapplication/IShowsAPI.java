package com.example.chaitali.showsapplication;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IShowsAPI {
    @GET("shows")
    Observable<List<Show>> getShows();
}
