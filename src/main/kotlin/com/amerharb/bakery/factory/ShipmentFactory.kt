package com.amerharb.bakery.factory

import com.amerharb.bakery.exceptions.BakeryInvalidPacksSizesException
import com.amerharb.bakery.model.*

object ShipmentFactory {

    fun getShipment(bakeryProducts: BakeryProducts, order: Order): Shipment {
        val shipmentLineList = ArrayList<Shipment.Line>()
        for (line in order.lines) {
            val packsSorted = bakeryProducts.products.first { it.item == line.item }.packs.sortedByDescending { it.qty }
                shipmentLineList.add(getProductPacks(packsSorted, line))
        }
        return Shipment(shipmentLineList)
    }

    private fun getProductPacks(packs: List<Pack>, orderLine: Order.Line): Shipment.Line {
        data class PackRemain(var total: Int = 0) {
            val qtyList = ArrayList<Int>()
        }

        val packsRemainResult = ArrayList<PackRemain>()

        fun findChangeRemain(packRemain: PackRemain) {
            for (p in packs) {
                if (packRemain.total > p.qty) {
                    val total = packRemain.total - p.qty
                    val newPackRemain = PackRemain(total)
                    newPackRemain.qtyList.addAll(packRemain.qtyList)
                    newPackRemain.qtyList.add(p.qty)
                    findChangeRemain(newPackRemain)
                } else if (packRemain.total == p.qty) {
                    packRemain.total = 0
                    packRemain.qtyList.add(p.qty)
                    packsRemainResult.add(packRemain)
                }
            }
        }
        findChangeRemain(PackRemain(orderLine.qty))

        return if (packsRemainResult.isNotEmpty()) {
            val qpList = ArrayList<Shipment.Line.QtyPack>()
            for (qq in packsRemainResult.minBy { it.qtyList.size }!!.qtyList){
                val alreadyExistQtyPack = qpList.firstOrNull { it.pack.qty == qq }
                if (alreadyExistQtyPack != null){
                    alreadyExistQtyPack.qty++
                } else {
                    qpList.add(Shipment.Line.QtyPack(1, packs.first { it.qty == qq }))
                }
            }
            Shipment.Line(orderLine, qpList)
        } else {
            throw BakeryInvalidPacksSizesException("Invliad Packs sizes for this order: \n packs: $packs\n" +
                    "  input order: $orderLine ", orderLine = orderLine, productPacks = packs)
        }
    }

    fun getShipmentText(shipment: Shipment): String {
        val sb = StringBuilder()
        for (line in shipment.lines) {
            sb.appendln("${line.orderLine.qty} ${line.orderLine.item.code} $CURRENCY_SYMBOL${line.value}")
            for (qp in line.qtyPacks) {
                sb.appendln("\t${qp.qty} x ${qp.pack.qty} $CURRENCY_SYMBOL ${qp.pack.price}")
            }
        }
        sb.appendln()
        sb.appendln("Shipment total value : $CURRENCY_SYMBOL ${shipment.value}")
        return sb.toString()
    }
}