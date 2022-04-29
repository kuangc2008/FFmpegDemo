package com.github.kc.opengles

class MyNativeRender {

    companion object {
        // Used to load the 'myapplication' library on application startup.
        init {
            System.loadLibrary("my_opengl_es")
        }
    }

    external fun native_OnInit() : Unit
    external fun native_OnUnInit() : Unit
    external fun native_SetImageData(format : Int, width: Int,height: Int, byteArray: ByteArray) : Unit
    external fun native_OnSurfaceCreated() : Unit
    external fun native_OnSurfaceChanged(width : Int, height : Int) : Unit
    external fun native_OnDrawFrame() : Unit

}