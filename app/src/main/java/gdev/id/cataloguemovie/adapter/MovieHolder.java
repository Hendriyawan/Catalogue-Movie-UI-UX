package gdev.id.cataloguemovie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import gdev.id.cataloguemovie.R;

//created by Hendriyawan 5 December 2018
//Submisson 2 (MADE) : Catalogue Movie Ui Ux

public class MovieHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_poster)
    ImageView imgPoster;
    @BindViews({R.id.tv_title, R.id.tv_overview, R.id.tv_date_of_release})
    List<TextView> tvMovie;
    @BindViews({R.id.btn_detail, R.id.btn_share})
    List<ImageButton> btnMovie;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
