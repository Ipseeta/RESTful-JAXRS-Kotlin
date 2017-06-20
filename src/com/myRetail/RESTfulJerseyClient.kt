package com.myRetail

import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.api.client.WebResource
import com.myRetail.model.Price
import com.myRetail.model.Product
import com.sun.jersey.api.client.Client
import javax.ws.rs.core.MediaType


/**
 * Created by ipseeta on 6/21/17.
 */
object RESTfulJerseyClient {

    private val webServiceURI = "http://localhost:8080/myRetailKotlin/myRetailKotlin/products/"

    @JvmStatic fun main(args: Array<String>) {
        val client = Client.create()
        val webResource = client.resource(webServiceURI)
        createDummyPriceTest(webResource)
        getTest(webResource)
        getByIdTest(webResource)
        getNameByIdTest(webResource)
        updatePriceTest(webResource)
        deleteTest(webResource)

    }

    private fun getTest(webResource: WebResource) {
        val response = webResource.get(ClientResponse::class.java)
        if (response.status != 200) {
            throw RuntimeException("Failed : HTTP error code : " + response.status)
        }
        val output = response.getEntity(String::class.java)
        println(output)

    }

    private fun createDummyPriceTest(webResource: WebResource) {
        val p = Price()
        p._id = 16483589
        p.currency_code = "USD"
        p.value = 13.49
        //String input = "{\"currency_code\":\"USD\",\"_id\":\"16483589\",\"value\":\"13.49\"}";
        val response = webResource.path("create").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse::class.java, p)
        if (response.getStatus() != 201) {
            throw RuntimeException("Failed : HTTP error code : " + response.getStatus())
        }
        val output = response.getEntity(String::class.java)
        println(output)
    }

    private fun getByIdTest(webResource: WebResource) {
        val response = webResource.path("16483589").get(ClientResponse::class.java)
        /*if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}*/
        val output = response.getEntity(String::class.java)
        println(output)
    }

    private fun getNameByIdTest(webResource: WebResource) {
        val response = webResource.path("name").path("16483589").get(ClientResponse::class.java)
        if (response.status != 200) {
            throw RuntimeException("Failed : HTTP error code : " + response.status)
        }
        val output = response.getEntity(String::class.java)
        println(output)
    }

    private fun updatePriceTest(webResource: WebResource) {
        val product = Product()
        product.id = 16483589
        product.name = "Dazed and Confused"
        val price = Price()
        price._id = 16483589
        price.currency_code = "INR"
        price.value = 300.67
        product.current_price = price
        val response = webResource.path("16483589").accept(MediaType.APPLICATION_JSON).put(ClientResponse::class.java, product)
        if (response.getStatus() != 200) {
            throw RuntimeException("Failed : HTTP error code : " + response.getStatus())
        }
        val output = response.getEntity(String::class.java)
        println(output)
    }

    private fun deleteTest(webResource: WebResource) {

        val response = webResource.path("16483589").delete(ClientResponse::class.java)
        if (response.status != 202) {
            throw RuntimeException("Failed : HTTP error code : " + response.status)
        }
        val output = response.getEntity(String::class.java)
        println(output)
    }
}
