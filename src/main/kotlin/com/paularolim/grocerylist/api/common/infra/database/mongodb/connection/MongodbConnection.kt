package com.paularolim.grocerylist.api.common.infra.database.mongodb.connection

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.paularolim.grocerylist.api.common.main.configuration.Environment

object MongodbConnection {
    private val CONNECTION_STRING = Environment.get("MONGODB_URI")
    private val DATABASE_STRING = Environment.get("MONGODB_DATABASE_NAME")

    private fun getMongoClient(): MongoClient {
        return MongoClient.create(CONNECTION_STRING)
    }

    fun getMongoDatabase(): MongoDatabase {
        return getMongoClient().getDatabase(DATABASE_STRING)
    }

    inline fun <reified T : Any> getCollection(collection: String): MongoCollection<T> {
        return getMongoDatabase().getCollection<T>(collection)
    }

    fun close() {
        getMongoClient().close()
    }
}