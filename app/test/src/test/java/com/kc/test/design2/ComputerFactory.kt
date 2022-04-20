package com.kc.test.design2

import org.junit.Test
import java.lang.IllegalArgumentException


/**
 * 抽象工厂
 */
interface Computer

class Dell : Computer
class Asus : Computer
class Acer : Computer



abstract class AbstractFactory {
    abstract fun produce() : Computer

    companion object {
        operator fun invoke (factory: AbstractFactory) : AbstractFactory {
            return factory
        }
    }
}

class DellFactory : AbstractFactory() {
    override fun produce() = Dell()
}

class AsusFactory : AbstractFactory() {
    override fun produce() = Asus()
}

class AcerFactory : AbstractFactory() {
    override fun produce() = Acer()
}

fun main1() {
    val abstractFactory = AbstractFactory(DellFactory())
    val produce = abstractFactory.produce();
    println(produce)


}


// 用内联函数来改进
abstract class AbstractFactory2 {
    abstract fun produce() : Computer

    companion object {
        inline operator fun <reified T : Computer> invoke () : AbstractFactory {
            return when(T::class) {
                Dell::class -> DellFactory()
                Asus::class -> AsusFactory()
                Acer::class -> AcerFactory()
                else -> throw IllegalArgumentException()
            }
        }
    }
}

fun main() {
    val abstractFactory = AbstractFactory2.Companion<Dell>()
    val produce = abstractFactory.produce()
    println(produce)
}