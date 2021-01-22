package com.bliss.blissandroidchallenge.ui.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_emoji_list.view.*

class ListHolder(
    itemView: View,
    private val onClickEmojiListener: OnClickEmojiListener
) : RecyclerView.ViewHolder(itemView) {

    fun bindView(emoji: EmojiEntity, position: Int) {
        Glide.with(itemView.context)
            .load(emoji.urlEmoji)
            .into(itemView.imageItemList)

        itemView.imageItemList.setOnClickListener {
            onClickEmojiListener.onClickImage(emoji, position)
        }
    }

}

interface OnClickEmojiListener {
    fun onClickImage(emoji: EmojiEntity, position: Int)
}