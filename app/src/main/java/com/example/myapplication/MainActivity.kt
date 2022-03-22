package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import com.kc.java_native_call.NativeLib
import com.kc.uiwatch.UiWatcher
import coroutine.CoroutineTest1
import coroutine.CoroutineTest2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()


        // java native call
        binding.sampleText.text = NativeLib().stringFromJNI()

        Log.i("kcc", "1111");
//        NativeLib().javaToc("哈哈哈哈哈")
//        NativeLib().accessField();
//        NativeLib().accessMethod();
        NativeLib().exceptionDeal()
        Log.i("kcc", "2222");



        CoroutineTest2().main()

        UiWatcher.getInstance(application)
            .cacheSize(10, 17)
            .listen {

            }
            .
                startWatch()



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