package com.bliss.blissandroidchallenge.domain.di

import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.domain.main.MainUseCasesImpl
import com.bliss.blissandroidchallenge.domain.repolist.RepoListUseCases
import com.bliss.blissandroidchallenge.domain.repolist.RepoListUseCasesImpl
import org.koin.dsl.module

val domainModule = module {

    single<MainUseCases> {
        MainUseCasesImpl(get())
    }

    single<RepoListUseCases> {
        RepoListUseCasesImpl(get())
    }
}