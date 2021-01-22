package com.bliss.blissandroidchallenge.ui.avatarlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity

class AvatarListAdapter(
    private val onClickAvatarListener: OnClickAvatarListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listAvatar = listOf<UserAvatarEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AvatarListHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_avatar_list, parent, false), onClickAvatarListener)
    }

    override fun getItemCount(): Int = listAvatar.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val emojiHolder = holder as AvatarListHolder
        emojiHolder.bindView(listAvatar[position])
    }

    fun setAvatarList(listAvatar: List<UserAvatarEntity>) {
        this.listAvatar = listAvatar
        notifyDataSetChanged()
    }

    fun removeItem(avatarEntity: UserAvatarEntity) {
        val list = this.listAvatar.toMutableList()
        list.remove(avatarEntity)
        notifyItemRemoved(this.listAvatar.indexOf(avatarEntity))
        this.listAvatar = list
    }
}