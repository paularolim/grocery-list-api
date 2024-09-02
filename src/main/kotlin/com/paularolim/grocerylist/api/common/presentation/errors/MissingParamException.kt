package com.paularolim.grocerylist.api.common.presentation.errors

class MissingParamException(fieldName: String) : Exception("Missing param: $fieldName")