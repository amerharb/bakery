package com.amerharb.bakery

object BakeryApplication

fun main() {
    println("Hello World, Bakery Application")
    val products = BakeryProductsFactory.getHardCoded()
    println("BakeryProductsFactory is:")
    println(products)
    println()
    val inputText = BakeryApplication::class.java.getResource("Input").readText()
    println("the input text is:")
    println(inputText)
    val inputOrder = InputOrderFactory.fromText(products, inputText)
    println(inputOrder)
    println()
    println("shipment object:")
    val shipment = ShippmentFactory.shipment(products, inputOrder)
    println(shipment)
    println()
    println("shipment text:")
    val shipmentText= ShippmentFactory.getShipmentText(shipment)
    println(shipmentText)
}