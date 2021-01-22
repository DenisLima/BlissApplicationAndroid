package com.bliss.blissandroidchallenge.data.di

import com.bliss.blissandroidchallenge.data.general.EmojiConverterFactory
import com.bliss.blissandroidchallenge.data.general.createRoomDb
import com.bliss.blissandroidchallenge.data.main.MainRepositoryImpl
import com.bliss.blissandroidchallenge.data.main.datasource.local.AppDatabase
import com.bliss.blissandroidchallenge.data.main.datasource.local.DatabaseHelper
import com.bliss.blissandroidchallenge.data.main.datasource.local.DatabaseHelperImpl
import com.bliss.blissandroidchallenge.data.main.model.EmojiList
import com.bliss.blissandroidchallenge.domain.main.MainRepository
import com.bliss.blissandroidchallenge.data.main.datasource.remote.MainRemoteSource
import com.bliss.blissandroidchallenge.data.repolist.RepoListRepositoryImpl
import com.bliss.blissandroidchallenge.data.repolist.remote.RepoListRemoteSource
import com.bliss.blissandroidchallenge.domain.repolist.RepoListRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val KOIN_DB_NAME = "koinDb"

val dataModule = module {

    // Services for requests
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(EmojiList::class.java, EmojiConverterFactory())

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .callFactory(OkHttpClient.Builder().build())
            .baseUrl("https://api.github.com/")
            .client(get())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //Database

    single(named(KOIN_DB_NAME)) {
        "bliss-application-db"
    }

    single {
        createRoomDb<AppDatabase>(get(), get(named(KOIN_DB_NAME)))
    }

    single {
        get<AppDatabase>().emojiDao()
    }

    single {
        get<Retrofit>().create(MainRemoteSource::class.java)
    }

    //Main

    single<DatabaseHelper> {
        DatabaseHelperImpl(get())
    }

    single<MainRepository> {
        MainRepositoryImpl(get(), get())
    }

    //Repo List

    single<RepoListRepository> {
        RepoListRepositoryImpl(get())
    }

    single {
        get<Retrofit>().create(RepoListRemoteSource::class.java)
    }

}