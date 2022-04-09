package com.kc.test

import org.junit.Test
import kotlin.reflect.KProperty

class Kotlin2 {

    @Test
    fun test1() {
        val b = BaseImpl(10)
        Derived(b).print() // 输出 10
    }




    @Test
    fun test2() {
        val example = Example()
        example.pppp = "aaaa"
        println(example.pppp)

        val demo1 = Demo1()
        val a = demo1.a
        println(a)
    }

}

//  类代理1
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b


// 属性代理2
// 属性的代理，就是把自己的属性对应的get()和set()会被代理给它的getValue()和setValue()方
class Example {
    var pppp: String by Delegate()
}

class Delegate {
    private var mRealValue = ""

    operator fun getValue(example: Example, property: KProperty<*>): String {
        println(property.name)
        println(example.pppp)
        return mRealValue
    }

    operator fun setValue(example: Example, property: KProperty<*>, s: String) {
        mRealValue = s
    }
}


public interface BBBB {
    public val value: Int

    public fun isInitialized(): Boolean
}

// 属性代理3
class Demo1 {
    val a by lazy { 1+2+3+4+5 }
}
