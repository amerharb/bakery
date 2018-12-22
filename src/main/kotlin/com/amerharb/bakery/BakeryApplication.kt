package com.amerharb.bakery

object BakeryApplication

fun main() {
    println("Hello World, Bakery Application")
    val inputText = BakeryApplication::class.java.getResource("Input").readText()
    val products = BakeryProductsFactory.getHardCoded()
    println("BakeryProductsFactory is:")
    println(products)
    println("------------------------------")
    println("the input is:")
    println(inputText)
}