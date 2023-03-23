package com.example.retrofitapp

import com.google.gson.annotations.SerializedName

//1
data class AlbumItem (
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("name")
    val title: String
    )

