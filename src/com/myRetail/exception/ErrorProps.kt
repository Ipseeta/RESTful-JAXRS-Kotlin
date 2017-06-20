package com.myRetail.exception

import javax.xml.bind.annotation.XmlRootElement

/**
 * Created by ipseeta on 6/20/17.
 */
@XmlRootElement
class ErrorProps {

    var status: String? = null
    var errorMessage: String? = null

    constructor() {}

    constructor(statusFromOutside: String, errorMessageFromOutside: String?) {
        this.status = statusFromOutside
        this.errorMessage = errorMessageFromOutside
    }

}
