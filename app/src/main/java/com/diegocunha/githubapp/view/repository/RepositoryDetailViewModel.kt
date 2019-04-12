package com.diegocunha.githubapp.view.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.githubapp.extensions.map
import com.diegocunha.githubapp.extensions.mutableLiveDataOf
import com.diegocunha.githubapp.model.data.GitRepo
import com.diegocunha.githubapp.model.repository.GitRepository
import io.reactivex.disposables.Disposable

class RepositoryDetailViewModel(private val repository: GitRepository) : ViewModel() {

    private val _isLoading = mutableLiveDataOf(true)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _repository = MutableLiveData<GitRepo>()
    val repositoryResponse: LiveData<GitRepo> = _repository
    private val _emptyResponse = mutableLiveDataOf(false)
    val emptyResponse: LiveData<Boolean> = _emptyResponse
    private var repositoryDisposable: Disposable? = null

    val ownerImage = repositoryResponse.map { it?.owner?.avatarUrl }
    val description = repositoryResponse.map { it?.description }


    fun getRepositoryDetail(projectName: String, projectOwnerName: String) {
        repositoryDisposable = repository.getRepositoryDetail(projectOwnerName, projectName)
                .doOnError { _emptyResponse.postValue(true) }
                .doOnSuccess { _repository.postValue(it) }
                .doAfterTerminate { _isLoading.postValue(false) }
                .subscribe()

    }

    override fun onCleared() {
        super.onCleared()
        repositoryDisposable?.dispose()
    }


}