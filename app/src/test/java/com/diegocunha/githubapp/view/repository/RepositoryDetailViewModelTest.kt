package com.diegocunha.githubapp.view.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.githubapp.assertLiveDataEquals
import com.diegocunha.githubapp.model.data.createRepositoryCompleted
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
class RepositoryDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repository = mock<GitRepository>()

    @Before
    fun setup() {
        whenever(repository.getRepositoryDetail(any(), any())).thenReturn(Single.just(createRepositoryCompleted))
    }


    @Test
    fun shouldGetRepositoryDetail() {
        val viewModel = RepositoryDetailViewModel(repository)
        viewModel.getRepositoryDetail("diego", "githubapp")
        assertLiveDataEquals(createRepositoryCompleted, viewModel.repositoryResponse)

    }

    @Test
    fun shouldGetRepositoryImage() {
        val viewModel = RepositoryDetailViewModel(repository)
        viewModel.getRepositoryDetail("diego", "githubapp")
        assertLiveDataEquals(createRepositoryCompleted.owner.avatarUrl, viewModel.ownerImage)
    }

    @Test
    fun shouldGetRepositoryDescription() {
        val viewModel = RepositoryDetailViewModel(repository)
        viewModel.getRepositoryDetail("diego", "githubapp")
        assertLiveDataEquals(createRepositoryCompleted.description, viewModel.description)
    }

}