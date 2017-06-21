package com.myRetail

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor
import com.mongodb.DBObject
import com.myRetail.exception.GenericException
import com.myRetail.exception.ProductDeletionException
import com.myRetail.exception.ProductSearchException
import com.myRetail.model.Price
import com.myRetail.model.Product
import com.myRetail.util.Utility
import java.util.*

/**
 * Created by ipseeta on 6/20/17.
 */
class MyRetailDAO {
    internal var bundle = ResourceBundle.getBundle("retail")

    fun findById(id: Int): Product? {
        val found = false
        createDummyProducts()
                .filter { id == it.id }
                .forEach { return it }
        if (!found) throw ProductSearchException(bundle.getString("noproduct") + id)
        return null
    }

    fun createDummyProducts(): List<Product> {
        val mongoClient = Utility.dbConnect()
        val db = mongoClient.getDB(DB)
        val collection = db.getCollection(COLLECTION)
        val list = ArrayList<Product>()

        var product = Product()
        product.id = 15117729
        product.name = "The Big Lebowski (Blu-ray) (Widescreen)"
        var newProductCursor = collection.find(BasicDBObject().append("_id", product.id))
        var price = getPrice(newProductCursor)
        product.current_price = price
        list.add(product)

        product = Product()
        product.id = 16483589
        product.name = "Dazed and Confused"
        newProductCursor = collection.find(BasicDBObject().append("_id", product.id))
        price = getPrice(newProductCursor)
        product.current_price = price
        list.add(product)

        product = Product()
        product.id = 16696652
        product.name = "Friday"
        newProductCursor = collection.find(BasicDBObject().append("_id", product.id))
        price = getPrice(newProductCursor)
        product.current_price = price
        list.add(product)

        product = Product()
        product.id = 15643793
        product.name = "Half Baked"
        newProductCursor = collection.find(BasicDBObject().append("_id", product.id))
        price = getPrice(newProductCursor)
        product.current_price = price
        list.add(product)

        product = Product()
        product.id = 13860428
        product.name = "Pineapple Express"
        newProductCursor = collection.find(BasicDBObject().append("_id", product.id))
        price = getPrice(newProductCursor)
        product.current_price = price
        list.add(product)

        Utility.closeDB(mongoClient)

        return list

    }

    private fun getPrice(cursor: DBCursor): Price {
        val result = ArrayList<Price>()
        while (cursor.hasNext()) {
            val product = Price()
            var obj: DBObject = BasicDBObject()
            obj = cursor.next()
            product._id = if (obj.get("_id") == null) 0 else Integer.parseInt(obj.get("_id").toString())
            product.value = if (obj.get("value") == null) 0.0 else java.lang.Double.parseDouble(obj.get("value").toString())
            product.currency_code = if (obj.get("currency_code") == null) "" else obj.get("currency_code").toString()
            result.add(product)
        }
        return result[0]
    }

    fun createPriceData(p: Price): String {
        val mongoClient = Utility.dbConnect()
        val db = mongoClient.getDB(DB)
        val collection = db.getCollection(COLLECTION)
        val document = BasicDBObject()

        document.put("_id", p._id)
        document.put("value", p.value)
        document.put("currency_code", p.currency_code)
        collection.insert(document)

        Utility.closeDB(mongoClient)
        return "Created"
    }

    fun findNameById(id: Int): String? {
        val found = false
        createDummyProducts()
                .filter { id == it.id }
                .forEach { return it.name }
        if (!found) throw GenericException(bundle.getString("generalMessage"))
        return ""
    }

    fun update(id: Int, product: Product): Product {
        val mongoClient = Utility.dbConnect()
        val db = mongoClient.getDB(DB)
        val collection = db.getCollection(COLLECTION)
        val updateDocument = BasicDBObject()
        if (id == product.id && null != product.current_price && 0.0 != product.current_price!!.value && "" !== product.current_price!!.currency_code) {
            var price: Price = product.current_price!!
            updateDocument.put("value", price.value)
            updateDocument.put("currency_code", price.currency_code)
            val searchQuery = BasicDBObject().append("_id", product.id)
            collection.update(searchQuery, updateDocument)
            val newProductCursor = collection.find(BasicDBObject().append("_id", product.id))
            price = getPrice(newProductCursor)
            product.current_price = price
        } else throw GenericException(bundle.getString("generalMessage"))
        Utility.closeDB(mongoClient)
        return product
    }

    fun delete(id: Int): String {
        val mongoClient = Utility.dbConnect()
        val db = mongoClient.getDB(DB)
        val whereQuery = BasicDBObject()
                .append("_id", id)
        val dbObj = db.getCollection(COLLECTION).findAndRemove(whereQuery) ?: throw ProductDeletionException("No such product exists")
        Utility.closeDB(mongoClient)
        return "Deleted Successfully"
    }

    companion object {
        private val DB = "myRetail"
        private val COLLECTION = "price"
    }

}
