package com.kc.test.basic

import org.junit.Test

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



    fun test2() {
        val dependency = DependencyHandler()
        dependency.compile("androidx.core:core-ktx:1.6.0", "b")

        dependency.invoke {
            compile("androidx.core:core-ktx:1.6.0", "a")
        }

        dependency{
            compile("androidx.core:core-ktx:1.6.0", "c")
        }

    }



    class DependencyHandler{
        //编译库
        fun compile(libString: String, libString2: String){
            println("add $libString $libString2")
        }

        // 接受者为Lambda表达式
        operator fun invoke(body: DependencyHandler.() -> Unit){
            body()
        }
    }

    @Test
    fun  test3() {
        object : Runnable {
            override fun run() {
                println("aaaa")
            }
        }

        java.lang.Runnable {
            println("aaaa")
        }

        Thread.sleep(1000)
    }

}
