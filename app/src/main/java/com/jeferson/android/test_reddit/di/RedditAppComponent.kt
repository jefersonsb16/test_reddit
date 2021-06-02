package com.jeferson.android.test_reddit.di

import android.app.Application
import com.jeferson.android.test_reddit.data.di.RepositoryModule
import com.jeferson.android.test_reddit.requestmanager.di.APIModule
import com.jeferson.android.test_reddit.usecases.di.UseCasesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        APIModule::class,
        UseCasesModule::class,
        RepositoryModule::class,
    ]
)
interface RedditAppComponent {

    fun inject(module: PostsRedditListModule): PostsRedditListComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): RedditAppComponent
    }
}