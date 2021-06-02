package com.jeferson.android.test_reddit.data.di

import com.jeferson.android.test_reddit.data.LocalPostRedditDataSource
import com.jeferson.android.test_reddit.data.PostRedditRepository
import com.jeferson.android.test_reddit.data.RemotePostRedditDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun postRedditRepositoryProvider(
        remotePostRedditDataSource: RemotePostRedditDataSource,
        localPostRedditDataSource: LocalPostRedditDataSource
    ) = PostRedditRepository(
        remotePostRedditDataSource,
        localPostRedditDataSource
    )
}