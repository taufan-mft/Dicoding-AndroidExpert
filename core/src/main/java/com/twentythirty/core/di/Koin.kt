package com.twentythirty.core.di

import androidx.room.Room
import com.twentythirty.core.data.FilmRepository
import com.twentythirty.core.data.source.local.LocalDataSource
import com.twentythirty.core.data.source.local.room.FilmDatabase
import com.twentythirty.core.data.source.remote.RemoteDataSource
import com.twentythirty.core.data.source.remote.network.TmApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Koin {
    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(TmApi::class.java)
        }
    }


    val databaseModule = module {
        factory { get<FilmDatabase>().filmDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                FilmDatabase::class.java, "film_database"
            ).fallbackToDestructiveMigration().build()
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        single {
            FilmRepository(
                get(),
                get()
            )
        }
    }
}