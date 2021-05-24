package com.twentythirty.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by taufan-mft on 5/24/2021.
 */
val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}