package com.bliss.blissandroidchallenge.ui.avatarlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.ui.avatarlist.adapter.AvatarListAdapter
import com.bliss.blissandroidchallenge.ui.avatarlist.adapter.OnClickAvatarListener
import com.bliss.blissandroidchallenge.utils.Status
import kotlinx.android.synthetic.main.activity_avatar_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AvatarListActivity : AppCompatActivity(), OnClickAvatarListener {

    private val viewModel by viewModel<AvatarListActivityViewModel>()
    private val avatarListAdapter = AvatarListAdapter(onClickAvatarListener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_list)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.label_list_avatar_activity)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        viewModel.fetchAvatarFromDb()
        prepareObservers()
    }

    private fun prepareObservers() {

        viewModel.getAvatarsList().observe(this) {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    it.data?.let { it1 -> buildRecycler(it1) }
                }
                else -> {

                }
            }
        }

        viewModel.getRemoveAvatar().observe(this) {
            avatarListAdapter.removeItem(it)
        }
    }

    private fun buildRecycler(avatarList: List<UserAvatarEntity>) {

        recyclerAvatarList.layoutManager =
            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)

        recyclerAvatarList.adapter = avatarListAdapter
        avatarList.let { it1 -> avatarListAdapter.setAvatarList(it1) }
    }

    override fun onClickImage(avatar: UserAvatarEntity) {
        viewModel.deleteAvatar(avatar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}