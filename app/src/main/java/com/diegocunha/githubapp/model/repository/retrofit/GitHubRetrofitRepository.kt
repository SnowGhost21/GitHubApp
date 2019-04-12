package com.diegocunha.githubapp.model.repository.retrofit

import com.diegocunha.githubapp.model.data.RetrofitResponse
import com.diegocunha.githubapp.model.repository.GitRepository
import io.reactivex.Single

class GitHubRetrofitRepository(private val api: GitHubApi) : GitRepository {

    override fun getRepositories(parameter: String?, page: Int): Single<RetrofitResponse> {
        return api.getRepositories(parameter, 15, page)
    }
}