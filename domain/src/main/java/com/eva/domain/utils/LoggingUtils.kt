package com.eva.domain.utils

import android.util.Log
import kotlin.reflect.KClass


val KClass<*>.TAG: String get() {
    return this.simpleName ?: this.toString()
}

fun Throwable.debugInfo(showStackTrace: Boolean = false): String = buildString {
    append(this@debugInfo.javaClass.name)
    append(this@debugInfo.message)
    if (showStackTrace) {
        append(this@debugInfo.stackTraceToString())
    }
}

fun fastlog(msg: String) = Log.d("fastlog", msg)