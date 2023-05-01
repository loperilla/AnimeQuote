package com.loperilla.data.combined.anime

import com.loperilla.datasource.database.dao.AnimeDao
import com.loperilla.datasource.database.entity.AnimeEntity
import com.loperilla.datasource.network.api.AnimeApi
import com.loperilla.model.quote.Anime
import com.loperilla.model.result.CallResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val animeApi: AnimeApi,
    private val animeDao: AnimeDao
) {
    fun getAllAnimeName(): Flow<CallResult<List<Anime>>> = flow {
        animeDao.getAllAnime().collect { entityList: List<AnimeEntity> ->
            entityList.ifEmpty {
                getAllNetworkAnimeName()
            }
            emit(CallResult
                .Success(
                    entityList.map {
                        Anime(it.name)
                    }
                )
            )
        }
    }


    private suspend fun getAllNetworkAnimeName() {
        val networkResultAnimeList: Result<List<String>> = animeApi.getAllAnime()
        if (networkResultAnimeList.isSuccess) {
            val list: List<String> = networkResultAnimeList.getOrThrow()

            animeDao.addAnimeList(
                animeList = list.map { animeName ->
                    AnimeEntity(
                        name = animeName
                    )
                }
            )
        }
    }
}
