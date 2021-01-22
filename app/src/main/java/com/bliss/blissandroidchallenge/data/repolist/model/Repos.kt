package com.bliss.blissandroidchallenge.data.repolist.model

import com.google.gson.annotations.SerializedName

data class Repos (
    @SerializedName("full_name") val fullRepoName: String
)