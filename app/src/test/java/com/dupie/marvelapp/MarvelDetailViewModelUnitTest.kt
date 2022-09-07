package com.dupie.marvelapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dupie.marvelapp.data.MarvelRepository
import com.dupie.marvelapp.network.response.ComicDTO
import com.dupie.marvelapp.network.response.ComicResponseDTO
import com.dupie.marvelapp.network.response.DataDTO
import com.dupie.marvelapp.network.response.ThumbnailDTO
import com.dupie.marvelapp.network.service.MarvelService
import com.dupie.marvelapp.ui.detail.MarvelDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MarvelDetailViewModelUnitTest {

    val mockResponse = ComicResponseDTO(DataDTO(
        listOf<ComicDTO>(
            ComicDTO(
                1,
                "someTitle",
                "someDesc",
                ThumbnailDTO("somePath", "ext"),
                null
            )
        )
    ))

    val mockRepo = MarvelRepository(object: MarvelService {
        override suspend fun fetchComics(): ComicResponseDTO {
            return mockResponse
        }

        override suspend fun fetchComic(comicId: Int): ComicResponseDTO {
            return mockResponse
        }
    })

    val viewModel = MarvelDetailViewModel(Mockito.mock(Application::class.java), repo = mockRepo)

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val mockExpectedDetailUiState = MarvelDetailViewModel.DetailUiState(
        "somePath.ext",
        "someTitle",
        "someDesc"
    )

    @Test
    fun `given a ComicDTO validate that MarvelDetailViewModel transforms data properly`() {
        viewModel.onCreate(1)
        val value = viewModel.uiStateLD.value
        assert(value == mockExpectedDetailUiState)
    }

}