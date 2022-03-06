package com.thatta.cantosdeave.model

import com.google.gson.annotations.SerializedName

data class Bird(@SerializedName("en") var birdName: String,
                @SerializedName("file") var soundUrl: String)
