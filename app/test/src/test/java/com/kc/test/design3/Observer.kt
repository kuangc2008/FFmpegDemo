package com.kc.test.design3

import kotlin.properties.Delegates


interface StockUpdateListener {
    fun onRise(price : Int)
    fun onFall(price : Int)
}

class StockDisplay : StockUpdateListener {
    override fun onRise(price: Int) {
    }

    override fun onFall(price: Int) {
    }
}

class StockUpdate {
//    var listeners = mutableListOf<StockUpdateListener>()
    val listeners by lazy {
        mutableListOf<StockUpdateListener>()
    }

    var price : Int by Delegates.observable(0) { _ , old, new ->
        listeners.forEach {
            if (new > old) {
                it.onRise(price)
            } else {
                it.onFall(price)
            }
        }
    }

    var price2 : Int = 0
        set(value) {
           if (value > field) {
               listeners.forEach {
                   it.onRise(price)
               }
           }
        }
}

fun main() {
    val stockUpdate = StockUpdate()
    val stockDisplay = StockDisplay()
    stockUpdate.listeners.add(stockDisplay)
    stockUpdate.price2 = 3
}