package com.amerharb.bakery.exceptions

import com.amerharb.bakery.model.Order
import com.amerharb.bakery.model.Pack

class BakeryProductNotFoundException(message: String) : Exception(message)

class BakeryInvalidPacksSizesException(message: String, val productPacks: List<Pack>, val orderLine: Order.Line) : Exception(message)

class BakeryInvalidOrderLineException(message: String, val orderLine: String) : Exception(message)