package com.myRetail


import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.GenericEntity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response as res

import com.myRetail.model.Price
import com.myRetail.model.Product

/**
 * Created by ipseeta on 6/20/17.
 */
@Path("/products")
class KotlinMyRetailService {

    private var dao = MyRetailDAO()

    @GET
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun findAll(): res {
        val product = dao.createDummyProducts()
        val entity = object : GenericEntity<List<Product>>(product) {

        }
        return res.ok(entity).build()
    }

    /**
     * Get product details by product id
     * @param id request param
     * @return product with 200 http status
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun findById(@PathParam("id") id: String): res {
        if (Integer.parseInt(id) < 0) {
            return res.noContent().build()
        }
        val product = dao.findById(Integer.parseInt(id))
        return res.status(200).entity(product).build()
    }

    /**
     * It is a dummy method called only once to create price data in database
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun createPriceData(price: Price): res {
        val success = dao.createPriceData(price)
        return res.status(201).entity(success).build()
    }

    /**
     * Find product name by its id
     * @param id
     * @return name of the product
     */
    @GET
    @Path("name/{id}")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun findNameById(@PathParam("id") id: String): res {
        val name = dao.findNameById(Integer.parseInt(id))
        return res.status(200).entity(name).build()
    }

    /**
     * To update product price we call this update method
     * @param id
     * @param product
     * @return
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun updatePrice(@PathParam("id") id: String, product: Product): res {
        if (null == product.current_price) return res.status(400).entity("Please provide the product price").build()
        val updatedProduct = dao.update(Integer.parseInt(id), product)
        return res.status(200).entity(updatedProduct).build()
    }

    /**
     * Delete a price entry from mongodb
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    fun delete(@PathParam("id") id: String): res {
        val response = dao.delete(Integer.parseInt(id))
        return res.status(202).entity(response).build()
    }

}
