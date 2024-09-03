package com.paularolim.grocerylist.api.common.infra.database.mongodb.repositories

import com.paularolim.grocerylist.api.common.infra.database.mongodb.connection.MongodbConnection
import com.paularolim.grocerylist.api.common.infra.database.mongodb.models.User
import com.paularolim.grocerylist.api.features.user.data.protocols.UserRegisterRepository
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase

class UserMongodbRepository : UserRegisterRepository {
    override suspend fun register(data: UserRegisterUsecase.RegisterParams): Boolean {
        val collection = MongodbConnection.getCollection<User>("users")
        val user = User(data.name, data.email, data.password, data.passwordConfirmation)
        val result = collection.insertOne(user)
        return result.insertedId != null
    }
}