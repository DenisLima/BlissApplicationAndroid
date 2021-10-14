package com.bliss.blissandroidchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.data.main.model.UserAvatar
import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.ui.main.MainActivityViewModel
import com.bliss.blissandroidchallenge.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mainUseCases: MainUseCases

    private val viewModel by lazy {
        spyk(
            MainActivityViewModel(
                mainUseCases = mainUseCases
            ), recordPrivateCalls = true
        )
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN fake emoji list WHEN get emojis fromDB THEN return success`() {
        dispatcher.runBlockingTest {
            //Given
            coEvery {
                mainUseCases.getEmojisFromDb()
            } returns FAKE_EMOJI_LIST

            //When
            viewModel.checkCacheData()

            //Then
            assertEquals(viewModel.getButtonStatus().value, true)
        }
    }

    @Test
    fun `GIVEN empty emoji list WHEN get emojis fromDB THEN return success`() {
        dispatcher.runBlockingTest {
            //Given
            coEvery {
                mainUseCases.getEmojisFromDb()
            } returns listOf()

            //When
            viewModel.checkCacheData()

            //Then
            assertEquals(viewModel.getButtonStatus().value, false)
        }
    }

    @Test
    fun `GIVEN fake avatar list WHEN get avatar fromDB THEN return success`() {
        dispatcher.runBlockingTest {
            //Given
            coEvery {
                mainUseCases.getEmojisFromDb()
            } returns FAKE_EMOJI_LIST

            coEvery {
                mainUseCases.getUserAvatar("fakeUser")
            } returns UserAvatar(
                login = "fakeLogin",
                id = 0,
                avatarUrl = "fakeUrl"
            )

            //When
            viewModel.fetchEmojis()

            //Then
            coVerify(exactly = 1) {
                viewModel.selectRandomImage(FAKE_EMOJI_LIST)
            }
        }
    }

    @Test
    fun `GIVEN fake avatar WHEN get avatar with user THEN return success`() {
        dispatcher.runBlockingTest {
            //Given
            coEvery {
                mainUseCases.getUserAvatarFromDb(FAKE_USER)
            } returns FAKE_AVATAR

            //When
            viewModel.getUserAvatar(FAKE_USER)

            //Then
            assertEquals(viewModel.getUserAvatarLv().value, Resource.success(data = FAKE_AVATAR))
        }
    }

    companion object {
        private val FAKE_EMOJI_LIST = listOf(EmojiEntity(
            id = 0,
            emojiName = "test",
            urlEmoji = "test"
        ))

        private val FAKE_AVATAR = UserAvatarEntity(
            id = 0,
            login = "fakeLogin",
            avatarUrl = "fakeUrl"
        )

        private val FAKE_USER = "fakeUser"
    }
}