package org.di

import org.cmparchitecture.presentation.dashboard.DashboardViewModel
import org.core.presentation.viewmodel.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { MainViewModel() }
    viewModelOf(::DashboardViewModel)
}