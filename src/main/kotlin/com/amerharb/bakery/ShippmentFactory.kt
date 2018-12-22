package com.amerharb.bakery

import com.amerharb.bakery.model.BakeryProducts
import com.amerharb.bakery.model.CURRENCY_SYMBOL
import com.amerharb.bakery.model.InputOrder
import com.amerharb.bakery.model.Shipment
import java.lang.StringBuilder

object ShippmentFactory {

    fun getShipment(bakeryProducts: BakeryProducts, inputOrder: InputOrder): Shipment {
        val list = ArrayList<Shipment.Line>()
        for (inputLine in inputOrder.inputList) {
            val packsSortedByQty = bakeryProducts.products.first { it.item == inputLine.item }.packs.sortedByDescending { it.qty }
            var rem = inputLine.qty
            val qpList = ArrayList<Shipment.Line.QtyPack>()
            for (p in packsSortedByQty) { // from bigger to lesser
                if (rem > p.qty) {
                    val q = rem / p.qty
                    rem -= (q * p.qty)
                    val qp = Shipment.Line.QtyPack(q, p)
                    qpList.add(qp)
                }
            }
            if (rem != 0) throw Exception("invalid qty, qty the is required in order is not dividable to packs")
            list.add(Shipment.Line(inputLine, qpList))
        }
        return Shipment(list)
    }

    fun getShipmentText(shipment: Shipment): String {
        val sb = StringBuilder()
        for (line in shipment.outputList) {
            sb.appendln("${line.inputLine.qty} ${line.inputLine.item.code} $CURRENCY_SYMBOL${line.value}")
            for (qp in line.qtyPacks) {
                sb.appendln("\t${qp.qty} x ${qp.pack.qty} $CURRENCY_SYMBOL ${qp.pack.price}")
            }
        }
        return sb.toString()
    }
}