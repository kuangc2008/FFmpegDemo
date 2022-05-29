package coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.coroutines.*

class CoroutineTest3 {

    fun main() {

        val continuation = suspend {
            println("执行协程体")
            1
        }.createCoroutine(object :Continuation<Int>{
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
                println("协程结束， $result")
            }
        })
    }



}