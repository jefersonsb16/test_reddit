package com.jeferson.android.test_reddit.requestmanager

import com.jeferson.android.test_reddit.domain.DataPostDomain
import com.jeferson.android.test_reddit.domain.PostDomain

fun PostRedditResponseServer.toPostRedditDomainList(): List<PostDomain> = data.children.map {
    it.run {
        PostDomain(
            kind,
            data.toDataPostDomain()
        )
    }
}

fun DataPostServer.toDataPostDomain() = DataPostDomain(
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