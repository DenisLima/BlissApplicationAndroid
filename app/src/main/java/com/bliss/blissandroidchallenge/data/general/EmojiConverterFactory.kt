package com.bliss.blissandroidchallenge.data.general

import com.bliss.blissandroidchallenge.data.main.model.Emoji
import com.bliss.blissandroidchallenge.data.main.model.EmojiList
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.json.JSONObject
import java.lang.reflect.Type

class EmojiConverterFactory : JsonDeserializer<EmojiList> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EmojiList? {

        val jsonObject = JSONObject(json.toString())
        val keys = jsonObject.keys()
        val emojiList = mutableListOf<Emoji>()

        while (keys.hasNext()) {
            val key = keys.next()

            emojiList.add(
                Emoji(
                    emojiName = key,
                    emojiUrl = jsonObject[key].toString()
                )
            )
        }

        return EmojiList(emojiList)

    }

}