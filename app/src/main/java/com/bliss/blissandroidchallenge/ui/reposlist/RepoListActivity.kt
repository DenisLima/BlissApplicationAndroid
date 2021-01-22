package com.bliss.blissandroidchallenge.ui.reposlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bliss.blissandroidchallenge.R
import com.bliss.blissandroidchallenge.ui.reposlist.adapter.HeaderFooterAdapter
import com.bliss.blissandroidchallenge.ui.reposlist.adapter.RepoListAdapter
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

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.label_google_repo_activity)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}