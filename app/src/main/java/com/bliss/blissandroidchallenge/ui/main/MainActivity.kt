package com.bliss.blissandroidchallenge.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.observe
import com.bliss.blissandroidchallenge.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        prepareObservers()
    }

    private fun initComponents() {

        btnEmoji.setOnClickListener {
            viewModel.fetchEmojis()
        }

    }

    private fun prepareObservers() {

        viewModel.getRandomPosition().observe(this) {
            Glide.with(applicationContext)
                .load(it)
                .into(ivEmoji)
        }

    }

}