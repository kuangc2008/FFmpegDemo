package com.kc.test

import org.junit.Test
import kotlin.reflect.KProperty

class Kotlin3 {

    var add : (Int, Int) -> Int = {a, b -> a + b}

    @Test
    fun test1() {
        val add1 = add(3, 4)
        print(add1)


        val button = Java1.Button2()
        var a = "333"
        button.setListener {
            a = "4444"
            print(it)
        }
    }



}
