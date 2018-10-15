package com.example.chaitali.showsapplication.repository;

import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.repository.database.IDataBaseSource;
import com.example.chaitali.showsapplication.repository.database.ShowsDataBaseImp;
import com.example.chaitali.showsapplication.repository.network.INetworkBaseSource;
import com.example.chaitali.showsapplication.repository.network.NetworkSourceImp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowsRepositorySourceImp implements IShowsRepositorySource {
    INetworkBaseSource networkBaseSource;
    IDataBaseSource dataBaseSource;

    @Inject
    public ShowsRepositorySourceImp(ShowsDataBaseImp dataBaseSource, NetworkSourceImp networkBaseSource) {
        this.dataBaseSource = dataBaseSource;
        this.networkBaseSource = networkBaseSource;
    }

    @Override
    public void getShows(final IShowsCallBack callBack) {
        networkBaseSource.getShowsList().observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Show>>() {
                    @Override
                    public void accept(final List<Show> shows) throws Exception {
                        dataBaseSource.saveAllShowsData(shows);
                        callBack.onSuccess(shows);
                    }
                });;
    }
}
