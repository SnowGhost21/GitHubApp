package com.diegocunha.githubapp.model.repository.retrofit

import com.diegocunha.githubapp.model.data.GitRepo
import com.diegocunha.githubapp.model.repository.GitRepository
import io.reactivex.Single

class GitHubRetrofitRepository(private val api: GitHubApi) : GitRepository {

    override fun getRepositories(parameter: String?, page: Int): Single<com.diegocunha.githubapp.model.data.GitRepositoryResponse> {
        return api.getRepositories(parameter, 15, page)
    }

    override fun getRepositoryDetail(owner: String, repo: String): Single<GitRepo> {
        return api.getRepositoryDetail(owner, repo)
    }
}