package com.myRetail.exception

/**
 * Created by ipseeta on 6/20/17.
 */
class ProductDeletionException(message: String?) : RuntimeException(message) {
    override val message: String?
        get() = super.message
}