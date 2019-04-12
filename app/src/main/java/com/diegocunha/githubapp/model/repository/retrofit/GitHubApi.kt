package com.diegocunha.githubapp.model.repository.retrofit

import com.diegocunha.githubapp.model.data.GitRepo
import com.diegocunha.githubapp.model.data.GitRepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("repositories")
    fun getRepositories(@Query("q") parameter: String?, @Query("per_page") pageSize: Int, @Query("page") page: Int): Single<GitRepositoryResponse>

    @GET("repos/{owner}/{repo}")
    fun getRepositoryDetail(@Path("owner") owner: String, @Path("repo") repo: String):Single<GitRepo>
}