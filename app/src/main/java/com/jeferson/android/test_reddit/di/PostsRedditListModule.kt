package com.jeferson.android.test_reddit.di

import com.jeferson.android.test_reddit.presentation.PostsRedditListViewModel
import com.jeferson.android.test_reddit.usecases.AddAllPostRedditUseCase
import com.jeferson.android.test_reddit.usecases.DeleteAllPostRedditLocalUseCase
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import com.jeferson.android.test_reddit.usecases.GetPostRedditLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class PostsRedditListModule {

    @Provides
    fun postsRedditListViewModelProvider(
        getAllPostsRedditUseCase: GetAllPostsRedditUseCase,
        getPostRedditLocalUseCase: GetPostRedditLocalUseCase,
        getAddAllPostRedditUseCase: AddAllPostRedditUseCase,
        deleteAllPostRedditLocalUseCase: DeleteAllPostRedditLocalUseCase
    ) = PostsRedditListViewModel(
        getAllPostsRedditUseCase,
        getPostRedditLocalUseCase,
        getAddAllPostRedditUseCase,
        deleteAllPostRedditLocalUseCase
    )
}

@Subcomponent(modules = [(PostsRedditListModule::class)])
interface PostsRedditListComponent {
    val postsRedditListViewModel: PostsRedditListViewModel
}