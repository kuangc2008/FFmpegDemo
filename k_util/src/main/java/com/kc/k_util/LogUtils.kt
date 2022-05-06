package com.kc.k_util

import android.util.Log


object LogUtils {

    @JvmStatic
    fun logMethod() {

        val stackTrace = Thread.currentThread().stackTrace
        if (stackTrace.isNotEmpty()) {
            val ste = stackTrace[3]

            val className = ste.className
            val methodName = ste.methodName
            val lineNumber = ste.lineNumber
            Log.i("kcc2", "class:$className   method:$methodName   line:$lineNumber")
        }


    }
}