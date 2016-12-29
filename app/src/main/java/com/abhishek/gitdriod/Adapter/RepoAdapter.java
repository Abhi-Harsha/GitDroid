package com.abhishek.gitdriod.Adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhishek.gitdriod.Activity.RecylcerListener;
import com.abhishek.gitdriod.Activity.RepositoryDetailsActivity;
import com.abhishek.gitdriod.Fragments.RepoDetailsFragment;
import com.abhishek.gitdriod.Model.GitHubRepositoriesResponseBody;
import com.abhishek.gitdriod.R;
import com.squareup.picasso.Picasso;


import java.security.acl.Owner;
import java.util.List;




/**
 * Created by Abhishek on 26/12/16.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ReportAdapterViewHolder> {

    public static List<GitHubRepositoriesResponseBody> reposList;
    private int rowNumber;
    private  Context context;
    RecyclerView recyclerView;



    @Override
    public ReportAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowNumber, parent, false);
        return new ReportAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReportAdapterViewHolder holder, final int position) {
            holder.repoNameTextView.setText(reposList.get(position).getName());
            holder.repoLanguageTextView.setText(reposList.get(position).getLanguage());
            holder.layout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    showRepoDetailsFragment(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public void showRepoDetailsFragment(int pos) {
        RepositoryDetailsActivity activity= (RepositoryDetailsActivity) context;
        FragmentManager manager = activity.getSupportFragmentManager();
        RepoDetailsFragment fragment = (RepoDetailsFragment) manager.findFragmentById(R.id.repoContainerLayout);

        if(fragment == null) {
            fragment = RepoDetailsFragment.newInstance(reposList.get(pos));
            recyclerView.setVisibility(RecyclerView.GONE);
            manager.beginTransaction().add(R.id.repoContainerLayout, fragment).addToBackStack(null).commit();
        }
    }


    public static class ReportAdapterViewHolder extends RecyclerView.ViewHolder{

            RelativeLayout layout;
            TextView repoNameTextView;
            TextView repoLanguageTextView;
            //public static adapterListener listener;


        public ReportAdapterViewHolder(View v){
            super(v);
            layout = (RelativeLayout)v.findViewById(R.id.layout);
            repoNameTextView = (TextView)v.findViewById(R.id.repoNameTextView);
            repoLanguageTextView = (TextView)v.findViewById(R.id.languageTextView);
//            repoNameTextView.setOnClickListener(new RelativeLayout.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    RepositoryDetailsActivity activity= (RepositoryDetailsActivity) context;
//                    FragmentManager manager = activity.getSupportFragmentManager();
//                    Fragment fragment; //= manager.findFragmentById(R.id.repoDetailsFragment);
//                    fragment = new RepoDetailsFragment();
//                    manager.beginTransaction().add(R.id.repoDetailsFragment, fragment);
//                }
//            });



        }

//        public static void setListener(adapterListener listener) {
//            ReportAdapterViewHolder.listener = listener;
//        }

//        public static interface adapterListener {
//            public void itemClicked(GitHubRepositoriesResponseBody repository);
//        }



    }

    public RepoAdapter(List<GitHubRepositoriesResponseBody> repos, int rowNumber, Context context, RecyclerView recyclerView) {
            this.reposList = repos;
            this.rowNumber = rowNumber;
            this.context = context;
            this.recyclerView = recyclerView;
    }



}
