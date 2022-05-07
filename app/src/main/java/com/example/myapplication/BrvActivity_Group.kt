package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drake.tooltip.toast
import com.example.myapplication.databinding.FragmentMultiTypeBinding
import com.example.myapplication.rv.model.DividerModel
import com.example.myapplication.rv.model.GroupBasicModel
import com.example.myapplication.rv.model.GroupModel
import com.example.myapplication.rv.model.GroupSecondModel
import com.github.kc.brv.item.ItemExpand
import com.github.kc.brv.util.divider
import com.github.kc.brv.util.linear
import com.github.kc.brv.util.setup

class BrvActivity_Group : AppCompatActivity() {
    private lateinit var binding: FragmentMultiTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMultiTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.linear().setup {



            // 任何条目都需要添加类型到BindingAdapter中
            addType<GroupModel>(R.layout.item_group_title)
            addType<GroupSecondModel>(R.layout.item_group_title_second)
            addType<GroupBasicModel>(R.layout.item_group_basic)
            R.id.item.onFastClick {
                when (itemViewType) {
                    R.layout.item_group_title_second, R.layout.item_group_title -> {

                        val changeCount =
                            if (getModel<ItemExpand>().itemExpand) "折叠 ${expandOrCollapse()} 条" else "展开 ${expandOrCollapse()} 条"

                        toast(changeCount)
                    }
                }
            }

        }.models = getData()
    }

    fun getData() : MutableList<GroupModel> {
        return mutableListOf<GroupModel>().apply {
            for (i in 0..4) {

                // 第二个分组存在嵌套分组
                if (i == 0) {
                    val nestedGroupModel = GroupModel().apply {
                        itemSublist =
                            listOf(GroupSecondModel(), GroupSecondModel(), GroupSecondModel())
                    }
                    add(nestedGroupModel)
                    continue
                }

                add(GroupModel())
            }
        }
    }
}