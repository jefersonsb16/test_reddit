package com.jeferson.android.test_reddit.requestmanager

import com.jeferson.android.test_reddit.requestmanager.APIConstants.END_POINT_POSTS
import io.reactivex.Single
import retrofit2.http.GET

interface PostRedditService {

    @GET(END_POINT_POSTS)
    fun getPosts(): Single<PostRedditResponseServer>
}