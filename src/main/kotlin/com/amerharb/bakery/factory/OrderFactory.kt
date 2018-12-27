package com.amerharb.bakery.factory

import com.amerharb.bakery.exceptions.BakeryInvalidOrderLineException
import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.Order

object OrderFactory {
    fun fromText(bakeryProducts: BakeryProducts, text: String): Order {
        val list = ArrayList<Order.Line>()
        text.lines().forEach {
            if (it.isNotBlank()) {
                val lineSplit = it.split(" ")
                if (lineSplit.isValidLine) {
                    val qty = lineSplit[0].toInt()
                    val codeText = lineSplit[1]
                    val item = bakeryProducts.getItem(codeText)
                    list.add(Order.Line(qty, item))
                } else{
                     throw BakeryInvalidOrderLineException("invalid order Line [$it] ", lineSplit.toString())
                }
            }
        }
        return Order(list)
    }

    private val List<String>.isValidLine: Boolean
        get() {
            if (this.size != 2) return false
            if (this[0].isBlank()) return false
            if (this[1].isBlank()) return false
            if (this[0].asSequence().any { !it.isDigit() }) return false
            return true
        }

}
