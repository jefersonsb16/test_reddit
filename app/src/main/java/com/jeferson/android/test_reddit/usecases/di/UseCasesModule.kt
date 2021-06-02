package com.jeferson.android.test_reddit.usecases.di

import com.jeferson.android.test_reddit.data.PostRedditRepository
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getAllPostsRedditUseCaseProvider(
        postRedditRepository: PostRedditRepository
    ) = GetAllPostsRedditUseCase(postRedditRepository)
}