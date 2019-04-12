package com.diegocunha.githubapp.model.data


val createRepositoryOwner = RetrofitOwner("ownerName",
    "avatarUrl","profie")

val createRepositoryCompleted = RetrofitRepository("name",
    "fullName",
    "description",
    "language",
    1203433,
    createRepositoryOwner
    )

val createListOfRepositories = listOf(createRepositoryCompleted, createRepositoryCompleted)

val createResponse = RetrofitResponse(createListOfRepositories)