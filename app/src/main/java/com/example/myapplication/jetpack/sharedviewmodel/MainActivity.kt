package com.example.myapplication.jetpack.sharedviewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityJetpackMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityJetpackMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_jetpack_main)


        mainBinding = ActivityJetpackMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportFragmentManager.beginTransaction().add(R.id.layout_top, InputFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.layout_bottom, OutputFragment()).commit()

        val sharedViewModel : SharedViewModel by viewModels()



        sharedViewModel.inputNumber.observe(this) {
            Log.i("kcc", "222222")
            it?.let {
                mainBinding.tvShowInput.text = "11111  $it"
            }
        }
    }
}