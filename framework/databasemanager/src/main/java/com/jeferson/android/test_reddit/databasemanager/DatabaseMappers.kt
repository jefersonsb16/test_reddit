package com.jeferson.android.test_reddit.databasemanager

import com.jeferson.android.test_reddit.domain.DataPostDomain
import com.jeferson.android.test_reddit.domain.PostDomain

fun List<PostEntity>.toPostDomainList() = map(PostEntity::toPostDomain)

fun List<PostDomain>.toPostEntityList() = map(PostDomain::toPostEntity)

fun PostEntity.toPostDomain() = PostDomain(
    kind,
    data.toDataPostDomain()
)

fun PostDomain.toPostEntity() = PostEntity(
    kind,
    data.toDataPostEntity()
)

fun DataPostEntity.toDataPostDomain() = DataPostDomain(
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

fun DataPostDomain.toDataPostEntity() = DataPostEntity(
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