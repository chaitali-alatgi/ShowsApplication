package com.example.chaitali.showsapplication.repository;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

public interface IShowsRepositorySource {
    interface IShowsCallBack {
        void onSuccess(List<Show> showList);

        void onError(String message);

    }

    void getShows(IShowsCallBack callBack);
}
