package gdev.id.cataloguemovie.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import gdev.id.cataloguemovie.R;
import gdev.id.cataloguemovie.model.MovieModel;
//created by Hendriyawan 5 December 2018
//Submisson 2 (MADE) : Catalogue Movie Ui Ux

public class DetailMovieActivity extends AppCompatActivity {
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.img_poster)
    ImageView imgPoster;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_popularity)
    TextView tvPopularity;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_date_of_release)
    TextView tvDateRelease;
    @BindView(R.id.tv_overview)
    TextView tvOverview;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        //bind view
        ButterKnife.bind(this);

        //set android toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //action upbutton
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //get parcelable
        MovieModel movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String posterPath = movie.getPosterPath();
        String title = movie.getTitle();
        String vote = movie.getVote();
        String rating = movie.getRating();
        String dateOfRelease = movie.getDateOfRelease();
        String overview = movie.getOverview();

        collapsingToolbarLayout.setTitle(title);
        Picasso.get().load("http://image.tmdb.org/t/p/w342/" + posterPath)
                .placeholder(getResources().getDrawable(R.drawable.ic_blank_image100))
                .error(getResources().getDrawable(R.drawable.ic_blank_image100)).into(imgPoster);

        tvPopularity.setText(vote);
        tvRating.setText(rating + "/10");
        tvDateRelease.setText(dateOfRelease);
        tvOverview.setText(overview);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
