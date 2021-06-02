package com.jeferson.android.test_reddit.data

import com.jeferson.android.test_reddit.domain.PostDomain
import io.reactivex.Single

interface RemotePostRedditDataSource {
    fun getPostsReddit(): Single<List<PostDomain>>
}