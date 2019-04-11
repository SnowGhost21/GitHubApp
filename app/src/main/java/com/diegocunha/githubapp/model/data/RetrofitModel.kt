package com.diegocunha.githubapp.model.data

import com.google.gson.annotations.SerializedName

data class RetrofitResponse(@SerializedName("items") val repositories: List<RetrofitRepository>)

data class RetrofitRepository(
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("language") val language: String,
    @SerializedName("watchers_count") val watchersCount: Long,
    @SerializedName("owner") val owner: RetrofitOwner
)

data class RetrofitOwner(@SerializedName("login") val name: String,
                         @SerializedName("avatar_url") val avatarUrl: String,
                         @SerializedName("url") val profile: String)