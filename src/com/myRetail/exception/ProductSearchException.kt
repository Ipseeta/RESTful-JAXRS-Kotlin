package com.myRetail.exception

/**
 * Created by ipseeta on 6/20/17.
 */
class ProductSearchException(exceptionMsg: String) : RuntimeException(exceptionMsg) {
    companion object {
        private val serialVersionUID = 1L
    }
}