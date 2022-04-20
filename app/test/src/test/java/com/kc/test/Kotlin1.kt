package com.kc.test

import android.view.View
import androidx.core.app.Person
import org.junit.Test

class Kotlin1 {

    @Test
    fun test1() {
        // Array<Int>
        // java : Integer[]
        val arrayOf = arrayOf(1, 2, 3, 4, 5)
        val array = Array<Int>(5) { i ->
            i * 2;
        }

        // IntArray
        // java : int[]
        val intArrayOf = intArrayOf(1, 2, 3, 4, 5)
        val intArrayOf1 = intArrayOf(10)

        val arrayOfNulls = arrayOfNulls<Person>(10)
    }


    @Test
    fun test2() {
        val people = ArrayList<Person>()
        people.add((Person.Builder().setBot(true).setName("a").build()))
        people.add((Person.Builder().setBot(true).setName("b").build()))
        people.add((Person.Builder().setBot(true).setName("c").build()))
        people.add((Person.Builder().setBot(true).setName("z").build()))
        people.add((Person.Builder().setBot(true).setName("r").build()))
        people.add((Person.Builder().setBot(true).setName("y").build()))
        people.add((Person.Builder().setBot(true).setName("s").build()))
        people.add((Person.Builder().setBot(true).setName("t").build()))
        // Iterable的接口
        // filter生成要给对象 遍历1
        // map 生成一个对象  遍历2
        // take 生成一个对象  遍历3
//        val list = people.filter { it.isBot }.map { it.name }
//            .take(5)


        // 仅仅遍历一次
        val list2 = people.asSequence().filter { it.isBot }.map { it.name }
            .take(5).toList()

        println(list2)
        println(people)
    }


    @Test
    fun test3() {
        val arrayOf = arrayOf(1, 2, 3, 4, 5)

        for (i in 0..arrayOf.size - 1) {
            println(arrayOf[i])
        }

        for (i in 0 until arrayOf.size) {
            println(arrayOf[i])
        }

        for (i in 0..arrayOf.lastIndex) {
            println(arrayOf[i])
        }

        for (i in arrayOf.indices) {
            println(arrayOf[i])
        }

        for (arg in arrayOf) {
            println(arg)
        }

        arrayOf.forEach {
            println(it)
        }

        arrayOf.forEachIndexed { index, arg ->
            println("$index: $arg")
        }

        for ( (index, arg) in  arrayOf.withIndex()) {
            println("$index: $arg")
        }

        val intArray = intArrayOf(1, 2, 3, 4, 5)
        val withIndex = intArray.withIndex()
    }

    public interface OnClickListener {
        fun onClick(value : Int)
    }

    public class Button {
        fun setListener(listener : OnClickListener) {
            listener.onClick(1)
        }

//        fun setListener(listener: (value : Int) -> Unit) {
//            listener.invoke(3)
//        }
    }


    @Test
    fun test4() {
        val button = Java1.Button2()
        button.setListener { value -> println(value) }
//        button.setListener {
//        }
    }

    @Test
    fun test5() {
        a()

        b()
    }

    companion object {
        const val a = 6
        @JvmStatic
        var b = 7
        @JvmField
        var c = 8


        fun a() {

        }
    }


}

fun b() {

}



