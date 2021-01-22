package com.bliss.blissandroidchallenge.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.ui.list.adapter.ListAdapter
import com.bliss.blissandroidchallenge.ui.list.adapter.OnClickEmojiListener
import com.bliss.blissandroidchallenge.utils.Status
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity(), OnClickEmojiListener {

    private val viewModel by viewModel<ListActivityViewModel>()
    private val emojiListAdapter = ListAdapter(onClickEmojiListener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        viewModel.fetchEmojisFromDb()
        prepareObservers()
        initComponents()
    }

    private fun initComponents() {

        swipeToRefresh.setOnRefreshListener {
            viewModel.fetchEmojisFromDb()
        }

    }

    private fun prepareObservers() {

        viewModel.getEmojisList().observe(this) {

            when (it.status) {
                Status.LOADING -> {
                    swipeToRefresh.isRefreshing = true
                }
                Status.SUCCESS -> {
                    it.data?.let { it1 -> buildRecycler(it1) }
                    swipeToRefresh.isRefreshing = false
                }
                else -> {
                    swipeToRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun buildRecycler(emojiList: List<EmojiEntity>) {

        recyclerList.layoutManager =
            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)

        recyclerList.adapter = emojiListAdapter
        emojiList.let { it1 -> emojiListAdapter.setEmojiList(it1) }
    }

    override fun onClickImage(emoji: EmojiEntity, position: Int) {
        emojiListAdapter.removeItem(emoji)
    }
}