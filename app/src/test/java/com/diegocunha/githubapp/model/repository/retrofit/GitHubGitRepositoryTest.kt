package com.diegocunha.githubapp.model.repository.retrofit

import com.diegocunha.githubapp.model.data.createRepositoryCompleted
import com.diegocunha.githubapp.model.data.createResponse
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GitHubGitRepositoryTest {

    private val api = mock<GitHubApi>()

    @Before
    fun setup() {
        whenever(api.getRepositories(any(), any(), any())).thenReturn(Single.just(createResponse))
        whenever(api.getRepositoryDetail(any(), any())).thenReturn(Single.just(createRepositoryCompleted))
    }

    @Test
    fun shouldGetListOfRepositories() {
        val repository = GitHubRetrofitRepository(api)
        repository.getRepositories("parameter", 1)
        verify(api).getRepositories(any(), any(), any())
    }

    @Test
    fun shouldGetDetailRepository() {
        val repository = GitHubRetrofitRepository(api)
        repository.getRepositoryDetail("diego", "githubapp")
        verify(api).getRepositoryDetail(any(), any())
    }
}