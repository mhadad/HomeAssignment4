package com.mf.homeassignment4.data.models



sealed class UiStates<out T> {
    object Loading: UiStates<Nothing>()
    object UnInitialized: UiStates<Nothing>()
    class Error(message: String) : UiStates<Nothing>()
    class Success<T>(val data : T): UiStates<T>()
}