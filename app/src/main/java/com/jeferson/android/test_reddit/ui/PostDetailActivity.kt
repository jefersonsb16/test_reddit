package com.jeferson.android.test_reddit.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jeferson.android.test_reddit.R
import com.jeferson.android.test_reddit.databinding.ActivityPostDetailBinding
import com.jeferson.android.test_reddit.domain.PostDomain
import com.jeferson.android.test_reddit.parcelables.PostParcelable
import com.jeferson.android.test_reddit.parcelables.toPostDomain
import com.jeferson.android.test_reddit.utils.Constants.POST_DETAIL
import com.jeferson.android.test_reddit.utils.bindCircularImageUrl

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
        binding.lifecycleOwner = this@PostDetailActivity

        val post: PostDomain? =
            intent.getParcelableExtra<PostParcelable>(POST_DETAIL)?.toPostDomain()

        if (post == null) {
            finish()
            return
        }

        loadPostRedditValues(post)
    }

    private fun loadPostRedditValues(post: PostDomain) {
        binding.imagePostDetail.bindCircularImageUrl(
            url = post.data.thumbnail,
            placeholder = R.drawable.ic_camera,
            errorPlaceholder = R.drawable.ic_broken_image
        )

        binding.title = post.data.title
        binding.author = post.data.author_fullname
        binding.score = post.data.score?.toString() ?: "0"
        binding.comments = post.data.num_comments?.toString() ?: "0"

        binding.tvLinkDetail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(post.data.url)

            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@PostDetailActivity,
                    getString(R.string.message_error_open_url),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}