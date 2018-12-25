package com.amerharb.bakery.exceptions

class BakeryProductNotFoundException(message: String) : Exception(message)

class BakeryInvalidPacksSizesException(message: String) : Exception(message)