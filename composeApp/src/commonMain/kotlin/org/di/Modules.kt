package org.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import org.cmparchitecture.data.repository.AuthRepo
import org.cmparchitecture.data.repository.AuthRepoImpl
import org.cmparchitecture.presentation.signup.SignupViewModel
import org.cmparchitecture.presentation.signin.SignInViewModel
import org.cmparchitecture.presentation.splash.SplashViewModel
import org.cmparchitecture.presentation.welcomesreen.WelcomeScreenViewModel
import org.core.presentation.viewmodel.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    single<FirebaseAuth> { Firebase.auth }
    single { MainViewModel() }
    viewModelOf(::SignupViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::WelcomeScreenViewModel)
    viewModelOf(::SplashViewModel)

    singleOf(::AuthRepoImpl).bind<AuthRepo>()
}