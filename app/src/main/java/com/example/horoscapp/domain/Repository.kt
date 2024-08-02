package com.example.horoscapp.domain

import com.example.horoscapp.data.network.response.PredictionResponse

interface Repository {
    suspend fun getPrediction(sign: String) : PredictionResponse?
}