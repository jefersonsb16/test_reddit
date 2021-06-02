package com.jeferson.android.test_reddit.requestmanager

import com.jeferson.android.test_reddit.data.RemotePostRedditDataSource
import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostRedditRetrofitDataSource(
    private val postRedditRequest: PostRedditRequest
) : RemotePostRedditDataSource {

    override fun getPostsReddit(): Single<List<PostDomain>> {
        return postRedditRequest.getService<PostRedditService>()
            .getPosts()
            .map(PostRedditResponseServer::toPostRedditDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}