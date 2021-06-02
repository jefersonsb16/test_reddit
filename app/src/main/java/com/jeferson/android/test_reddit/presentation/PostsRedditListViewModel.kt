package com.jeferson.android.test_reddit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeferson.android.test_reddit.domain.PostDomain
import com.jeferson.android.test_reddit.presentation.utils.Event
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import com.jeferson.android.test_reddit.utils.MessageErrorFactory.Companion.GENERIC_ERROR
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class PostsRedditListViewModel(
    private val getAllPostsRedditUseCase: GetAllPostsRedditUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _postsList: MutableList<PostDomain> = mutableListOf()

    // Live data events
    private val _events = MutableLiveData<Event<PostsListNavigation>>()
    val events: LiveData<Event<PostsListNavigation>> get() = _events

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _sizePostsList = MutableLiveData(0)
    val sizePostsList: LiveData<Int> get() = _sizePostsList


    // call for get movies in Api REST
    fun onGetPostsReddit() {
        disposable.add(
            getAllPostsRedditUseCase
                .invoke()
                .doOnSubscribe { showLoading() }
                .subscribe({ postsList ->
                    _postsList.clear()
                    _postsList.addAll(postsList)
                    _sizePostsList.value = _postsList.size

                    hideLoading()

                    // send movie list to view with live data event
                    _events.value = Event(PostsListNavigation.ShowPostList(_postsList))
                }, { error ->
                    hideLoading()

                    var errorCode: Int = GENERIC_ERROR

                    try {
                        val httpError = error as HttpException
                        errorCode = httpError.code()
                    } catch (e: Exception) {
                        e.message?.let { Log.e("ERROR GET POSTS REDDIT", it) }
                    }

                    _events.value = Event(PostsListNavigation.ShowPostError(errorCode))
                })
        )
    }

    // handling progress
    private fun showLoading() {
        _isLoading.value = true
        _events.value = Event(PostsListNavigation.ShowLoading)
    }

    private fun hideLoading() {
        _isLoading.value = false
        _events.value = Event(PostsListNavigation.HideLoading)
    }

    // clear disposable
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    sealed class PostsListNavigation {
        data class ShowPostError(val error: Int) : PostsListNavigation()
        data class ShowPostList(val postsList: List<PostDomain>) : PostsListNavigation()

        object HideLoading : PostsListNavigation()
        object ShowLoading : PostsListNavigation()
    }
}