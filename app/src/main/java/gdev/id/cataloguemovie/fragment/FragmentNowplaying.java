package gdev.id.cataloguemovie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import gdev.id.cataloguemovie.BuildConfig;
import gdev.id.cataloguemovie.R;
import gdev.id.cataloguemovie.utils.Movie;

/**
 * A simple {@link Fragment} subclass.
 */

//created by Hendriyawan 5 December 2018
//Submisson 2 (MADE) : Catalogue Movie Ui Ux

public class FragmentNowplaying extends Fragment {
    @BindView(R.id.recycler_view_nowpaying)
    RecyclerView recyclerView;

    public FragmentNowplaying() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_nowplaying, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + BuildConfig.API_KEY + "&language=en-US";
        Movie.loadData(getActivity(), recyclerView, url);
        return view;
    }

}
