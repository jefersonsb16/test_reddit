package com.jeferson.android.test_reddit.data

import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface RemotePostRedditDataSource {
    fun getPostsReddit(): Single<List<PostDomain>>
}

interface LocalPostRedditDataSource {
    fun getPostRedditLocal(): Flowable<List<PostDomain>>

    fun addAllPostReddit(postList: List<PostDomain>): Completable
}