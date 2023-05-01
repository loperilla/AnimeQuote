package com.loperilla.data.combined.character

import com.loperilla.datasource.database.dao.CharacterDao
import com.loperilla.datasource.database.entity.CharacterEntity
import com.loperilla.datasource.network.api.CharactersApi
import com.loperilla.model.quote.Character
import com.loperilla.model.result.CallResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.data.combined.character
 * Created By Manuel Lopera on 1/5/23 at 18:37
 * All rights reserved 2023
 */
class CharacterRepository @Inject constructor(
    private val characterApi: CharactersApi,
    private val characterDao: CharacterDao
) {
    fun getAllCharacterName(): Flow<CallResult<List<Character>>> = flow {
        characterDao.getAllCharacter().collect { entityList: List<CharacterEntity> ->
            entityList.ifEmpty {
                getAllNetworkCharacterName()
            }
            emit(
                CallResult
                    .Success(
                        entityList.map {
                            Character(it.name)
                        }
                    )
            )
        }
    }


    private suspend fun getAllNetworkCharacterName() {
        val networkResultAnimeList: Result<List<String>> = characterApi.getAllCharacters()
        if (networkResultAnimeList.isSuccess) {
            val list: List<String> = networkResultAnimeList
                .getOrThrow()
                .distinct()
                .filter {
                    it.isNotBlank()
                }

            characterDao.addAnimeList(
                characterList = list.map { characterName ->
                    CharacterEntity(
                        name = characterName
                    )
                }
            )
        }
    }
}
