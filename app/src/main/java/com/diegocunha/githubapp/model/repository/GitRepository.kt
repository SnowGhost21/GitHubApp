package com.diegocunha.githubapp.model.repository

import com.diegocunha.githubapp.model.data.GitRepo
import com.diegocunha.githubapp.model.data.GitRepositoryResponse
import io.reactivex.Single

interface GitRepository {

    fun getRepositories(parameter: String?, page: Int): Single<GitRepositoryResponse>

    fun getRepositoryDetail(owner: String, repo: String):Single<GitRepo>
}