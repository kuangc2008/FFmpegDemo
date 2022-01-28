package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import com.kc.test.TestUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = GetFFmpegVersion()

        TestUtil.test()
    }

    /**
     * A native method that is implemented by the 'myapplication' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    public fun GetFFmpegVersion():String{
        return native_GetFFmpegVersion();
    }

    external fun native_GetFFmpegVersion() : String

    companion object {
        // Used to load the 'myapplication' library on application startup.
        init {
            System.loadLibrary("learn-ffmpeg")
        }
    }
}