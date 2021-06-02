package com.jeferson.android.test_reddit.parcelables

import com.jeferson.android.test_reddit.domain.DataPostDomain
import com.jeferson.android.test_reddit.domain.PostDomain

fun PostDomain.toPostParcelable() = PostParcelable(
    kind,
    data.toDataPostParcelable()
)

fun PostParcelable.toPostDomain() = PostDomain(
    kind,
    data.toDataPostDomain()
)

fun DataPostParcelable.toDataPostDomain() = DataPostDomain(
    author_fullname,
    title,
    score,
    thumbnail,
    post_hint,
    id,
    author,
    num_comments,
    url,
    is_video
)

fun DataPostDomain.toDataPostParcelable() = DataPostParcelable(
    author_fullname,
    title,
    score,
    thumbnail,
    post_hint,
    id,
    author,
    num_comments,
    url,
    is_video
)