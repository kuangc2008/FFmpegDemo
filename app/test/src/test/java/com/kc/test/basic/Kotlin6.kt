package com.kc.test.basic

import org.junit.Test

class Kotlin6 {


    val sum : Int.(Int) -> Int = { a -> this.plus(a) }

    @Test
    fun test6() {
        val sum1 = 2.sum(1)
        println(sum1)
    }


    inline fun IntArray.map2 (action : (Int) -> Int) : List<Int> {
        val arrayList = ArrayList<Int>(size)
        for (item in this) {
            arrayList.add( action(item)  )
        }
        return arrayList
    }

    inline fun IntArray.filter2 (action : (Int) -> Boolean) : List<Int> {
        val arrayList = ArrayList<Int>(size)
        for (item in this) {
            if (action(item)) {
                arrayList.add( item)
            }
        }
        return arrayList
    }

    inline fun IntArray.count2 (action : (Int) -> Boolean) : Int {
        var count = 0
        for (item in this) {
            if (action(item)) {
                count++
            }
        }
        return count
    }

    @Test
    fun test7() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        val result: List<Int> = array.map2 {
            it * 2
        }
        println(result)

        val count = array.filter { it > 2}
        print(count)

        array.sumOf {  it * 3 }
        val fold = array.fold(100) { acc, item ->
            acc + item
        }
        println(fold)

        array.reduce { acc, i ->
            acc + i
        }

        val map = array.groupBy {
            if (it <= 2) "low" else "high"
        }
        println(map)


        val listOf: List<List<Int>> = listOf(listOf<Int>(1, 2), listOf<Int>(3, 4, 5))
        val flatten = listOf.flatten()
        val flatMap = listOf.flatMap { it.filter { it > 3 } }
        println(flatMap)
    }
    
    @Test
    fun test8() {
        val mutableListOf = mutableListOf(1, 2, 3, 4, 5)
        mutableListOf[0] = 0
        println( mutableListOf.javaClass.name )

        val listOf = listOf(1, 2, 3, 4, 5)
        println( listOf.javaClass.name )
    }
}