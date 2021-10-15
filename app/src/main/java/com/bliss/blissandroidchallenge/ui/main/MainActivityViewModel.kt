package com.bliss.blissandroidchallenge.ui.main

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(
    private val mainUseCases: MainUseCases
): ViewModel() {

    private val randomUrlEmoji = MutableLiveData<String>()
    fun getRandomPosition(): LiveData<String> = randomUrlEmoji

    private val buttonStatusLv = MutableLiveData<Boolean>()
    fun getButtonStatus(): LiveData<Boolean> = buttonStatusLv

    private val userAvatarLv = MutableLiveData<Resource<UserAvatarEntity>>()
    fun getUserAvatarLv(): LiveData<Resource<UserAvatarEntity>> = userAvatarLv

    fun checkCacheData() {

        viewModelScope.launch {
            try {

                val emojisFromDb = mainUseCases.getEmojisFromDb()
                if (emojisFromDb.isEmpty()) {
                    buttonStatusLv.postValue(false)
                } else {
                    buttonStatusLv.postValue(true)
                }
            } catch (e: Exception) {
                buttonStatusLv.postValue(false)
            }
        }
    }

    fun fetchEmojis() {

        viewModelScope.launch {
            try {
                val emojisFromDb = mainUseCases.getEmojisFromDb()
                if (emojisFromDb.isEmpty()) {
                    val emojisFromApi = mainUseCases.getEmojis()
                    val emojisToInsertInDB = mutableListOf<EmojiEntity>()
                    var emojiId = 1

                    for (apiEmoji in emojisFromApi.emojiList) {
                        val emoji = EmojiEntity(
                            emojiId,
                            apiEmoji.emojiName,
                            apiEmoji.emojiUrl
                        )
                        emojisToInsertInDB.add(emoji)
                        emojiId++
                    }

                    mainUseCases.insertAll(emojisToInsertInDB)
                    selectRandomImage(emojisToInsertInDB)

                } else {
                    selectRandomImage(emojisFromDb)
                }

            } catch (e: Exception){
                Log.e("ERROR", e.message.toString())
            }
        }
    }

    fun getUserAvatar(username: String) {

        userAvatarLv.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {

                val userAvatarFromDb = mainUseCases.getUserAvatarFromDb(username)
                if (userAvatarFromDb == null){
                    val userAvatarFromApi = mainUseCases.getUserAvatar(username)
                    val userAvatar = UserAvatarEntity(
                        id = userAvatarFromApi.id,
                        login = userAvatarFromApi.login,
                        avatarUrl = userAvatarFromApi.avatarUrl
                    )

                    mainUseCases.insertUserAvatar(userAvatar)
                    userAvatarLv.postValue(Resource.success(userAvatar))
                } else {
                    userAvatarLv.postValue(Resource.success(userAvatarFromDb))
                }

            }catch (e: Exception) {
                userAvatarLv.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }

    @VisibleForTesting
    fun selectRandomImage(emojiList: List<EmojiEntity>) {
        val range = (START_RANGE..emojiList.size)
        val emojiRandom = emojiList[range.random()].urlEmoji
        randomUrlEmoji.postValue(emojiRandom)
    }

    companion object {
        private const val START_RANGE = 1
    }
}