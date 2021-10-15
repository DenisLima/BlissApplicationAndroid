package com.bliss.blissandroidchallenge.data.main.mapper

import com.bliss.blissandroidchallenge.data.main.model.DEmojiList
import com.bliss.blissandroidchallenge.domain.model.EmojiList
import com.bliss.blissandroidchallenge.domain.utils.BaseMapper

class MainMapper: BaseMapper<DEmojiList, EmojiList>() {
    override fun transform(entity: DEmojiList): EmojiList {
        return transform(entity)
    }
}