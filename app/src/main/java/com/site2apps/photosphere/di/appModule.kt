package com.site2apps.photosphere.di

import com.site2apps.photosphere.ui.register.RegisterViewModel
import com.site2apps.photosphere.data.api.ApiService
import com.site2apps.photosphere.data.repository.ImageRepository
import com.site2apps.photosphere.ui.home.HomeViewModel
import com.site2apps.photosphere.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API service
    single { get<Retrofit>().create(ApiService::class.java) }

    // Repository
    single { ImageRepository(get()) }

    // ViewModel
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { HomeViewModel(repository = get()) }
}
