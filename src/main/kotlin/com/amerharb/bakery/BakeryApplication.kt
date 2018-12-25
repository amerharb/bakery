package com.amerharb.bakery

import com.amerharb.bakery.factory.BakeryProductsFactory
import com.amerharb.bakery.factory.OrderFactory
import com.amerharb.bakery.factory.ShipmentFactory
import java.io.File

object BakeryApplication

fun main(arg: Array<String>) {
    //Arg are:
    //1. order file
    //2. shippment filename for output, in case missing it will output to console

    println(getAsciiArt())
    println("Version: 0.3-SNAPSHOT")
    println()

    println("Bakery Products is hard coded in the app")
    val products = BakeryProductsFactory.getHardCoded()
    println("BakeryProducts Object is:")
    println(products)
    println()

    val inputText = if (arg.size > 0) {
        File(arg[0]).readText(Charsets.UTF_8)
    } else {
        println("Bakery Order read from Resource file [Input]")
        BakeryApplication::class.java.getResource("Input").readText()
    }
    println("the input text is:")
    println(inputText)
    val inputOrder = OrderFactory.fromText(products, inputText)
    println("the input object:")
    println(inputOrder)
    println()

    println("calculating Shipment Object ...")
    println("Shipment object is:")
    val shipment = ShipmentFactory.getShipment(products, inputOrder)
    println(shipment)
    println()

    println("Shipment text:")
    val shipmentText = ShipmentFactory.getShipmentText(shipment)
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