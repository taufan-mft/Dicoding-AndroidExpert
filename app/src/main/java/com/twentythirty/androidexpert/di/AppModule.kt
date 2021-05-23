package com.twentythirty.androidexpert.di

import com.twentythirty.androidexpert.ui.home.HomeViewModel
import com.twentythirty.core.domain.usecase.FilmInteractor
import com.twentythirty.core.domain.usecase.FilmUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by taufan-mft on 5/23/2021.
 */
object AppModule {
    val useCaseModule = module {
        factory<FilmUseCase> { FilmInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
    }
}