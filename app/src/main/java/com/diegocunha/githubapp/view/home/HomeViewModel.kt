package com.diegocunha.githubapp.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.githubapp.extensions.mutableLiveDataOf
import com.diegocunha.githubapp.model.data.RetrofitRepository
import com.diegocunha.githubapp.model.repository.GitRepository
import io.reactivex.disposables.Disposable

class HomeViewModel(private val repository: GitRepository) : ViewModel() {

    private var numberPage = 1
    private val _repositories = MutableLiveData<List<RetrofitRepository>>()
    val repositories: LiveData<List<RetrofitRepository>> = _repositories
    private val _emptyResponse = mutableLiveDataOf(true)
    val emptyResponse: LiveData<Boolean> = _emptyResponse
    private var repositoriesDisposable: Disposable? = null
    private var lastQuery: String? = null
    private val _isLoading = mutableLiveDataOf(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getRepositories(query: String?) {
        val shouldUpdatePage = shouldIncrementPage(query)
        lastQuery = if (!query.isNullOrEmpty() && query != lastQuery) query else lastQuery

        if (!shouldUpdatePage) {
            clearData()
        }

        repositoriesDisposable = repository.getRepositories(lastQuery, numberPage)
            .map { it.repositories }
            .doOnSubscribe { _isLoading.postValue(true) }
            .doOnSuccess {
                _emptyResponse.postValue(it.isEmpty())
                _repositories.postValue(it)
            }
            .doOnError {
                _emptyResponse.postValue(true)
            }
            .doAfterTerminate {
                calculatePageSize(shouldUpdatePage)
                _isLoading.postValue(false)
            }
            .onErrorReturnItem(emptyList())
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        repositoriesDisposable?.dispose()
    }

    private fun shouldIncrementPage(query: String?): Boolean {
        if (query.isNullOrEmpty()) {
            return true
        }

        return lastQuery.equals(query)
    }

    private fun clearData() {
        _repositories.postValue(null)
        _emptyResponse.postValue(true)
        numberPage = 1
    }

    private fun calculatePageSize(shouldUpdatePage: Boolean) {
        if (shouldUpdatePage) {
            numberPage++
        } else {
            numberPage = if (numberPage == 1) numberPage else numberPage--
        }
    }
}