package com.example.chaitali.showsapplication.repository.database;

import com.example.chaitali.showsapplication.model.Show;

import java.util.List;

public interface IDataBaseSource {

    void saveAllShowsData(List<Show> showList);

    List<Show> getAllShows();
}
