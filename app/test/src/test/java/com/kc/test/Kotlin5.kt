package com.kc.test

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.KProperty

class Kotlin5 {
    @Test
    fun test1() {
        val callback = {
            println("D")
        }

        val task = {
            println("C")
            callback()
        }

        println("A")
        thread(block = task)
        println("B")

    }

    @Test
    fun test2() {
        loopMain {
            runOnIOThread {
                println("A")
                delay(1000) {
                    println("B")
                    runOnMainThread {
                        println("C")
                    }
                }
            }
        }

        Thread.sleep(4000)
    }

    private fun loopMain(block: () -> Unit) {
        Looper.prepareMainLooper()
        runOnIOThread(block)
        Looper.loop()
    }

    val ioExecutor =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    val delayExecutor = Executors.newScheduledThreadPool(1)

    val mainExecutor by lazy { Handler(Looper.getMainLooper()) }

    fun runOnIOThread(block: () -> Unit) {
        ioExecutor.execute(block)
    }

    fun delay(ms: Long, block: () -> Unit) {
        delayExecutor.schedule(block, ms, TimeUnit.MILLISECONDS)
    }

    fun runOnMainThread(block: () -> Unit) {
        mainExecutor.post(block)
    }


}