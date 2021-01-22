package com.bliss.blissandroidchallenge.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class ListActivityViewModel(
    private val mainUseCases: MainUseCases
): ViewModel() {

    private val emojisList = MutableLiveData<Resource<List<EmojiEntity>>>()
    fun getEmojisList(): LiveData<Resource<List<EmojiEntity>>> = emojisList

    fun fetchEmojisFromDb() {

        emojisList.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {

                val emojisFromDb = mainUseCases.getEmojisFromDb()
                if (emojisFromDb.isNotEmpty()) {
                    emojisList.postValue(Resource.success(emojisFromDb))
                } else {
                    emojisList.postValue(Resource.error("No data cached!", null))
                }

            }catch (e: Exception) {
                emojisList.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }
}