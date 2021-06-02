package com.jeferson.android.test_reddit.data

import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PostRedditRepository(
    private val remotePostRedditDataSource: RemotePostRedditDataSource,
    private val localPostRedditDataSource: LocalPostRedditDataSource
) {

    fun getPostsReddit(): Single<List<PostDomain>> =
        remotePostRedditDataSource.getPostsReddit()

    fun getPostRedditLocal(): Flowable<List<PostDomain>> =
        localPostRedditDataSource.getPostRedditLocal()

    fun addAllPostReddit(movieList: List<PostDomain>): Completable =
        localPostRedditDataSource.addAllPostReddit(movieList)
}