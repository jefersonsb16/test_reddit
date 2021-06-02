package com.jeferson.android.test_reddit.parcelables

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostParcelable(
    val kind: String,
    val data: DataPostParcelable
) : Parcelable

@Parcelize
data class DataPostParcelable(
    val author_fullname: String?,
    val title: String,
    val score: Int?,
    val thumbnail: String?,
    val post_hint: String?,
    val id: String,
    val author: String?,
    val num_comments: Int?,
    val url: String?,
    val is_video: Boolean?
) : Parcelable