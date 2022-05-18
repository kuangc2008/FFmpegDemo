package com.example.myapplication


import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

val Int.dp: Int
    get() = this + 2


fun Int.dp() : Int {
    return this + 3
}

fun  a( param: (Int) -> Unit) : (String) -> Int {
    param(2)
    val a = { a : String ->
        a.length
    }
    return a
}

fun  b( param: Int.(String) -> Unit) : Int.(String) -> Int {
    2.param("")
    val a = {param :Int, a : String ->
        a.length
    }
    return a
}

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
        print("aaa")


        val dp = 2.dp
        println(dp)
        println(2.dp())


        val c = a {
            2
        }

        b {
            val i = it.length + this
        }

        val b = ::a
    }



}