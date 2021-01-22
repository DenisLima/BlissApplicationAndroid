package com.bliss.blissandroidchallenge.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.observe
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.ui.avatarlist.AvatarListActivity
import com.bliss.blissandroidchallenge.ui.list.ListActivity
import com.bliss.blissandroidchallenge.utils.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.checkCacheData()
        initComponents()
        prepareObservers()
    }

    private fun initComponents() {

        btnEmoji.setOnClickListener {
            viewModel.fetchEmojis()
        }

        btnEmojiList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        btnSearch.setOnClickListener {
            viewModel.getUserAvatar(editSearch.text.toString())
        }

        btnAvatarList.setOnClickListener {
            val it = Intent(this, AvatarListActivity::class.java)
            startActivity(it)
        }

    }

    private fun prepareObservers() {

        viewModel.getRandomPosition().observe(this) {
            Glide.with(applicationContext)
                .load(it)
                .into(ivEmoji)
            btnEmojiList.isEnabled = true
        }

        viewModel.getButtonStatus().observe(this) {
            btnEmojiList.isEnabled = it
        }

        viewModel.getUserAvatarLv().observe(this) {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    Glide.with(applicationContext)
                        .load(it.data?.avatarUrl)
                        .into(ivEmoji)
                }
                else -> {

                }
            }
        }

    }

}