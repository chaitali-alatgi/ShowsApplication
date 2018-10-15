package com.example.chaitali.showsapplication.database;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.chaitali.showsapplication.model.Show;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseManager {

    private static Context mContext;

    private static ShowsDao showsDao;

    private RoomDatabaseManager(Context context) {
        mContext = context;
    }

    public static RoomDatabaseManager getInstance(Context context) {
        showsDao = AppDatabase.getInstance(context).getShowsDao();
       return new RoomDatabaseManager(context);
    }

    public void saveShows(List<Show> showList) {
        for(Show show : showList) {
            ShowsEntity entity = new ShowsEntity();
            entity.setId(show.getId());
            entity.setName(show.getName());
            entity.setStatus(show.getStatus());
            showsDao.addAllShows(entity);
        }
    }

    public List<Show> getAllShowsList() {
        List<ShowsEntity> entityList = showsDao.getAllShows();
        List<Show> showList = new ArrayList<>();
        for(ShowsEntity entity : entityList) {
            Show show = new Show();
            show.setId(entity.getId());
            show.setName(entity.getName());
            show.setStatus(entity.getStatus());
            showList.add(show);
        }
        return showList;
    }
}
