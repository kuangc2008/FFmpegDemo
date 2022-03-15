package coroutine

import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.*

class CoroutineTest2 {

    fun main() {
        // 1
        val runBlocking = runBlocking {
            Log.i("kcc", "runBlocking")

            Thread.sleep(2000)

            Log.i("kcc", "runBlocking 2 ")
            41
        }
        Log.i("kcc", "abc");
        Log.i("kcc", "block ${runBlocking}")




        //2
        GlobalScope.launch{
            Log.i("kcc", "start 1111: hehe " )

            for (index in 1 until 10) {
                launch (Dispatchers.Main) {
                    Log.i("kcc", "start 1111:  ${index}" )
                }
            }
        }



    }

}