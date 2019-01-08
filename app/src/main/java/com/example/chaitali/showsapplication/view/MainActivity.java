package com.example.chaitali.showsapplication.view;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chaitali.showsapplication.R;
import com.example.chaitali.showsapplication.model.Show;
import com.example.chaitali.showsapplication.presenter.IShowsPresenter;

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

    PaginationAdapter adapter;

    @BindView(R.id.shows_list)
    RecyclerView rvShowsList;

    @BindView(R.id.main_progress)
    ProgressBar progressBar;


    private int pageNumber = 0;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private PublishProcessor<Integer> paginator = PublishProcessor.create();
    private Queue<Disposable> compositeDisposable;
    LinearLayoutManager layoutManager;
    int numberofItems = 10;


    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 0;
    private int currentPage = PAGE_START;
    List<Show> showList = new ArrayList<>();
    int count = 0;

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
        rvShowsList.setLayoutManager(layoutManager);
        adapter = new PaginationAdapter(this);
        rvShowsList.setAdapter(adapter);

        rvShowsList.setItemAnimator(new DefaultItemAnimator());

        rvShowsList.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage(getNextItems());
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }


    private void loadFirstPage(List<Show> showList) {
        Log.d("", "loadFirstPage: ");
        List<Show> movies = showList;
        progressBar.setVisibility(View.GONE);
        adapter.addAll(movies);

        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;

    }

    private void loadNextPage(List<Show> showList) {
        Log.d("", "loadNextPage: " + currentPage);
        List<Show> movies = showList;

        adapter.removeLoadingFooter();
        isLoading = false;

        adapter.addAll(movies);

        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;
    }

    @Override
    public void showShows(final List<Show> shows) {
        showList = shows;
        TOTAL_PAGES = showList.size() / 10;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadFirstPage(getNextItems());
            }
        });

    }

    private List<Show> getNextItems() {
        List<Show> moviesList = new ArrayList<>();
        for(int i = count; i < count + 10; i++) {
            if(count < showList.size()) {
                moviesList.add(showList.get(i));
            }
        }
        count += 10;
        return moviesList;
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
        rvShowsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        adapter.setMovies(items);
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
