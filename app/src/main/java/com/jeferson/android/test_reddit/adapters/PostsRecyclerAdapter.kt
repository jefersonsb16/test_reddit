package com.jeferson.android.test_reddit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeferson.android.test_reddit.R
import com.jeferson.android.test_reddit.databinding.ItemPostBinding
import com.jeferson.android.test_reddit.domain.PostDomain
import com.jeferson.android.test_reddit.ui.OnItemPostClickListener
import com.jeferson.android.test_reddit.utils.bindCircularImageUrl

class PostsRecyclerAdapter(
    private val listener: OnItemPostClickListener
) : RecyclerView.Adapter<PostsRecyclerAdapter.PostViewHolder>() {

    private val postsRedditList: MutableList<PostDomain> = mutableListOf()

    fun setDataList(postsList: List<PostDomain>) {
        postsRedditList.clear()
        postsRedditList.addAll(postsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postsRedditList[position])
    }

    override fun getItemCount(): Int = postsRedditList.size

    class PostViewHolder(
        view: View,
        private val listener: OnItemPostClickListener
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPostBinding.bind(view)

        fun bind(post: PostDomain) {
            binding.post = post

            binding.imagePost.bindCircularImageUrl(
                url = post.data.thumbnail,
                placeholder = R.drawable.ic_camera,
                errorPlaceholder = R.drawable.ic_broken_image
            )

            itemView.setOnClickListener { listener.openPostDetail(post) }
        }
    }
}