package com.github.kc.opengles

import android.graphics.Color
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class _1_TriangleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frameLayout = FrameLayout(this)
        frameLayout.setBackgroundColor(Color.RED)

        val glSurfaceView = MyGlSurfaceView(this)
        frameLayout.addView(glSurfaceView)

        setContentView(frameLayout)
    }

}