package com.bliss.blissandroidchallenge.data.general

import com.bliss.blissandroidchallenge.data.main.model.DEmoji
import com.bliss.blissandroidchallenge.data.main.model.DEmojiList
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.json.JSONObject
import java.lang.reflect.Type

class EmojiConverterFactory : JsonDeserializer<DEmojiList> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DEmojiList? {

        val jsonObject = JSONObject(json.toString())
        val keys = jsonObject.keys()
        val emojiList = mutableListOf<DEmoji>()

        while (keys.hasNext()) {
            val key = keys.next()

            emojiList.add(
                DEmoji(
                    emojiName = key,
                    emojiUrl = jsonObject[key].toString()
                )
            )
        }

        return DEmojiList(emojiList)

    }

}