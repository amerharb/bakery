package com.amerharb.bakery

import com.amerharb.bakery.factory.BakeryProductsFactory
import com.amerharb.bakery.factory.OrderFactory
import com.amerharb.bakery.factory.ShippmentFactory

object BakeryApplication

fun main() {
    println(getAsciiArt())
    println("Version: 0.3-SNAPSHOT")
    println()

    println("Bakery Products is hard coded in the app")
    val products = BakeryProductsFactory.getHardCoded()
    println("BakeryProducts Object is:")
    println(products)
    println()

    println("Bakery Order read from Resource file [Input]")
    val inputText = BakeryApplication::class.java.getResource("Input").readText()
    println("the input text is:")
    println(inputText)
    val inputOrder = OrderFactory.fromText(products, inputText)
    println("the input object:")
    println(inputOrder)
    println()

    println("calculating Shipment Object ...")
    println("shipment object is:")
    val shipment = ShippmentFactory.shipment(products, inputOrder)
    println(shipment)
    println()

    println("shipment text:")
    val shipmentText= ShippmentFactory.getShipmentText(shipment)
    println(shipmentText)
}

fun getAsciiArt() = """
______         _
| ___ \       | |
| |_/ /  __ _ | | __  ___  _ __  _   _
| ___ \ / _` || |/ / / _ \| '__|| | | |
| |_/ /| (_| ||   < |  __/| |   | |_| |
\____/  \__,_||_|\_\ \___||_|    \__, |
                                  __/ |
                                 |___/
---------------------------------------
""".trimIndent()