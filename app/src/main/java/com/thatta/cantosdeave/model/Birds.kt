package com.thatta.cantosdeave.model

import com.google.gson.annotations.SerializedName

data class Birds(@SerializedName("recordings") var birds: List<Bird>)
