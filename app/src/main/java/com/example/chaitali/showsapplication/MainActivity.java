package com.example.chaitali.showsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.presenter.IShowsPresenter;
import com.example.chaitali.showsapplication.view.IShowsView;
import com.example.chaitali.showsapplication.view.ShowsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import di.ShowsModule;

public class MainActivity extends AppCompatActivity implements IShowsView{

    @Inject
    IShowsPresenter presenter;

    ShowsAdapter adapter;

    @BindView(R.id.shows_list)
    RecyclerView showsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ShowsApplication.getAppComponent().plus(new ShowsModule(this)).inject(this);
        setUpView();
        presenter.getShows();
    }

    private void setUpView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        showsList.setLayoutManager(layoutManager);
        adapter = new ShowsAdapter(this);
        showsList.setAdapter(adapter);
    }

    @Override
    public void showShows(List<Show> shows) {
        adapter.setShowList(shows);
    }
}
