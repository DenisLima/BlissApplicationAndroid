package com.bliss.blissandroidchallenge.ui.reposlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.ui.reposlist.adapter.HeaderFooterAdapter
import com.bliss.blissandroidchallenge.ui.reposlist.adapter.RepoListAdapter
import com.bliss.blissandroidchallenge.utils.Status
import kotlinx.android.synthetic.main.activity_google_repo.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListActivity: AppCompatActivity() {

    private val viewModel by viewModel<RepoListActivityViewModel>()
    private var repoListAdapter = RepoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_repo)

        setupList()
        setupView()
    }

    private fun setupView() {

        lifecycleScope.launch {
            viewModel.listData.collect {
                repoListAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {

        recyclerRepoList.apply {
            layoutManager = LinearLayoutManager(this@RepoListActivity)
            adapter = repoListAdapter.withLoadStateFooter(
                footer = HeaderFooterAdapter{ repoListAdapter.retry() }
            )
        }
    }

}