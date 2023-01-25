package com.eva.domain

sealed class RequestState<out T> {
    object Loading: RequestState<Nothing>()
    class Error(val exception: Throwable): RequestState<Nothing>()
    class Success<T>(val result: T): RequestState<T>()
}