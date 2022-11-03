package com.juangranadilla.domain.result

sealed class DataState<out T: Any> {
    data class Loading(val isLoading: Boolean): DataState<Nothing>()
    data class Success<out T: Any>(val value: T): DataState<T>()
    data class Error(val message: String, val cause: Throwable? = null): DataState<Nothing>()
}
