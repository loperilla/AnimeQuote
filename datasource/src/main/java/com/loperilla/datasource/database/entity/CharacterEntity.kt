package com.loperilla.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database.entity
 * Created By Manuel Lopera on 29/4/23 at 14:23
 * All rights reserved 2023
 */

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)
