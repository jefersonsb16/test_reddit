package com.jeferson.android.test_reddit.usecases

import com.jeferson.android.test_reddit.data.PostRedditRepository
import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class GetAllPostsRedditUseCase(
    private val postRedditRepository: PostRedditRepository
) {
    fun invoke(): Single<List<PostDomain>> =
        postRedditRepository.getPostsReddit()
}

class GetPostRedditLocalUseCase(
    private val postRedditRepository: PostRedditRepository
) {
    fun invoke(): Flowable<List<PostDomain>> = postRedditRepository.getPostRedditLocal()
}

class AddAllPostRedditUseCase(
    private val postRedditRepository: PostRedditRepository
) {
    fun invoke(postList: List<PostDomain>): Completable =
        postRedditRepository.addAllPostReddit(postList)
}

class DeleteAllPostRedditLocalUseCase(
    private val postRedditRepository: PostRedditRepository
) {
    fun invoke(): Completable = postRedditRepository.deleteAllPostRedditLocal()
}