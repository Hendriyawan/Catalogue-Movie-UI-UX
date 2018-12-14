package gdev.id.cataloguemovie.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gdev.id.cataloguemovie.R;
import gdev.id.cataloguemovie.activity.DetailMovieActivity;
import gdev.id.cataloguemovie.adapter.MovieAdapterRecycler;
import gdev.id.cataloguemovie.model.MovieModel;

//created by Hendriyawan 5 December 2018
//Submisson 2 (MADE) : Catalogue Movie Ui Ux
public class Movie {

    public static void loadData(final Context context, final RecyclerView recyclerView, String url) {
        final ProgressDialog progessDialog = new ProgressDialog(context);
        progessDialog.setMessage(context.getResources().getString(R.string.progress_dialog_loading_message));
        progessDialog.setIndeterminate(false);
        progessDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progessDialog.dismiss();
                List<MovieModel> listMovie = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject value = jsonArray.getJSONObject(i);
                        //put parcelable
                        MovieModel movie = new MovieModel();
                        movie.setPosterPath(value.getString("poster_path"));
                        movie.setTitle(value.getString("title"));
                        movie.setVote(value.getString("vote_count"));
                        movie.setRating(value.getString("vote_average"));
                        movie.setDateOfRelease(value.getString("release_date"));
                        movie.setOverview(value.getString("overview"));
                        listMovie.add(movie);
                    }

                } catch (JSONException e) {
                    //handle exception
                    e.printStackTrace();
                }
                //MovieRecyclerView Adapter
                MovieAdapterRecycler adapter = new MovieAdapterRecycler(context, listMovie);
                recyclerView.setAdapter(adapter);
                //action click for detail button
                adapter.setOnButtonDetailClick(new MovieAdapterRecycler.OnButtonDetailClick() {
                    @Override
                    public void onDetailClick(MovieModel movie) {
                        Intent detailMovie = new Intent(context, DetailMovieActivity.class);
                        detailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
                        context.startActivity(detailMovie);
                    }
                });
                //action click for share button
                adapter.setOnButtonShareClick(new MovieAdapterRecycler.OnButtonShareClick() {
                    @Override
                    public void onShareClick(MovieModel movie) {
                        Toast.makeText(context, "Button share clicked " + movie.getTitle(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progessDialog.dismiss();
            }
        });
        queue.add(request);
    }
}