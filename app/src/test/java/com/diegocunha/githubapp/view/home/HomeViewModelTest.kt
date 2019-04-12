package com.diegocunha.githubapp.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.githubapp.assertLiveDataEquals
import com.diegocunha.githubapp.model.data.createListOfRepositories
import com.diegocunha.githubapp.model.data.createRepositoryOwner
import com.diegocunha.githubapp.model.data.createResponse
import com.diegocunha.githubapp.model.repository.GitRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repository = mock<GitRepository>()

    @Before
    fun setup() {
        whenever(repository.getRepositories(any(), any())).thenReturn(Single.just(createResponse))
    }

    @Test
    fun shouldGetRepositories() {
        val viewModel = HomeViewModel(repository)
        viewModel.getRepositories("android")
        assertLiveDataEquals(createListOfRepositories, viewModel.repositories)
    }

    @Test
    fun shouldHideEmptyInformation() {
        val viewModel = HomeViewModel(repository)
        viewModel.getRepositories("android")

        assertLiveDataEquals(false, viewModel.emptyResponse)

    }

    @Test
    fun loadShouldHide() {
        val viewModel = HomeViewModel(repository)
        viewModel.getRepositories("android")
        assertLiveDataEquals(false, viewModel.isLoading)
    }
}