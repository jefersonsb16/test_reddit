package com.jeferson.android.test_reddit.presentation

import androidx.lifecycle.ViewModel
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase

class PostsRedditListViewModel(
    private val getAllPostsRedditUseCase: GetAllPostsRedditUseCase
) : ViewModel() {

}