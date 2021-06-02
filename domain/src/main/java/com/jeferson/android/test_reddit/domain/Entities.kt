package com.jeferson.android.test_reddit.domain

data class PostDomain(
    val kind: String,
    val data: DataPostDomain
)

data class DataPostDomain(
    val author_fullname: String?,
    val title: String,
    val score: Int?,
    val thumbnail: String?,
    val post_hint: String,
    val id: String,
    val author: String?,
    val num_comments: Int?,
    val url: String,
    val is_video: Boolean
)