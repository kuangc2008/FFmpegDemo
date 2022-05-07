package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.FragmentMultiTypeBinding
import com.example.myapplication.rv.model.DividerModel
import com.github.kc.brv.util.divider
import com.github.kc.brv.util.linear
import com.github.kc.brv.util.setup

class BrvActivity_Divider : AppCompatActivity() {
    private lateinit var binding: FragmentMultiTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMultiTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.linear().divider(R.drawable.divider_horizontal).setup {
            addType<DividerModel>(R.layout.item_divider_vertical)
        }.models = getData()
    }

    fun getData() : MutableList<Any> {
       return mutableListOf<Any>().apply {
           for (i in 0..3) {
               add(DividerModel())
           }
       }
    }
}