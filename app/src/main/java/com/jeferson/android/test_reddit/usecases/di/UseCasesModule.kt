package com.jeferson.android.test_reddit.usecases.di

import com.jeferson.android.test_reddit.data.PostRedditRepository
import com.jeferson.android.test_reddit.usecases.AddAllPostRedditUseCase
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import com.jeferson.android.test_reddit.usecases.GetPostRedditLocalUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getAllPostsRedditUseCaseProvider(
        postRedditRepository: PostRedditRepository
    ) = GetAllPostsRedditUseCase(postRedditRepository)

    @Provides
    fun getPostRedditLocalUseCaseProvider(
        postRedditRepository: PostRedditRepository
    ) = GetPostRedditLocalUseCase(postRedditRepository)

    @Provides
    fun addAllPostRedditUseCaseProvider(
        postRedditRepository: PostRedditRepository
    ) = AddAllPostRedditUseCase(postRedditRepository)
}