package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.protocols.Validation

class ValidationComposite<T>(
    private val validations: List<Validation<T>>
) : Validation<T> {
    override fun validate(input: T): Exception? {
        for (validation in validations) {
            val error = validation.validate(input)
            if (error != null) {
                return error
            }
        }

        return null
    }
}