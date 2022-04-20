package com.kc.test.design1

import org.junit.Test


/**
 * 工厂模式
 */
enum class ComputerType2 {
    PC, Server
}

interface Computer2 {
    val cpu : String

    companion object {
        operator fun invoke(type : ComputerType2) : Computer2 {
            return when (type) {
                ComputerType2.PC -> PC2()
                ComputerType2.Server -> Server2()
            }
        }
    }
}

class PC2(override val cpu : String = "Core") : Computer2

class Server2(override val cpu : String = "Xeon") : Computer2

//object ComputerFactory2 {
//    operator fun invoke(type : ComputerType2) : Computer2 {
//        return when (type) {
//            ComputerType2.PC -> PC2()
//            ComputerType2.Server -> Server2()
//        }
//    }
//}


fun Computer2.Companion.fromCpu (cpu : String) : ComputerType2? {
    return when (cpu) {
        "Core" -> ComputerType2.PC
        else -> null
    }
}

class Kotlin7_2 {
    @Test
    fun test1() {
        val computer2 = Computer2(ComputerType2.Server)
    }
}