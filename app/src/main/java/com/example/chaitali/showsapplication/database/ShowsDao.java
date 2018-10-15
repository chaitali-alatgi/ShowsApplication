package com.example.chaitali.showsapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ShowsDao {

    @Insert
    void addAllShows(ShowsEntity... showsEntities);

    @Query("SELECT * FROM shows")
    List<ShowsEntity> getAllShows();


}
