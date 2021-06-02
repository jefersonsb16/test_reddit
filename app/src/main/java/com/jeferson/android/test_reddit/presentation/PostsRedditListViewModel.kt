package com.jeferson.android.test_reddit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeferson.android.test_reddit.domain.PostDomain
import com.jeferson.android.test_reddit.presentation.utils.Event
import com.jeferson.android.test_reddit.usecases.AddAllPostRedditUseCase
import com.jeferson.android.test_reddit.usecases.DeleteAllPostRedditLocalUseCase
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import com.jeferson.android.test_reddit.usecases.GetPostRedditLocalUseCase
import com.jeferson.android.test_reddit.utils.MessageErrorFactory.Companion.GENERIC_ERROR
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class PostsRedditListViewModel(
    private val getAllPostsRedditUseCase: GetAllPostsRedditUseCase,
    private val getPostRedditLocalUseCase: GetPostRedditLocalUseCase,
    private val getAddAllPostRedditUseCase: AddAllPostRedditUseCase,
    private val deleteAllPostRedditLocalUseCase: DeleteAllPostRedditLocalUseCase
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

                    // delete all post
                    _events.value = Event(PostsListNavigation.DeleteDataPostRedditList(postsList))

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

    // save the response from the API
    fun savePostReddit(postList: List<PostDomain>) {
        disposable.add(
            getAddAllPostRedditUseCase.invoke(postList)
                .subscribe {
                    Log.d("INSERT ALL POST", "SUCCESS")
                }
        )
    }

    fun deleteAllPostReddit(postList: List<PostDomain>) {
        disposable.add(
            deleteAllPostRedditLocalUseCase.invoke()
                .subscribe {
                    Log.d("DELETE ALL POST", "SUCCESS")

                    // save post
                    _events.value = Event(PostsListNavigation.SaveDataPostRedditList(postList))
                }
        )
    }

    // get post list from local db
    fun onGetPostRedditLocal() {
        disposable.add(
            getPostRedditLocalUseCase.invoke()
                .doOnSubscribe { showLoading() }
                .subscribe { resultList ->
                    hideLoading()

                    _postsList.clear()
                    _postsList.addAll(resultList)

                    // send movie list to view with live data event
                    _sizePostsList.value = _postsList.size
                    _events.value = Event(PostsListNavigation.ShowPostList(_postsList))
                }
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
        data class SaveDataPostRedditList(val postList: List<PostDomain>) : PostsListNavigation()
        data class DeleteDataPostRedditList(val postList: List<PostDomain>) : PostsListNavigation()

        object HideLoading : PostsListNavigation()
        object ShowLoading : PostsListNavigation()
    }
}