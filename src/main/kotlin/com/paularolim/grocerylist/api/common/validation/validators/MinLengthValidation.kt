package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import kotlin.reflect.full.memberProperties

class MinLengthValidation<T : Any>(
    private val fieldName: String,
    private val minLength: Int
) : Validation<T> {
    override fun validate(input: T): Exception? {
        val property = input::class.memberProperties.find { it.name == fieldName }
        if (property != null) {
            val value = property.call(input)
            if (value == null || (value is String && value.length < minLength)) {
                return InvalidParamException(fieldName)
            }
        }
        return null
    }
}