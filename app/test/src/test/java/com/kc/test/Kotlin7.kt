package com.kc.test

import kotlin.reflect.KProperty
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

class Kotlin7 {
    var name : String = "a"
    var age = 1;

    fun a() {

    }

    object Mapper {
        fun <A : Any> toMap(a : A)  = run {
            a::class.members.forEach {
                println(it)
            }

            a::class.memberProperties.forEach {
                println("properties:" + it)
            }

            a::class.memberFunctions.forEach {
                println("method:" + it)
            }


//            a::class.memberProperties.map { m ->
//                val p = m as KProperty<*>
//                p.name to p.call(a)
//            }.toMap()


            val associate: Map<String, Any?> = a::class.memberProperties.associate { m ->
                val p = m as KProperty<*>
                p.name to p.call(a)
            }

            println(associate)

        }
    }
}

fun main() {
    Kotlin7.Mapper.toMap(Kotlin7())
}