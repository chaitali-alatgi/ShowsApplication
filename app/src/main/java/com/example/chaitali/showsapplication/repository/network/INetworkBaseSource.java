package com.example.chaitali.showsapplication.repository.network;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import io.reactivex.Observable;

public interface INetworkBaseSource {

    Observable<List<Show>> getShowsList();
}
