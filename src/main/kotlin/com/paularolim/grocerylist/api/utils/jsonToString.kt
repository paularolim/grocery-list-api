package com.paularolim.grocerylist.api.utils

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

fun jsonToString(field: JsonElement?): String? {
    return field?.jsonPrimitive?.contentOrNull
}