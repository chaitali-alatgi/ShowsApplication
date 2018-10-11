package com.example.chaitali.showsapplication;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.presenter.IShowsPresenter;
import com.example.chaitali.showsapplication.view.IShowsView;
import com.example.chaitali.showsapplication.view.ShowsAdapter;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import di.ShowsModule;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;

public class MainActivity extends AppCompatActivity implements IShowsView{

    @Inject
    IShowsPresenter presenter;

    ShowsAdapter adapter;

    @BindView(R.id.shows_list)
    RecyclerView showsList;


    private int pageNumber = 0;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private PublishProcessor<Integer> paginator = PublishProcessor.create();
    private Queue<Disposable> compositeDisposable;
    LinearLayoutManager layoutManager;
    int numberofItems = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ButterKnife.bind(this);
        ShowsApplication.getAppComponent().plus(new ShowsModule(this)).inject(this);
        setUpView();
        setUpLoadMoreListener();
        presenter.getShows();
    }

    private void setUpView() {
        layoutManager = new LinearLayoutManager(this);
        showsList.setLayoutManager(layoutManager);
        adapter = new ShowsAdapter(this);
        showsList.setAdapter(adapter);
    }

    @Override
    public void showShows(final List<Show> shows) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                subscribeForData(shows);
            }
        });

    }

    @Override
    public void onError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    private void setUpLoadMoreListener() {
        showsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager
                        .findLastVisibleItemPosition();
                if ( totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber ++;
                    paginator.onNext(pageNumber);
                }
            }
        });
    }

    /**
     * subscribing for data
     */
    private void subscribeForData(final List<Show> showsList) {
        Disposable disposable = paginator
                .onBackpressureDrop()
                .concatMap(new Function<Integer, Publisher<List<Show>>>() {
                    @Override
                    public Publisher<List<Show>> apply(@NonNull Integer page) throws Exception {
                        ArrayList<Show> shows = new ArrayList<>();
                        shows.addAll(showsList);
                        return  dataFromNetwork(pageNumber, shows);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Show>>() {
                    @Override
                    public void accept(@NonNull List<Show> items) throws Exception {
                        adapter.setShowList(items);
                        adapter.notifyDataSetChanged();
                    }
                });

        paginator.onNext(pageNumber);
    }

    private Flowable<List<Show>> dataFromNetwork(final int page, final List<Show> showsList) {
        return Flowable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<Boolean, List<Show>>() {
                    @Override
                    public List<Show> apply(@NonNull Boolean value) {
                        ArrayList<Show> items = new ArrayList<>();
                        for(int i = 0; i < 10; i++) {
                            if(numberofItems < showsList.size()) {
                                items.add(showsList.get(numberofItems));
                                numberofItems++;
                            }
                        }
                        return items;
                    }
                });
    }
}
