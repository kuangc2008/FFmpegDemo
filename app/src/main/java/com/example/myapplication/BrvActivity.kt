package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.drake.tooltip.toast
import com.example.myapplication.rv.model.SimpleModel
import com.github.kc.brv.util.linear
import com.github.kc.brv.util.setup
import java.util.*
import kotlin.text.StringBuilder

class BrvActivity : AppCompatActivity() {
    private lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brv)

        rv = findViewById(R.id.rv)


        rv.linear().setup {
            addType<SimpleModel>(R.layout.item_simple)
            onBind {

//                val nextInt = Random().nextInt(1000)
                var value : StringBuilder = StringBuilder()
//                for (i in 0..nextInt) {
//                    value.append()
//                }

                val name = getModel<SimpleModel>().name
                val toInt = name.toInt()
                for (i in 0..toInt) {
                    value.append(name)
                }

                findView<TextView>(R.id.tv_simple).text = value.toString()
            }
            R.id.tv_simple.onClick {
                toast("点击文本")
            }
        }.models = getData()
    }

    private fun getData() : MutableList<Any> {
        return mutableListOf<Any>().apply {
            for (i in 0..1000) {
                add(SimpleModel(i.toString()))
            }
        }
    }
}