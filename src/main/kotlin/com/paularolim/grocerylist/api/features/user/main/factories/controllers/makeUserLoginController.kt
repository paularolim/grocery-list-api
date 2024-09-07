package com.paularolim.grocerylist.api.features.user.main.factories.controllers

import com.paularolim.grocerylist.api.features.user.main.factories.validations.makeUserLoginValidation
import com.paularolim.grocerylist.api.features.user.presentation.controllers.UserLoginController

fun makeUserLoginController(): UserLoginController {
    return UserLoginController(makeUserLoginValidation())
}