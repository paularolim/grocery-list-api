package com.paularolim.grocerylist.api.features.user.main.factories.validations

import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.common.validation.validators.EmailValidation
import com.paularolim.grocerylist.api.common.validation.validators.RequiredFieldValidation
import com.paularolim.grocerylist.api.common.validation.validators.ValidationComposite

fun makeUserLoginValidation(): ValidationComposite<Any> {
    val validations = mutableListOf<Validation<Any>>()

    val requiredFields = listOf("email", "password")
    for (field in requiredFields) {
        validations.add(RequiredFieldValidation(field))
    }

    validations.add(EmailValidation("email"))

    return ValidationComposite(validations)
}