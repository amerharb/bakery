package com.amerharb.bakery.factory

import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.Order

object InputOrderFactory {
    fun fromText(bakeryProducts: BakeryProducts, text: String): Order {
        val list = ArrayList<Order.Line>()
        text.lines().forEach {
            val lineSplit = it.split(" ")
            val qty = lineSplit[0].toInt()
            val codeText = lineSplit[1]
            val item = bakeryProducts.getItem(codeText)
            list.add(Order.Line(qty, item))
        }
        return Order(list)
    }
}