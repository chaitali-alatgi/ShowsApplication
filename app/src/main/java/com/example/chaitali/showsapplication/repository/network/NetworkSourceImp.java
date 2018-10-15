package com.example.chaitali.showsapplication.repository.network;

import com.example.chaitali.showsapplication.repository.IShowsAPI;
import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetworkSourceImp implements INetworkBaseSource {

    @Inject
    IShowsAPI iShowsAPI;

    @Inject
    public NetworkSourceImp() {

    }

    @Override
    public Observable<List<Show>> getShowsList() {
        return iShowsAPI.getShows();
    }
}
