package com.paularolim.grocerylist.api.common.infra.database.mongodb.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserRegister(
    val name: String,
    val email: String,
    val password: String,
)

data class User(
    @BsonId
    val id: ObjectId,
    val name: String,
    val email: String,
    val password: String
)

data class UserResult(
    @BsonId
    val id: ObjectId,
    val name: String,
    val email: String
)
