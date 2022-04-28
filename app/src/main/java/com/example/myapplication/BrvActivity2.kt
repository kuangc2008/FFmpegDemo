package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.rv.model.CommentModel
import com.github.kc.brv.util.setup
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class BrvActivity2 : AppCompatActivity() {

    /** 最好的Json序列化框架 https://github.com/Kotlin/kotlinx.serialization */
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val comments by lazy {
        json.decodeFromStream<List<CommentModel>>(resources.openRawResource(R.raw.compose))
    }

    private lateinit var binding: ViewDataBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val view = LayoutInflater.from(this).inflate(R.layout.fragment_compose, null)
//        binding = DataBindingUtil.bind(view)!!


        setContentView(R.layout.fragment_compose)


        recyclerView = findViewById<RecyclerView>(R.id.rv)

        recyclerView.setup {
            addType<String>(R.layout.item_comment_title)
            addType<CommentModel>(R.layout.item_comment)
        }


        val modes = mutableListOf<Any>()
        modes.add("title")
        modes.add(comments)
    }
}