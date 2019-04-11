package com.diegocunha.githubapp.model.repository

import com.diegocunha.githubapp.model.data.RetrofitResponse
import io.reactivex.Single

interface GitRepository {

    fun getRepositories(parameter: String, pageSize: Int, page: Int): Single<RetrofitResponse>
}