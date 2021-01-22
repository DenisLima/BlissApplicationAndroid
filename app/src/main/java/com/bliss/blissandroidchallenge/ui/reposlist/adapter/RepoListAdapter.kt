package com.bliss.blissandroidchallenge.ui.reposlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.data.repolist.model.Repos
import kotlinx.android.synthetic.main.item_google_list.view.*

class RepoListAdapter: PagingDataAdapter<Repos, RepoListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    object DataDifferntiator : DiffUtil.ItemCallback<Repos>() {

        override fun areItemsTheSame(oldItem: Repos, newItem: Repos): Boolean {
            return oldItem.fullRepoName == newItem.fullRepoName
        }

        override fun areContentsTheSame(oldItem: Repos, newItem: Repos): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvItemRepo.text = "${getItem(position)?.fullRepoName}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_google_list, parent, false)
        )
    }

}