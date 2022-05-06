package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.drake.tooltip.toast
import com.example.myapplication.databinding.FragmentMultiTypeBinding
import com.example.myapplication.rv.model.HoverHeaderModel
import com.example.myapplication.rv.model.Model
import com.github.kc.brv.util.linear
import com.github.kc.brv.util.setup

class BrvActivity_Hover : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var binding: FragmentMultiTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMultiTypeBinding.inflate(layoutInflater)


        setContentView(binding.root)


        recyclerView = findViewById<RecyclerView>(R.id.rv)

        recyclerView.linear().setup {
            addType<Model>(R.layout.item_multi_type_simple)
            addType<HoverHeaderModel>(R.layout.item_hover_header)

            onBind {
                when (itemViewType) {
                    R.layout.item_multi_type_simple -> {
                        Log.i("kcc4", "findView：：：：：11111111")
                        val textView = findView<TextView>(R.id.text)
                        textView.setText( "" + this.modelPosition )
                    }

                    R.layout.item_hover_header -> {
                        val textView = findView<TextView>(R.id.tv)
                        textView.setText( "头部" + this.modelPosition )
                    }
                }
            }

            onClick(R.id.item) {
                when (itemViewType) {
                    R.layout.item_hover_header -> toast("1")
                    else-> toast("2")
                }
            }



            models = getData()
        }

    }

    private fun getData(): List<Any?> {
        return listOf(
            HoverHeaderModel(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            HoverHeaderModel(),
            Model(),
            Model(),
            Model(),
            HoverHeaderModel(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            Model(),
            HoverHeaderModel()
        )
    }
}