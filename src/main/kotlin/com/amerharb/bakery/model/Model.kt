package com.amerharb.bakery.model

import com.amerharb.bakery.exceptions.BakeryProductNotFoundException
import java.math.BigDecimal
import java.math.RoundingMode

const val CURRENCY_SYMBOL = "$"

data class Item(val name: String, val code: String)
data class Pack(val qty: Int, val price: BigDecimal)

data class BakeryProducts(val products: List<Product>) {
    fun getItem(codeText: String): Item {
        try {
            return products.first { it.item.code == codeText }.item
        } catch (e: NoSuchElementException) {
            throw BakeryProductNotFoundException ("Product not found for product code [$codeText]")
        }
    }

    data class Product(val item: Item, val packs: List<Pack>)
}

data class Order(val lines: List<Line>) {
    data class Line(val qty: Int, val item: Item)
}

data class Shipment(val lines: List<Line>) {
    val value: BigDecimal
    get() {
        var bd = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
        lines.forEach { bd = bd.add(it.value) }
        return bd
    }

    data class Line(val orderLine: Order.Line, val qtyPacks: List<QtyPack>) {
        val value: BigDecimal
            get() {
                var bd = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                qtyPacks.forEach { bd = bd.add(it.pack.price.multiply(it.qty)) }
                return bd
            }

        data class QtyPack(var qty: Int, val pack: Pack)
    }
}

private fun BigDecimal.multiply(i: Int): BigDecimal {
    return this.multiply(BigDecimal(i)).setScale(2, RoundingMode.HALF_UP)
}
