package com.myRetail.model

import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

/**
 * Created by ipseeta on 6/20/17.
 */
@XmlRootElement
class Product : Serializable {
    var id: Int = 0
    var name: String? = null
    var current_price: Price? = null

    companion object {

        private const val serialVersionUID = 1L
    }

}