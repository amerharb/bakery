package com.amerharb.bakery.model

import java.math.BigDecimal
import java.math.RoundingMode

val CURRENCY_SYMBOL = "$"

data class Item(val name: String, val code: String)
data class Pack(val qty: Int, val price: BigDecimal)

data class BakeryProducts(val products: List<Product>) {
    fun getItem(codeText: String): Item {
        //TODO: catch exception: java.util.NoSuchElementException in case the codeText not found
        return products.first { it.item.code == codeText }.item
    }

    data class Product(val item: Item, val packs: List<Pack>)
}

data class InputOrder(val inputList: List<InputLine>) {
    data class InputLine(val qty: Int, val item: Item)
}

data class Shipment(val outputList: List<Line>) {
    data class Line(val qtyPacks: List<QtyPack>) {
        val value: BigDecimal
            get() {
                var bd = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                qtyPacks.forEach { bd = bd.add(it.pack.price.multiply(it.qty))}
                return bd
            }
        data class QtyPack(val qty: Int, val pack: Pack)
    }
}

private fun BigDecimal.multiply(i: Int): BigDecimal {
    return this.multiply(BigDecimal(i)).setScale(2, RoundingMode.HALF_UP)
}
