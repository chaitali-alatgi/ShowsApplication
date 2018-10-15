package com.example.chaitali.showsapplication.repository.database;

import android.content.Context;

import com.example.chaitali.showsapplication.database.RoomDatabaseManager;
import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

import javax.inject.Inject;

public class ShowsDataBaseImp implements IDataBaseSource {

    Context mContext;

    @Inject
    public ShowsDataBaseImp(Context context) {
        mContext = context;
    }

    @Override
    public void saveAllShowsData(List<Show> showList) {
        RoomDatabaseManager.getInstance(mContext).saveShows(showList);
    }

    @Override
    public List<Show> getAllShows() {
        return RoomDatabaseManager.getInstance(mContext).getAllShowsList();
    }
}
