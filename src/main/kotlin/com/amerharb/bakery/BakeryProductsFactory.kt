package com.amerharb.bakery

import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.Item
import com.amerharb.bakery.model.Pack
import java.math.BigDecimal
import java.math.RoundingMode

object BakeryProductsFactory {
    fun getHardCoded(): BakeryProducts {
        var tempPackList: ArrayList<Pack>
        val list = ArrayList<BakeryProducts.Product>()

        tempPackList = ArrayList()
        tempPackList.add(Pack(3, BigDecimal(6.99).setScale(2, RoundingMode.HALF_UP)))
        tempPackList.add(Pack(5, BigDecimal(8.99).setScale(2, RoundingMode.HALF_UP)))
        list.add(BakeryProducts.Product(Item("Vegemite Scroll", "VS5"), tempPackList))

        tempPackList = ArrayList()
        tempPackList.add(Pack(2, BigDecimal(9.95).setScale(2, RoundingMode.HALF_UP)))
        tempPackList.add(Pack(5, BigDecimal(16.95).setScale(2, RoundingMode.HALF_UP)))
        tempPackList.add(Pack(8, BigDecimal(24.95).setScale(2, RoundingMode.HALF_UP)))
        list.add(BakeryProducts.Product(Item("Blueberry Muffin", "MB11"), tempPackList))

        tempPackList = ArrayList()
        tempPackList.add(Pack(3, BigDecimal(5.95).setScale(2, RoundingMode.HALF_UP)))
        tempPackList.add(Pack(5, BigDecimal(9.95).setScale(2, RoundingMode.HALF_UP)))
        tempPackList.add(Pack(9, BigDecimal(16.99).setScale(2, RoundingMode.HALF_UP)))
        list.add(BakeryProducts.Product(Item("Croissant", "CF"), tempPackList))

        return BakeryProducts(list)
    }
}