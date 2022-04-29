package com.github.kc.opengles

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGlSurfaceView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) : GLSurfaceView(context, attrs) {
    val myNativeRender : MyNativeRender

    init {
        Log.i("kcc", "MyGlSurfaceView init")

        setEGLContextClientVersion(3)
        myNativeRender = MyNativeRender()

        val myGlRender = MyGlRender(myNativeRender)
        setRenderer(myGlRender)
        renderMode = (RENDERMODE_CONTINUOUSLY)
    }

    class MyGlRender(private val nativeRender: MyNativeRender) : GLSurfaceView.Renderer {
        override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
            Log.i("kcc", "onSurfaceCreated")
            nativeRender.native_OnSurfaceCreated()
        }

        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            Log.i("kcc", "onSurfaceChanged")
            nativeRender.native_OnSurfaceChanged(width, height)
        }

        override fun onDrawFrame(gl: GL10?) {
            Log.i("kcc", "onDrawFrame")
            nativeRender.native_OnDrawFrame()
        }

    }

}