package com.jeferson.android.test_reddit.ui

import com.jeferson.android.test_reddit.domain.PostDomain

interface OnItemPostClickListener {
    fun openPostDetail(post: PostDomain)
}