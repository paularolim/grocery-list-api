package com.paularolim.grocerylist.api.features.user.main.factories.validations

import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.common.validation.validators.*

fun makeUserRegisterValidation(): ValidationComposite<Any> {
    val validations = mutableListOf<Validation<Any>>()

    val requiredFields = listOf("name", "email", "password", "passwordConfirmation")
    for (field in requiredFields) {
        validations.add(RequiredFieldValidation(field))
    }

    validations.add(EmailValidation("email"))
    validations.add(CompareFieldsValidation("password", "passwordConfirmation"))
    validations.add(MinLengthValidation("password", 8))

    return ValidationComposite(validations)
}