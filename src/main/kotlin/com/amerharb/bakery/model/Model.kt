package com.amerharb.bakery.model

import java.math.BigDecimal

val CURRENCY_SYMBOL = "$"

data class Item(val name: String, val code: String)
data class Pack(val qty: Int, val price: BigDecimal)

data class BakeryProducts(val products: List<Product>) {
    fun getItem(codeText: String): Item {
        return products.first { it.item.code == codeText }.item
    }

    data class Product(val item: Item, val packs: List<Pack>)
}

data class InputOrder(val inputList: List<InputLine>) {
    data class InputLine(val qty: Int, val item: Item)
}
