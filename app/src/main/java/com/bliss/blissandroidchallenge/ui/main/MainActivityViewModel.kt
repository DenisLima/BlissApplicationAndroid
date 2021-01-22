package com.bliss.blissandroidchallenge.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(
    private val mainUseCases: MainUseCases
): ViewModel() {

    private val emojisList = MutableLiveData<Resource<List<EmojiEntity>>>()
    fun getEmojisList(): LiveData<Resource<List<EmojiEntity>>> = emojisList

    private val mustShowErrorLv = MutableLiveData<String>()
    fun isMustShowError(): LiveData<String> = mustShowErrorLv

    private val randomUrlEmoji = MutableLiveData<String>()
    fun getRandomPosition(): LiveData<String> = randomUrlEmoji

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
                    emojisList.postValue(Resource.success(emojisToInsertInDB))
                    selectRandomImage(emojisToInsertInDB)

                } else {
                    emojisList.postValue(Resource.success(emojisFromDb))
                    selectRandomImage(emojisFromDb)
                }

            } catch (e: Exception){
                emojisList.postValue(Resource.error("Something Went Wrong", null))
                mustShowErrorLv.postValue(e.message)
                Log.e("EROO", e.message.toString())
            }
        }
    }

    private fun selectRandomImage(emojiList: List<EmojiEntity>) {
        val range = (START_RANGE..emojiList.size)
        val emojiRandom = emojiList[range.random()].urlEmoji
        randomUrlEmoji.postValue(emojiRandom)
    }

    companion object {
        private const val START_RANGE = 1
    }
}