package com.example.horoscapp.data.network.response

import com.google.gson.annotations.SerializedName
import java.sql.Date

data class PredictionResponse (
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String,
)