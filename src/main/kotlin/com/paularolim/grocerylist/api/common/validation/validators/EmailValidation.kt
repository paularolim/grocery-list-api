package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import kotlin.reflect.full.memberProperties

class EmailValidation<T : Any>(
    private val fieldName: String
) : Validation<T> {
    private val emailRegex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")

    override fun validate(input: T): Exception? {
        val property = input::class.memberProperties.find { it.name == fieldName }
        if (property != null) {
            val value = property.call(input)
            if (value != null && value is String && !value.matches(emailRegex)) {
                return InvalidParamException(fieldName)
            }
        }
        return null
    }
}