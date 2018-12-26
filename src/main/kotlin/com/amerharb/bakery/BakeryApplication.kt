package com.amerharb.bakery

import com.amerharb.bakery.factory.BakeryProductsFactory
import com.amerharb.bakery.factory.OrderFactory
import com.amerharb.bakery.factory.ShipmentFactory
import java.io.File

object BakeryApplication {
    @JvmStatic
    fun main(arg: Array<String>) {
        //Arg are:
        //1. order file
        //2. shipment filename for output, in case missing it will output to console anyway

        println(getAsciiArt())
        println("Version: 0.4.1")
        println()

        println("Bakery Products is hard coded")
        val products = BakeryProductsFactory.getHardCoded()
        println("BakeryProducts Object is:")
        println(products)
        println()

        val inputText = if (arg.size >= 1) {
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

        if (arg.size >= 2) {
            try {
                File(arg[1]).writeText(shipmentText)
                println("Shipment as text file written to [${arg[1]}]")
            } catch (e: Exception) {
                println("error during writing shipment to the output file")
                println(e.message)
            }
        }
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

}