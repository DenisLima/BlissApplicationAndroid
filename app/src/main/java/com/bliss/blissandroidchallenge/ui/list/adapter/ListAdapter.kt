package com.bliss.blissandroidchallenge.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity

class ListAdapter(
    private val onClickEmojiListener: OnClickEmojiListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listEmoji = listOf<EmojiEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_emoji_list, parent, false), onClickEmojiListener)
    }

    override fun getItemCount(): Int = listEmoji.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val emojiHolder = holder as ListHolder
        emojiHolder.bindView(listEmoji[position], position)
    }

    fun setEmojiList(listEmoji: List<EmojiEntity>) {
        this.listEmoji = listEmoji
        notifyDataSetChanged()
    }

    fun removeItem(emojiEntity: EmojiEntity) {
        val list = this.listEmoji.toMutableList()
        list.remove(emojiEntity)
        notifyItemRemoved(this.listEmoji.indexOf(emojiEntity))
        this.listEmoji = list
    }
}