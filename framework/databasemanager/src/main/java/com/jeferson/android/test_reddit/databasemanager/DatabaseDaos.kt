package com.jeferson.android.test_reddit.databasemanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PostRedditDao {

    @Query("SELECT * FROM post")
    fun getAllPostRedditLocal(): Flowable<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllListPostReddit(postList: List<PostEntity>): Completable
}