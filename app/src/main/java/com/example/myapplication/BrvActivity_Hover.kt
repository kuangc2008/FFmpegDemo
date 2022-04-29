package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.drake.tooltip.toast
import com.example.myapplication.rv.model.HoverHeaderModel
import com.example.myapplication.rv.model.Model
import com.github.kc.brv.util.linear
import com.github.kc.brv.util.setup

class BrvActivity_Hover : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_multi_type)


        recyclerView = findViewById<RecyclerView>(R.id.rv)

        recyclerView.linear().setup {
            addType<Model>(R.layout.item_multi_type_simple)
            addType<HoverHeaderModel>(R.layout.item_hover_header)

            onBind {
                when (itemViewType) {
                    R.layout.item_multi_type_simple -> {
                        val textView = findView<TextView>(R.id.text)
                        textView.setText( "" + this.modelPosition )
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