package com.abhishek.gitdriod.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abhishek.gitdriod.API.ApiEndPoints;
import com.abhishek.gitdriod.API.ApiInterface;
import com.abhishek.gitdriod.Model.GitHubRepositoriesResponseBody;
import com.abhishek.gitdriod.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    public static final String TAG = "TAG";
    public static final String EXTRA_QUERY = "com.searchview.query";
    public static final String VIEWHOLDER = "ViewHolder";

    private SearchView searchView;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView = (SearchView)findViewById(R.id.searchView);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                Log.i(MainActivity.TAG, "Clicked on submit");
                Log.i(MainActivity.TAG, query);
                if(!query.isEmpty() && query != "") {
                    showDetailsActivity(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                return false;
            }
        });


    }

    private void showDetailsActivity(String query) {
        Intent intent = new Intent(this, RepositoryDetailsActivity.class);
        intent.putExtra(EXTRA_QUERY, query);
        startActivity(intent);
    }


}
