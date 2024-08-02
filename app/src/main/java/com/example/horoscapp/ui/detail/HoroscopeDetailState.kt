package com.example.horoscapp.ui.detail

sealed class HoroscopeDetailState {
    data object Loading : HoroscopeDetailState()
    data class Error(val error: String) : HoroscopeDetailState()
    data class Success(val data: String): HoroscopeDetailState()

}