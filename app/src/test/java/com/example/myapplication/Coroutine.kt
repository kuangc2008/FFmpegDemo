package com.example.myapplication

import kotlinx.coroutines.*
import org.junit.Test

class Coroutine {

    @Test
    fun a() = runBlocking {
        val deferred: Deferred<String> = async {
           println("IN async : ${Thread.currentThread().name}")

            delay(1000L)

            println("In async after delay")

            return@async "task complte"
        }
        delay(3000L)
        println("bbbbb")
//        val await = deferred.await()
//        println(await)
    }





// 代码段4

// delay(1000L)用于模拟网络请求

    //挂起函数
// ↓
    suspend fun getUserInfo(): String {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
        return "BoyCoder"
    }

    //挂起函数
// ↓
    suspend fun getFriendList(user: String): String {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
        return "Tom, Jack"
    }

    //挂起函数
// ↓
    suspend fun getFeedList(list: String): String {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
        return "{FeedList..}"
    }




// 代码段15
    @Test
    fun b() = runBlocking {
        val job = launch {
            println("First coroutine start!")
            delay(1000L)
            println("First coroutine end!")
        }

        job.join()
        val job2 = launch(job) {
            println("Second coroutine start!")
            delay(1000L)
            println("Second coroutine end!")
        }
        job2.join()
        println("Process end!")
    }
}