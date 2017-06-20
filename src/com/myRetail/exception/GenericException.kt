package com.myRetail.exception

/**
 * Created by ipseeta on 6/20/17.
 */
class GenericException(exceptionMsg: String) : RuntimeException(exceptionMsg) {
    companion object {
        private val serialVersionUID = 1L
    }

}