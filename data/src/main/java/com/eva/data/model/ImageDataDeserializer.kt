package com.eva.data.model

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ImageDataDeserializer : JsonDeserializer<List<ImageDataDto>> {

    companion object {
        private val TAG = ImageDataDeserializer::class.simpleName
    }


    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<ImageDataDto> {

        return buildList<ImageDataDto> {
            json?.asJsonArray?.forEach {
                it?.let {
                    try {
                        add(deserializeImageData(it.asJsonObject))
                    } catch (e: IllegalStateException) {
                        Log.d(TAG, "json object is dropped because of: \n${e.message}")
                    }

                }
            }
        }

    }

    private fun deserializeImageData(jsonObject: JsonObject): ImageDataDto {
        fun nullException(field: String): Exception {
            return  IllegalStateException("Image data can't be null on field $field")
        }

        val url = jsonObject.get("urls")?.asJsonObject?.get("small")?.asString
            ?: throw nullException("urls or small")
        val username = jsonObject.get("user").asJsonObject?.get("username")?.asString
            ?: throw nullException("user or username")
        val blurHash = jsonObject.get("blur_hash")?.asString
            ?: throw nullException("blur_hash")
        val dateStamp = jsonObject.get("created_at")?.asString
            ?: throw nullException("created_at")
        val likes = jsonObject.get("likes")?.asInt
            ?: throw nullException("likes")

        val id = jsonObject.get("id")?.asString
            ?: throw nullException("id")

        val date = calculateDate(dateStamp)

        return ImageDataDto(
            imageId = id,
            url = url,
            username = username,
            blurHash = blurHash,
            date = date,
            likes = likes
        )
    }

    private fun calculateDate(timestamp: String): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val ldt = LocalDateTime.parse(timestamp, dateTimeFormatter)
        return "${ldt.year}.${ldt.month.value}.${ldt.dayOfMonth}"
    }


}