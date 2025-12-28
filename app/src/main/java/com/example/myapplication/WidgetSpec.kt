package com.example.myapplication

import android.content.Context
import org.json.JSONObject

data class WidgetSpec(
    val text: String,
    val imageRes: Int
) {
    companion object {
        fun fromJson(context: Context, json: String): WidgetSpec {
            val root = JSONObject(json)
            val content = root.getJSONObject("content")

            val text = if (content.has("text")) {
                content.getJSONObject("text").getString("value")
            } else {
                "Default text"
            }

            val imageRes = if (content.has("image")) {
                val name = content.getJSONObject("image").getString("name")
                context.resources.getIdentifier(
                    name, "drawable", context.packageName
                )
            } else {
                0
            }

            return WidgetSpec(text, imageRes)
        }

    }
}