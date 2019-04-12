package com.diegocunha.githubapp.view.repository

import com.diegocunha.githubapp.model.data.RetrofitRepository

class RepositoryItemViewModel(val repository: RetrofitRepository) {

    val imageUrl = repository.owner.avatarUrl

    val projectName = repository.name

    val projectOwner = repository.owner.name
}