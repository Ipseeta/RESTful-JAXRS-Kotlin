package com.myRetail.model

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Price : java.io.Serializable {
    var _id: Int = 0
    var value: Double = 0.0
    var currency_code: String? = null
    override fun toString(): String = "Price [value=$value, currency_code=$currency_code]"

    companion object {

        private const val serialVersionUID = 1L
    }

}

