package com.myRetail.exception

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/**
 * Created by ipseeta on 6/20/17.
 */
@Provider
class ProductSearchExceptionMapper : ExceptionMapper<ProductSearchException> {

    override fun toResponse(ex: ProductSearchException): Response = Response.status(Response.Status.NOT_FOUND)
            .entity(ErrorProps("404", ex.message))
            .build()
}