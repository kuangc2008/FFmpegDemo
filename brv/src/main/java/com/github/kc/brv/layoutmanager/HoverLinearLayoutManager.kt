package com.github.kc.brv.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HoverLinearLayoutManager @JvmOverloads constructor(
    context : Context,
    orientation : Int = RecyclerView.VERTICAL,
    reverseLayout : Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout){

    var scrollEnabled = false
}