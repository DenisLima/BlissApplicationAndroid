package com.bliss.blissandroidchallenge.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.observe
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.ui.avatarlist.AvatarListActivity
import com.bliss.blissandroidchallenge.ui.list.ListActivity
import com.bliss.blissandroidchallenge.ui.reposlist.RepoListActivity
import com.bliss.blissandroidchallenge.utils.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.label_bliss_activity)

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
            val intent = Intent(this, AvatarListActivity::class.java)
            startActivity(intent)
        }

        btnGoogleRepos.setOnClickListener {
            val intent = Intent(this, RepoListActivity::class.java)
            startActivity(intent)
        }

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnSearch.isEnabled = s.toString().trim().isNotEmpty()
            }

        })

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