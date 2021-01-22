package com.bliss.blissandroidchallenge.ui.reposlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bliss.blissandroidchallenge.data.repolist.model.Repos
import kotlinx.android.synthetic.main.item_google_list.view.*

class RepoListHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bindView(repo: Repos) {
        itemView.tvItemRepo.text = repo.fullRepoName
    }

}