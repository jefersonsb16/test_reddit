package com.jeferson.android.test_reddit.databasemanager

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.AUTHOR_FULL_NAME_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.AUTHOR_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.ID_DATA_POST_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.ID_POST_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.IS_VIDEO_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.KIND_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.NUM_COMMENTS_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.POST_HINT_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.SCORE_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.TABLE_DATA_POST
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.TABLE_POST
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.THUMBNAIL_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.TITLE_KEY
import com.jeferson.android.test_reddit.databasemanager.utils.DBConstants.URL_KEY

@Entity(tableName = TABLE_POST)
data class PostEntity(
    @ColumnInfo(name = KIND_KEY) var kind: String,
    @Embedded var data: DataPostEntity,
) {
    @ColumnInfo(name = ID_POST_KEY)
    @PrimaryKey(autoGenerate = true)
    var postId: Int = 0
}

@Entity(tableName = TABLE_DATA_POST)
data class DataPostEntity(
    @ColumnInfo(name = AUTHOR_FULL_NAME_KEY) var author_fullname: String?,
    @ColumnInfo(name = TITLE_KEY) var title: String,
    @ColumnInfo(name = SCORE_KEY) var score: Int?,
    @ColumnInfo(name = THUMBNAIL_KEY) var thumbnail: String?,
    @ColumnInfo(name = POST_HINT_KEY) var post_hint: String?,
    @PrimaryKey @ColumnInfo(name = ID_DATA_POST_KEY) var id: String,
    @ColumnInfo(name = AUTHOR_KEY) var author: String?,
    @ColumnInfo(name = NUM_COMMENTS_KEY) var num_comments: Int?,
    @ColumnInfo(name = URL_KEY) var url: String?,
    @ColumnInfo(name = IS_VIDEO_KEY) var is_video: Boolean?
)
