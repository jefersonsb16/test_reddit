package com.jeferson.android.test_reddit.requestmanager.di

import com.jeferson.android.test_reddit.data.RemotePostRedditDataSource
import com.jeferson.android.test_reddit.requestmanager.APIConstants.BASE_API_URL
import com.jeferson.android.test_reddit.requestmanager.PostRedditRequest
import com.jeferson.android.test_reddit.requestmanager.PostRedditRetrofitDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class APIModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = BASE_API_URL

    @Provides
    fun postRedditRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = PostRedditRequest(baseUrl)

    @Provides
    fun remotePostRedditDataSourceProvider(
        postRedditRequest: PostRedditRequest
    ): RemotePostRedditDataSource = PostRedditRetrofitDataSource(postRedditRequest)
}