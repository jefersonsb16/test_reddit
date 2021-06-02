package com.jeferson.android.test_reddit

import android.app.Application
import com.jeferson.android.test_reddit.di.DaggerRedditAppComponent
import com.jeferson.android.test_reddit.di.RedditAppComponent

class RedditApp : Application() {

    lateinit var component: RedditAppComponent

    override fun onCreate() {
        super.onCreate()
        component = initAppComponent()
    }

    private fun initAppComponent() = DaggerRedditAppComponent.factory().create(this)
}