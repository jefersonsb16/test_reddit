package com.jeferson.android.test_reddit.databasemanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.DATABASE_NAME
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.DB_VERSION

@Database(
    entities = [PostEntity::class, DataPostEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class TestRedditDatabase : RoomDatabase() {

    abstract fun postRedditDao(): PostRedditDao

    companion object {
        @Synchronized
        fun getDatabase(context: Context): TestRedditDatabase = Room.databaseBuilder(
            context.applicationContext,
            TestRedditDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}