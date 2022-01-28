package com.kc.java_native_call

class NativeLib {

    var b : Int = 111
    /**
     * A native method that is implemented by the 'java_native_call' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun javaToc(string : String): Unit

    external fun accessField(): Unit

    companion object {
        // Used to load the 'java_native_call' library on application startup.
        init {
            System.loadLibrary("java_native_call")
        }

        var a : Int = 333;
    }
}