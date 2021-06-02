package com.jeferson.android.test_reddit.databasemanager.di

import android.app.Application
import com.jeferson.android.test_reddit.data.LocalPostRedditDataSource
import com.jeferson.android.test_reddit.databasemanager.PostRedditRoomDataSource
import com.jeferson.android.test_reddit.databasemanager.TestRedditDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): TestRedditDatabase =
        TestRedditDatabase.getDatabase(app)

    @Provides
    fun localPostRedditDataSourceProvider(
        database: TestRedditDatabase
    ): LocalPostRedditDataSource = PostRedditRoomDataSource(database)
}