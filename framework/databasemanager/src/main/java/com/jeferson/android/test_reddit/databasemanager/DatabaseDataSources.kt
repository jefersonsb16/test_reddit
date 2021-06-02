package com.jeferson.android.test_reddit.databasemanager

import com.jeferson.android.test_reddit.data.LocalPostRedditDataSource
import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostRedditRoomDataSource(
    database: TestRedditDatabase
) : LocalPostRedditDataSource {

    // dao instance
    private val postRedditDao by lazy { database.postRedditDao() }

    override fun getPostRedditLocal(): Flowable<List<PostDomain>> = postRedditDao
        .getAllPostRedditLocal()
        .map(List<PostEntity>::toPostDomainList)
        .onErrorReturn { emptyList() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun addAllPostReddit(postList: List<PostDomain>): Completable = postRedditDao
        .insertAllListPostReddit(postList.toPostEntityList())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun deleteAllPostRedditLocal(): Completable {
        return postRedditDao
            .deleteAllPostRedditLocal()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}