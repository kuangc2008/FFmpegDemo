package coroutine

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.*

class CoroutineTest2 {

    fun main() {
        val runBlocking = runBlocking {
            Log.i("kcc", "runBlocking")
            41
        }
        Log.i("kcc", "block ${runBlocking}")
        val launch = GlobalScope.launch {
            Log.i("kcc", "222222")
        }
        Log.i("kcc", "global launch ${launch}")
        val async = GlobalScope.async {
            Log.i("kcc", "3333")
            "return"
        }
        Log.i("kcc", "rturn: ${async}")

    }

}