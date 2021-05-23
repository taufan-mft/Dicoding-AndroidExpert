package com.twentythirty.androidexpert

import android.app.Application
import com.twentythirty.androidexpert.di.AppModule.useCaseModule
import com.twentythirty.androidexpert.di.AppModule.viewModelModule
import com.twentythirty.core.di.Koin.databaseModule
import com.twentythirty.core.di.Koin.networkModule
import com.twentythirty.core.di.Koin.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by taufan-mft on 5/23/2021.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}