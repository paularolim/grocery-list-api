package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import kotlin.reflect.full.memberProperties

class RequiredFieldValidation<T : Any>(
    private val fieldName: String
) : Validation<T> {
    override fun validate(input: T): Exception? {
        val property = input::class.memberProperties.find { it.name == fieldName }
        return if (property != null) {
            val value = property.call(input)
            if (value == null || (value is String && value.isBlank())) {
                MissingParamException(fieldName)
            } else {
                null
            }
        } else {
            return MissingParamException(fieldName)
        }
    }
}