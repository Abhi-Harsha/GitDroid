package com.abhishek.gitdriod.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishek.gitdriod.API.ApiEndPoints;
import com.abhishek.gitdriod.API.ApiInterface;
import com.abhishek.gitdriod.Adapter.RepoAdapter;
import com.abhishek.gitdriod.Model.GitHubRepositoriesResponseBody;
import com.abhishek.gitdriod.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView userImageView;
    private TextView personTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        userImageView = (ImageView)findViewById(R.id.userImageView);
        personTextView = (TextView) findViewById(R.id.userNameTextView);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String query = getIntent().getStringExtra(MainActivity.EXTRA_QUERY);
        makeRepoSearchCall(query);
    }

    public void makeRepoSearchCall(String name) {
        ApiEndPoints endEpoint = ApiInterface.getClient().create(ApiEndPoints.class);

        Call<List<GitHubRepositoriesResponseBody>> call = endEpoint.getRepos(name);

        call.enqueue(new Callback<List<GitHubRepositoriesResponseBody>>() {
            @Override
            public void onResponse(Call<List<GitHubRepositoriesResponseBody>> call, Response<List<GitHubRepositoriesResponseBody>> response) {
                List<GitHubRepositoriesResponseBody> repos = response.body();
                recyclerView.setAdapter(new RepoAdapter(repos, R.layout.repo_row_item, getApplicationContext()));
                personTextView.setText(repos.get(0).getName());
                Picasso.with(getApplicationContext()).load(repos.get(0).getOwner().getAvatarUrl()).into(userImageView);
            }

            @Override
            public void onFailure(Call<List<GitHubRepositoriesResponseBody>> call, Throwable t) {

            }
        });
    }
}
