package com.bliss.blissandroidchallenge.ui.avatarlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_avatar_list.view.*

class AvatarListHolder(
    itemView: View,
    private val onClickAvatarListener: OnClickAvatarListener
) : RecyclerView.ViewHolder(itemView) {

    fun bindView(avatar: UserAvatarEntity) {
        Glide.with(itemView.context)
            .load(avatar.avatarUrl)
            .into(itemView.ivAvatarList)

        itemView.ivAvatarList.setOnClickListener {
            onClickAvatarListener.onClickImage(avatar)
        }
    }

}

interface OnClickAvatarListener {
    fun onClickImage(avatar: UserAvatarEntity)
}