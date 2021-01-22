package com.bliss.blissandroidchallenge.data.main.model

import com.google.gson.annotations.SerializedName

data class UserAvatar (
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String
)