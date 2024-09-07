package com.paularolim.grocerylist.api.common.data.protocols.cryptography

interface HashComparer {
    fun compare(plainText: String, digest: String): Boolean
}