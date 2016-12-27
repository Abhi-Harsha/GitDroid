package com.abhishek.gitdriod.API;

import com.abhishek.gitdriod.Model.GitHubRepositoriesResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Abhishek on 26/12/16.
 */

public interface ApiEndPoints {
    @GET("users/{user}/repos")
    Call<List<GitHubRepositoriesResponseBody>> getRepos(@Path("user") String user);
}
