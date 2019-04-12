package com.diegocunha.githubapp.view.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.githubapp.extensions.mutableLiveDataOf
import com.diegocunha.githubapp.model.repository.GitRepository

class RepositoryDetailViewModel(private val repository: GitRepository): ViewModel() {

    private val _isLoading = mutableLiveDataOf(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _repository = MutableLiveData<com.diegocunha.githubapp.model.data.GitRepo>()

}