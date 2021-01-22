package com.bliss.blissandroidchallenge.domain.di

import com.bliss.blissandroidchallenge.domain.main.MainUseCases
import com.bliss.blissandroidchallenge.domain.main.MainUseCasesImpl
import org.koin.dsl.module

val domainModule = module {

    single<MainUseCases> {
        MainUseCasesImpl(get())
    }

}