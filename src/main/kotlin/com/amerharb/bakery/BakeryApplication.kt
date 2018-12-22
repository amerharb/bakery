package com.amerharb.bakery

object BakeryApplication

fun main() {
    println("Hello World, Bakery Application")
    val inputText = BakeryApplication::class.java.getResource("Input").readText()
    println(inputText)
}