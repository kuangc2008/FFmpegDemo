package com.kc.test.design3


/**
 * 策略与模板
 */
class Run (val runAction : () -> Unit) {
    fun run() {
        runAction()
    }
}

fun a_run() {
    println("a")
}

fun b_run(){
    print("b")
}

fun main() {
    val run = Run(::a_run)
    run.run()
}