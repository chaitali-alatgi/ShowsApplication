package com.example.chaitali.showsapplication.view;

import android.content.Context;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

public interface IShowsView {
    void showShows(List<Show> shows);

    void onError(String message);

    Context getContext();
}
