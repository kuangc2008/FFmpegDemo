package com.kc.test.basic


/**
 * 加了 crossinline ，则无法在方法体中，添加return了
 */
inline fun foo( crossinline returning: ()-> Unit) {
    println("before")
    returning()
    println("after")
}


fun main() {
    foo {
//        return
    }
    println("hahaha")
}