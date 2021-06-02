package com.jeferson.android.test_reddit.requestmanager

data class PostRedditResponseServer(
    val kind: String,
    val data: DataPostRedditServer
)

data class DataPostRedditServer(
    val modhash: String?,
    val dist: Int?,
    val children: List<PostServer>,
    val after: String?,
    val before: Any?
)

data class PostServer(
    val kind: String,
    val data: DataPostServer
)

data class DataPostServer(
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