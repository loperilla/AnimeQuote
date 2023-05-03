package com.loperilla.model.interfaces

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.interfaces
 * Created By Manuel Lopera on 3/5/23 at 16:21
 * All rights reserved 2023
 */
abstract class IRemoteResponse<C : IEntityModel<M>, M : IModel> : IModel {
    abstract fun toEntity(): C
    abstract fun toDomain(): M
}

