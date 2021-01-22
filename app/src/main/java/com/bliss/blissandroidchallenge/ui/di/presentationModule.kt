package com.bliss.blissandroidchallenge.ui.di

import com.bliss.blissandroidchallenge.ui.avatarlist.AvatarListActivityViewModel
import com.bliss.blissandroidchallenge.ui.list.ListActivityViewModel
import com.bliss.blissandroidchallenge.ui.main.MainActivityViewModel
import com.bliss.blissandroidchallenge.ui.reposlist.RepoListActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainActivityViewModel(get())
    }

    viewModel {
        ListActivityViewModel(get())
    }

    viewModel {
        AvatarListActivityViewModel(get())
    }

    viewModel {
        RepoListActivityViewModel(get())
    }
}