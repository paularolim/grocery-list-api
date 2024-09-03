package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import kotlin.reflect.full.memberProperties

class CompareFieldsValidation<T : Any>(
    private val fieldName: String,
    private val fieldToCompare: String
) : Validation<T> {
    override fun validate(input: T): Exception? {
        val property1 = input::class.memberProperties.find { it.name == fieldName }
        val property2 = input::class.memberProperties.find { it.name == fieldToCompare }
        if (property1 != null && property2 != null) {
            val value1 = property1.call(input)
            val value2 = property2.call(input)
            val value1IsValid = value1 != null && value1 is String
            val value2IsValid = value2 != null && value2 is String
            if (value1IsValid && value2IsValid && value1 != value2) {
                return InvalidParamException(fieldToCompare)
            }
        }
        return null
    }
}