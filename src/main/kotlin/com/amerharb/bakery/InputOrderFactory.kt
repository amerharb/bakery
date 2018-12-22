package com.amerharb.bakery

import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.InputOrder

object InputOrderFactory {
    fun fromText(bakeryProducts: BakeryProducts, text: String): InputOrder {
        val list = ArrayList<InputOrder.Line>()
        text.lines().forEach {
            val lineSplit = it.split(" ")
            val qty = lineSplit[0].toInt()
            val codeText = lineSplit[1]
            val item = bakeryProducts.getItem(codeText)
            list.add(InputOrder.Line(qty, item))
        }
        return InputOrder(list)
    }
}