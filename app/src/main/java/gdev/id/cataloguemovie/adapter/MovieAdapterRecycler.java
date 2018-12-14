package gdev.id.cataloguemovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import gdev.id.cataloguemovie.R;
import gdev.id.cataloguemovie.model.MovieModel;

//created by Hendriyawan 5 December 2018
//Submisson 2 (MADE) : Catalogue Movie Ui Ux

public class MovieAdapterRecycler extends RecyclerView.Adapter<MovieHolder> {
    private Context context;
    private List<MovieModel> listMovie;
    private OnButtonDetailClick buttonDetailListener;
    private OnButtonShareClick buttonShareListener;

    public MovieAdapterRecycler(Context context, List<MovieModel> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    public void setOnButtonDetailClick(OnButtonDetailClick buttonDetailListener) {
        this.buttonDetailListener = buttonDetailListener;
    }

    public void setOnButtonShareClick(OnButtonShareClick buttonShareListener) {
        this.buttonShareListener = buttonShareListener;
    }

    public List<MovieModel> getListMovie() {
        return listMovie;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_movie, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int position) {
        final MovieModel movie = listMovie.get(position);
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                .placeholder(context.getResources().getDrawable(R.drawable.ic_blank_image100))
                .error(context.getResources().getDrawable(R.drawable.ic_blank_image100)).into(movieHolder.imgPoster);
        movieHolder.tvMovie.get(0).setText(movie.getTitle());
        movieHolder.tvMovie.get(1).setText(movie.getOverview());
        movieHolder.tvMovie.get(2).setText(movie.getDateOfRelease());

        //button detail
        int BUTTON_DETAIL = 0;
        movieHolder.btnMovie.get(BUTTON_DETAIL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDetailListener.onDetailClick(movie);
            }
        });
        //button share
        int BUTTON_SHARE = 1;
        movieHolder.btnMovie.get(BUTTON_SHARE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonShareListener.onShareClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    //interface
    public interface OnButtonDetailClick {
        void onDetailClick(MovieModel movie);
    }

    public interface OnButtonShareClick {
        void onShareClick(MovieModel movie);
    }
}