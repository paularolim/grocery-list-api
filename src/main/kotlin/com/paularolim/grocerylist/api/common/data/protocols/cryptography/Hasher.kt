package com.paularolim.grocerylist.api.common.data.protocols.cryptography

interface Hasher {
    fun hash(plainText: String): String
}
