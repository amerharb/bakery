package com.amerharb.bakery

import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.InputOrder

object InputOrderFactory {
    fun fromText(bakeryProducts: BakeryProducts, text: String): InputOrder {
        val list = ArrayList<InputOrder.InputLine>()
        for (line in text.lines()) {
            val qtyText = line.split(" ")[0]
            val qty: Int = qtyText.toInt()
            val codeText = line.split(" ")[1]
            val item = bakeryProducts.getItem(codeText)
            list.add(InputOrder.InputLine(qty, item))
        }
        return InputOrder(list)
    }
}