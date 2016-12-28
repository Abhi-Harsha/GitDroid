package com.abhishek.gitdriod.Activity;

import android.app.AlertDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishek.gitdriod.API.ApiEndPoints;
import com.abhishek.gitdriod.API.ApiInterface;
import com.abhishek.gitdriod.Adapter.RepoAdapter;
import com.abhishek.gitdriod.Fragments.RepoDetailsFragment;
import com.abhishek.gitdriod.Model.GitHubRepositoriesResponseBody;
import com.abhishek.gitdriod.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryDetailsActivity extends AppCompatActivity implements  RepoDetailsFragment.OnFragmentInteractionListener, View.OnClickListener{

    private RecyclerView recyclerView;
    private ImageView userImageView;
    private TextView personTextView;


    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.repoDetailsFragment);

        if (fragment == null) {
            fm.beginTransaction().add(R.id.repoDetailsFragment, fragment).commit();
        }
    }

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
                System.out.println(repos);

                if (repos == null) {
                    AlertDialog dialog = new AlertDialog.Builder(RepositoryDetailsActivity.this)
                            .setTitle("No repos found")
                            .setMessage("Sorry no repos found, try again with a different name")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                                    finish();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {

                    recyclerView.setAdapter(new RepoAdapter(repos, R.layout.repo_row_item, RepositoryDetailsActivity.this));
                    personTextView.setText(repos.get(0).getName());
                    Picasso.with(getApplicationContext()).load(repos.get(0).getOwner().getAvatarUrl()).into(userImageView);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepositoriesResponseBody>> call, Throwable t) {

            }
        });


    }



//    @Override
//    public void itemClicked(GitHubRepositoriesResponseBody repository) {
//        System.out.println("about to start a fragment");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentById(R.id.repoDetailsFragment);
//        fragment = new RepoDetailsFragment();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(fragment, "Fragment");
//        fragmentTransaction.commit();
//    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
