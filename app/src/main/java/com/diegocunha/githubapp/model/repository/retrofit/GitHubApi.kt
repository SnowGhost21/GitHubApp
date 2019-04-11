package com.diegocunha.githubapp.model.repository.retrofit

import com.diegocunha.githubapp.model.data.RetrofitResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("repositories")
    fun getRepositories(@Query("q") parameter: String, @Query("per_page") pageSize: Int, @Query("page") page: Int): Single<RetrofitResponse>
}