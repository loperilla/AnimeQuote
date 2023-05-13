package com.loperilla.model.interfaces

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.interfaces
 * Created By Manuel Lopera on 3/5/23 at 16:20
 * All rights reserved 2023
 */
abstract class IEntityModel<M : IModel> : IModel {
    abstract fun toDomain(): M
}