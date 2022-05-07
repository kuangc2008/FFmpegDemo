package com.example.myapplication.rv.model

import com.github.kc.brv.item.ItemPosition

data class DividerModel(var height : Int = 600, override var itemPosition: Int = 0) : ItemPosition
