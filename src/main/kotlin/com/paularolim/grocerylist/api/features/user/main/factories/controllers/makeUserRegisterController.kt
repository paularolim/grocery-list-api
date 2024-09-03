package com.paularolim.grocerylist.api.features.user.main.factories.controllers

import com.paularolim.grocerylist.api.common.infra.database.mongodb.repositories.UserMongodbRepository
import com.paularolim.grocerylist.api.features.user.data.usecases.DatabaseUserRegisterUsecase
import com.paularolim.grocerylist.api.features.user.main.factories.validations.makeUserRegisterValidation
import com.paularolim.grocerylist.api.features.user.presentation.controllers.UserRegisterController

fun makeUserRegisterController(): UserRegisterController {
    val validation = makeUserRegisterValidation()
    val repository = UserMongodbRepository()
    val database = DatabaseUserRegisterUsecase(repository)
    val controller = UserRegisterController(validation, database)
    return controller
}