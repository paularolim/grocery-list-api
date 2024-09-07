package com.paularolim.grocerylist.api.common.infra.database.mongodb.repositories

import com.paularolim.grocerylist.api.common.infra.database.mongodb.connection.MongodbConnection
import com.paularolim.grocerylist.api.features.user.data.protocols.UserByEmailRepository
import com.paularolim.grocerylist.api.features.user.data.protocols.UserRegisterRepository
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Projections
import com.paularolim.grocerylist.api.common.infra.database.mongodb.models.User
import com.paularolim.grocerylist.api.common.infra.database.mongodb.models.UserRegister
import com.paularolim.grocerylist.api.common.infra.database.mongodb.models.UserResult
import kotlinx.coroutines.flow.firstOrNull



class UserMongodbRepository : UserRegisterRepository, UserByEmailRepository {
    override suspend fun register(data: UserRegisterUsecase.RegisterParams): Boolean {
        val collection = MongodbConnection.getCollection<UserRegister>("users")
        val user = UserRegister(data.name, data.email, data.password)
        val result = collection.insertOne(user)
        MongodbConnection.close()
        return result.insertedId != null
    }

    override suspend fun getByEmail(email: String): UserByEmailRepository.Result? {
        val collection = MongodbConnection.getCollection<User>("users")
        val projectionFields= Projections.fields(
            Projections.include(User::name.name, User::email.name),
        )
        val resultsFlow = collection.withDocumentClass<UserResult>()
            .find(eq(User::email.name, email))
            .projection(projectionFields)
            .firstOrNull()
        MongodbConnection.close()
        return if (resultsFlow == null) {
            null
        } else {
            UserByEmailRepository.Result(
                id = resultsFlow.id.toString(),
                name = resultsFlow.name,
                email = resultsFlow.email
            )
        }
    }
}