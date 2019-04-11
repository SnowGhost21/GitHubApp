package com.diegocunha.githubapp

import android.app.Application
import com.diegocunha.githubapp.dependencyinjection.appModules
import org.koin.android.ext.android.startKoin

class GitHubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModules))
    }

}