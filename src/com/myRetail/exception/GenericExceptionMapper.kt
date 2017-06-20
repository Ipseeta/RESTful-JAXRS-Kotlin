package com.myRetail.exception

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/**
 * Created by ipseeta on 6/20/17.
 */
@Provider
class GenericExceptionMapper : ExceptionMapper<Throwable> {

    override fun toResponse(ex: Throwable): Response {
        if (ex is ProductSearchException) {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorProps("404", ex.message))
                    .build()
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorProps("Some error code, 500 or somthing", ex.message))
                    .build()
        }
    }

}