package com.diegocunha.githubapp.dependencyinjection

import com.diegocunha.githubapp.BuildConfig
import com.diegocunha.githubapp.model.repository.GitRepository
import com.diegocunha.githubapp.model.repository.retrofit.GitHubApi
import com.diegocunha.githubapp.model.repository.retrofit.GitHubRetrofitRepository
import com.diegocunha.githubapp.view.home.HomeViewModel
import com.diegocunha.githubapp.view.repository.RepositoryDetailViewModel
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {

    factory {

        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    factory { GsonBuilder().create() }

    factory {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(GitHubApi::class.java)
    }

    single { GitHubRetrofitRepository(get()) as GitRepository }

    viewModel { HomeViewModel(get()) }

    viewModel { RepositoryDetailViewModel(get()) }
}