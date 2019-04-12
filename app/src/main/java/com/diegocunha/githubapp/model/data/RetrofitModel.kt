package com.diegocunha.githubapp.model.data

import com.google.gson.annotations.SerializedName

data class GitRepositoryResponse(@SerializedName("items") val repositories: List<GitRepo>)

data class GitRepo(
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("language") val language: String,
    @SerializedName("watchers_count") val watchersCount: Long,
    @SerializedName("owner") val owner: GitRepositoryOwner
)

data class GitRepositoryOwner(@SerializedName("login") val name: String,
                              @SerializedName("avatar_url") val avatarUrl: String,
                              @SerializedName("url") val profile: String)