package com.amerharb.bakery

import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.Item
import com.amerharb.bakery.model.Pack
import java.math.BigDecimal

object BakeryProductsFactory {
    fun getHardCoded(): BakeryProducts {
        var tempPackList = ArrayList<Pack>()
        val list = ArrayList<BakeryProducts.Product>()

        tempPackList.add(Pack(3, BigDecimal(6.99)))
        tempPackList.add(Pack(5, BigDecimal(8.99)))
        list.add(BakeryProducts.Product(Item("Vegemite Scroll", "VS5"), tempPackList))

        tempPackList = ArrayList<Pack>()
        tempPackList.add(Pack(2, BigDecimal(9.95)))
        tempPackList.add(Pack(5, BigDecimal(16.95)))
        tempPackList.add(Pack(8, BigDecimal(24.95)))
        list.add(BakeryProducts.Product(Item("Blueberry Muffin", "MB11"), tempPackList))

        tempPackList = ArrayList<Pack>()
        tempPackList.add(Pack(3, BigDecimal(5.95)))
        tempPackList.add(Pack(5, BigDecimal(9.95)))
        tempPackList.add(Pack(9, BigDecimal(16.99)))
        list.add(BakeryProducts.Product(Item("Blueberry Muffin", "MB11"), tempPackList))

        return BakeryProducts(list)
    }
}