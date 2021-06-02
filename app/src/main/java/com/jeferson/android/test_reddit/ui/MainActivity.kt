package com.jeferson.android.test_reddit.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeferson.android.test_reddit.R
import com.jeferson.android.test_reddit.adapters.PostsRecyclerAdapter
import com.jeferson.android.test_reddit.databinding.ActivityMainBinding
import com.jeferson.android.test_reddit.di.PostsRedditListComponent
import com.jeferson.android.test_reddit.di.PostsRedditListModule
import com.jeferson.android.test_reddit.domain.PostDomain
import com.jeferson.android.test_reddit.presentation.PostsRedditListViewModel
import com.jeferson.android.test_reddit.presentation.utils.Event
import com.jeferson.android.test_reddit.utils.MessageErrorFactory
import com.jeferson.android.test_reddit.utils.app
import com.jeferson.android.test_reddit.utils.getViewModel

class MainActivity : AppCompatActivity(), OnItemPostClickListener {

    private val messageErrorFactory = MessageErrorFactory()

    // adapter for recyclerview
    private lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    // dagger component
    private lateinit var postsRedditListComponent: PostsRedditListComponent

    private val postsRedditListViewModel: PostsRedditListViewModel by lazy {
        getViewModel {
            postsRedditListComponent.postsRedditListViewModel
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsRedditListComponent = this.app.component.inject(PostsRedditListModule())

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = postsRedditListViewModel

        // initialize recycler adapter
        postsRecyclerAdapter = PostsRecyclerAdapter(
            this
        )

        // initialize recyclerview list
        binding.rvPostsRedditList.run {
            adapter = postsRecyclerAdapter
        }

        // initialize an instance of divider item decoration
        DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        ).apply {
            // add divider item decoration to recycler view
            binding.rvPostsRedditList.addItemDecoration(this)
        }

        // observe events
        postsRedditListViewModel.events.observe(this, Observer(this::validateEvents))

        // call api rest
        postsRedditListViewModel.onGetPostsReddit()
    }

    // handle livedata events
    private fun validateEvents(event: Event<PostsRedditListViewModel.PostsListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is PostsRedditListViewModel.PostsListNavigation.ShowPostError -> navigation.run {
                    messageErrorFactory.showSnackBar(this@MainActivity, error, binding.root)

                    // get data from room
                    postsRedditListViewModel.onGetPostsReddit()
                }
                is PostsRedditListViewModel.PostsListNavigation.ShowPostList -> navigation.run {
                    postsRecyclerAdapter.setDataList(postsList)
                }
                PostsRedditListViewModel.PostsListNavigation.HideLoading -> {
                    binding.loadingPosts.visibility = View.INVISIBLE
                }
                PostsRedditListViewModel.PostsListNavigation.ShowLoading -> {
                    binding.loadingPosts.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun openPostDetail(post: PostDomain) {

    }
}