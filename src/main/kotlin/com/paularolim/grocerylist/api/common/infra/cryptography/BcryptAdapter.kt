package com.paularolim.grocerylist.api.common.infra.cryptography

import at.favre.lib.crypto.bcrypt.BCrypt
import com.paularolim.grocerylist.api.common.data.protocols.cryptography.Hasher

class BcryptAdapter(
    private val salt: Int
) : Hasher {
    override fun hash(plainText: String): String {
        return BCrypt.withDefaults().hashToString(salt, plainText.toCharArray())
    }
}