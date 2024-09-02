package com.paularolim.grocerylist.api.features.user.main.factories.validations

import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.common.validation.validators.RequiredFieldValidation
import com.paularolim.grocerylist.api.common.validation.validators.ValidationComposite

fun makeUserRegisterValidation(): ValidationComposite<Any> {
    val validations = mutableListOf<Validation<Any>>()

    val requiredFields = listOf("name", "email")
    for (field in requiredFields) {
        validations.add(RequiredFieldValidation(field))
    }

    return ValidationComposite(validations)
}