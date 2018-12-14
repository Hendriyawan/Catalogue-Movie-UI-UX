package gdev.id.cataloguemovie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import gdev.id.cataloguemovie.BuildConfig;
import gdev.id.cataloguemovie.R;
import gdev.id.cataloguemovie.utils.Movie;

public class ActivitySearchMovie extends AppCompatActivity {
    @BindView(R.id.recycler_view_search)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public static final String EXTRA_QUERY = "extra_query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        //bind view
        ButterKnife.bind(this);

        //setup android toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_search_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //action upbutton
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String query = getIntent().getStringExtra(EXTRA_QUERY);
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US&query=" + query;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Movie.loadData(this, recyclerView, url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
