package com.abhishek.gitdriod.Activity;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abhishek.gitdriod.Fragments.RepoDetailsFragment;
import com.abhishek.gitdriod.R;

/**
 * Created by Abhishek on 28/12/16.
 */

public class RecylcerListener extends AppCompatActivity implements View.OnClickListener {

    private Context context;


    @Override
    public void onClick(View v) {
        showDetailsFragment();
    }

    public void showDetailsFragment() {
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.repoDetailsFragment);
            fm.beginTransaction().add(R.id.repoDetailsFragment, fragment).commit();
    }
}
