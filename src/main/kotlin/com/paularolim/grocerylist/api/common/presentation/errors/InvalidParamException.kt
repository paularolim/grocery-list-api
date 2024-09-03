package com.paularolim.grocerylist.api.common.presentation.errors

data class InvalidParamException(private val fieldName: String) : Exception("Invalid param: $fieldName")