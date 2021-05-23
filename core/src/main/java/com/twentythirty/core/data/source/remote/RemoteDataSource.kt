package com.twentythirty.core.data.source.remote

import com.twentythirty.core.data.Resource
import com.twentythirty.core.data.source.remote.network.TmApi
import com.twentythirty.core.data.source.remote.response.FilmResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by taufan-mft on 5/23/2021.
 */
class RemoteDataSource(private val TmApi: TmApi) {
    suspend fun getFilms(): Flow<Resource<List<FilmResponse>>> {
        return flow {
            try {
                emit(Resource.success(TmApi.getFilms()))
            } catch (e: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Error Occurred!"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

}