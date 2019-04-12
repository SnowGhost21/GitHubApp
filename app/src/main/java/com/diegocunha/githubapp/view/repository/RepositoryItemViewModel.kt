package com.diegocunha.githubapp.view.repository

import com.diegocunha.githubapp.model.data.GitRepo

class RepositoryItemViewModel(val repository: GitRepo) {

    val imageUrl = repository.owner.avatarUrl

    val projectName = repository.name

    val projectOwner = repository.owner.name
}