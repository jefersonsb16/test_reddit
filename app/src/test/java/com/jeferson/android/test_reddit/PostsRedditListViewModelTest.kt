package com.jeferson.android.test_reddit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jeferson.android.test_reddit.domain.DataPostDomain
import com.jeferson.android.test_reddit.domain.PostDomain
import com.jeferson.android.test_reddit.presentation.PostsRedditListViewModel
import com.jeferson.android.test_reddit.presentation.utils.Event
import com.jeferson.android.test_reddit.usecases.AddAllPostRedditUseCase
import com.jeferson.android.test_reddit.usecases.DeleteAllPostRedditLocalUseCase
import com.jeferson.android.test_reddit.usecases.GetAllPostsRedditUseCase
import com.jeferson.android.test_reddit.usecases.GetPostRedditLocalUseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsRedditListViewModelTest {

    @get:Rule
    val rxSchedulerRules = RxSchedulerRules()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAllPostsRedditUseCase: GetAllPostsRedditUseCase

    @Mock
    lateinit var getPostRedditLocalUseCase: GetPostRedditLocalUseCase

    @Mock
    lateinit var getAddAllPostRedditUseCase: AddAllPostRedditUseCase

    @Mock
    lateinit var deleteAllPostRedditLocalUseCase: DeleteAllPostRedditLocalUseCase

    @Mock
    lateinit var eventsObserve: Observer<Event<PostsRedditListViewModel.PostsListNavigation>>

    private lateinit var postsRedditListViewModel: PostsRedditListViewModel

    @Before
    fun setUp() {
        postsRedditListViewModel = PostsRedditListViewModel(
            getAllPostsRedditUseCase,
            getPostRedditLocalUseCase,
            getAddAllPostRedditUseCase,
            deleteAllPostRedditLocalUseCase
        )
    }

    @Test
    fun `onGetPostsReddit should return an expected success list of posts reddit`() {
        val expectedResult = listOf(mockedPost)
        given(getAllPostsRedditUseCase.invoke()).willReturn(Single.just(expectedResult))

        postsRedditListViewModel.events.observeForever(eventsObserve)
        postsRedditListViewModel.onGetPostsReddit()

        verify(eventsObserve).onChanged(
            Event(
                PostsRedditListViewModel.PostsListNavigation.ShowPostList(
                    expectedResult
                )
            )
        )
    }

    @Test
    fun `onGetPostRedditLocal should return an expected success list of posts reddit`() {
        val expectedResult = listOf(mockedPost)
        given(getPostRedditLocalUseCase.invoke()).willReturn(Flowable.just(expectedResult))

        postsRedditListViewModel.events.observeForever(eventsObserve)
        postsRedditListViewModel.onGetPostRedditLocal()

        verify(eventsObserve).onChanged(
            Event(
                PostsRedditListViewModel.PostsListNavigation.ShowPostList(
                    expectedResult
                )
            )
        )
    }
}

val mockedDataPost = DataPostDomain(
    "Test",
    "Test",
    0,
    "",
    "",
    "test",
    "Test",
    0,
    "",
    false
)

val mockedPost = PostDomain(
    "",
    mockedDataPost
)