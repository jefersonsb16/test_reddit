package com.jeferson.android.test_reddit.usecases

import com.jeferson.android.test_reddit.data.PostRedditRepository
import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Single

class GetAllPostsRedditUseCase(
    private val postRedditRepository: PostRedditRepository
) {
    fun invoke(): Single<List<PostDomain>> =
        postRedditRepository.getPostsReddit()
}