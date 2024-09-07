package com.paularolim.grocerylist.api.common.infra.cryptography

import at.favre.lib.crypto.bcrypt.BCrypt
import com.paularolim.grocerylist.api.common.data.protocols.cryptography.HashComparer
import com.paularolim.grocerylist.api.common.data.protocols.cryptography.Hasher

class BcryptAdapter(
    private val salt: Int
) : Hasher, HashComparer {
    override fun hash(plainText: String): String {
        return BCrypt.withDefaults().hashToString(salt, plainText.toCharArray())
    }

    override fun compare(plainText: String, digest: String): Boolean {
        return BCrypt.verifyer().verify(plainText.toCharArray(), digest.toCharArray()).verified
    }
}