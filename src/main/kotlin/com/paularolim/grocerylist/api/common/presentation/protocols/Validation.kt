package com.paularolim.grocerylist.api.common.presentation.protocols

interface Validation<T> {
    fun validate(input: T): Exception?
}