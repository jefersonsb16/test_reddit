package com.jeferson.android.test_reddit.di

import com.jeferson.android.test_reddit.presentation.PostsRedditListViewModel
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class PostsRedditListModule {

    @Provides
    fun postsRedditListViewModelProvider(
        getAllPostsRedditUseCase: GetAllPostsRedditUseCase
    ) = PostsRedditListViewModel(getAllPostsRedditUseCase)
}

@Subcomponent(modules = [(PostsRedditListModule::class)])
interface PostsRedditListComponent {
    val postsRedditListViewModel: PostsRedditListViewModel
}