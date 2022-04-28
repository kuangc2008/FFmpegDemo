package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.drake.tooltip.toast
import com.example.myapplication.rv.model.CommentModel
import com.example.myapplication.rv.model.Model
import com.example.myapplication.rv.model.SimpleModel
import com.example.myapplication.rv.model.TwoSpanModel
import com.github.kc.brv.util.bindingAdapter
import com.github.kc.brv.util.linear
import com.github.kc.brv.util.models
import com.github.kc.brv.util.setup
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class BrvActivity2_MultiType : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val view = LayoutInflater.from(this).inflate(R.layout.fragment_compose, null)
//        binding = DataBindingUtil.bind(view)!!


        setContentView(R.layout.fragment_multi_type)


        recyclerView = findViewById<RecyclerView>(R.id.rv)

        recyclerView.linear().setup {
            addType<Model>(R.layout.item_multi_type_simple)
            addType<TwoSpanModel>(R.layout.item_multi_type_two_span)
        }.models = getData()


        recyclerView.bindingAdapter.onClick(R.id.item) {
            when (this.itemViewType) {
                R.layout.item_multi_type_simple -> toast("type 1")
                else -> toast("type 2")
            }
        }
    }

    private fun getData(): MutableList<Any> {
        return mutableListOf(
            Model(),
            TwoSpanModel(),
            TwoSpanModel(),
            Model(),
            Model(),
            Model(),
            Model(),
            TwoSpanModel(),
            TwoSpanModel(),
            TwoSpanModel(),
            Model(),
            Model(),
            Model()
        )
    }
}