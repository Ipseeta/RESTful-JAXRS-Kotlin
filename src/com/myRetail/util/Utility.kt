package com.myRetail.util

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.myRetail.exception.GenericException
import java.util.*

/**
 * Created by ipseeta on 6/20/17.
 */
object Utility {

    fun dbConnect(): MongoClient {
        val bundle = ResourceBundle.getBundle("retail")
        var mongoClient: MongoClient? = null
        try {
            mongoClient = MongoClient(MongoClientURI(bundle.getString("mongo.url")))
        } catch (e: Exception) {
            throw GenericException(bundle.getString("connectionFailure"))
        }

        return mongoClient
    }

    fun closeDB(mongoClient: MongoClient) = mongoClient.close()

}