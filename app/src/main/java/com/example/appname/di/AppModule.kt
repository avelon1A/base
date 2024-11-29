package com.example.appname.di


import com.example.appname.data.remote.HttpClientProvider
import com.example.appname.data.remote.UserRemoteDataSource
import com.example.appname.data.repository.LocalStroageImp
import com.example.appname.data.repository.UserRepositoryImpl
import com.example.appname.data.store.UserPreferencesDataStore
import com.example.appname.domain.repository.LoacalStorage
import com.example.appname.domain.repository.UserRepository
import com.example.appname.domain.usecase.GetUserDataUseCase
import com.example.appname.domain.usecase.LoginUseCases
import com.example.appname.domain.usecase.TokenUseCases
import com.example.appname.presentaion.viewmodels.InitialViewModel
import com.example.appname.presentaion.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  AppModule = module {
    single { HttpClientProvider.createHttpClient() }
    single { UserRemoteDataSource(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { GetUserDataUseCase(get()) }
    single { LoginUseCases(get()) }
    single<LoacalStorage> { LocalStroageImp(get()) }
    single { UserPreferencesDataStore(get()) }
    single { TokenUseCases(get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { InitialViewModel(get(),get()) }
}