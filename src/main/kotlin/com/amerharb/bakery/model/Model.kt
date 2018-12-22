package com.amerharb.bakery.model

class Item(val name: String, val code: String)
class Pack(qty: Int, price: Float)

class BakeryProducts(val products: List<Product>) {
    class Product(item: Item, val packs: List<Pack>)
}

class InputOrder(val inputList: List<InputLine>) {
    class InputLine(val qty: Int, val item: Item)
}
