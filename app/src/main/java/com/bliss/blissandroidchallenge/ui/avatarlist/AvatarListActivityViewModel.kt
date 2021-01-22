package com.bliss.blissandroidchallenge.ui.avatarlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class AvatarListActivityViewModel(
    private val mainUseCases: MainUseCases
) : ViewModel() {

    private val avatarList = MutableLiveData<Resource<List<UserAvatarEntity>>>()
    fun getAvatarsList(): LiveData<Resource<List<UserAvatarEntity>>> = avatarList

    private val removeAvatarLv = MutableLiveData<UserAvatarEntity>()
    fun getRemoveAvatar(): LiveData<UserAvatarEntity> = removeAvatarLv

    fun fetchAvatarFromDb() {

        avatarList.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {

                val avatarFromDb = mainUseCases.getAllUserAvatarFromDb()
                if (avatarFromDb.isNotEmpty()) {
                    avatarList.postValue(Resource.success(avatarFromDb))
                } else {
                    avatarList.postValue(Resource.error("No data cached!", null))
                }

            } catch (e: Exception) {
                avatarList.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }

    fun deleteAvatar(avatarEntity: UserAvatarEntity) {
        viewModelScope.launch {
            try {

                mainUseCases.deleteAvatar(avatarEntity)
                removeAvatarLv.postValue(avatarEntity)
            } catch (e: Exception) {

                avatarList.postValue(Resource.error("Problems to remove element", null))
            }
        }
    }

}