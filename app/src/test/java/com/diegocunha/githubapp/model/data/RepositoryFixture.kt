package com.diegocunha.githubapp.model.data


val createRepositoryOwner = GitRepositoryOwner("ownerName",
    "avatarUrl","profie")

val createRepositoryCompleted = GitRepo("name",
    "fullName",
    "description",
    "language",
    1203433,
    createRepositoryOwner
    )

val createListOfRepositories = listOf(createRepositoryCompleted, createRepositoryCompleted)

val createResponse = GitRepositoryResponse(createListOfRepositories)