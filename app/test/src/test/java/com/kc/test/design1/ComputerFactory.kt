package com.kc.test.design1

import org.junit.Test


/**
 * 工厂模式
 */
enum class ComputerType {
    PC, Server
}

interface Computer {
    val cpu : String
}

class PC(override val cpu : String = "Core") : Computer

class Server(override val cpu : String = "Xeon") : Computer

object ComputerFactory {
    operator fun invoke(type : ComputerType) : Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}

class Kotlin7 {
    @Test
    fun test1() {
        val computerFactory = ComputerFactory(ComputerType.PC)
    }
}