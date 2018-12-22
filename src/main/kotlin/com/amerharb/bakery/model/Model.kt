package com.amerharb.bakery.model

data class Item(val name: String, val code: String)
data class Pack(val qty: Int, val price: Float)

data class BakeryProducts(val products: List<Product>) {
    data class Product(val item: Item, val packs: List<Pack>)
}

data class InputOrder(val inputList: List<InputLine>) {
    data class InputLine(val qty: Int, val item: Item)
}
