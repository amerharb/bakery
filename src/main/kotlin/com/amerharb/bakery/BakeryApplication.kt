package com.amerharb.bakery

import com.amerharb.bakery.exceptions.BakeryInvalidOrderLineException
import com.amerharb.bakery.exceptions.BakeryInvalidPacksSizesException
import com.amerharb.bakery.factory.BakeryProductsFactory
import com.amerharb.bakery.factory.BakeryProductsFactory.getProductText
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
        println("Version: 0.4.5-SNAPSHOT")
        println()

        println("Bakery products source :[hard coded]")

        val products = if (arg.size >= 3 && arg[2] != "_") {
            println("this feature is not supported yet")
            val productsText = File(arg[2]).readText(Charsets.UTF_8)
            TODO("products from file is not supported yet")
        } else {
            println("Bakery order read from resource file [Input]")
            BakeryProductsFactory.getHardCoded()
        }

//        println("BakeryProducts Object is:")
//        println(products)
//        println()
        println()
        println("producs are :")
        println(getProductText(products))

        try {
            val inputText = if (arg.size >= 1) {
                println("Bakery order reading from file [${arg[0]}]")
                File(arg[0]).readText(Charsets.UTF_8)
            } else {
                println("Bakery order read from resource file [Input]")
                BakeryApplication::class.java.getResource("Input").readText()
            }
            println()
            println("the input text is:")
            println(inputText)
            val inputOrder = OrderFactory.fromText(products, inputText)
//            println("the input object:")
//            println(inputOrder)
//            println()

            println()
            println("calculating Shipment ...")
            val shipment = ShipmentFactory.getShipment(products, inputOrder)
            println()
            println("Shipment text:")
            val shipmentText = ShipmentFactory.getShipmentText(shipment)
            println(shipmentText)

            if (arg.size >= 2 && arg[1] != "_") { //incase the 2nd argument is underscore then ignore it
                try {
                    File(arg[1]).writeText(shipmentText)
                    println("Shipment as text file written to [${arg[1]}]")
                } catch (e: Exception) {
                    println("error during writing shipment to the output file")
                    println(e.message)
                }
            }


        } catch (e: BakeryInvalidPacksSizesException) {
            println("ERROR: the order qty is invalid for this product packs sizes")
            println(e.message)
        } catch (e: BakeryInvalidOrderLineException) {
            println("ERROR: the order input line is invalid, each line should have only 2 value seperated by only single white space, start with integer value then product code")
            println(e.message)

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