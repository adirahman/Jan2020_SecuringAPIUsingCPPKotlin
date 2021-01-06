package com.arc.secureapikotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ModelFan(
    @SerializedName("ver")
    @Expose
    val ver: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("api")
    @Expose
    val api: String
)