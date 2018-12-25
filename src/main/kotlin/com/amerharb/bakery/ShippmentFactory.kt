package com.amerharb.bakery

import com.amerharb.bakery.model.*
import java.lang.StringBuilder

object ShippmentFactory {

    fun shipment(bakeryProducts: BakeryProducts, inputOrder: InputOrder): Shipment {
        val shipmentLineList = ArrayList<Shipment.Line>()
        for (inputLine in inputOrder.inputList) {
            val packsSorted = bakeryProducts.products.first { it.item == inputLine.item }.packs.sortedByDescending { it.qty }
            shipmentLineList.add(getProductPacks(packsSorted, inputLine))
        }
        return Shipment(shipmentLineList)
    }

    private fun getProductPacks(packs: List<Pack>, inputLine: InputOrder.Line): Shipment.Line {
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
        findChangeRemain(PackRemain(inputLine.qty))

        return if (packsRemainResult.isNotEmpty()) {
            val qpList = ArrayList<Shipment.Line.QtyPack>()
            for (qq in packsRemainResult.minBy { it.qtyList.size }!!.qtyList){
                val alreadyExsistQtyPack = qpList.firstOrNull { it.pack.qty == qq }
                if (alreadyExsistQtyPack != null){
                    alreadyExsistQtyPack.qty++
                } else {
                    qpList.add(Shipment.Line.QtyPack(1, packs.first { it.qty == qq }))
                }
            }
            Shipment.Line(inputLine, qpList)
        } else {
            throw Exception("Invliad Packs sized for this order: \n packs: $packs\n" +
                    "  input order: $inputLine ")
        }
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