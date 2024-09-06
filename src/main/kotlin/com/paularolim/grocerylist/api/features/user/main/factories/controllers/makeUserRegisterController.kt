package com.paularolim.grocerylist.api.features.user.main.factories.controllers

import com.paularolim.grocerylist.api.features.user.main.factories.usecases.makeUserRegisterUsecase
import com.paularolim.grocerylist.api.features.user.main.factories.validations.makeUserRegisterValidation
import com.paularolim.grocerylist.api.features.user.presentation.controllers.UserRegisterController

fun makeUserRegisterController(): UserRegisterController {
    return UserRegisterController(
        makeUserRegisterValidation(),
        makeUserRegisterUsecase()
    )
}