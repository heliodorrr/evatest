package com.eva.domain.utils

import android.util.Log

val Any.TAG: String get() {
    return this::class.simpleName ?: this::class.toString()
}

fun Throwable.debugInfo(showStackTrace: Boolean = false): String = buildString {
    append(this@debugInfo.javaClass.name)
    append(this@debugInfo.message)
    if (showStackTrace) {
        append(this@debugInfo.stackTraceToString())
    }
}

fun fastlog(msg: String) = Log.d("fastlog", msg)